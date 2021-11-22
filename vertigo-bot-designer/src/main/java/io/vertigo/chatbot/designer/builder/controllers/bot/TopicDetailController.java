package io.vertigo.chatbot.designer.builder.controllers.bot;

import io.vertigo.account.authorization.annotations.Secured;
import io.vertigo.chatbot.commons.ChatbotUtils;
import io.vertigo.chatbot.commons.domain.Chatbot;
import io.vertigo.chatbot.commons.domain.topic.*;
import io.vertigo.chatbot.commons.multilingual.topics.TopicsMultilingualResources;
import io.vertigo.chatbot.designer.builder.model.topic.SaveTopicObject;
import io.vertigo.chatbot.designer.builder.services.ResponsesButtonServices;
import io.vertigo.chatbot.designer.builder.services.UtterTextServices;
import io.vertigo.chatbot.designer.builder.services.topic.*;
import io.vertigo.chatbot.designer.domain.commons.BotPredefinedTopic;
import io.vertigo.chatbot.designer.utils.StringUtils;
import io.vertigo.chatbot.domain.DtDefinitions;
import io.vertigo.core.lang.Assertion;
import io.vertigo.core.lang.VUserException;
import io.vertigo.core.locale.MessageText;
import io.vertigo.core.util.StringUtil;
import io.vertigo.datamodel.structure.definitions.DtField;
import io.vertigo.datamodel.structure.model.DtList;
import io.vertigo.datamodel.structure.model.DtObject;
import io.vertigo.ui.core.ViewContext;
import io.vertigo.ui.core.ViewContextKey;
import io.vertigo.ui.impl.springmvc.argumentresolvers.ViewAttribute;
import io.vertigo.vega.webservice.stereotype.Validate;
import io.vertigo.vega.webservice.validation.AbstractDtObjectValidator;
import io.vertigo.vega.webservice.validation.DtObjectErrors;
import io.vertigo.vega.webservice.validation.UiMessageStack;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bot/{botId}/topics/detail")
@Secured("BotUser")
public class TopicDetailController extends AbstractBotCreationController<Topic> {

    protected static final ViewContextKey<Topic> topicKey = ViewContextKey.of("topic");
    protected static final ViewContextKey<Topic> topicListKey = ViewContextKey.of("topicList");

    protected static final ViewContextKey<TopicCategory> topicCategoryKey = ViewContextKey.of("topicCategory");
    protected static final ViewContextKey<TopicCategory> topicCategoryListKey = ViewContextKey.of("topicCategoryList");

    protected static final ViewContextKey<String> newNluTrainingSentenceKey = ViewContextKey.of("newNluTrainingSentence");
    protected static final ViewContextKey<NluTrainingSentence> nluTrainingSentencesKey = ViewContextKey
            .of("nluTrainingSentences");
    protected static final ViewContextKey<NluTrainingSentence> nluTrainingSentencesToDeleteKey = ViewContextKey
            .of("nluTrainingSentencesToDelete");

    // Label
    protected static final ViewContextKey<TopicLabel> topicLabelListKey = ViewContextKey
            .of("topicLabelList");
    protected static final ViewContextKey<TopicLabel> initialTopicLabelListKey = ViewContextKey
            .of("initialTopicLabelList");
    protected static final ViewContextKey<TopicLabel> allTopicLabelListKey = ViewContextKey
            .of("allTopicLabelList");

    @Inject
    protected TopicServices topicServices;

    @Inject
    protected TopicCategoryServices topicCategoryServices;

    @Inject
    protected NluTrainingSentenceServices nluTrainingSentenceServices;

    @Inject
    protected TopicLabelServices topicLabelServices;

    @Inject
    private ScriptIntentionServices scriptIntentionServices;

    @Inject
    private SmallTalkServices smallTalkServices;

    private static final ViewContextKey<SmallTalk> smallTalkKey = ViewContextKey.of("smallTalk");

    private static final ViewContextKey<ResponseType> responseTypeKey = ViewContextKey.of("responseTypes");

    private static final ViewContextKey<UtterText> utterTextsKey = ViewContextKey.of("utterTexts");

    private static final ViewContextKey<ResponseButton> buttonsKey = ViewContextKey.of("buttons");

    private static final ViewContextKey<ScriptIntention> scriptIntentionKey = ViewContextKey.of("scriptIntention");

    private static final ViewContextKey<TypeTopic> typeTopicListKey = ViewContextKey.of("typeTopicList");

    @Inject
    private UtterTextServices utterTextServices;

    @Inject
    private ResponsesButtonServices responsesButtonServices;

    @Inject
    private TypeTopicServices typeTopicServices;

