package io.vertigo.chatbot.commons.dao.topic;

import javax.inject.Inject;

import io.vertigo.core.lang.Generated;
import io.vertigo.core.node.Node;
import io.vertigo.datamodel.task.definitions.TaskDefinition;
import io.vertigo.datamodel.task.model.Task;
import io.vertigo.datamodel.task.model.TaskBuilder;
import io.vertigo.datastore.entitystore.EntityStoreManager;
import io.vertigo.datastore.impl.dao.DAO;
import io.vertigo.datastore.impl.dao.StoreServices;
import io.vertigo.datamodel.smarttype.SmartTypeManager;
import io.vertigo.datamodel.task.TaskManager;
import io.vertigo.chatbot.commons.domain.topic.UtterText;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class UtterTextDAO extends DAO<UtterText, java.lang.Long> implements StoreServices {

	/**
	 * Contructeur.
	 * @param entityStoreManager Manager de persistance
	 * @param taskManager Manager de Task
	 * @param smartTypeManager SmartTypeManager
	 */
	@Inject
	public UtterTextDAO(final EntityStoreManager entityStoreManager, final TaskManager taskManager, final SmartTypeManager smartTypeManager) {
		super(UtterText.class, entityStoreManager, taskManager, smartTypeManager);
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
	 * Execute la tache TkExportSmallTalkRelativeUtter.
	 * @param stIds List de Long
	 * @return DtList de UtterText utterTexts
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkExportSmallTalkRelativeUtter",
			request = "select " + 
 "            	*" + 
 "			from utter_text utt" + 
 "			where utt.smt_id in (#stIds.rownum#)",
			taskEngineClass = io.vertigo.basics.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(smartType = "STyDtUtterText")
	public io.vertigo.datamodel.structure.model.DtList<io.vertigo.chatbot.commons.domain.topic.UtterText> exportSmallTalkRelativeUtter(@io.vertigo.datamodel.task.proxy.TaskInput(name = "stIds", smartType = "STyNumber") final java.util.List<Long> stIds) {
		final Task task = createTaskBuilder("TkExportSmallTalkRelativeUtter")
				.addValue("stIds", stIds)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TkGetBasicUtterTextByTopId.
	 * @param topId Long
	 * @return DtList de UtterText utterTexts
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkGetBasicUtterTextByTopId",
			request = "SELECT 	utt.*" + 
 "			from utter_text utt " + 
 "			join small_talk smt on smt.smt_id = utt.smt_id" + 
 "			where smt.top_id = #topId#" + 
 "			LIMIT 1",
			taskEngineClass = io.vertigo.basics.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(smartType = "STyDtUtterText")
	public io.vertigo.datamodel.structure.model.DtList<io.vertigo.chatbot.commons.domain.topic.UtterText> getBasicUtterTextByTopId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "topId", smartType = "STyId") final Long topId) {
		final Task task = createTaskBuilder("TkGetBasicUtterTextByTopId")
				.addValue("topId", topId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

}
