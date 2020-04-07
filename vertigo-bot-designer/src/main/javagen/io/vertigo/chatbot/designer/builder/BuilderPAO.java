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
package io.vertigo.chatbot.designer.builder;

import javax.inject.Inject;

import io.vertigo.app.Home;
import io.vertigo.dynamo.task.TaskManager;
import io.vertigo.dynamo.task.metamodel.TaskDefinition;
import io.vertigo.dynamo.task.model.Task;
import io.vertigo.dynamo.task.model.TaskBuilder;
import io.vertigo.dynamo.store.StoreServices;
import io.vertigo.lang.Assertion;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
 @Generated
public final class BuilderPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public BuilderPAO(final TaskManager taskManager) {
		Assertion.checkNotNull(taskManager);
		//-----
		this.taskManager = taskManager;
	}

	/**
	 * Creates a taskBuilder.
	 * @param name  the name of the task
	 * @return the builder 
	 */
	private static TaskBuilder createTaskBuilder(final String name) {
		final TaskDefinition taskDefinition = Home.getApp().getDefinitionSpace().resolve(name, TaskDefinition.class);
		return Task.builder(taskDefinition);
	}

	/**
	 * Execute la tache TkCleanOldTrainings.
	 * @param botId Long 
	*/
	public void cleanOldTrainings(final Long botId) {
		final Task task = createTaskBuilder("TkCleanOldTrainings")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkGetNextModelNumber.
	 * @param botId Long 
	 * @return Long nextModelNumber
	*/
	public Long getNextModelNumber(final Long botId) {
		final Task task = createTaskBuilder("TkGetNextModelNumber")
				.addValue("botId", botId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TkRemoveAllUtterTextBySmtId.
	 * @param smtId Long 
	*/
	public void removeAllUtterTextBySmtId(final Long smtId) {
		final Task task = createTaskBuilder("TkRemoveAllUtterTextBySmtId")
				.addValue("smtId", smtId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkResetDevNode.
	 * @param botId Long 
	*/
	public void resetDevNode(final Long botId) {
		final Task task = createTaskBuilder("TkResetDevNode")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
