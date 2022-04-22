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
package io.vertigo.chatbot.designer.analytics.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import io.vertigo.chatbot.commons.domain.Chatbot;
import io.vertigo.chatbot.commons.domain.topic.Topic;
import io.vertigo.chatbot.commons.domain.topic.TopicIhm;
import io.vertigo.chatbot.designer.builder.services.topic.TopicServices;
import io.vertigo.chatbot.designer.domain.analytics.SentenseDetail;
import io.vertigo.chatbot.designer.domain.analytics.StatCriteria;
import io.vertigo.chatbot.designer.domain.analytics.TopIntent;
import io.vertigo.commons.transaction.Transactional;
import io.vertigo.core.node.component.Component;
import io.vertigo.database.timeseries.TabularDatas;
import io.vertigo.database.timeseries.TimedDataSerie;
import io.vertigo.database.timeseries.TimedDatas;
import io.vertigo.datamodel.structure.model.DtList;
import io.vertigo.datamodel.structure.util.VCollectors;

@Transactional
public class AnalyticsServices implements Component {

	@Inject
	private TopicServices topicServices;

	@Inject
	private TimeSerieServices timeSerieServices;

	/**
	 * Get the sentence unreconized by the bot
	 *
	 * @param criteria statscriteria for unreconized sentence
	 * @return the unknown sentences
	 */
	public DtList<SentenseDetail> getSentenseDetails(final StatCriteria criteria) {
		// get data from influxdb
		final TimedDatas tabularTimedData = timeSerieServices.getSentenceDetails(criteria);

		// build DtList from InfluxDb data
		final DtList<SentenseDetail> retour = new DtList<>(SentenseDetail.class);
		for (final TimedDataSerie timedData : tabularTimedData.getTimedDataSeries()) {
			final Map<String, Object> values = timedData.getValues();

			final SentenseDetail newSentenseDetail = new SentenseDetail();
			newSentenseDetail.setDate(timedData.getTime());
			newSentenseDetail.setText((String) values.get("text"));
			newSentenseDetail.setConfidence(BigDecimal.valueOf((Double) values.get("confidence")));
			newSentenseDetail.setModelName((String) values.get("modelName"));
			retour.add(newSentenseDetail);
		}

		return retour;
	}

	public DtList<TopIntent> getTopIntents(final Chatbot bot, final String locale, final StatCriteria criteria) {
		// get data from influxdb
		final TabularDatas tabularDatas = timeSerieServices.getAllTopIntents(criteria);
		// build DtList from InfluxDb data
		final DtList<TopicIhm> topics = topicServices.getAllNonTechnicalTopicIhmByBot(bot, locale);
		final Map<String, Long> topicCountMap = new HashMap<>();
		tabularDatas.getTabularDataSeries().forEach(x -> topicCountMap.put(x.getValues().get("name").toString(), ((Long) x.getValues().get("name:count"))));

		return topics.stream().map(topic -> {
			final TopIntent topIntent = new TopIntent();
			topIntent.setIntentRasa(topic.getTitle());
			topIntent.setTopId(topic.getTopId());
			topIntent.setCode(topic.getCode());
			topIntent.setCount(topicCountMap.getOrDefault(topic.getCode(), 0L));
			return topIntent;
		}).collect(VCollectors.toDtList(TopIntent.class));
	}

	/**
	 * Get the known sentence for a specific intentRasa
	 *
	 * @param criteria statCriteria
	 * @param intentRasa intent associated
	 * @return List of know sentences
	 */
	public DtList<SentenseDetail> getKnownSentensesDetail(final StatCriteria criteria, final String intentRasa) {
		// get data from influxdb
		final TimedDatas tabularTimedData = timeSerieServices.getKnowSentence(criteria, intentRasa);
		final DtList<SentenseDetail> retour = new DtList<>(SentenseDetail.class);
		final Optional<Topic> topic = topicServices.getTopicByCode(intentRasa, criteria.getBotId());
		for (final TimedDataSerie timedData : tabularTimedData.getTimedDataSeries()) {
			final Map<String, Object> values = timedData.getValues();

			final SentenseDetail newSentenseDetail = new SentenseDetail();
			newSentenseDetail.setDate(timedData.getTime());
			newSentenseDetail.setText((String) values.get("text"));
			newSentenseDetail.setIntentRasa(intentRasa);
			newSentenseDetail.setConfidence(BigDecimal.valueOf((Double) values.get("confidence")));
			newSentenseDetail.setTopId(topic.isPresent() ? topic.get().getTopId() : null);

			retour.add(newSentenseDetail);
		}

		return retour;
	}

}