    @GetMapping("/{topId}")
    public void initContext(final ViewContext viewContext, final UiMessageStack uiMessageStack, @PathVariable("botId") final Long botId,
                            @PathVariable("topId") final Long topId) {

        final Chatbot bot = initCommonContext(viewContext, uiMessageStack, botId);
        final Topic topic = topicServices.findTopicById(topId);
        initTopicContext(viewContext, uiMessageStack, bot, topic);
        if (TypeTopicEnum.SCRIPTINTENTION.name().equals(topic.getTtoCd())) {
            final ScriptIntention scriptIntention = scriptIntentionServices.findByTopId(topId).orElseThrow();
            addMessageDeactivate(uiMessageStack, scriptIntention, viewContext.readDtListModifiable(nluTrainingSentencesKey, uiMessageStack), bot);
            viewContext.publishDto(scriptIntentionKey, scriptIntention);
            viewContext.publishDto(smallTalkKey, smallTalkServices.getNewSmallTalk(bot));
            viewContext.publishDtListModifiable(buttonsKey, new DtList<>(ResponseButton.class));
            final DtList<UtterText> utterTextList =  new DtList<>(UtterText.class);
            utterTextList.add(new UtterText());
            viewContext.publishDtListModifiable(utterTextsKey, utterTextList);
        } else {
            final SmallTalk smallTalk = smallTalkServices.findByTopId(topId).orElseThrow();
            addMessageDeactivate(uiMessageStack, smallTalk, viewContext.readDtListModifiable(nluTrainingSentencesKey, uiMessageStack), bot);
            viewContext.publishDto(smallTalkKey, smallTalk);
            viewContext.publishDtListModifiable(buttonsKey, responsesButtonServices.getButtonsBySmalltalk(bot, smallTalk.getSmtId()));
            final DtList<UtterText> utterTextList = utterTextServices.getUtterTextList(bot, smallTalk);
            utterTextList.add(new UtterText()); // add the next for random, or the 1st for rich text if 0 lines
            viewContext.publishDtListModifiable(utterTextsKey, utterTextList);
            viewContext.publishDto(scriptIntentionKey, scriptIntentionServices.getNewScriptIntention(bot));
        }

        viewContext.publishMdl(responseTypeKey, ResponseType.class, null);
        viewContext.publishDtList(typeTopicListKey, typeTopicServices.getAllTypeTopic());

        super.initBreadCrums(viewContext, topic);
        toModeReadOnly();
    }

    @GetMapping("/new")
    public void initContext(final ViewContext viewContext, final UiMessageStack uiMessageStack, @PathVariable("botId") final Long botId) {

        final Chatbot bot = initCommonContext(viewContext, uiMessageStack, botId);

        initContextNewTopic(viewContext, bot);

        viewContext.publishDto(smallTalkKey, smallTalkServices.getNewSmallTalk(bot));
        viewContext.publishDto(scriptIntentionKey, scriptIntentionServices.getNewScriptIntention(bot));
        viewContext.publishMdl(responseTypeKey, ResponseType.class, null);

        final DtList<UtterText> utterTextList = new DtList<>(UtterText.class);
        utterTextList.add(new UtterText()); // add the first one for initialization, list can't be empty
        viewContext.publishDtListModifiable(utterTextsKey, utterTextList);
        viewContext.publishDtList(typeTopicListKey, typeTopicServices.getAllTypeTopic());

        viewContext.publishDtListModifiable(buttonsKey, new DtList<>(ResponseButton.class));
        super.initEmptyBreadcrums(viewContext);
        toModeCreate();
    }

    private void initTopicContext(final ViewContext viewContext, final UiMessageStack uiMessageStack, final Chatbot bot, final Topic topic) {
        Assertion.check().isTrue(topic.getBotId().equals(bot.getBotId()), "Incoherent parameters");

        viewContext.publishDtList(topicListKey, topicServices.getAllTopicByBot(bot));
        viewContext.publishDto(topicKey, topic);

        viewContext.publishRef(newNluTrainingSentenceKey, "");
        final DtList<NluTrainingSentence> nluSentences = topicServices.getNluTrainingSentenceByTopic(bot, topic);
        viewContext.publishDtListModifiable(nluTrainingSentencesKey, nluSentences);
        viewContext.publishDtList(nluTrainingSentencesToDeleteKey,
                new DtList<NluTrainingSentence>(NluTrainingSentence.class));

        viewContext.publishDto(topicCategoryKey, topicCategoryServices.getTopicCategoryById(bot, topic.getTopCatId()));
        viewContext.publishDtList(topicCategoryListKey, topicCategoryServices.getAllActiveCategoriesByBot(bot));

        //Label
        final DtList<TopicLabel> initialList = this.topicLabelServices.getTopicLabelByBotIdAndTopId(bot, topic.getTopId());
        viewContext.publishDtList(initialTopicLabelListKey, initialList);
        viewContext.publishDtListModifiable(topicLabelListKey, new DtList<>(TopicLabel.class));
        viewContext.publishDtList(allTopicLabelListKey, this.topicLabelServices.getTopicLabelByBotId(bot));
    }

