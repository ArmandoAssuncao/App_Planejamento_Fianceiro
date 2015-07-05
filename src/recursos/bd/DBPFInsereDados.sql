/*Forma de pagamento*/
INSERT INTO forma_pagamento VALUES (NEXT VALUE FOR seq_forma_pagamento, 'A Vista');
INSERT INTO forma_pagamento VALUES (NEXT VALUE FOR seq_forma_pagamento, 'Cartão de Credito');
INSERT INTO forma_pagamento VALUES (NEXT VALUE FOR seq_forma_pagamento, 'Cheque');
INSERT INTO forma_pagamento VALUES (NEXT VALUE FOR seq_forma_pagamento, 'Prazo');
/* */
/*Categorias*/
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Automóvel');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Casa');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Condominio');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Diversos');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Educação');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Energia Eletrica');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Esporte');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Farmácia');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Gasolina');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Gastos Pessoais');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Habitação');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Internet e TV');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Investimentos');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Lazer');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Plano de Saúde');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Profissionais da Saúde');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Supermercado');
INSERT INTO categoria VALUES (NEXT VALUE FOR seq_categoria, 'Telefone');
/* */
/*Meta Mensal*/
INSERT INTO meta_mensal VALUES (1, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (2, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (3, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (4, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (5, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (6, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (7, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (8, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (9, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (10, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (11, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (12, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (13, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (14, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (15, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (16, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (17, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);
INSERT INTO meta_mensal VALUES (18, TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'), 0, 70);