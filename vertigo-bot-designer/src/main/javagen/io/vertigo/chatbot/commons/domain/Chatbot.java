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
public final class Chatbot implements Entity {
	private static final long serialVersionUID = 1L;

	private Long botId;
	private String name;
	private String description;
	private java.time.LocalDate creationDate;
	private String status;

	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "AChatbotMediaFileInfo",
			fkFieldName = "filIdAvatar",
			primaryDtDefinitionName = "DtMediaFileInfo",
			primaryIsNavigable = true,
			primaryRole = "MediaFileInfo",
			primaryLabel = "Avatar",
			primaryMultiplicity = "0..1",
			foreignDtDefinitionName = "DtChatbot",
			foreignIsNavigable = false,
			foreignRole = "Chatbot",
			foreignLabel = "Chatbot",
			foreignMultiplicity = "0..*")
	private final VAccessor<io.vertigo.chatbot.commons.domain.MediaFileInfo> filIdAvatarAccessor = new VAccessor<>(io.vertigo.chatbot.commons.domain.MediaFileInfo.class, "MediaFileInfo");

	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "AChatbotUtterTextWelcome",
			fkFieldName = "uttIdWelcome",
			primaryDtDefinitionName = "DtUtterText",
			primaryIsNavigable = true,
			primaryRole = "utterTextWelcome",
			primaryLabel = "Welcome text",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtChatbot",
			foreignIsNavigable = false,
			foreignRole = "Chatbot",
			foreignLabel = "Chatbot",
			foreignMultiplicity = "0..*")
	private final VAccessor<io.vertigo.chatbot.commons.domain.UtterText> uttIdWelcomeAccessor = new VAccessor<>(io.vertigo.chatbot.commons.domain.UtterText.class, "utterTextWelcome");

	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "AChatbotUtterTextDefault",
			fkFieldName = "uttIdDefault",
			primaryDtDefinitionName = "DtUtterText",
			primaryIsNavigable = true,
			primaryRole = "utterTextDefault",
			primaryLabel = "Default text",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtChatbot",
			foreignIsNavigable = false,
			foreignRole = "Chatbot",
			foreignLabel = "Chatbot",
			foreignMultiplicity = "0..*")
	private final VAccessor<io.vertigo.chatbot.commons.domain.UtterText> uttIdDefaultAccessor = new VAccessor<>(io.vertigo.chatbot.commons.domain.UtterText.class, "utterTextDefault");

	/** {@inheritDoc} */
	@Override
	public UID<Chatbot> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'ID'.
	 * @return Long botId <b>Obligatoire</b>
	 */
	@Field(domain = "DoId", type = "ID", required = true, label = "ID")
	public Long getBotId() {
		return botId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'ID'.
	 * @param botId Long <b>Obligatoire</b>
	 */
	public void setBotId(final Long botId) {
		this.botId = botId;
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
	 * Récupère la valeur de la propriété 'Description'.
	 * @return String description <b>Obligatoire</b>
	 */
	@Field(domain = "DoText", required = true, label = "Description")
	public String getDescription() {
		return description;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Description'.
	 * @param description String <b>Obligatoire</b>
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Creation date'.
	 * @return LocalDate creationDate <b>Obligatoire</b>
	 */
	@Field(domain = "DoLocaldate", required = true, label = "Creation date")
	public java.time.LocalDate getCreationDate() {
		return creationDate;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Creation date'.
	 * @param creationDate LocalDate <b>Obligatoire</b>
	 */
	public void setCreationDate(final java.time.LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Status'.
	 * @return String status <b>Obligatoire</b>
	 */
	@Field(domain = "DoCode", required = true, label = "Status")
	public String getStatus() {
		return status;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Status'.
	 * @param status String <b>Obligatoire</b>
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Avatar'.
	 * @return Long filIdAvatar
	 */
	@Field(domain = "DoId", type = "FOREIGN_KEY", label = "Avatar")
	public Long getFilIdAvatar() {
		return (Long) filIdAvatarAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Avatar'.
	 * @param filIdAvatar Long
	 */
	public void setFilIdAvatar(final Long filIdAvatar) {
		filIdAvatarAccessor.setId(filIdAvatar);
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Welcome text'.
	 * @return Long uttIdWelcome <b>Obligatoire</b>
	 */
	@Field(domain = "DoId", type = "FOREIGN_KEY", required = true, label = "Welcome text")
	public Long getUttIdWelcome() {
		return (Long) uttIdWelcomeAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Welcome text'.
	 * @param uttIdWelcome Long <b>Obligatoire</b>
	 */
	public void setUttIdWelcome(final Long uttIdWelcome) {
		uttIdWelcomeAccessor.setId(uttIdWelcome);
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Default text'.
	 * @return Long uttIdDefault <b>Obligatoire</b>
	 */
	@Field(domain = "DoId", type = "FOREIGN_KEY", required = true, label = "Default text")
	public Long getUttIdDefault() {
		return (Long) uttIdDefaultAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Default text'.
	 * @param uttIdDefault Long <b>Obligatoire</b>
	 */
	public void setUttIdDefault(final Long uttIdDefault) {
		uttIdDefaultAccessor.setId(uttIdDefault);
	}

 	/**
	 * Association : Avatar.
	 * @return l'accesseur vers la propriété 'Avatar'
	 */
	public VAccessor<io.vertigo.chatbot.commons.domain.MediaFileInfo> mediaFileInfo() {
		return filIdAvatarAccessor;
	}

 	/**
	 * Association : Default text.
	 * @return l'accesseur vers la propriété 'Default text'
	 */
	public VAccessor<io.vertigo.chatbot.commons.domain.UtterText> utterTextDefault() {
		return uttIdDefaultAccessor;
	}

 	/**
	 * Association : Welcome text.
	 * @return l'accesseur vers la propriété 'Welcome text'
	 */
	public VAccessor<io.vertigo.chatbot.commons.domain.UtterText> utterTextWelcome() {
		return uttIdWelcomeAccessor;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
