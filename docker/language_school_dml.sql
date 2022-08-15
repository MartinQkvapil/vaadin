insert into db.teaching_lang (id_teaching_lang, code, name)
values  (2, 'es', 'španělština'),
        (3, 'de', 'němčina');

ALTER TABLE test_to_question
    ADD CONSTRAINT duplicae_question_in_test UNIQUE (id_test, id_question);