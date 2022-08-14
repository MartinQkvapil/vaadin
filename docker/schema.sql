SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `db` ;

-- -----------------------------------------------------
-- Schema db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db` DEFAULT CHARACTER SET utf8 COLLATE utf8;
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
                                             PRIMARY KEY (`id_course`, `id_teaching_lang`),
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
                                               `points` INT NOT NULL,
                                               `answ4` VARCHAR(250) NULL,
                                               PRIMARY KEY (`id_question`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db`.`test`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`test` ;

CREATE TABLE IF NOT EXISTS `db`.`test` (
                                           `id_test` INT NOT NULL AUTO_INCREMENT,
                                           `question_count` INT NULL,
                                           `user_id_user` INT NOT NULL,
                                           PRIMARY KEY (`id_test`),
                                           INDEX `fk_test_user1_idx` (`user_id_user` ASC) VISIBLE,
                                           CONSTRAINT `fk_test_user1`
                                               FOREIGN KEY (`user_id_user`)
                                                   REFERENCES `db`.`user` (`id_user`)
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
                                                       PRIMARY KEY (`id_test_to_question`, `id_test`, `id_question`),
                                                       INDEX `fk_test_to_question_test1_idx` (`id_test` ASC) VISIBLE,
                                                       INDEX `fk_test_to_question_question1_idx` (`id_question` ASC) VISIBLE,
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
                                                     `id_course_to_test` INT NOT NULL,
                                                     `id_course` INT NOT NULL,
                                                     `id_test` INT NOT NULL,
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `db`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`user` (`id_user`, `name`, `surname`, `email`, `hashed_password`, `role`, `username`, `icon`) VALUES (1, 'Martin', 'Kvapil', 'm@gmail.com', '$2a$10$yxQdnSRiQeEpGZ0clMukRux0GYWvdEGgxB4sQWTmiAE7YiUvjU8wG', 'admin', 'admin', NULL);
INSERT INTO `db`.`user` (`id_user`, `name`, `surname`, `email`, `hashed_password`, `role`, `username`, `icon`) VALUES (2, 'Jiri', 'Kvapil', 'j@gmail.com', '$2a$10$Xg8twqsOV9ubpvgoYSMGoesSNvm0muqG.3n3G3KzCjhG4joAXUZJm', 'user', 'user', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`teaching_lang`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`teaching_lang` (`id_teaching_lang`, `code`, `name`) VALUES (1, 'en', 'angličtina');

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`course`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`course` (`id_course`, `points`, `native_lang`, `description`, `id_teaching_lang`, `id_user`) VALUES (1, 0, 'cz', 'course of english', 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`question`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`question` (`id_question`, `question`, `answ1`, `answ2`, `answ3`, `points`, `answ4`) VALUES (1, 'car', 'auto', 'kolo', 'letadlo', 10, 'koloběžka');

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`test`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`test` (`id_test`, `question_count`, `user_id_user`) VALUES (1, 10, 2);
INSERT INTO `db`.`test` (`id_test`, `question_count`, `user_id_user`) VALUES (2, 15, 2);
INSERT INTO `db`.`test` (`id_test`, `question_count`, `user_id_user`) VALUES (3, 10, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`test_to_question`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`test_to_question` (`id_test_to_question`, `id_test`, `id_question`) VALUES (1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `db`.`course_to_test`
-- -----------------------------------------------------
START TRANSACTION;
USE `db`;
INSERT INTO `db`.`course_to_test` (`id_course_to_test`, `id_course`, `id_test`) VALUES (1, 1, 1);

COMMIT;

