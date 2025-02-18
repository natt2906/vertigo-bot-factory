package io.vertigo.chatbot.designer.builder.services.topic.export;

import javax.inject.Inject;

import io.vertigo.chatbot.commons.LogsUtils;
import io.vertigo.chatbot.commons.domain.BotExport;
import io.vertigo.chatbot.commons.domain.Chatbot;
import io.vertigo.chatbot.commons.domain.topic.KindTopicEnum;
import io.vertigo.commons.transaction.Transactional;
import io.vertigo.core.node.component.Component;

@Transactional
public class BotExportServices implements Component {

	@Inject
	private TopicExportServices topicExportServices;

	public BotExport exportBot(final Chatbot bot, final StringBuilder logs) {
		final BotExport export = new BotExport();
		export.setBot(bot);
		LogsUtils.addLogs(logs, "Active topics export");
		LogsUtils.breakLine(logs);
		export.setTopics(topicExportServices.exportActiveTopics(bot, logs));

		export.setFallbackBT(topicExportServices.getBasicBt(bot, KindTopicEnum.FAILURE.name(), logs));
		export.setEndBT(topicExportServices.getBasicBt(bot, KindTopicEnum.END.name(), logs));
		export.setWelcomeBT(topicExportServices.getBasicBt(bot, KindTopicEnum.START.name(), logs));
		return export;
	}

}
