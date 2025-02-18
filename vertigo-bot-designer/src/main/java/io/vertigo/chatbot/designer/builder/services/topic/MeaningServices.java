package io.vertigo.chatbot.designer.builder.services.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.inject.Inject;

import io.vertigo.account.authorization.annotations.SecuredOperation;
import io.vertigo.chatbot.commons.domain.Chatbot;
import io.vertigo.chatbot.designer.builder.meaning.MeaningPAO;
import io.vertigo.chatbot.designer.dao.MeaningDAO;
import io.vertigo.chatbot.designer.domain.Meaning;
import io.vertigo.chatbot.designer.domain.Synonym;
import io.vertigo.chatbot.designer.domain.TupleSynonymIhm;
import io.vertigo.commons.transaction.Transactional;
import io.vertigo.core.node.component.Component;
import io.vertigo.core.util.StringUtil;
import io.vertigo.datamodel.structure.model.DtList;
import io.vertigo.datamodel.structure.util.VCollectors;

@Transactional
public class MeaningServices implements Component {

	@Inject
	private MeaningDAO meaningDAO;

	@Inject
	private MeaningPAO meaningPAO;

	@Inject
	private SynonymServices synonymServices;

	/**
	 * get Meaning by id
	 *
	 * @param id
	 * @return meaning
	 */
	public Meaning findMeaningById(final Long id) {
		return meaningDAO.get(id);
	}

	/**
	 * Save meaning
	 *
	 * @param meaning
	 * @return meaning
	 */
	public Meaning save(@SecuredOperation("botAdm") final Chatbot chatbot, final Meaning meaning) {
		return meaningDAO.save(meaning);
	}

	/**
	 * Save meaning with synonyms
	 *
	 * @param meaning
	 * @param synonyms
	 * @param synonymsToDelete
	 * @return meaning
	 */
	public Meaning save(@SecuredOperation("botAdm") final Chatbot chatbot, final Meaning meaning,
			final DtList<Synonym> synonyms,
			final DtList<Synonym> synonymsToDelete) {

		saveAllNotBlankSynonym(meaning, synonyms);
		synonymServices.removeSynonym(synonymsToDelete);

		return meaningDAO.save(meaning);
	}

	/**
	 * delete meaning
	 *
	 * @param bot
	 * @param meaId
	 */
	public void deleteMeaning(@SecuredOperation("botAdm") final Chatbot bot, final Long meaId) {
		meaningDAO.delete(meaId);
	}

	/**
	 * Save synonyms
	 *
	 * @param meaning
	 * @param synonyms
	 * @return list of synonyms
	 */
	protected DtList<Synonym> saveAllNotBlankSynonym(final Meaning meaning, final DtList<Synonym> synonyms) {

		final Long meaId = meaning.getMeaId();
		final Long botId = meaning.getBotId();
		return synonyms.stream()
				.filter(syn -> !StringUtil.isBlank(syn.getLabel()))
				.map(syn -> createSynonym(syn, meaId, botId))
				.collect(VCollectors.toDtList(Synonym.class));
	}

	/**
	 * create Synonym
	 *
	 * @param syn
	 * @param meaId
	 * @param botId
	 * @return synonym
	 */
	protected Synonym createSynonym(final Synonym syn, final Long meaId, final Long botId) {
		syn.setMeaId(meaId);
		syn.setBotId(botId);
		return synonymServices.save(syn);
	}

	/**
	 * get meaning by label and botId
	 *
	 * @param label
	 * @param botId
	 * @return meaning
	 */
	public Meaning findMeaningBySynonymLabelAndBotId(final String label, final Long botId) {
		final Optional<Meaning> result = meaningDAO.getMeaningBySynonymLabelAndBotId(botId, label);
		return result.isPresent() ? result.get() : null;
	}

	/*
	 * Recursive method to generate nlu with synonyms from a list of sentence
	 * For a meaning, a sentence is generated from each sentence and each synonym,
	 * and then it is used to generate more sentences with the next group of synonyms.
	 *
	 * Example : original sentence T W1 T' W2 T''
	 * W1 has as a meaning M1, with synonyms S1 and S'1 (S1 == W1)
	 * W2 has as a meaning M2, with synonyms S2 and S'2 (S2 == W2)
	 * T, T' and T'' are strings with no meaning found
	 *
	 * There are 2 differents meaning in the sentence, so there will be two calls to the method combine.
	 * So first, the folowing sentence will be generated : 	T S1  T' W2 T''
	 * 														T S'1 T' W2 T''
	 *
	 * and then on the second loop, same work done but on the two sentences we just generated, and with the second meaning :
	 * 														T S1  T' S2  T''
	 * 														T S1  T' S'2 T''
	 * 														T S'1 T' S2  T''
	 * 														T S'1 T' S'2 T''
	 *
	 *
	 */
	public ArrayList<String> combine(final Map<String, List<TupleSynonymIhm>> listTupleSynonymIhmPerWord, final String nluOriginal) {

		final ArrayList<String> listText = new ArrayList<String>();
		listText.add(nluOriginal);

		// loop on words with synonyms
		for (final Entry<String, List<TupleSynonymIhm>> entry : listTupleSynonymIhmPerWord.entrySet()) {

			final List<TupleSynonymIhm> listTupleSynonyms = entry.getValue();

			// The size of the listEntry is saved because listEntry is modified in the loops
			final int size = listText.size();
			// loop on text entries (sentences already generated)
			for (int j = 0; j < size; j++) {
				// loop on synonyms
				for (final TupleSynonymIhm tupleSyn : listTupleSynonyms) {
					// the sentence is generated with the word replaced by a synonym
					listText.add(populateExit(tupleSyn, listText.get(j)));
				}
			}

		}

		return listText;
	}

	/**
	 * Modify entry by replacing occurence of a word with its synonyms
	 *
	 * @param synonym
	 * @param originalWord
	 * @param entry
	 * @return string
	 */
	private String populateExit(final TupleSynonymIhm tupleSyn, final String entry) {
		return entry.replaceAll(tupleSyn.getWord(), tupleSyn.getSynonymLabel());
	}

	/**
	 * Return a list tuple <word,synonym>
	 *
	 * @param words
	 * @param botId
	 * @return list of TupleSynonymIhm
	 */
	public DtList<TupleSynonymIhm> getTuplesSynonym(final List<String> words, final Long botId) {
		return meaningPAO.getTuplesSynonym(botId, words);
	}

}
