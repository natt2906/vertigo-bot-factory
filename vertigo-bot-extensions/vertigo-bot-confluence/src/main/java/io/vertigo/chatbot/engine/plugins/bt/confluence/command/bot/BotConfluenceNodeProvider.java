package io.vertigo.chatbot.engine.plugins.bt.confluence.command.bot;

import static io.vertigo.ai.bt.BTNodes.sequence;
import static io.vertigo.chatbot.engine.plugins.bt.command.bot.BotNodeProvider.inputString;

import java.util.List;

import javax.inject.Inject;

import io.vertigo.ai.bb.BBKey;
import io.vertigo.ai.bb.BBKeyPattern;
import io.vertigo.ai.bb.BlackBoard;
import io.vertigo.ai.bt.BTNode;
import io.vertigo.ai.bt.BTStatus;
import io.vertigo.chatbot.engine.BotEngine;
import io.vertigo.chatbot.engine.plugins.bt.confluence.impl.ConfluenceServerServices;
import io.vertigo.core.node.component.Component;

public final class BotConfluenceNodeProvider implements Component {

	@Inject
	private ConfluenceServerServices confluenceServerService;

	public BTNode confluenceSearch(final BlackBoard bb, final String keyTemplate, final String question, final String listPresentation) {
		return sequence(
				inputString(bb, keyTemplate, question),
				() -> { 
					bb.listPush(BotEngine.BOT_RESPONSE_KEY, listPresentation);
					final var searchObject = bb.getString(BBKey.of(keyTemplate));
					final List<String> searchResult = confluenceServerService.searchOnConfluenceCommand(searchObject);
					for (String result : searchResult) {
						bb.listPush(BotEngine.BOT_RESPONSE_KEY, result);
					}
					bb.delete(BBKeyPattern.of(keyTemplate));
					return BTStatus.Succeeded;
				});

	}

}
