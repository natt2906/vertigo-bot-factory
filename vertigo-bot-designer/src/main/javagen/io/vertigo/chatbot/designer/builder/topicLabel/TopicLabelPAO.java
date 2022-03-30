package io.vertigo.chatbot.designer.builder.topicLabel;

import javax.inject.Inject;

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
public final class TopicLabelPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public TopicLabelPAO(final TaskManager taskManager) {
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
	 * Execute la tache TkAddInNNTopicLabel.
	 * @param tpls List de String
	 * @param topId Long
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkAddInNNTopicLabel",
			request = "INSERT INTO topic_topic_label\n" + 
 " 				select #topId#, tpl.label_id\n" + 
 " 				from topic_label tpl\n" + 
 " 				where tpl.label in (#tpls.rownum#) and tpl.bot_id = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void addInNNTopicLabel(@io.vertigo.datamodel.task.proxy.TaskInput(name = "tpls", smartType = "STyLabel") final java.util.List<String> tpls, @io.vertigo.datamodel.task.proxy.TaskInput(name = "topId", smartType = "STyId") final Long topId, @io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkAddInNNTopicLabel")
				.addValue("tpls", tpls)
				.addValue("topId", topId)
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkRemoveAllLabelByBotId.
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllLabelByBotId",
			request = "delete \n" + 
 " 				from topic_label tpl\n" + 
 " 				where tpl.bot_id = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllLabelByBotId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkRemoveAllLabelByBotId")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkRemoveAllLabelByTopicId.
	 * @param topId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllLabelByTopicId",
			request = "delete \n" + 
 " 				from topic_topic_label ttl\n" + 
 " 				where ttl.top_id = #topId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllLabelByTopicId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "topId", smartType = "STyId") final Long topId) {
		final Task task = createTaskBuilder("TkRemoveAllLabelByTopicId")
				.addValue("topId", topId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkRemoveAllLabelFromBotId.
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllLabelFromBotId",
			request = "delete \n" + 
 " 				from topic_topic_label ttl\n" + 
 " 				using topic top\n" + 
 " 				where top.top_id = ttl.top_id and top.bot_id = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllLabelFromBotId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkRemoveAllLabelFromBotId")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkRemoveFromNNTopicLabel.
	 * @param tpls List de String
	 * @param topId Long
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveFromNNTopicLabel",
			request = "delete \n" + 
 " 				from topic_topic_label ttl\n" + 
 " 				using topic_label tpl \n" + 
 " 				where ttl.label_id = tpl.label_id \n" + 
 " 				and tpl.label in (#tpls.rownum#) \n" + 
 " 				and tpl.bot_id = #botId# \n" + 
 " 				and ttl.top_id = #topId#;",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeFromNNTopicLabel(@io.vertigo.datamodel.task.proxy.TaskInput(name = "tpls", smartType = "STyLabel") final java.util.List<String> tpls, @io.vertigo.datamodel.task.proxy.TaskInput(name = "topId", smartType = "STyId") final Long topId, @io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkRemoveFromNNTopicLabel")
				.addValue("tpls", tpls)
				.addValue("topId", topId)
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkResetNNTopicLabel.
	 * @param topId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkResetNNTopicLabel",
			request = "delete \n" + 
 " 				from topic_topic_label ttl\n" + 
 " 				where ttl.top_id = #topId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void resetNNTopicLabel(@io.vertigo.datamodel.task.proxy.TaskInput(name = "topId", smartType = "STyId") final Long topId) {
		final Task task = createTaskBuilder("TkResetNNTopicLabel")
				.addValue("topId", topId)
				.build();
		getTaskManager().execute(task);
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
