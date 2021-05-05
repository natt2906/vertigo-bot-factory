-- ============================================================
--   Table : KIND_TOPIC                                        
-- ============================================================
create table KIND_TOPIC
(
    KTO_CD      	 VARCHAR(100)	not null,
    LABEL       	 VARCHAR(100)	not null,
    default_english  TEXT			not null,
    default_french 	 TEXT			not null,
    constraint PK_KIND_TOPIC primary key (KTO_CD)
);

comment on column KIND_TOPIC.KTO_CD is
'ID';

comment on column KIND_TOPIC.LABEL is
'Title';

-- ============================================================
--   Insert MasterData values : KIND_TOPIC                                        
-- ============================================================
insert into KIND_TOPIC(KTO_CD, LABEL, DEFAULT_ENGLISH, DEFAULT_FRENCH) values ('START', 'Start', 'Hello !', 'Bonjour !');
insert into KIND_TOPIC(KTO_CD, LABEL, DEFAULT_ENGLISH, DEFAULT_FRENCH) values ('END', 'End', 'Bye !', 'Au revoir !');
insert into KIND_TOPIC(KTO_CD, LABEL, DEFAULT_ENGLISH, DEFAULT_FRENCH) values ('FAILURE', 'Failure', 'Sorry, I don''t understand.', 'Désolé, je n''ai pas compris.');
insert into KIND_TOPIC(KTO_CD, LABEL, DEFAULT_ENGLISH, DEFAULT_FRENCH) values ('NORMAL', 'Normal', 'Text', 'Texte');

ALTER TABLE TOPIC
ADD COLUMN KTO_CD VARCHAR(100) ;

-- Add constraint 
alter table TOPIC
	add constraint FK_A_TOPIC_KIND_TOPIC foreign key (KTO_CD)
	references KIND_TOPIC (KTO_CD);
	
create index A_TOPIC_TO_KIND_TOPIC_FK on TOPIC (TTO_CD asc);

UPDATE TOPIC SET KTO_CD = 'NORMAL';

ALTER TABLE TOPIC
ALTER COLUMN KTO_CD SET NOT NULL;

ALTER TABLE TOPIC_CATEGORY
ADD COLUMN IS_TECHNICAL BOOLEAN;


CREATE OR REPLACE FUNCTION reprise_basic_message()  RETURNS void AS $$
DECLARE
  bot chatbot;
  topCatId NUMERIC;
  topId NUMERIC;
  smtId NUMERIC;
  uttWelcome utter_text;
  uttDefault utter_text;
BEGIN
	FOR bot IN (select * from chatbot) 
	LOOP
		SELECT * from utter_text where utt_id = bot.utt_id_welcome INTO uttWelcome;
		SELECT * from utter_text where utt_id = bot.utt_id_default INTO uttDefault;
		
		SELECT nextval('SEQ_TOPIC_CATEGORY') INTO topCatId;
		INSERT INTO topic_category (top_cat_id, label, level, is_enabled, bot_id, is_technical)
		VALUES (topCatId, 'Basics', 1, true, bot.bot_id, true);
		
		SELECT nextval('SEQ_TOPIC') INTO topId;
		SELECT nextval('SEQ_SMALL_TALK') INTO smtId;
		
		INSERT INTO TOPIC (top_id, title, description, is_enabled, bot_id, top_cat_id, tto_cd, kto_cd)
		values (topId, 'Failure', 'Default failure response', true, bot.bot_id, topCatId, 'SMALLTALK', 'FAILURE');
		
		INSERT INTO small_talk (smt_id, rty_id, top_id) VALUES (smtId, 'RICHTEXT', topId);
		UPDATE utter_text set smt_id = smtId where utt_id = utterTextDefault.utt_id;
		
		
		SELECT nextval('SEQ_TOPIC') INTO topId;
		SELECT nextval('SEQ_SMALL_TALK') INTO smtId;
		
		INSERT INTO TOPIC (top_id, title, description, is_enabled, bot_id, top_cat_id, tto_cd, kto_cd)
		values (topId, 'Start', 'Default start response', true, bot.bot_id, topCatId, 'SMALLTALK', 'START');
		
		INSERT INTO small_talk (smt_id, rty_id, top_id) VALUES (smtId, 'RICHTEXT', topId);
		UPDATE utter_text set smt_id = smtId where utt_id = utterTextWelcome.utt_id;
		
		SELECT nextval('SEQ_TOPIC') INTO topId;
		SELECT nextval('SEQ_SMALL_TALK') INTO smtId;
		
		INSERT INTO TOPIC (top_id, title, description, is_enabled, bot_id, top_cat_id, tto_cd, kto_cd)
		values (topId, 'End', 'Default end response', true, bot.bot_id, topCatId, 'SMALLTALK', 'END');
		
		INSERT INTO small_talk (smt_id, rty_id, top_id) VALUES (smtId, 'RICHTEXT', topId);
		INSERT INTO utter_text (utt_id, text, smt_id) VALUES (nextval('SEQ_UTTER_TEXT'), 'Bye !', smtId);
		
		
	END LOOP;
END;
$$
LANGUAGE plpgsql;

SELECT reprise_basic_message();

ALTER TABLE CHATBOT
DROP COLUMN UTT_ID_WELCOME,
DROP COLUMN UTT_ID_DEFAULT;

ALTER TABLE RESPONSE_BUTTON
DROP COLUMN BOT_ID_WELCOME,
DROP COLUMN BOT_ID_DEFAULT;



