--
-- Interval table definition
--
CREATE TABLE IF NOT EXISTS "Interval"
(
    `id`           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`         VARCHAR     NOT NULL UNIQUE,
    `name`         VARCHAR(60) NOT NULL UNIQUE,
    `abbreviation` VARCHAR(10) NOT NULL UNIQUE,
    `duration`     BIGINT      NOT NULL,
    `createdAt`    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Category table definition
--
CREATE TABLE IF NOT EXISTS "Category"
(
    `id`        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`      VARCHAR     NOT NULL UNIQUE,
    `color`     VARCHAR(20) NOT NULL UNIQUE,
    `name`      VARCHAR(60) NOT NULL UNIQUE,
    `createdAt` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Template table definition
--
CREATE TABLE IF NOT EXISTS "Template"
(
    `id`           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`         VARCHAR     NOT NULL UNIQUE,
    `templateName` VARCHAR(60) NOT NULL,
    `eventName`    VARCHAR(60),
    `isDone`       BOOLEAN     NOT NULL,
    `location`     VARCHAR(150),
    `time`         VARCHAR(10),
    `duration`     INT,
    `createdAt`    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Event table definition
--
CREATE TABLE IF NOT EXISTS "Event"
(
    `id`        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`      VARCHAR     NOT NULL UNIQUE,
    `name`      VARCHAR(60) NOT NULL UNIQUE,
    `isDone`    BOOLEAN     NOT NULL,
    `location`  VARCHAR(150),
    `date`      DATE,
    `time`      TIME,
    `duration`  INT         NOT NULL,
    `createdAt` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Category mapping for Event mapping to categories
--
CREATE TABLE IF NOT EXISTS "EventCategories"
(
    "eventId"    BIGINT                              NOT NULL,
    "categoryId" BIGINT                              NOT NULL,
    "createdAt"  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY ("eventId") REFERENCES "Event" ("id"),
    FOREIGN KEY ("categoryId") REFERENCES "Category" ("id"),
    unique ("eventId", "categoryId")
);

--
-- Category mapping for Template mapping to categories
--
CREATE TABLE IF NOT EXISTS "TemplateCategories"
(
    "templateId" BIGINT                              NOT NULL,
    "categoryId" BIGINT                              NOT NULL,
    "createdAt"  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY ("templateId") REFERENCES "Template" ("id"),
    FOREIGN KEY ("categoryId") REFERENCES "Category" ("id"),
    unique ("templateId", "categoryId")
);
