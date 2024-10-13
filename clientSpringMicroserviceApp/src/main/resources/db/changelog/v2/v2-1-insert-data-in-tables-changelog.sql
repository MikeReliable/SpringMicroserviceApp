INSERT INTO coffee (variety, country, arabica, robusta, weight)
VALUES ('Coffee1', 'Country1', 100, 0, 600000),
       ('Coffee2', 'Country1', 90, 10, 300000),
       ('Coffee3', 'Country2', 80, 20, 180000),
       ('Coffee4', 'Country3', 70, 30, 120000),
       ('Coffee5', 'Country4', 60, 4, 60000);

INSERT INTO roasting (teamuuid, variety, country, weight_income, weight_outcome)
VALUES ('a64c4df3-eb97-4ab1-b16d-0eb1dc8a2fa4'::UUID, 'Coffee5', 'Country4', 15000, 11000),
       ('16182d84-ce13-476f-a800-30a96764b231'::UUID, 'Coffee4', 'Country3', 14000, 10000),
       ('71da7796-3594-43a3-be99-9192581c91a7'::UUID, 'Coffee3', 'Country2', 13000, 9000),
       ('b046eb99-9d4a-4b72-9bf6-60e3c4f40f98'::UUID, 'Coffee2', 'Country1', 12000, 8000),
       ('0393e6e3-6ad5-43e4-9aad-1607d9110dac'::UUID, 'Coffee1', 'Country1', 11000, 7000);

INSERT INTO coffee_roasting (roasting_id, variety)
VALUES (1, 'Coffee5'),
       (2, 'Coffee4'),
       (3, 'Coffee3'),
       (4, 'Coffee2'),
       (5, 'Coffee1');