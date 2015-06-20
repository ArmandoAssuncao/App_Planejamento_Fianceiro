/* Deleta Registos das Tabelas */
TRUNCATE TABLE renda;
TRUNCATE TABLE renda_mensal;
TRUNCATE TABLE forma_pagamento;
TRUNCATE TABLE categoria;
TRUNCATE TABLE despesa;
TRUNCATE TABLE planejamento_mensal;
TRUNCATE TABLE meta_mensal;
TRUNCATE TABLE configuracoes;
/* */
/* Reinicia Sequencias*/
ALTER SEQUENCE seq_renda RESTART WITH 1;
ALTER SEQUENCE seq_forma_pagamento RESTART WITH 1;
ALTER SEQUENCE seq_categoria RESTART WITH 1;
ALTER SEQUENCE seq_despesa RESTART WITH 1;
ALTER SEQUENCE seq_configuracoes RESTART WITH 1;