    public void initContextNewTopic(final ViewContext viewContext, final Chatbot bot) {
        viewContext.publishDtList(topicListKey, topicServices.getAllTopicByBot(bot));
        viewContext.publishDto(topicKey, topicServices.getNewTopic(bot));

        viewContext.publishRef(newNluTrainingSentenceKey, "");
        viewContext.publishDtListModifiable(nluTrainingSentencesKey,
                new DtList<NluTrainingSentence>(NluTrainingSentence.class));
        viewContext.publishDtList(nluTrainingSentencesToDeleteKey,
                new DtList<NluTrainingSentence>(NluTrainingSentence.class));

        viewContext.publishDto(topicCategoryKey, new TopicCategory());
        viewContext.publishDtList(topicCategoryListKey, topicCategoryServices.getAllActiveCategoriesByBot(bot));

        //Labels
        viewContext.publishDtList(initialTopicLabelListKey, new DtList<>(TopicLabel.class));
        viewContext.publishDtListModifiable(topicLabelListKey, new DtList<>(TopicLabel.class));
        viewContext.publishDtList(allTopicLabelListKey, this.topicLabelServices.getTopicLabelByBotId(bot));

    }

    private void addMessageDeactivate(final UiMessageStack uiMessageStack, final ScriptIntention scriptIntention, final DtList<NluTrainingSentence> sentences, final Chatbot chatbot) {
        final boolean hasToBeDeactivate = scriptIntentionServices.hasToBeDeactivated(scriptIntention, chatbot);
        if (hasToBeDeactivate || sentences.isEmpty()) {
            uiMessageStack.info(scriptIntentionServices.getDeactivateMessage());
        }
    }

    private void addMessageDeactivate(final UiMessageStack uiMessageStack, final SmallTalk smallTalk, final DtList<NluTrainingSentence> sentences, final Chatbot chatbot) {
        final boolean hasToBeDeactivate = smallTalkServices.hasToBeDeactivated(smallTalk, chatbot);
        if (hasToBeDeactivate || sentences.isEmpty()) {
            uiMessageStack.info(smallTalkServices.getDeactivateMessage());
        }
    }

    @Override
    protected String getBreadCrums(final Topic object) {
        return object.getTitle();
    }

    @PostMapping("/_edit")
    public void doEdit() {
        toModeEdit();
    }

    @PostMapping("/_save")
    public String doSave(final ViewContext viewContext, final UiMessageStack uiMessageStack,
                         @ViewAttribute("smallTalk") final SmallTalk smallTalk,
                         @ViewAttribute("scriptIntention") final ScriptIntention scriptIntention,
                         @ViewAttribute("topic") @Validate(TopicCategoryNotEmptyValidator.class) final Topic topic,
                         @ViewAttribute("bot") final Chatbot chatbot,
                         @ViewAttribute("newNluTrainingSentence") final String newNluTrainingSentence,
                         @ViewAttribute("nluTrainingSentences") final DtList<NluTrainingSentence> nluTrainingSentences,
                         @ViewAttribute("nluTrainingSentencesToDelete") final DtList<NluTrainingSentence> nluTrainingSentencesToDelete,
                         @ViewAttribute("topicLabelList") final DtList<TopicLabel> labels,
                         @ViewAttribute("initialTopicLabelList") final DtList<TopicLabel> initialLabels) {

        final Long botId = chatbot.getBotId();
        DtList<UtterText> utterTexts = new DtList<>(UtterText.class);
        DtList<ResponseButton> buttonList = new DtList<>(ResponseButton.class);
        DtObject dtObject = scriptIntention;
        if (TypeTopicEnum.SMALLTALK.name().equals(topic.getTtoCd())) {
            utterTexts = ChatbotUtils.getRawDtList(viewContext.getUiListModifiable(utterTextsKey),
                    uiMessageStack);
            buttonList = ChatbotUtils.getRawDtList(viewContext.getUiListModifiable(buttonsKey),
                    uiMessageStack);
            dtObject = smallTalk;
        }

        saveTopic(topic, chatbot, dtObject, buttonList, utterTexts, nluTrainingSentences, newNluTrainingSentence, nluTrainingSentencesToDelete, labels,
                initialLabels);
        return "redirect:/bot/" + botId + "/topics/detail/" + topic.getTopId();
    }

