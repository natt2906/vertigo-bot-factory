package io.vertigo.chatbot.commons.domain;

import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.structure.model.Entity;
import io.vertigo.datamodel.structure.model.UID;
import io.vertigo.datamodel.structure.stereotype.Field;
import io.vertigo.datamodel.structure.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class AttachmentFileInfo implements Entity {
	private static final long serialVersionUID = 1L;

	private Long attFiId;
	private String fileName;
	private String mimeType;
	private Long length;
	private java.time.Instant lastModified;
	private String filePath;

	/** {@inheritDoc} */
	@Override
	public UID<AttachmentFileInfo> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Id'.
	 * @return Long attFiId <b>Obligatoire</b>
	 */
	@Field(smartType = "STyId", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Id")
	public Long getAttFiId() {
		return attFiId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Id'.
	 * @param attFiId Long <b>Obligatoire</b>
	 */
	public void setAttFiId(final Long attFiId) {
		this.attFiId = attFiId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Name'.
	 * @return String fileName <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Name")
	public String getFileName() {
		return fileName;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Name'.
	 * @param fileName String <b>Obligatoire</b>
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'MimeType'.
	 * @return String mimeType <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "MimeType")
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'MimeType'.
	 * @param mimeType String <b>Obligatoire</b>
	 */
	public void setMimeType(final String mimeType) {
		this.mimeType = mimeType;
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
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Modification Date'.
	 * @return Instant lastModified <b>Obligatoire</b>
	 */
	@Field(smartType = "STyInstant", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Modification Date")
	public java.time.Instant getLastModified() {
		return lastModified;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Modification Date'.
	 * @param lastModified Instant <b>Obligatoire</b>
	 */
	public void setLastModified(final java.time.Instant lastModified) {
		this.lastModified = lastModified;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'path'.
	 * @return String filePath
	 */
	@Field(smartType = "STyFilePath", label = "path")
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'path'.
	 * @param filePath String
	 */
	public void setFilePath(final String filePath) {
		this.filePath = filePath;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
