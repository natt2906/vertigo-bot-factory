/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2020, Vertigo.io, team@vertigo.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vertigo.chatbot.designer.builder.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertigo.account.authorization.annotations.SecuredOperation;
import io.vertigo.chatbot.commons.JaxrsProvider;
import io.vertigo.chatbot.commons.LogsUtils;
import io.vertigo.chatbot.commons.dao.TrainingDAO;
import io.vertigo.chatbot.commons.domain.BotExport;
import io.vertigo.chatbot.commons.domain.Chatbot;
import io.vertigo.chatbot.commons.domain.ChatbotNode;
import io.vertigo.chatbot.commons.domain.ExecutorConfiguration;
import io.vertigo.chatbot.commons.domain.Training;
import io.vertigo.chatbot.commons.domain.TrainingStatusEnum;
import io.vertigo.chatbot.commons.multilingual.model.ModelMultilingualResources;
import io.vertigo.chatbot.designer.builder.services.topic.export.BotExportServices;
import io.vertigo.chatbot.designer.builder.training.TrainingPAO;
import io.vertigo.chatbot.designer.commons.services.FileServices;
import io.vertigo.chatbot.designer.utils.HttpRequestUtils;
import io.vertigo.chatbot.designer.utils.ObjectConvertionUtils;
import io.vertigo.chatbot.domain.DtDefinitions.TrainingFields;
import io.vertigo.commons.transaction.Transactional;
import io.vertigo.core.lang.Assertion;
import io.vertigo.core.lang.VSystemException;
import io.vertigo.core.lang.VUserException;
import io.vertigo.core.node.component.Component;
import io.vertigo.datamodel.criteria.Criteria;
import io.vertigo.datamodel.criteria.Criterions;
import io.vertigo.datamodel.structure.definitions.DtDefinition;
import io.vertigo.datamodel.structure.definitions.DtField;
import io.vertigo.datamodel.structure.model.DtList;
import io.vertigo.datamodel.structure.model.DtListState;
import io.vertigo.datamodel.structure.model.DtObject;
import io.vertigo.datamodel.structure.util.DtObjectUtil;
import io.vertigo.datastore.filestore.model.VFile;

@Transactional
public class TrainingServices implements Component {

	private static final String API_KEY = "apiKey";

	@Inject
	private AsynchronousServices asynchronousServices;

	@Inject
	private BotExportServices botExportServices;

	@Inject
	private TrainingDAO trainingDAO;

	@Inject
	private TrainingPAO trainingPAO;

	@Inject
	private JaxrsProvider jaxrsProvider;

	@Inject
	private NodeServices nodeServices;

	@Inject
	private FileServices fileServices;

	private static final Logger LOGGER = LogManager.getLogger(TrainingServices.class);

	private static final String URL_MODEL = "/api/chatbot/admin/model";

	public Training trainAgent(@SecuredOperation("botContributor") final Chatbot bot, final Long nodId) {
		final StringBuilder logs = new StringBuilder("new Training");
		LogsUtils.breakLine(logs);

		final Long botId = bot.getBotId();

		trainingPAO.cleanOldTrainings(botId);

		final ChatbotNode devNode = nodeServices.getDevNodeByBotId(botId)
				.orElseThrow(() -> new VUserException(ModelMultilingualResources.MISSING_NODE_ERROR));

		//Set training

		final Training training = createTraining(bot);
		saveTraining(bot, training);

		try {
			LogsUtils.addLogs(logs, "Executor configuration... ");
			final ExecutorConfiguration execConfig = getExecutorConfig(training, devNode);
			LogsUtils.logOK(logs);

			LogsUtils.addLogs(logs, "Bot export :");
			LogsUtils.breakLine(logs);
			final Map<String, Object> requestData = new HashMap<String, Object>();
			requestData.put("botExport", exportBot(bot, logs));
			requestData.put("executorConfig", execConfig);
			LogsUtils.addLogs(logs, "Bot export ");
			LogsUtils.logOK(logs);

			final Map<String, String> headers = Map.of(API_KEY, devNode.getApiKey(),
					"Content-type", "application/json");

			LogsUtils.addLogs(logs, "Call executor training :");
			LogsUtils.breakLine(logs);
			final BodyPublisher publisher = BodyPublishers.ofString(ObjectConvertionUtils.objectToJson(requestData));
			final HttpRequest request = HttpRequestUtils.createPutRequest(devNode.getUrl() + URL_MODEL, headers, publisher);
			HttpRequestUtils.sendAsyncRequest(null, request, BodyHandlers.ofString())
					.thenApply(response -> {
						return this.handleResponse(response, training, devNode, bot, logs);
					});
			LogsUtils.addLogs(logs, "Call training OK, training in progress...");
			return training;
		} catch (final Exception e) {
			LogsUtils.logKO(logs);
			LogsUtils.addLogs(logs, e.getMessage());
			LOGGER.error("error", e);
			training.setLog(logs.toString());
			throw new VSystemException("error training", e);
		} finally {
			training.setLog(logs.toString());
			saveTraining(bot, training);
		}
	}