    private void saveTopic(final Topic topic,
                          final Chatbot chatbot,
                          final DtObject dtObject,
                          final DtList<ResponseButton> buttonList,
                          final DtList<UtterText> utterTexts,
                          final DtList<NluTrainingSentence> nluTrainingSentences,
                          final String newNluTrainingSentence,
                          final DtList<NluTrainingSentence> nluTrainingSentencesToDelete,
                          final DtList<TopicLabel> labels,
                          final DtList<TopicLabel> initialLabels) {
        topicServices.saveTopic(topic, chatbot, newNluTrainingSentence, nluTrainingSentences, nluTrainingSentencesToDelete, dtObject, buttonList, utterTexts, labels, initialLabels);

    }

    public static final class TopicCategoryNotEmptyValidator extends AbstractDtObjectValidator<Topic> {

        /** {@inheritDoc} */
        @Override
        protected void checkMonoFieldConstraints(final Topic topic, final DtField dtField, final DtObjectErrors dtObjectErrors) {
            if (DtDefinitions.TopicFields.topCatId.name().equals(dtField.getName())) {
                final Long value = (Long) dtField.getDataAccessor().getValue(topic);
                if (value == null) {
                    dtObjectErrors.addError(dtField.getName(), MessageText.of("Le champ doit être renseigné")); // TODO: use same i18n resource when avaiable in DefaultDtObjectValidator
                }
            }
        }
    }


    @PostMapping("/_addTrainingSentence")
    public ViewContext doAddTrainingSentence(final ViewContext viewContext,
                                             @ViewAttribute("newNluTrainingSentence") final String newNluTrainingSentenceIn,
                                             @ViewAttribute("nluTrainingSentences") final DtList<NluTrainingSentence> nluTrainingSentences) {

        nluTrainingSentenceServices.addTrainingSentense(newNluTrainingSentenceIn, nluTrainingSentences);

        viewContext.publishDtListModifiable(nluTrainingSentencesKey, nluTrainingSentences);
        viewContext.publishRef(newNluTrainingSentenceKey, "");

        return viewContext;
    }

    @PostMapping("/_editTrainingSentence")
    public ViewContext doEditTrainingSentence(final ViewContext viewContext, @RequestParam("index") final int index,
                                              @ViewAttribute("newNluTrainingSentence") final String newNluTrainingSentence,
                                              @ViewAttribute("nluTrainingSentences") final DtList<NluTrainingSentence> nluTrainingSentences) {

        if (StringUtil.isBlank(newNluTrainingSentence)) {
            // empty edit, rollback modification
            viewContext.markModifiedKeys(nluTrainingSentencesKey);
            return viewContext;
        }

        int curIdx = 0;
        for (final NluTrainingSentence nts : nluTrainingSentences) {
            if (curIdx == index) {
                nts.setText(newNluTrainingSentence);
            } else if (newNluTrainingSentence.equalsIgnoreCase(nts.getText())) {
                throw new VUserException(TopicsMultilingualResources.NLU_ALREADY_EXISTS);
            }
            curIdx++;
        }

        viewContext.publishDtListModifiable(nluTrainingSentencesKey, nluTrainingSentences);

        return viewContext;
    }

    @PostMapping("/_removeTrainingSentence")
    public ViewContext doRemoveTrainingSentence(final ViewContext viewContext, @RequestParam("index") final int index,
                                                @ViewAttribute("nluTrainingSentencesToDelete") final DtList<NluTrainingSentence> nluTrainingSentencesToDelete,
                                                @ViewAttribute("nluTrainingSentences") final DtList<NluTrainingSentence> nluTrainingSentences) {

        // remove from list
        final NluTrainingSentence removed = nluTrainingSentences.remove(index);
        viewContext.publishDtListModifiable(nluTrainingSentencesKey, nluTrainingSentences);

        // keep track of deleted persisted NluTrainingSentence
        if (removed.getNtsId() != null) {
            nluTrainingSentencesToDelete.add(removed);
        }
        viewContext.publishDtList(nluTrainingSentencesToDeleteKey, nluTrainingSentencesToDelete);

        return viewContext;
    }

    @PostMapping("/_delete")
    String doDelete(@ViewAttribute("bot") final Chatbot chatbot, @ViewAttribute("topic") final Topic topic) {
        final DtList<Topic> listTopicRef = topicServices.getTopicReferencingTopId(topic.getTopId());
        if (!listTopicRef.isEmpty()) {
            final String topicErrorList = listTopicRef.stream()
                    .map(Topic::getTitle)
                    .collect(Collectors.joining(", "));

            throw new VUserException(TopicsMultilingualResources.DELETION_REF_ERROR, topicErrorList);
        }
        topicServices.deleteTopic(topic, chatbot);

        return "redirect:/bot/" + topic.getBotId() + "/topics/";

    }
}
