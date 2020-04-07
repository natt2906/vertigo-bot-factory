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
package io.vertigo.chatbot.commons.domain;

import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.UID;
import io.vertigo.dynamo.domain.model.VAccessor;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class ChatbotNode implements Entity {
	private static final long serialVersionUID = 1L;

	private Long nodId;
	private String name;
	private String url;
	private Boolean isDev;
	private String color;
	private String apiKey;

	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "ANodeChatbot",
			fkFieldName = "botId",
			primaryDtDefinitionName = "DtChatbot",
			primaryIsNavigable = true,
			primaryRole = "Chatbot",
			primaryLabel = "Chatbot",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtChatbotNode",
			foreignIsNavigable = false,
			foreignRole = "ChatbotNode",
			foreignLabel = "ChatbotNode",
			foreignMultiplicity = "0..*")
	private final VAccessor<io.vertigo.chatbot.commons.domain.Chatbot> botIdAccessor = new VAccessor<>(io.vertigo.chatbot.commons.domain.Chatbot.class, "Chatbot");

	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "ANodeTraining",
			fkFieldName = "traId",
			primaryDtDefinitionName = "DtTraining",
			primaryIsNavigable = true,
			primaryRole = "Training",
			primaryLabel = "Loaded model",
			primaryMultiplicity = "0..1",
			foreignDtDefinitionName = "DtChatbotNode",
			foreignIsNavigable = false,
			foreignRole = "ChatbotNode",
			foreignLabel = "ChatbotNode",
			foreignMultiplicity = "0..*")
	private final VAccessor<io.vertigo.chatbot.commons.domain.Training> traIdAccessor = new VAccessor<>(io.vertigo.chatbot.commons.domain.Training.class, "Training");

	/** {@inheritDoc} */
	@Override
	public UID<ChatbotNode> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'ID'.
	 * @return Long nodId <b>Obligatoire</b>
	 */
	@Field(domain = "DoId", type = "ID", required = true, label = "ID")
	public Long getNodId() {
		return nodId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'ID'.
	 * @param nodId Long <b>Obligatoire</b>
	 */
	public void setNodId(final Long nodId) {
		this.nodId = nodId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Name'.
	 * @return String name <b>Obligatoire</b>
	 */
	@Field(domain = "DoLabel", required = true, label = "Name")
	public String getName() {
		return name;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Name'.
	 * @param name String <b>Obligatoire</b>
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'URL'.
	 * @return String url <b>Obligatoire</b>
	 */
	@Field(domain = "DoUrl", required = true, label = "URL")
	public String getUrl() {
		return url;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'URL'.
	 * @param url String <b>Obligatoire</b>
	 */
	public void setUrl(final String url) {
		this.url = url;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Dev node'.
	 * @return Boolean isDev <b>Obligatoire</b>
	 */
	@Field(domain = "DoYesNo", required = true, label = "Dev node")
	public Boolean getIsDev() {
		return isDev;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Dev node'.
	 * @param isDev Boolean <b>Obligatoire</b>
	 */
	public void setIsDev(final Boolean isDev) {
		this.isDev = isDev;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Color'.
	 * @return String color <b>Obligatoire</b>
	 */
	@Field(domain = "DoColor", required = true, label = "Color")
	public String getColor() {
		return color;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Color'.
	 * @param color String <b>Obligatoire</b>
	 */
	public void setColor(final String color) {
		this.color = color;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'ApiKey'.
	 * @return String apiKey <b>Obligatoire</b>
	 */
	@Field(domain = "DoLabel", required = true, label = "ApiKey")
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'ApiKey'.
	 * @param apiKey String <b>Obligatoire</b>
	 */
	public void setApiKey(final String apiKey) {
		this.apiKey = apiKey;
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Chatbot'.
	 * @return Long botId <b>Obligatoire</b>
	 */
	@Field(domain = "DoId", type = "FOREIGN_KEY", required = true, label = "Chatbot")
	public Long getBotId() {
		return (Long) botIdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Chatbot'.
	 * @param botId Long <b>Obligatoire</b>
	 */
	public void setBotId(final Long botId) {
		botIdAccessor.setId(botId);
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Loaded model'.
	 * @return Long traId
	 */
	@Field(domain = "DoId", type = "FOREIGN_KEY", label = "Loaded model")
	public Long getTraId() {
		return (Long) traIdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Loaded model'.
	 * @param traId Long
	 */
	public void setTraId(final Long traId) {
		traIdAccessor.setId(traId);
	}

 	/**
	 * Association : Chatbot.
	 * @return l'accesseur vers la propriété 'Chatbot'
	 */
	public VAccessor<io.vertigo.chatbot.commons.domain.Chatbot> chatbot() {
		return botIdAccessor;
	}

 	/**
	 * Association : Loaded model.
	 * @return l'accesseur vers la propriété 'Loaded model'
	 */
	public VAccessor<io.vertigo.chatbot.commons.domain.Training> training() {
		return traIdAccessor;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
