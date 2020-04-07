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
package io.vertigo.chatbot.designer.domain;

import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class UnknownSentense implements DtObject {
	private static final long serialVersionUID = 1L;

	private String messageId;
	private java.time.Instant date;
	private String text;
	private String intentRasa;
	private java.math.BigDecimal confidence;
	private Long smtId;
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Rasa message ID'.
	 * @return String messageId
	 */
	@Field(domain = "DoIdStr", label = "Rasa message ID")
	public String getMessageId() {
		return messageId;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Rasa message ID'.
	 * @param messageId String
	 */
	public void setMessageId(final String messageId) {
		this.messageId = messageId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Date'.
	 * @return Instant date
	 */
	@Field(domain = "DoInstant", label = "Date")
	public java.time.Instant getDate() {
		return date;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Date'.
	 * @param date Instant
	 */
	public void setDate(final java.time.Instant date) {
		this.date = date;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'User text'.
	 * @return String text
	 */
	@Field(domain = "DoLabel", label = "User text")
	public String getText() {
		return text;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'User text'.
	 * @param text String
	 */
	public void setText(final String text) {
		this.text = text;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Rasa intent'.
	 * @return String intentRasa
	 */
	@Field(domain = "DoLabel", label = "Rasa intent")
	public String getIntentRasa() {
		return intentRasa;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Rasa intent'.
	 * @param intentRasa String
	 */
	public void setIntentRasa(final String intentRasa) {
		this.intentRasa = intentRasa;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Confidence'.
	 * @return BigDecimal confidence
	 */
	@Field(domain = "DoPercentage", label = "Confidence")
	public java.math.BigDecimal getConfidence() {
		return confidence;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Confidence'.
	 * @param confidence BigDecimal
	 */
	public void setConfidence(final java.math.BigDecimal confidence) {
		this.confidence = confidence;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Small Talk selection'.
	 * @return Long smtId
	 */
	@Field(domain = "DoId", label = "Small Talk selection")
	public Long getSmtId() {
		return smtId;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Small Talk selection'.
	 * @param smtId Long
	 */
	public void setSmtId(final Long smtId) {
		this.smtId = smtId;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
