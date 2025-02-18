package io.vertigo.chatbot.designer.builder.topic;

import javax.inject.Inject;

import java.util.Optional;
import io.vertigo.core.node.Node;
import io.vertigo.core.lang.Assertion;
import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.task.TaskManager;
import io.vertigo.datamodel.task.definitions.TaskDefinition;
import io.vertigo.datamodel.task.model.Task;
import io.vertigo.datamodel.task.model.TaskBuilder;
import io.vertigo.datastore.impl.dao.StoreServices;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
 @Generated
public final class TopicPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public TopicPAO(final TaskManager taskManager) {
		Assertion.check().isNotNull(taskManager);
		//-----
		this.taskManager = taskManager;
	}

	/**
	 * Creates a taskBuilder.
	 * @param name  the name of the task
	 * @return the builder 
	 */
	private static TaskBuilder createTaskBuilder(final String name) {
		final TaskDefinition taskDefinition = Node.getNode().getDefinitionSpace().resolve(name, TaskDefinition.class);
		return Task.builder(taskDefinition);
	}

	/**
	 * Execute la tache TkCheckUnicityTopicCode.
	 * @param botId Long
	 * @param code String
	 * @param topId Long
	 * @return Boolean exist
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkCheckUnicityTopicCode",
			request = "select exists(" + 
 "					select 1" + 
 "					from topic top " + 
 "					where top.bot_id = #botId# and top.code = #code#" + 
 "					<%if (topId != null) { %>" + 
 "						and top.top_id != #topId#" + 
 "					<% } %>" + 
 "				)",
			taskEngineClass = io.vertigo.basics.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(smartType = "STyYesNo")
	public Boolean checkUnicityTopicCode(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId, @io.vertigo.datamodel.task.proxy.TaskInput(name = "code", smartType = "STyLabel") final String code, @io.vertigo.datamodel.task.proxy.TaskInput(name = "topId", smartType = "STyId") final Optional<Long> topId) {
		final Task task = createTaskBuilder("TkCheckUnicityTopicCode")
				.addValue("botId", botId)
				.addValue("code", code)
				.addValue("topId", topId.orElse(null))
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TkGetAllTopicsIhmFromBot.
	 * @param botId Long
	 * @param ktoCd String
	 * @return DtList de TopicIhm topicIHM
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkGetAllTopicsIhmFromBot",
			request = "SELECT 	top.top_id," + 
 "					top.title," + 
 "					top.code," + 
 "					smt.smt_id," + 
 "					sin.sin_id," + 
 "					top.is_enabled," + 
 "					tto.label as type," + 
 "					top.tto_cd," + 
 "					tpc.label as cat_label," + 
 "					string_agg(tpl.label, ',') as labels" + 
 "			from topic top " + 
 "			left join small_talk smt on smt.top_id = top.top_id" + 
 "			left join script_intention sin on sin.top_id = top.top_id" + 
 "			join type_topic tto on top.tto_cd = tto.tto_cd" + 
 "			join topic_category tpc on (tpc.top_cat_id = top.top_cat_id)" + 
 "			left join topic_topic_label ttl on (ttl.top_id = top.top_id)" + 
 "			left join topic_label tpl on (tpl.label_id = ttl.label_id)" + 
 "			where top.bot_id = #botId#" + 
 "			<% if (ktoCd != null){ %>" + 
 "				and top.kto_cd = #ktoCd#" + 
 "			<% } %>" + 
 "			group by top.top_id," + 
 "					top.title," + 
 "					top.code," + 
 "					smt.smt_id," + 
 "					sin.sin_id," + 
 "					top.is_enabled," + 
 "					tto.label ," + 
 "					top.tto_cd," + 
 "					tpc.label",
			taskEngineClass = io.vertigo.basics.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(smartType = "STyDtTopicIhm")
	public io.vertigo.datamodel.structure.model.DtList<io.vertigo.chatbot.commons.domain.topic.TopicIhm> getAllTopicsIhmFromBot(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId, @io.vertigo.datamodel.task.proxy.TaskInput(name = "ktoCd", smartType = "STyCode") final Optional<String> ktoCd) {
		final Task task = createTaskBuilder("TkGetAllTopicsIhmFromBot")
				.addValue("botId", botId)
				.addValue("ktoCd", ktoCd.orElse(null))
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TkGetTopicIhmById.
	 * @param topId Long
	 * @return TopicIhm topicIHM
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkGetTopicIhmById",
			request = "SELECT 	top.top_id," + 
 "					top.title," + 
 "					top.code," + 
 "					smt.smt_id," + 
 "					sin.sin_id," + 
 "					top.is_enabled," + 
 "					tto.label as type," + 
 "					top.tto_cd," + 
 "					tpc.label as cat_label," + 
 "					string_agg(tpl.label, ',') as labels" + 
 "			from topic top " + 
 "			left join small_talk smt on smt.top_id = top.top_id" + 
 "			left join script_intention sin on sin.top_id = top.top_id" + 
 "			join type_topic tto on top.tto_cd = tto.tto_cd" + 
 "			join topic_category tpc on (tpc.top_cat_id = top.top_cat_id)" + 
 "			left join topic_topic_label ttl on (ttl.top_id = top.top_id)" + 
 "			left join topic_label tpl on (tpl.label_id = ttl.label_id)" + 
 "			where top.top_id = #topId#" + 
 "			group by top.top_id," + 
 "					top.title," + 
 "					top.code," + 
 "					smt.smt_id," + 
 "					sin.sin_id," + 
 "					top.is_enabled," + 
 "					tto.label ," + 
 "					top.tto_cd," + 
 "					tpc.label" + 
 "			" + 
 "			LIMIT 1",
			taskEngineClass = io.vertigo.basics.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(smartType = "STyDtTopicIhm")
	public io.vertigo.chatbot.commons.domain.topic.TopicIhm getTopicIhmById(@io.vertigo.datamodel.task.proxy.TaskInput(name = "topId", smartType = "STyId") final Long topId) {
		final Task task = createTaskBuilder("TkGetTopicIhmById")
				.addValue("topId", topId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TkRemoveAllNluTrainingSentenceByBotId.
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllNluTrainingSentenceByBotId",
			request = "delete from nlu_training_sentence nts" + 
 "			using topic top" + 
 "			where nts.top_id = top.top_id and top.bot_id = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllNluTrainingSentenceByBotId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkRemoveAllNluTrainingSentenceByBotId")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkRemoveAllTopicsFromBot.
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllTopicsFromBot",
			request = "delete from topic top " + 
 "			where top.bot_id = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllTopicsFromBot(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkRemoveAllTopicsFromBot")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
