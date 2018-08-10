CREATE SCHEMA IF NOT EXISTS `ActivitiesDB`;
USE `ActivitiesDB` ;

-- -----------------------------------------------------
-- Table `ActivitiesDB`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`login` (
  `customerID` int NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `seller` BOOLEAN,
  PRIMARY KEY (`customerID`));

-- -----------------------------------------------------
-- Table `ActivitiesDB`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`customer` (
  `customerID` int NOT NULL,
  `fName` VARCHAR(45) NOT NULL,
  `sName` VARCHAR(45) NULL,
  `dOb` DATE,
  `postcode` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customerID`),
  CONSTRAINT `fkcustomerID`
    FOREIGN KEY (`customerID`)
    REFERENCES `ActivitiesDB`.`login` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `ActivitiesDB`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`address` (
  `postcode` VARCHAR(10) NOT NULL,
  `streetName` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  PRIMARY KEY (`postcode`));


-- -----------------------------------------------------
-- Table `ActivitiesDB`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`company` (
  `clientID` int NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`clientID`),
  CONSTRAINT `fkClientcustomerID`
    FOREIGN KEY (`clientID`)
    REFERENCES `ActivitiesDB`.`login` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `ActivitiesDB`.`friend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`friend` (
  `customerIDO` int NOT NULL,
  `customerIDT` int NOT NULL,
  PRIMARY KEY (`customerIDO`, `customerIDT`),
  INDEX `fkUserTwo_idx` (`customerIDT` ASC),
  CONSTRAINT `fkFriendUserOne`
    FOREIGN KEY (`customerIDO`)
    REFERENCES `ActivitiesDB`.`customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkFriendUserTwo`
    FOREIGN KEY (`customerIDT`)
    REFERENCES `ActivitiesDB`.`customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `ActivitiesDB`.`chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`chat` (
  `chatID` INT NOT NULL,
  `customerIDO` int NOT NULL,
  `customerIDT` int NOT NULL,
  `message` VARCHAR(300) NULL,
  `sender` int NOT NULL,
  PRIMARY KEY (`chatID`),
  INDEX `fkUserOne_idx` (`customerIDO` ASC, `customerIDT` ASC),
  CONSTRAINT `fkChatUserOne`
    FOREIGN KEY (`customerIDO` , `customerIDT`)
    REFERENCES `ActivitiesDB`.`friend` (`customerIDO` , `customerIDT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `ActivitiesDB`.`follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`follow` (
  `customerID` int NOT NULL,
  `clientID` int NOT NULL,
  PRIMARY KEY (`customerID`, `clientID`),
  INDEX `fkCompany_idx` (`clientID` ASC),
  CONSTRAINT `fkFollowUser`
    FOREIGN KEY (`customerID`)
    REFERENCES `ActivitiesDB`.`customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkFollowCompany`
    FOREIGN KEY (`clientID`)
    REFERENCES `ActivitiesDB`.`company` (`clientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table `ActivitiesDB`.`userImages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`userImages` (
  `imgID` INT NOT NULL,
  `customerID` int NULL,
  `image` BLOB(100) NULL,
  PRIMARY KEY (`imgID`),
  INDEX `fkUser_idx` (`customerID` ASC),
  CONSTRAINT `fkUImgUser`
    FOREIGN KEY (`customerID`)
    REFERENCES `ActivitiesDB`.`customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table `ActivitiesDB`.`companyMessage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`companyMessage` (
  `mID` INT NOT NULL,
  `customerID` int NOT NULL,
  `clientID` int NOT NULL,
  `message` VARCHAR(200) NULL,
  `sender` int NOT NULL,
  PRIMARY KEY (`mID`),
  INDEX `fkUser_idx` (`customerID` ASC),
  INDEX `fkCompany_idx` (`clientID` ASC),
  CONSTRAINT `fkCMsgUser`
    FOREIGN KEY (`customerID`)
    REFERENCES `ActivitiesDB`.`customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkCMsgCompany`
    FOREIGN KEY (`clientID`)
    REFERENCES `ActivitiesDB`.`company` (`clientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table `ActivitiesDB`.`activities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`activities` (
  `activityID` INT NOT NULL,
  `clientID` int NULL,
  `title` VARCHAR(50) NULL,
  `description` VARCHAR(200) NULL,
  `price` DECIMAL NULL,
  `AD` TINYINT(1) NULL,
  `date` DATE NULL,
  `time` TIME NULL,
  `active` TINYINT(1) NULL,
  `address1` varchar(20),
  `quantity` int,
  `postcode` varchar(10),
  PRIMARY KEY (`activityID`),
   INDEX `fkPostcode_idx` (`postcode` ASC),
  CONSTRAINT `fkClientPostcode`
    FOREIGN KEY (`postcode`)
    REFERENCES `ActivitiesDB`.`address` (`postcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fkCompany_idx` (`clientID` ASC),
  CONSTRAINT `fkActivityCompany`
    FOREIGN KEY (`clientID`)
    REFERENCES `ActivitiesDB`.`company` (`clientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table `ActivitiesDB`.`activityImages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`activityImages` (
  `actImgID` INT NOT NULL,
  `activityID` INT NULL,
  `image` BLOB(100) NULL,
  PRIMARY KEY (`actImgID`),
  INDEX `fkCompany_idx` (`activityID` ASC),
  CONSTRAINT `fkActivityImgCompany`
    FOREIGN KEY (`activityID`)
    REFERENCES `ActivitiesDB`.`activities` (`activityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `ActivitiesDB`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`review` (
  `rID` INT NOT NULL,
  `activityID` INT NULL,
  `customerID` VARCHAR(10) NULL,
  `reviewText` VARCHAR(250) NULL,
  PRIMARY KEY (`rID`),
  INDEX `fkActivity_idx` (`activityID` ASC),
  CONSTRAINT `fkReviewActivity`
    FOREIGN KEY (`activityID`)
    REFERENCES `ActivitiesDB`.`activities` (`activityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table `ActivitiesDB`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`order` (
  `orderID` INT NOT NULL,
  `activityID` INT NULL,
  `customerID` int NULL,
  `timeBought` TIMESTAMP NULL,
  PRIMARY KEY (`orderID`),
  INDEX `pkActivity_idx` (`activityID` ASC),
  INDEX `pkUser_idx` (`customerID` ASC),
  CONSTRAINT `pkOrderActivity`
    FOREIGN KEY (`activityID`)
    REFERENCES `ActivitiesDB`.`activities` (`activityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pkOrderUser`
    FOREIGN KEY (`customerID`)
    REFERENCES `ActivitiesDB`.`customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table `ActivitiesDB`.`activitiesDone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ActivitiesDB`.`activitiesDone` (
  `customerID` int NOT NULL,
  `activityID` INT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`customerID`),
  INDEX `fkCompany_idx` (`activityID` ASC),
  CONSTRAINT `fkADUser`
    FOREIGN KEY (`customerID`)
    REFERENCES `ActivitiesDB`.`customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkADCompany`
    FOREIGN KEY (`activityID`)
    REFERENCES `ActivitiesDB`.`activities` (`activityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);