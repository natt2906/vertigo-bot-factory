package io.vertigo.chatbot.commons.domain.topic;

import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.structure.model.Entity;
import io.vertigo.datamodel.structure.model.UID;
import io.vertigo.datastore.impl.entitystore.StoreVAccessor;
import io.vertigo.datamodel.structure.stereotype.Field;
import io.vertigo.datamodel.structure.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class TopicCategory implements Entity {
	private static final long serialVersionUID = 1L;

	private Long topCatId;
	private String code;
	private String label;
	private Long level;
	private Boolean isEnabled;
	private Boolean isTechnical;

	@io.vertigo.datamodel.structure.stereotype.Association(
			name = "ATopicCategoryChatbot",
			fkFieldName = "botId",
			primaryDtDefinitionName = "DtChatbot",
			primaryIsNavigable = true,
			primaryRole = "Chatbot",
			primaryLabel = "Chatbot",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtTopicCategory",
			foreignIsNavigable = false,
			foreignRole = "TopicCategory",
			foreignLabel = "TopicCategory",
			foreignMultiplicity = "0..*")
	private final StoreVAccessor<io.vertigo.chatbot.commons.domain.Chatbot> botIdAccessor = new StoreVAccessor<>(io.vertigo.chatbot.commons.domain.Chatbot.class, "Chatbot");

	/** {@inheritDoc} */
	@Override
	public UID<TopicCategory> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Topic category id'.
	 * @return Long topCatId <b>Obligatoire</b>
	 */
	@Field(smartType = "STyId", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Topic category id")
	public Long getTopCatId() {
		return topCatId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Topic category id'.
	 * @param topCatId Long <b>Obligatoire</b>
	 */
	public void setTopCatId(final Long topCatId) {
		this.topCatId = topCatId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Code'.
	 * @return String code <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Code")
	public String getCode() {
		return code;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Code'.
	 * @param code String <b>Obligatoire</b>
	 */
	public void setCode(final String code) {
		this.code = code;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Topic category label'.
	 * @return String label <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Topic category label")
	public String getLabel() {
		return label;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Topic category label'.
	 * @param label String <b>Obligatoire</b>
	 */
	public void setLabel(final String label) {
		this.label = label;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Category level'.
	 * @return Long level
	 */
	@Field(smartType = "STyNumber", label = "Category level")
	public Long getLevel() {
		return level;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Category level'.
	 * @param level Long
	 */
	public void setLevel(final Long level) {
		this.level = level;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Enabled'.
	 * @return Boolean isEnabled <b>Obligatoire</b>
	 */
	@Field(smartType = "STyYesNo", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Enabled")
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Enabled'.
	 * @param isEnabled Boolean <b>Obligatoire</b>
	 */
	public void setIsEnabled(final Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Technical'.
	 * @return Boolean isTechnical <b>Obligatoire</b>
	 */
	@Field(smartType = "STyYesNo", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Technical")
	public Boolean getIsTechnical() {
		return isTechnical;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Technical'.
	 * @param isTechnical Boolean <b>Obligatoire</b>
	 */
	public void setIsTechnical(final Boolean isTechnical) {
		this.isTechnical = isTechnical;
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Chatbot'.
	 * @return Long botId <b>Obligatoire</b>
	 */
	@io.vertigo.datamodel.structure.stereotype.ForeignKey(smartType = "STyId", label = "Chatbot", fkDefinition = "DtChatbot" )
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
	 * Association : Chatbot.
	 * @return l'accesseur vers la propriété 'Chatbot'
	 */
	public StoreVAccessor<io.vertigo.chatbot.commons.domain.Chatbot> chatbot() {
		return botIdAccessor;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
