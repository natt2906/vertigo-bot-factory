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
package io.vertigo.chatbot.executor.rasa.config;

import java.util.List;

import io.vertigo.chatbot.executor.rasa.util.StringUtils;
import io.vertigo.lang.Assertion;

public class RasaIntent {

	private static final String NEW_LINE = "\r\n";

	private final String code;
	private final List<String> nlus;
	private final RasaAction trigger;

	public static RasaIntent newSmallTalk(final String name, final List<String> nlus, final RasaAction trigger) {
		Assertion.checkArgument(trigger.isSmallTalk(), "Le trigger doit être une action de small talk");
		// ----
		return new RasaIntent("st_" + StringUtils.labelToCode(name), nlus, trigger);
	}

	public static RasaIntent newGenericIntent(final String name, final List<String> nlus) {
		return new RasaIntent("int_" + name, nlus, null);
	}

	private RasaIntent(final String code, final List<String> nlus, final RasaAction trigger) {
		this.code = code;
		this.nlus = nlus;
		this.trigger = trigger;
	}

	public String getCode() {
		return code;
	}

	public List<String> getNlus() {
		return nlus;
	}

	public RasaAction getTrigger() {
		return trigger;
	}

	public String getNluDeclaration() {
		final StringBuilder nluDef = new StringBuilder();

		nluDef.append("## intent:").append(code).append(NEW_LINE);

		for (final String question : nlus) {
			nluDef.append("- ").append(question).append(NEW_LINE);
		}

		nluDef.append(NEW_LINE);

		return nluDef.toString();
	}

	public String getDomainDeclaration() {
		String retour = "  - " + code;

		if (trigger != null) {
			retour += ":" + NEW_LINE + "     triggers: " + trigger.getCode();
		}

		return retour;
	}
}
