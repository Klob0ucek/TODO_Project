INSERT INTO "Intervalz" ("guid", "name", "abbreviation", "duration")
VALUES ('02d2cfaf-0448-452c-9b2e-514cf701cdd3', 'short break', 'sb', '15'),
       ('830aa6d3-64ff-4544-93f8-39cb989fa2c9', 'long break', 'long', '22'),
       ('2162e761-20d4-436d-a98c-4bf660e1ea6d', 'sleep', 'see', '69')
;

INSERT INTO "Category" ("guid", "color", "name")
VALUES ('d80bfe08-cfea-4215-8c3d-fb707ba1436f', 'ORANGE', 'Cooking'),
       ('e8359b1e-c34d-4966-92c9-c212eb17dd70', 'GREEN', 'Gardening'),
       ('0a2b3044-3816-415c-b4c6-4609282eb0dd', 'BLUE', 'Eating'),
       ('e2dd3ff3-3add-4ea5-afed-eb6ca199c3d8', 'CYAN', 'Sleeping'),
       ('9d2f1391-d969-4bcb-9651-2959ab00e4bf', 'BLACK', 'Heavy metal')
;

INSERT INTO "Event" ("guid", "name", "isDone", "location", "date", "time", "duration")
VALUES ('bb716fe5-6f2f-4c50-b4b3-7c8c53a82a8b', 'Deep Sleep', false, 'Lovely bed', null, '12:30', 69),
       ('9d2f1391-d969-4bcb-9651-2959ab00e3bf', 'Cooking dinner', true, 'My kitchen', '20231201', '12:30', 30),
       ('9d2f1391-d969-4bcb-9651-2959ab00e2bf', 'Listening to metal!', false, 'Nowhere', '20231210', null, 42),
       ('9d2f1391-d969-4bcb-9651-2959ab00e1bf', 'Eat and metal!', false, 'My kitchen', '20501201', '12:00', 60)
;

SELECT *
from "Event";

INSERT INTO "Template" ("guid", "templateName", "eventName", "isDone", "location", "time", "duration")
VALUES ('9eeaf2d8-2485-46fa-917b-47eb934f4b84', 'My sleep', 'Sleeping', false, 'Lovely bed', null, 69),
       ('8eeaf2d8-2485-46fa-917b-47eb934f4b84', 'Amalgam', null, false, null, null, 2)
;

INSERT INTO "EntityCats" ("templateGuid", "eventGuid", "categoryGuid")
VALUES (null, 'bb716fe5-6f2f-4c50-b4b3-7c8c53a82a8b', 'e2dd3ff3-3add-4ea5-afed-eb6ca199c3d8'),
       ('9eeaf2d8-2485-46fa-917b-47eb934f4b84', null, 'e2dd3ff3-3add-4ea5-afed-eb6ca199c3d8'),
       (null, '9d2f1391-d969-4bcb-9651-2959ab00e3bf', 'd80bfe08-cfea-4215-8c3d-fb707ba1436f'),
       (null, '9d2f1391-d969-4bcb-9651-2959ab00e2bf', '9d2f1391-d969-4bcb-9651-2959ab00e4bf'),
       (null, '9d2f1391-d969-4bcb-9651-2959ab00e1bf', '0a2b3044-3816-415c-b4c6-4609282eb0dd'),
       (null, '9d2f1391-d969-4bcb-9651-2959ab00e1bf', '9d2f1391-d969-4bcb-9651-2959ab00e4bf'),
       ('8eeaf2d8-2485-46fa-917b-47eb934f4b84', null, 'd80bfe08-cfea-4215-8c3d-fb707ba1436f'),
       ('8eeaf2d8-2485-46fa-917b-47eb934f4b84', null, 'e8359b1e-c34d-4966-92c9-c212eb17dd70'),
       ('8eeaf2d8-2485-46fa-917b-47eb934f4b84', null, 'e2dd3ff3-3add-4ea5-afed-eb6ca199c3d8'),
       ('8eeaf2d8-2485-46fa-917b-47eb934f4b84', null, '9d2f1391-d969-4bcb-9651-2959ab00e4bf')
;