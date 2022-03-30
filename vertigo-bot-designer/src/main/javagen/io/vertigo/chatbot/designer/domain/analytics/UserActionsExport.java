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
public final class UserActionsExport implements DtObject {
	private static final long serialVersionUID = 1L;

	private java.time.Instant date;
	private Double count;
	
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
	 * Récupère la valeur de la propriété 'Count'.
	 * @return Double count
	 */
	@Field(smartType = "STyDouble", label = "Count")
	public Double getCount() {
		return count;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Count'.
	 * @param count Double
	 */
	public void setCount(final Double count) {
		this.count = count;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
