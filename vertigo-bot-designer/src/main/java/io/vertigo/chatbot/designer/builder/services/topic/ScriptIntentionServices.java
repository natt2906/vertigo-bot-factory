package io.vertigo.chatbot.designer.builder.services.topic;

import java.util.Optional;

import javax.inject.Inject;

import io.vertigo.account.authorization.annotations.Secured;
import io.vertigo.account.authorization.annotations.SecuredOperation;
import io.vertigo.chatbot.commons.dao.topic.ScriptIntentionDAO;
import io.vertigo.chatbot.commons.domain.Chatbot;
import io.vertigo.chatbot.commons.domain.topic.ScriptIntention;
import io.vertigo.chatbot.commons.domain.topic.ScriptIntentionIhm;
import io.vertigo.chatbot.commons.domain.topic.Topic;
import io.vertigo.chatbot.commons.domain.topic.TypeTopicEnum;
import io.vertigo.chatbot.commons.domain.topic.UtterText;
import io.vertigo.chatbot.designer.builder.scriptIntention.ScriptIntentionPAO;
import io.vertigo.chatbot.domain.DtDefinitions.ScriptIntentionFields;
import io.vertigo.commons.transaction.Transactional;
import io.vertigo.core.lang.Assertion;
import io.vertigo.core.node.component.Component;
import io.vertigo.datamodel.criteria.Criterions;
import io.vertigo.datamodel.structure.model.DtList;

@Transactional
@Secured("BotUser")
public class ScriptIntentionServices implements Component, TopicInterfaceServices<ScriptIntention> {

	@Inject
	private ScriptIntentionDAO scriptIntentionDAO;

	@Inject
	private ScriptIntentionPAO scriptIntentionPAO;

	public ScriptIntention getScriptIntentionById(@SecuredOperation("botVisitor") final Chatbot bot, final Long sinId) {
		Assertion.check().isNotNull(sinId);
		// ---
		return scriptIntentionDAO.get(sinId);
	}

	public ScriptIntention getNewScriptIntention(@SecuredOperation("botAdm") final Chatbot bot) {
		final ScriptIntention scriptIntention = new ScriptIntention();
		return scriptIntention;
	}

	public ScriptIntention save(@SecuredOperation("botAdm") final Chatbot chatbot,
			final ScriptIntention scriptIntention,
			final Topic topic) {

		scriptIntention.setTopId(topic.getTopId());
		final ScriptIntention savedSI = this.save(scriptIntention);

		return savedSI;
	}

	@Override
	public void delete(@SecuredOperation("botAdm") final Chatbot chatbot, final ScriptIntention scriptIntention, final Topic topic) {

		// delete scriptIntention
		delete(scriptIntention);

	}

	public void removeAllScriptIntentionFromBot(@SecuredOperation("botAdm") final Chatbot bot) {
		scriptIntentionPAO.removeAllScriptIntentionByBotId(bot.getBotId());
	}

	public DtList<ScriptIntention> getAllActiveScriptIntentionsByBot(@SecuredOperation("botAdm") final Chatbot bot) {
		return scriptIntentionDAO.getAllActiveScriptIntentionByBot(bot.getBotId());
	}

	public DtList<ScriptIntentionIhm> getScriptIntentionsIhmByBot(@SecuredOperation("botAdm") final Chatbot bot) {
		return scriptIntentionPAO.getScriptIntentionIHMByBot(bot.getBotId());
	}

	@Override
	public ScriptIntention save(final ScriptIntention scriptIntention) {
		return scriptIntentionDAO.save(scriptIntention);
	}

	@Override
	public void delete(final ScriptIntention scriptIntention) {
		scriptIntentionDAO.delete(scriptIntention.getUID());
	}

	@Override
	public boolean handleObject(final Topic topic) {
		return TypeTopicEnum.SCRIPTINTENTION.name().equals(topic.getTtoCd());
	}

	@Override
	public ScriptIntention findByTopId(final Long topId) {
		if (topId != null) {
			final Optional<ScriptIntention> result = scriptIntentionDAO.findOptional(Criterions.isEqualTo(ScriptIntentionFields.topId, topId));
			return result.isPresent() ? result.get() : null;
		}
		return null;
	}

	@Override
	public void initializeBasic(final Chatbot chatbot, final Topic topic, final String text) {
		final ScriptIntention sin = new ScriptIntention();
		sin.setTopId(topic.getTopId());
		sin.setScript(text);
		save(chatbot, sin, topic);
	}

	@Override
	public boolean isEnabled(final ScriptIntention object, final boolean isEnabled, final Chatbot bot) {
		return object.getScript() != null ? !object.getScript().isBlank() && isEnabled : false;
	}

	@Override
	public UtterText getBasicUtterTextByTopId(final Long topId) {
		Assertion.check()
				.isNotNull(topId);
		// ---
		final UtterText utt = new UtterText();
		final ScriptIntention sin = findByTopId(topId);
		utt.setText(sin != null ? sin.getScript() : null);
		return utt;

	}

}
