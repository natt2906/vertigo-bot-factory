package io.vertigo.chatbot.domain;

import java.util.Arrays;
import java.util.Iterator;

import io.vertigo.dynamo.domain.metamodel.DtFieldName;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class DtDefinitions implements Iterable<Class<?>> {

	/**
	 * Enumération des DtDefinitions.
	 */
	public enum Definitions {
		/** Objet de données Action. */
		Action(io.vertigo.chatbot.commons.domain.Action.class),
		/** Objet de données ExecutorState. */
		ExecutorState(io.vertigo.chatbot.commons.domain.ExecutorState.class),
		/** Objet de données Intent. */
		Intent(io.vertigo.chatbot.commons.domain.Intent.class),
		/** Objet de données IntentTrainingSentence. */
		IntentTrainingSentence(io.vertigo.chatbot.commons.domain.IntentTrainingSentence.class),
		/** Objet de données RasaConfig. */
		RasaConfig(io.vertigo.chatbot.executor.domain.RasaConfig.class),
		/** Objet de données SmallTalkExport. */
		SmallTalkExport(io.vertigo.chatbot.commons.domain.SmallTalkExport.class),
		/** Objet de données UtterText. */
		UtterText(io.vertigo.chatbot.commons.domain.UtterText.class)		;

		private final Class<?> clazz;

		private Definitions(final Class<?> clazz) {
			this.clazz = clazz;
		}

		/** 
		 * Classe associée.
		 * @return Class d'implémentation de l'objet 
		 */
		public Class<?> getDtClass() {
			return clazz;
		}
	}

	/**
	 * Enumération des champs de Action.
	 */
	public enum ActionFields implements DtFieldName<io.vertigo.chatbot.commons.domain.Action> {
		/** Propriété 'ID'. */
		actId,
		/** Propriété 'Text'. */
		title	}

	/**
	 * Enumération des champs de ExecutorState.
	 */
	public enum ExecutorStateFields implements DtFieldName<io.vertigo.chatbot.commons.domain.ExecutorState> {
		/** Propriété 'Nom'. */
		name,
		/** Propriété 'Entrainement en cours'. */
		trainingInProgress,
		/** Propriété 'Version du modèle'. */
		loadedModelVersion,
		/** Propriété 'Log d'entrainement'. */
		latestTrainingLog	}

	/**
	 * Enumération des champs de Intent.
	 */
	public enum IntentFields implements DtFieldName<io.vertigo.chatbot.commons.domain.Intent> {
		/** Propriété 'ID'. */
		intId,
		/** Propriété 'Titre'. */
		title,
		/** Propriété 'SmallTalk'. */
		isSmallTalk	}

	/**
	 * Enumération des champs de IntentTrainingSentence.
	 */
	public enum IntentTrainingSentenceFields implements DtFieldName<io.vertigo.chatbot.commons.domain.IntentTrainingSentence> {
		/** Propriété 'ID'. */
		itsId,
		/** Propriété 'Text'. */
		text,
		/** Propriété 'Intent'. */
		intId	}

	/**
	 * Enumération des champs de RasaConfig.
	 */
	public enum RasaConfigFields implements DtFieldName<io.vertigo.chatbot.executor.domain.RasaConfig> {
		/** Propriété 'domain'. */
		domain,
		/** Propriété 'stories'. */
		stories,
		/** Propriété 'nlu'. */
		nlu	}

	/**
	 * Enumération des champs de SmallTalkExport.
	 */
	public enum SmallTalkExportFields implements DtFieldName<io.vertigo.chatbot.commons.domain.SmallTalkExport> {
		/** Propriété 'intent'. */
		intent,
		/** Propriété 'intentTrainingSentences'. */
		intentTrainingSentences,
		/** Propriété 'utterTexts'. */
		utterTexts	}

	/**
	 * Enumération des champs de UtterText.
	 */
	public enum UtterTextFields implements DtFieldName<io.vertigo.chatbot.commons.domain.UtterText> {
		/** Propriété 'ID'. */
		utxId,
		/** Propriété 'Text'. */
		text,
		/** Propriété 'Intent'. */
		intId,
		/** Propriété 'Action'. */
		actId	}

	/** {@inheritDoc} */
	@Override
	public Iterator<Class<?>> iterator() {
		return new Iterator<Class<?>>() {
			private Iterator<Definitions> it = Arrays.asList(Definitions.values()).iterator();

			/** {@inheritDoc} */
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			/** {@inheritDoc} */
			@Override
			public Class<?> next() {
				return it.next().getDtClass();
			}
		};
	}
}