	public <T> String handleResponse(final HttpResponse<T> response, final Training training, final ChatbotNode node, final Chatbot bot, final StringBuilder logs) {
		training.setEndTime(Instant.now());
		if (HttpRequestUtils.isResponseOk(response, 200)) {

			training.setStrCd(TrainingStatusEnum.OK.name());
			node.setTraId(training.getTraId());
			asynchronousServices.saveNodeWithoutAuthorizations(node);
			LogsUtils.logOK(logs);
			LogsUtils.addLogs(logs, response.body());

		} else {
			training.setStrCd(TrainingStatusEnum.KO.name());
			LogsUtils.logKO(logs);
			errorTreatment(response, logs);

		}
		training.setLog(logs.toString());
		asynchronousServices.saveTrainingWithoutAuthorizations(training);
		return "response handled";
	}

	private <T> void errorTreatment(final HttpResponse<T> response, final StringBuilder logs) {
		if (!HttpRequestUtils.isResponseKo(response, 404, 405)) {
			errorJsonTreatment(response, logs);
		} else {
			LogsUtils.addLogs(logs, response.body());
		}
	}

	private <T> void errorJsonTreatment(final HttpResponse<T> response, final StringBuilder logs) {
		final ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(response.body().toString());
			final String responseString = root.get("globalErrors").get(0).toString();
			LogsUtils.addLogs(logs, responseString.substring(1, responseString.length() - 1));

		} catch (final JsonProcessingException e) {
			LOGGER.info("error on deserialization");
			LogsUtils.addLogs(logs, e);
			e.printStackTrace();
		}
	}

	private Training createTraining(final Chatbot bot) {
		final Long botId = bot.getBotId();
		final Long versionNumber = trainingPAO.getNextModelNumber(botId);
		final Training training = new Training();
		training.setBotId(botId);
		training.setStartTime(Instant.now());
		training.setStrCd(TrainingStatusEnum.TRAINING.name());
		training.setVersionNumber(versionNumber);
		training.setNluThreshold(BigDecimal.valueOf(0.6));
		return training;
	}

	private ExecutorConfiguration getExecutorConfig(final Training training, final ChatbotNode node) {
		final Long botId = training.getBotId();

		final ExecutorConfiguration result = new ExecutorConfiguration();
		result.setBotId(botId);
		result.setNodId(node.getNodId());
		result.setTraId(training.getTraId());
		result.setModelName("model " + training.getVersionNumber());
		result.setNluThreshold(training.getNluThreshold());
		result.setCustomConfig("nothing");
		return result;
	}

	public Optional<Training> getCurrentTraining(final Chatbot bot) {
		return trainingDAO.getCurrentTrainingByBotId(bot.getBotId());
	}

	public Optional<Training> getDeployedTraining(final Chatbot bot) {
		return trainingDAO.getDeployedTrainingByBotId(bot.getBotId());
	}

	private BotExport exportBot(@SecuredOperation("botContributor") final Chatbot bot, final StringBuilder logs) {
		return botExportServices.exportBot(bot, logs);
	}

	public void loadModel(@SecuredOperation("botContributor") final Chatbot bot, final Long traId, final Long nodId) {
		Assertion.check()
				.isNotNull(traId)
				.isNotNull(nodId);

		final Training training = getTraining(bot, traId);
		final ChatbotNode node = nodeServices.getNodeByNodeId(bot, nodId);

		Assertion.check().isTrue(training.getBotId().equals(node.getBotId()), "Incoherent parameters");

		final VFile model = fileServices.getFile(training.getFilIdModel());

		doLoadModel(training, model, node);

		// update node-training link
		node.setTraId(traId);
		nodeServices.save(bot, node);
	}

	private void doLoadModel(final Training training, final VFile model, final ChatbotNode node) {
		final ExecutorConfiguration config = new ExecutorConfiguration();
		config.setBotId(node.getBotId());
		config.setNodId(node.getNodId());
		config.setTraId(training.getTraId());
		config.setModelName(training.getVersionNumber().toString());
		config.setNluThreshold(training.getNluThreshold());

		final Response response;
		try (final FormDataMultiPart fdmp = new FormDataMultiPart()) {
			final StreamDataBodyPart modelBodyPart = new StreamDataBodyPart("model", model.createInputStream(), model.getFileName());
			fdmp.bodyPart(modelBodyPart);

			addObjectToMultipart(fdmp, "config", config);

			response = jaxrsProvider.getWebTarget(node.getUrl()).path(URL_MODEL)
					.request(MediaType.APPLICATION_JSON)
					.header(API_KEY, node.getApiKey())
					.put(Entity.entity(fdmp, fdmp.getMediaType()));

		} catch (final IOException e) {
			LOGGER.info("error during loading model");
			throw new VSystemException(e, "error during loading model");
		}

		if (response.getStatus() != 204) {
			LOGGER.info("Impossible to load the model. {}", response.getStatusInfo());
			throw new VUserException(ModelMultilingualResources.LOAD_MODEL_ERROR);
		}
	}

	private void addObjectToMultipart(final FormDataMultiPart fdmp, final String name, final DtObject dto) {
		final DtDefinition def = DtObjectUtil.findDtDefinition(dto);

		for (final DtField field : def.getFields()) {
			final Object value = field.getDataAccessor().getValue(dto);

			if (value != null) {
				// TODO: date handling ?
				fdmp.field(name + '.' + field.getName(), value.toString());
			}
		}
	}

	public DtList<Training> getAllTrainings(@SecuredOperation("botVisitor") final Chatbot bot) {
		return trainingDAO.findAll(
				Criterions.isEqualTo(TrainingFields.botId, bot.getBotId()),
				DtListState.of(1000, 0, TrainingFields.versionNumber.name(), true));
	}

	public Training getTraining(@SecuredOperation("botVisitor") final Chatbot bot, final Long traId) {
		return trainingDAO.get(traId);
	}

	public Optional<Training> getTrainingByTraIdAndBotId(final Long botId, final Long traId) {
		final Criteria<Training> criteria = Criterions.isEqualTo(TrainingFields.botId, botId).and(Criterions.isEqualTo(TrainingFields.traId, traId));
		return trainingDAO.findOptional(criteria);
	}

	public Training saveTraining(@SecuredOperation("botContributor") final Chatbot bot, final Training training) {
		return trainingDAO.save(training);
	}

	public void removeTraining(@SecuredOperation("botContributor") final Chatbot bot, final Long traId) {
		trainingDAO.delete(traId);
	}

	public void removeAllTraining(@SecuredOperation("botAdm") final Chatbot bot) {
		final Long botId = bot.getBotId();
		final List<Long> filesId = trainingPAO.getAllTrainingFilIdsByBotId(botId);
		trainingPAO.removeTrainingByBotId(botId);
		trainingPAO.removeTrainingFileByFilIds(filesId);
	}

	public Instant getInstantEndDisplay(final Long botId, final Long traId) {
		final Optional<Training> optionalTraining = getTrainingByTraIdAndBotId(botId, traId);
		return optionalTraining.isPresent() ? optionalTraining.get().getEndTime() : null;
	}

}
