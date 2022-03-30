package io.vertigo.chatbot.commons.domain;

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
public final class Attachment implements Entity {
	private static final long serialVersionUID = 1L;

	private Long attId;
	private String label;
	private String type;
	private Long length;

	@io.vertigo.datamodel.structure.stereotype.Association(
			name = "AAttachmentAttachmentFileInfo",
			fkFieldName = "attFiId",
			primaryDtDefinitionName = "DtAttachmentFileInfo",
			primaryIsNavigable = true,
			primaryRole = "AttachmentFileInfo",
			primaryLabel = "AttachmentFileInfo",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtAttachment",
			foreignIsNavigable = false,
			foreignRole = "Attachment",
			foreignLabel = "Attachment",
			foreignMultiplicity = "0..*")
	private final StoreVAccessor<io.vertigo.chatbot.commons.domain.AttachmentFileInfo> attFiIdAccessor = new StoreVAccessor<>(io.vertigo.chatbot.commons.domain.AttachmentFileInfo.class, "AttachmentFileInfo");

	@io.vertigo.datamodel.structure.stereotype.Association(
			name = "AAttachmentChatbot",
			fkFieldName = "botId",
			primaryDtDefinitionName = "DtChatbot",
			primaryIsNavigable = true,
			primaryRole = "Chatbot",
			primaryLabel = "Chatbot",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtAttachment",
			foreignIsNavigable = false,
			foreignRole = "Attachment",
			foreignLabel = "Attachment",
			foreignMultiplicity = "0..*")
	private final StoreVAccessor<io.vertigo.chatbot.commons.domain.Chatbot> botIdAccessor = new StoreVAccessor<>(io.vertigo.chatbot.commons.domain.Chatbot.class, "Chatbot");

	/** {@inheritDoc} */
	@Override
	public UID<Attachment> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Attachment id'.
	 * @return Long attId <b>Obligatoire</b>
	 */
	@Field(smartType = "STyId", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Attachment id")
	public Long getAttId() {
		return attId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Attachment id'.
	 * @param attId Long <b>Obligatoire</b>
	 */
	public void setAttId(final Long attId) {
		this.attId = attId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Label'.
	 * @return String label <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Label")
	public String getLabel() {
		return label;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Label'.
	 * @param label String <b>Obligatoire</b>
	 */
	public void setLabel(final String label) {
		this.label = label;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'MimeType'.
	 * @return String type <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "MimeType")
	public String getType() {
		return type;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'MimeType'.
	 * @param type String <b>Obligatoire</b>
	 */
	public void setType(final String type) {
		this.type = type;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Size'.
	 * @return Long length <b>Obligatoire</b>
	 */
	@Field(smartType = "STySize", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Size")
	public Long getLength() {
		return length;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Size'.
	 * @param length Long <b>Obligatoire</b>
	 */
	public void setLength(final Long length) {
		this.length = length;
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'AttachmentFileInfo'.
	 * @return Long attFiId <b>Obligatoire</b>
	 */
	@io.vertigo.datamodel.structure.stereotype.ForeignKey(smartType = "STyId", label = "AttachmentFileInfo", fkDefinition = "DtAttachmentFileInfo", cardinality = io.vertigo.core.lang.Cardinality.ONE )
	public Long getAttFiId() {
		return (Long) attFiIdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'AttachmentFileInfo'.
	 * @param attFiId Long <b>Obligatoire</b>
	 */
	public void setAttFiId(final Long attFiId) {
		attFiIdAccessor.setId(attFiId);
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Chatbot'.
	 * @return Long botId <b>Obligatoire</b>
	 */
	@io.vertigo.datamodel.structure.stereotype.ForeignKey(smartType = "STyId", label = "Chatbot", fkDefinition = "DtChatbot", cardinality = io.vertigo.core.lang.Cardinality.ONE )
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
	 * Association : AttachmentFileInfo.
	 * @return l'accesseur vers la propriété 'AttachmentFileInfo'
	 */
	public StoreVAccessor<io.vertigo.chatbot.commons.domain.AttachmentFileInfo> attachmentFileInfo() {
		return attFiIdAccessor;
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
