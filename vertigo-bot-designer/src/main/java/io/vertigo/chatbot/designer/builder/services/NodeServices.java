package io.vertigo.chatbot.designer.builder.services;

import java.util.Optional;

import javax.inject.Inject;

import io.vertigo.account.authorization.AuthorizationManager;
import io.vertigo.account.authorization.annotations.Secured;
import io.vertigo.account.authorization.annotations.SecuredOperation;
import io.vertigo.chatbot.authorization.SecuredEntities.ChatbotOperations;
import io.vertigo.chatbot.commons.dao.ChatbotNodeDAO;
import io.vertigo.chatbot.commons.domain.Chatbot;
import io.vertigo.chatbot.commons.domain.ChatbotNode;
import io.vertigo.chatbot.designer.builder.chatbotNode.ChatbotNodePAO;
import io.vertigo.chatbot.domain.DtDefinitions.ChatbotNodeFields;
import io.vertigo.commons.transaction.Transactional;
import io.vertigo.core.lang.VSystemException;
import io.vertigo.core.node.component.Component;
import io.vertigo.datamodel.criteria.Criterions;
import io.vertigo.datamodel.structure.model.DtList;
import io.vertigo.datamodel.structure.model.DtListState;

@Transactional
@Secured("BotUser")
public class NodeServices implements Component {

	@Inject
	private ChatbotNodePAO chatbotNodePAO;

	@Inject
	private ChatbotNodeDAO chatbotNodeDAO;

	@Inject
	private AuthorizationManager authorizationManager;

	public ChatbotNode getNodeByNodeId(@SecuredOperation("botContributor") final Chatbot bot, final Long nodId) {
		final ChatbotNode node = chatbotNodeDAO.get(nodId);
		if (!node.getBotId().equals(bot.getBotId())) {
			throw new VSystemException("this node is not part of the bot");
		}
		return node;
	}

	//TODO voir quelle sécurité lui mettre
	public DtList<ChatbotNode> getAllNodesByBotId(final Long botId) {
		return chatbotNodeDAO.findAll(Criterions.isEqualTo(ChatbotNodeFields.botId, botId), DtListState.of(100));
	}

	public Optional<ChatbotNode> getDevNodeByBotId(final Long botId) {
		return chatbotNodeDAO.findOptional(
				Criterions.isEqualTo(ChatbotNodeFields.botId, botId)
						.and(Criterions.isEqualTo(ChatbotNodeFields.isDev, true)));
	}

	@Secured("SuperAdm")
	public void saveNode(final ChatbotNode node) {
		if (node.getNodId() != null) {
			// enforce previous values
			final ChatbotNode previousValues = chatbotNodeDAO.get(node.getNodId());

			node.setBotId(previousValues.getBotId());
			node.setTraId(previousValues.getTraId());
		}

		if (Boolean.TRUE.equals(node.getIsDev())) {
			// enforce only one dev node
			chatbotNodePAO.resetDevNode(node.getBotId());
		}

		chatbotNodeDAO.save(node);
	}

	public void save(@SecuredOperation("botContributor") final Chatbot bot, final ChatbotNode node) {
		chatbotNodeDAO.save(node);
	}

	@Secured("SuperAdmin")
	public void deleteNode(final Long nodId) {
		chatbotNodeDAO.delete(nodId);
	}

	public DtList<ChatbotNode> getNodesByBot(@SecuredOperation("botVisitor") final Chatbot chatbot) {
		if (authorizationManager.isAuthorized(chatbot, ChatbotOperations.botAdm)) {
			return getAllNodesByBotId(chatbot.getBotId());
		}
		final DtList<ChatbotNode> nodes = new DtList<>(ChatbotNode.class);
		final Optional<ChatbotNode> devNode = getDevNodeByBotId(chatbot.getBotId());
		if (devNode.isPresent()) {
			nodes.add(devNode.get());
		}
		return nodes;
	}

	public void deleteChatbotNodeByBot(@SecuredOperation("botAdm") final Chatbot bot) {
		chatbotNodePAO.removeChatbotNodeByBotId(bot.getBotId());
	}
}
