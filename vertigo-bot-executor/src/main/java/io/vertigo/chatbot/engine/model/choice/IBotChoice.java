package io.vertigo.chatbot.engine.model.choice;

/**
 * Choice in the most generic sense.<br>
 * This class can be used as a parent class for all types of user choices, for example a button or a carrousel.<br>
 * Implementers must declare a method '<i>public static IBotChoice of(final String[] params)</i>'
 *
 * @author skerdudou
 */
public interface IBotChoice {

	/**
	 * @return the label
	 */
	String getLabel();

	/**
	 * @return the payload
	 */
	String getPayload();
}
