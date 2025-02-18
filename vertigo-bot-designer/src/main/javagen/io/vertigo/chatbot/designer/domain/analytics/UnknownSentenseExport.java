package io.vertigo.chatbot.designer.domain.analytics;

import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.structure.model.DtObject;
import io.vertigo.datamodel.structure.stereotype.Field;
import io.vertigo.datamodel.structure.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class UnknownSentenseExport implements DtObject {
	private static final long serialVersionUID = 1L;

	private java.time.Instant date;
	private String text;
	private String modelName;
	private java.time.Instant dateTraining;
	private java.math.BigDecimal confidence;
	private String botName;
	private String creationBot;
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Date'.
	 * @return Instant date
	 */
	@Field(smartType = "STyInstant", label = "Date")
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
	@Field(smartType = "STyLabel", label = "User text")
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
	 * Récupère la valeur de la propriété 'Model Name'.
	 * @return String modelName
	 */
	@Field(smartType = "STyLabel", label = "Model Name")
	public String getModelName() {
		return modelName;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Model Name'.
	 * @param modelName String
	 */
	public void setModelName(final String modelName) {
		this.modelName = modelName;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Date training'.
	 * @return Instant dateTraining
	 */
	@Field(smartType = "STyInstant", label = "Date training")
	public java.time.Instant getDateTraining() {
		return dateTraining;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Date training'.
	 * @param dateTraining Instant
	 */
	public void setDateTraining(final java.time.Instant dateTraining) {
		this.dateTraining = dateTraining;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Confidence'.
	 * @return BigDecimal confidence
	 */
	@Field(smartType = "STyPercentage", label = "Confidence")
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
	 * Récupère la valeur de la propriété 'Bot name'.
	 * @return String botName
	 */
	@Field(smartType = "STyLabel", label = "Bot name")
	public String getBotName() {
		return botName;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Bot name'.
	 * @param botName String
	 */
	public void setBotName(final String botName) {
		this.botName = botName;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Date bot creation'.
	 * @return String creationBot
	 */
	@Field(smartType = "STyLabel", label = "Date bot creation")
	public String getCreationBot() {
		return creationBot;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Date bot creation'.
	 * @param creationBot String
	 */
	public void setCreationBot(final String creationBot) {
		this.creationBot = creationBot;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
