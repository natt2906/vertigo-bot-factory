package io.vertigo.chatbot.commons.domain.topic;

import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.structure.model.Entity;
import io.vertigo.datastore.impl.entitystore.EnumStoreVAccessor;
import io.vertigo.datastore.impl.entitystore.StoreListVAccessor;
import io.vertigo.datamodel.structure.model.UID;
import io.vertigo.datastore.impl.entitystore.StoreVAccessor;
import io.vertigo.datamodel.structure.stereotype.Field;
import io.vertigo.datamodel.structure.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class Topic implements Entity {
	private static final long serialVersionUID = 1L;

	private Long topId;
	private String title;
	private String description;
	private Boolean isEnabled;
	private Long code;

	@io.vertigo.datamodel.structure.stereotype.Association(
			name = "ATopicTypeTopic",
			fkFieldName = "ttoCd",
			primaryDtDefinitionName = "DtTypeTopic",
			primaryIsNavigable = true,
			primaryRole = "TypeTopic",
			primaryLabel = "Type du topic",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtTopic",
			foreignIsNavigable = false,
			foreignRole = "Topic",
			foreignLabel = "Topic",
			foreignMultiplicity = "0..*")
	private final EnumStoreVAccessor<io.vertigo.chatbot.commons.domain.topic.TypeTopic, io.vertigo.chatbot.commons.domain.topic.TypeTopicEnum> ttoCdAccessor = new EnumStoreVAccessor<>(io.vertigo.chatbot.commons.domain.topic.TypeTopic.class, "TypeTopic", io.vertigo.chatbot.commons.domain.topic.TypeTopicEnum.class);

	@io.vertigo.datamodel.structure.stereotype.Association(
			name = "ATopicChatbot",
			fkFieldName = "botId",
			primaryDtDefinitionName = "DtChatbot",
			primaryIsNavigable = true,
			primaryRole = "Chatbot",
			primaryLabel = "Chatbot",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtTopic",
			foreignIsNavigable = false,
			foreignRole = "Topic",
			foreignLabel = "Topic",
			foreignMultiplicity = "0..*")
	private final StoreVAccessor<io.vertigo.chatbot.commons.domain.Chatbot> botIdAccessor = new StoreVAccessor<>(io.vertigo.chatbot.commons.domain.Chatbot.class, "Chatbot");

	@io.vertigo.datamodel.structure.stereotype.Association(
			name = "ATopicCategoryTopic",
			fkFieldName = "topCatId",
			primaryDtDefinitionName = "DtTopicCategory",
			primaryIsNavigable = true,
			primaryRole = "Topic",
			primaryLabel = "Topic",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtTopic",
			foreignIsNavigable = false,
			foreignRole = "Topic",
			foreignLabel = "Topic",
			foreignMultiplicity = "0..*")
	private final StoreVAccessor<io.vertigo.chatbot.commons.domain.topic.TopicCategory> topCatIdAccessor = new StoreVAccessor<>(io.vertigo.chatbot.commons.domain.topic.TopicCategory.class, "Topic");

	@io.vertigo.datamodel.structure.stereotype.Association(
			name = "ATopicKindTopic",
			fkFieldName = "ktoCd",
			primaryDtDefinitionName = "DtKindTopic",
			primaryIsNavigable = true,
			primaryRole = "KindTopic",
			primaryLabel = "Kind of topic",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtTopic",
			foreignIsNavigable = false,
			foreignRole = "Topic",
			foreignLabel = "Topic",
			foreignMultiplicity = "0..*")
	private final EnumStoreVAccessor<io.vertigo.chatbot.commons.domain.topic.KindTopic, io.vertigo.chatbot.commons.domain.topic.KindTopicEnum> ktoCdAccessor = new EnumStoreVAccessor<>(io.vertigo.chatbot.commons.domain.topic.KindTopic.class, "KindTopic", io.vertigo.chatbot.commons.domain.topic.KindTopicEnum.class);

	@io.vertigo.datamodel.structure.stereotype.AssociationNN(
			name = "AnnTopicCategory",
			tableName = "TOPIC_TOPIC_CATEGORY",
			dtDefinitionA = "DtTopic",
			dtDefinitionB = "DtTopicCategory",
			navigabilityA = true,
			navigabilityB = true,
			roleA = "Topic",
			roleB = "Category",
			labelA = "Topic",
			labelB = "Category")
	private final StoreListVAccessor<io.vertigo.chatbot.commons.domain.topic.TopicCategory> categoryAccessor = new StoreListVAccessor<>(this, "AnnTopicCategory", "Category");

	/** {@inheritDoc} */
	@Override
	public UID<Topic> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'ID'.
	 * @return Long topId <b>Obligatoire</b>
	 */
	@Field(smartType = "STyId", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "ID")
	public Long getTopId() {
		return topId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'ID'.
	 * @param topId Long <b>Obligatoire</b>
	 */
	public void setTopId(final Long topId) {
		this.topId = topId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Title'.
	 * @return String title <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Title")
	public String getTitle() {
		return title;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Title'.
	 * @param title String <b>Obligatoire</b>
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Description'.
	 * @return String description
	 */
	@Field(smartType = "STyLabel", label = "Description")
	public String getDescription() {
		return description;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Description'.
	 * @param description String
	 */
	public void setDescription(final String description) {
		this.description = description;
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
	 * Récupère la valeur de la propriété 'Code'.
	 * @return Long code <b>Obligatoire</b>
	 */
	@Field(smartType = "STyNumber", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Code")
	public Long getCode() {
		return code;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Code'.
	 * @param code Long <b>Obligatoire</b>
	 */
	public void setCode(final Long code) {
		this.code = code;
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Type du topic'.
	 * @return String ttoCd <b>Obligatoire</b>
	 */
	@io.vertigo.datamodel.structure.stereotype.ForeignKey(smartType = "STyCode", label = "Type du topic", fkDefinition = "DtTypeTopic" )
	public String getTtoCd() {
		return (String) ttoCdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Type du topic'.
	 * @param ttoCd String <b>Obligatoire</b>
	 */
	public void setTtoCd(final String ttoCd) {
		ttoCdAccessor.setId(ttoCd);
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
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Topic'.
	 * @return Long topCatId <b>Obligatoire</b>
	 */
	@io.vertigo.datamodel.structure.stereotype.ForeignKey(smartType = "STyId", label = "Topic", fkDefinition = "DtTopicCategory" )
	public Long getTopCatId() {
		return (Long) topCatIdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Topic'.
	 * @param topCatId Long <b>Obligatoire</b>
	 */
	public void setTopCatId(final Long topCatId) {
		topCatIdAccessor.setId(topCatId);
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Kind of topic'.
	 * @return String ktoCd <b>Obligatoire</b>
	 */
	@io.vertigo.datamodel.structure.stereotype.ForeignKey(smartType = "STyCode", label = "Kind of topic", fkDefinition = "DtKindTopic" )
	public String getKtoCd() {
		return (String) ktoCdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Kind of topic'.
	 * @param ktoCd String <b>Obligatoire</b>
	 */
	public void setKtoCd(final String ktoCd) {
		ktoCdAccessor.setId(ktoCd);
	}

 	/**
	 * Association : Topic.
	 * @return l'accesseur vers la propriété 'Topic'
	 */
	public StoreVAccessor<io.vertigo.chatbot.commons.domain.topic.TopicCategory> topic() {
		return topCatIdAccessor;
	}

 	/**
	 * Association : Chatbot.
	 * @return l'accesseur vers la propriété 'Chatbot'
	 */
	public StoreVAccessor<io.vertigo.chatbot.commons.domain.Chatbot> chatbot() {
		return botIdAccessor;
	}

 	/**
	 * Association : Kind of topic.
	 * @return l'accesseur vers la propriété 'Kind of topic'
	 */
	public EnumStoreVAccessor<io.vertigo.chatbot.commons.domain.topic.KindTopic, io.vertigo.chatbot.commons.domain.topic.KindTopicEnum> kindTopic() {
		return ktoCdAccessor;
	}

 	/**
	 * Association : Type du topic.
	 * @return l'accesseur vers la propriété 'Type du topic'
	 */
	public EnumStoreVAccessor<io.vertigo.chatbot.commons.domain.topic.TypeTopic, io.vertigo.chatbot.commons.domain.topic.TypeTopicEnum> typeTopic() {
		return ttoCdAccessor;
	}

	/**
	 * Association : Category.
	 * @return l'accesseur vers la propriété 'Category'
	 */
	public StoreListVAccessor<io.vertigo.chatbot.commons.domain.topic.TopicCategory> category() {
		return categoryAccessor;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
