--
-- Interval table definition
--

CREATE TABLE IF NOT EXISTS "Intervalz"
(
    `id`           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`         VARCHAR     NOT NULL UNIQUE,
    `name`         VARCHAR(50) NOT NULL UNIQUE,
    `abbreviation` VARCHAR(10) NOT NULL UNIQUE,
    `duration`     BIGINT      NOT NULL UNIQUE,
    `createdAt`    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Category table definition
--
CREATE TABLE IF NOT EXISTS "Category"
(
    `id`        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`      VARCHAR     NOT NULL UNIQUE,
    `color`     VARCHAR(10) NOT NULL UNIQUE,
    `name`      VARCHAR(50) NOT NULL UNIQUE,
    `createdAt` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Category mapping for Template
--
CREATE TABLE IF NOT EXISTS "TemplateCats"
(
    `id`         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `templateId` BIGINT REFERENCES "Template" (`id`) NOT NULL,
    `categoryId` BIGINT REFERENCES "Category" (`id`) NOT NULL,
    `createdAt`  TIMESTAMP                           NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- TODO Categories
--
-- Template table definition
--
CREATE TABLE IF NOT EXISTS "Template"
(
    `id`           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`         VARCHAR      NOT NULL UNIQUE,
    `templateName` VARCHAR(150) NOT NULL,
    `eventName`    VARCHAR(150) NOT NULL,
    `isDone`       BOOLEAN      NOT NULL,
    `location`     VARCHAR(150) NOT NULL,
    `time`         VARCHAR(15)  NOT NULL,
    `duration`     INT          NOT NULL,
    `createdAt`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Event table definition
--
CREATE TABLE IF NOT EXISTS "Event"
(
    `id`        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`      VARCHAR      NOT NULL UNIQUE,
    `name`      VARCHAR(150) NOT NULL,
    `isDone`    BOOLEAN      NOT NULL,
    `location`  VARCHAR(150) NOT NULL,
    `date`      VARCHAR(15)  NOT NULL,
    `time`      VARCHAR(15)  NOT NULL,
    `duration`  INT          NOT NULL,
    `createdAt` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
