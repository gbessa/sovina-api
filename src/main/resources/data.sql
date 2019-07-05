INSERT INTO USER(NAME, EMAIL, PASSWORD) VALUES('Gustavo Bessa', 'gbessa@email.pt', '$2a$10$iAYYwGPlst9j6obIudhwt..PMaPa8QFZiVXeGFspkKjukzA2cI7pG');

INSERT INTO STORE(NAME, LOCATION, PLUS_CODE) VALUES('Pingo Doce', 'Pachancho', 'HH5M+76 Braga');
INSERT INTO STORE(NAME, LOCATION, PLUS_CODE) VALUES('Minipreço', 'Pachancho', 'HH5P+J8 Braga');
INSERT INTO STORE(NAME, LOCATION, PLUS_CODE) VALUES('Continente', 'Centro', 'HH2M+54 Braga');
INSERT INTO STORE(NAME, LOCATION, PLUS_CODE) VALUES('Worten', 'Minho Center', '');
INSERT INTO STORE(NAME, LOCATION, PLUS_CODE) VALUES('Worten', 'Nova Arcada', '');
INSERT INTO STORE(NAME, LOCATION, PLUS_CODE) VALUES('Pingo Doce', 'Braga Parque', '');
INSERT INTO STORE(NAME, LOCATION, PLUS_CODE) VALUES('Worten', 'Viana do Castelo', '');

INSERT INTO PRODUCT(NAME, SCANNED_CODE, BRAND, FAVORITED) VALUES('Peito de Peru', '', 'Granel', false);
INSERT INTO PRODUCT(NAME, SCANNED_CODE, BRAND, FAVORITED) VALUES('Café', '', 'Pilão', false);
INSERT INTO PRODUCT(NAME, SCANNED_CODE, BRAND, FAVORITED) VALUES('Sopa', '', 'Pingo Doce', false);
INSERT INTO PRODUCT(NAME, SCANNED_CODE, BRAND, FAVORITED) VALUES('Vinho Verde', '', 'Lagosta', false);
INSERT INTO PRODUCT(NAME, SCANNED_CODE, BRAND, FAVORITED) VALUES('Vinho Tinto', '', 'JP', true);
INSERT INTO PRODUCT(NAME, SCANNED_CODE, BRAND, FAVORITED) VALUES('Gelo 1KG', '', 'Pingo Doce', true);
INSERT INTO PRODUCT(NAME, SCANNED_CODE, BRAND, FAVORITED) VALUES('Queijo Brie', '', 'President', false);
INSERT INTO PRODUCT(NAME, SCANNED_CODE, BRAND, FAVORITED) VALUES('Geléia de Morango', '', 'Pingo Doce', false);

INSERT INTO PRICE(PRODUCT_ID, STORE_ID, PRICE, DATE_FIND) VALUES(1, 1, 10, TO_DATE('2019-05-05 20:00:00', 'YYYY-MM-DD HH:mm:ss'));
INSERT INTO PRICE(PRODUCT_ID, STORE_ID, PRICE, DATE_FIND) VALUES(1, 2, 11, TO_DATE('2019-05-06 20:00:00', 'YYYY-MM-DD HH:mm:ss'));
INSERT INTO PRICE(PRODUCT_ID, STORE_ID, PRICE, DATE_FIND) VALUES(2, 1, 3, TO_DATE('2019-05-07 20:00:00', 'YYYY-MM-DD HH:mm:ss'));
INSERT INTO PRICE(PRODUCT_ID, STORE_ID, PRICE, DATE_FIND) VALUES(2, 2, 4, TO_DATE('2019-05-08 20:00:00', 'YYYY-MM-DD HH:mm:ss'));
INSERT INTO PRICE(PRODUCT_ID, STORE_ID, PRICE, DATE_FIND) VALUES(2, 3, 3.5, TO_DATE('2019-05-09 20:00:00', 'YYYY-MM-DD HH:mm:ss'));
