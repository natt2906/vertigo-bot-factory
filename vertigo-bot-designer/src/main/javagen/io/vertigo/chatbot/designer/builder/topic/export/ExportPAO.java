package io.vertigo.chatbot.designer.builder.topic.export;

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
public final class ExportPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public ExportPAO(final TaskManager taskManager) {
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
	 * Execute la tache TkExportScriptIntentionRelativeTrainingSentence.
	 * @param botId Long
	 * @return DtList de NluTrainingExport tpcs
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkExportScriptIntentionRelativeTrainingSentence",
			request = "select   " + 
 "             	nts.nts_id,  " + 
 "             	nts.text,  " + 
 "             	smt.smt_id  " + 
 " 			from nlu_training_sentence nts  " + 
 " 			join topic top on (top.top_id = nts.top_id)  " + 
 " 			join script_intention sin on (sin.top_id = top.top_id)  " + 
 " 			where top.bot_id = #botId#  " + 
 " 			and top.is_enabled = true",
			taskEngineClass = io.vertigo.basics.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(smartType = "STyDtNluTrainingExport")
	public io.vertigo.datamodel.structure.model.DtList<io.vertigo.chatbot.commons.domain.topic.NluTrainingExport> exportScriptIntentionRelativeTrainingSentence(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkExportScriptIntentionRelativeTrainingSentence")
				.addValue("botId", botId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TkExportSmallTalkRelativeTrainingSentence.
	 * @param botId Long
	 * @return DtList de NluTrainingExport tpcs
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkExportSmallTalkRelativeTrainingSentence",
			request = "select   " + 
 "             	nts.nts_id,  " + 
 "             	nts.text,  " + 
 "             	smt.smt_id  " + 
 " 			from nlu_training_sentence nts  " + 
 " 			join topic top on (top.top_id = nts.top_id)  " + 
 " 			join small_talk smt on (smt.top_id = top.top_id)  " + 
 " 			where top.bot_id = #botId#  " + 
 " 			and top.is_enabled = true",
			taskEngineClass = io.vertigo.basics.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(smartType = "STyDtNluTrainingExport")
	public io.vertigo.datamodel.structure.model.DtList<io.vertigo.chatbot.commons.domain.topic.NluTrainingExport> exportSmallTalkRelativeTrainingSentence(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkExportSmallTalkRelativeTrainingSentence")
				.addValue("botId", botId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
