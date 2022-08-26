-- -----------------------------------------------------
-- Schema db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `db` ;

-- -----------------------------------------------------
-- Schema db
-- -----------------------------------------------------
SET NAMES 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';
CREATE SCHEMA IF NOT EXISTS `db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `db` ;



-- -----------------------------------------------------
-- Table `db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`user` ;

CREATE TABLE IF NOT EXISTS `db`.`user` (
                                           `id_user` INT NOT NULL AUTO_INCREMENT,
                                           `name` VARCHAR(45) NULL,
                                           `surname` VARCHAR(45) NULL,
                                           `email` VARCHAR(45) NOT NULL,
                                           `hashed_password` VARCHAR(100) NOT NULL,
                                           `role` VARCHAR(45) NULL,
                                           `username` VARCHAR(45) NULL,
                                           `icon` VARCHAR(100) NULL,
                                           PRIMARY KEY (`id_user`),
                                           UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db`.`teaching_lang`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`teaching_lang` ;

CREATE TABLE IF NOT EXISTS `db`.`teaching_lang` (
                                                    `id_teaching_lang` INT NOT NULL AUTO_INCREMENT,
                                                    `code` VARCHAR(3) NULL,
                                                    `name` VARCHAR(45) NULL,
                                                    PRIMARY KEY (`id_teaching_lang`),
                                                    UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db`.`course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`course` ;

CREATE TABLE IF NOT EXISTS `db`.`course` (
                                             `id_course` INT NOT NULL AUTO_INCREMENT,
                                             `points` INT NOT NULL,
                                             `native_lang` VARCHAR(20) NULL,
                                             `description` VARCHAR(45) NULL,
                                             `id_teaching_lang` INT NOT NULL,
                                             `id_user` INT NOT NULL,
                                             PRIMARY KEY (`id_course`),
                                             INDEX `fk_course_teaching_lang1_idx` (`id_teaching_lang` ASC) VISIBLE,
                                             INDEX `fk_course_user1_idx` (`id_user` ASC) VISIBLE,
                                             CONSTRAINT `fk_course_teaching_lang1`
                                                 FOREIGN KEY (`id_teaching_lang`)
                                                     REFERENCES `db`.`teaching_lang` (`id_teaching_lang`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION,
                                             CONSTRAINT `fk_course_user1`
                                                 FOREIGN KEY (`id_user`)
                                                     REFERENCES `db`.`user` (`id_user`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db`.`question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`question` ;

CREATE TABLE IF NOT EXISTS `db`.`question` (
                                               `id_question` INT NOT NULL AUTO_INCREMENT,
                                               `question` VARCHAR(1500) NOT NULL,
                                               `answ1` VARCHAR(250) NULL,
                                               `answ2` VARCHAR(250) NULL,
                                               `answ3` VARCHAR(250) NULL,
                                               `answ4` VARCHAR(250) NULL,
                                               `points` INT NULL DEFAULT 1,
                                               PRIMARY KEY (`id_question`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db`.`test`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`test` ;

CREATE TABLE IF NOT EXISTS `db`.`test` (
                                           `id_test` INT NOT NULL AUTO_INCREMENT,
                                           `question_count` INT NULL,
                                           `test_name` VARCHAR(250) NULL,
                                           `id_teaching_lang` INT NOT NULL,
                                           PRIMARY KEY (`id_test`, `id_teaching_lang`),
                                           INDEX `fk_test_teaching_lang1_idx` (`id_teaching_lang` ASC) VISIBLE,
                                           CONSTRAINT `fk_test_teaching_lang1`
                                               FOREIGN KEY (`id_teaching_lang`)
                                                   REFERENCES `db`.`teaching_lang` (`id_teaching_lang`)
                                                   ON DELETE NO ACTION
                                                   ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db`.`test_to_question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`test_to_question` ;

CREATE TABLE IF NOT EXISTS `db`.`test_to_question` (
                                                       `id_test_to_question` INT NOT NULL AUTO_INCREMENT,
                                                       `id_test` INT NOT NULL,
                                                       `id_question` INT NOT NULL,
                                                       `answ` VARCHAR(250) NULL,
                                                       PRIMARY KEY (`id_test_to_question`, `id_test`, `id_question`),
                                                       INDEX `fk_test_to_question_test1_idx` (`id_test` ASC) VISIBLE,
                                                       INDEX `fk_test_to_question_question1_idx` (`id_question` ASC) VISIBLE,
                                                       UNIQUE INDEX `id_test_to_question_UNIQUE` (`id_test_to_question` ASC) VISIBLE,
                                                       CONSTRAINT `fk_test_to_question_test1`
                                                           FOREIGN KEY (`id_test`)
                                                               REFERENCES `db`.`test` (`id_test`)
                                                               ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION,
                                                       CONSTRAINT `fk_test_to_question_question1`
                                                           FOREIGN KEY (`id_question`)
                                                               REFERENCES `db`.`question` (`id_question`)
                                                               ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db`.`course_to_test`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`course_to_test` ;

CREATE TABLE IF NOT EXISTS `db`.`course_to_test` (
                                                     `id_course_to_test` INT NOT NULL AUTO_INCREMENT,
                                                     `id_course` INT NOT NULL,
                                                     `id_test` INT NOT NULL,
                                                     `done` INT NULL DEFAULT 0,
                                                     `wrong_answers` INT NULL DEFAULT 0,
                                                     `correct_answers` INT NULL DEFAULT 0,
                                                     PRIMARY KEY (`id_course_to_test`, `id_course`, `id_test`),
                                                     INDEX `fk_course_to_test_course1_idx` (`id_course` ASC) VISIBLE,
                                                     INDEX `fk_course_to_test_test1_idx` (`id_test` ASC) VISIBLE,
                                                     CONSTRAINT `fk_course_to_test_course1`
                                                         FOREIGN KEY (`id_course`)
                                                             REFERENCES `db`.`course` (`id_course`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION,
                                                     CONSTRAINT `fk_course_to_test_test1`
                                                         FOREIGN KEY (`id_test`)
                                                             REFERENCES `db`.`test` (`id_test`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Data for table `db`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`user` (`id_user`, `name`, `surname`, `email`, `hashed_password`, `role`, `username`, `icon`) VALUES (1, 'Martin', 'Kvapil', 'm@gmail.com', '$2a$10$WceZ.eP/EXu9yRKDXjNscutDA39U30BtJ.Rre4nDrKT8u6MfaICHS', 'admin', 'admin', NULL);
INSERT INTO `db`.`user` (`id_user`, `name`, `surname`, `email`, `hashed_password`, `role`, `username`, `icon`) VALUES (2, 'Jiri', 'Kvapil', 'j@gmail.com', '$2a$10$YUMOP9zNmAkOkaKXXSVDAePGxW5rYG/RN/06nMWiO87objsmIFKYu', 'user', 'user', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`teaching_lang`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`teaching_lang` (`id_teaching_lang`, `code`, `name`) VALUES (1, 'en', 'english');
INSERT INTO `db`.`teaching_lang` (`id_teaching_lang`, `code`, `name`) VALUES (2, 'de', 'german');
INSERT INTO `db`.`teaching_lang` (`id_teaching_lang`, `code`, `name`) VALUES (3, 'es', 'spanish');
INSERT INTO `db`.`teaching_lang` (`id_teaching_lang`, `code`, `name`) VALUES (4, 'fr', 'french');

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`course`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`course` (`id_course`, `points`, `native_lang`, `description`, `id_teaching_lang`, `id_user`) VALUES (1, 0, 'cz', 'English course', 1, 2);
INSERT INTO `db`.`course` (`id_course`, `points`, `native_lang`, `description`, `id_teaching_lang`, `id_user`) VALUES (2, 0, 'cz', 'German course', 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`question`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (1, 'car', 'auto', 'kolo', 'letadlo', 'město', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (2, 'plane', 'letadlo', 'kolo', 'uživatel', 'kolo', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (3, 'bus', 'autobus', 'raketa', 'letadlo', 'auto', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (4, 'police', 'police', 'město', 'kolo', 'letadlo', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (5, 'wheel', 'kolo (od auta)', 'letadlo', 'letadlo', 'auto', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (6, 'bike', 'kolo', 'automobil', 'letadlo', 'auto', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (7, 'rocket', 'raketa', 'autobus', 'letadlo', 'auto', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (8, 'traffic jam', 'zácpa', 'růžová', 'modrá', 'auto', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (9, 'passanger', 'cestujicí', 'rajče', 'cibule', 'peach', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (10, 'jeep', 'džíp', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (11, 'tomato', 'rajče', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (12, 'onion', 'cibule', 'paprika', 'sdfa', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (13, 'peanut', 'arašíd', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (14, 'avocado', 'avokado', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (15, 'potato', 'brambora', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (16, 'celery', 'celer', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (17, 'beet root', 'řepa', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (18, 'lentils', 'čočka', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (19, 'fennel', 'fenykl', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (20, 'lettuce', 'salát', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (21, 'banana', 'banán', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (22, 'orange', 'pomeranč', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (23, 'pear', 'hruška', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (24, 'citron', 'citron', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (25, 'grapefruit', 'grep', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (26, 'apple', 'jablko', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (27, 'kiwi', 'kiwi', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (28, 'lime', 'limetka', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (29, 'grape', 'hrozny', 'parprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (30, 'coconut', 'kokos', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (31, 'river', 'řeka', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (32, 'milk', 'mléko', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (33, 'spoon', 'lžíce', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (34, 'ice', 'led', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (35, 'cafe', 'kavárna', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (36, 'wine', 'víno', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (37, 'coffee', 'kafe', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (38, 'limonade', 'limonáda', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (39, 'alcohol', 'alkohol', 'paprika', 'cibule', 'pití', 1);
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `answ4`, `points`) VALUES (40, 'water', 'voda', 'paprika', 'cibule', 'pití', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`test`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`test` (`id_test`, `question_count`, `test_name`, `id_teaching_lang`) VALUES (1, 10, 'Transport', 1);
INSERT INTO `db`.`test` (`id_test`, `question_count`, `test_name`, `id_teaching_lang`) VALUES (2, 10, 'Vegetable', 1);
INSERT INTO `db`.`test` (`id_test`, `question_count`, `test_name`, `id_teaching_lang`) VALUES (3, 10, 'Fruit', 1);
INSERT INTO `db`.`test` (`id_test`, `question_count`, `test_name`, `id_teaching_lang`) VALUES (4, 10, 'Drinks', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`test_to_question`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (1, 1, 1, '');
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (2, 1, 2, '');
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (3, 1, 3, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (4, 1, 4, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (5, 1, 5, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (6, 1, 6, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (7, 1, 7, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (8, 1, 8, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (9, 1, 9, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (10, 1, 10, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (11, 2, 11, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (12, 2, 12, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (13, 2, 13, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (14, 2, 14, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (15, 2, 15, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (16, 2, 16, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (17, 2, 17, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (18, 2, 18, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (19, 2, 19, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (20, 2, 20, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (21, 3, 21, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (22, 3, 22, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (23, 3, 23, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (24, 3, 24, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (25, 3, 25, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (26, 3, 26, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (27, 3, 27, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (28, 3, 28, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (29, 3, 29, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (30, 3, 30, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (31, 4, 31, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (32, 4, 32, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (33, 4, 33, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (34, 4, 34, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (35, 4, 35, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (36, 4, 36, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (37, 4, 37, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (38, 4, 38, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (39, 4, 39, NULL);
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`, `answ`) VALUES (40, 4, 40, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`course_to_test`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`course_to_test` (`id_course_to_test`, `id_course`, `id_test`, `done`, `wrong_answers`, `correct_answers`) VALUES (1, 1, 1, 0, 0, 0);
INSERT INTO `db`.`course_to_test` (`id_course_to_test`, `id_course`, `id_test`, `done`, `wrong_answers`, `correct_answers`) VALUES (2, 1, 2, 0, 0, 0);
INSERT INTO `db`.`course_to_test` (`id_course_to_test`, `id_course`, `id_test`, `done`, `wrong_answers`, `correct_answers`) VALUES (3, 1, 3, 0, 0, 0);
INSERT INTO `db`.`course_to_test` (`id_course_to_test`, `id_course`, `id_test`, `done`, `wrong_answers`, `correct_answers`) VALUES (4, 1, 4, 0, 0, 0);

COMMIT;

