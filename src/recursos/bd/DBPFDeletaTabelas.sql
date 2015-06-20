/* Deleta Tabelas */
DROP TABLE renda IF EXISTS CASCADE;
DROP TABLE renda_mensal IF EXISTS CASCADE;
DROP TABLE forma_pagamento IF EXISTS CASCADE;
DROP TABLE categoria IF EXISTS CASCADE;
DROP TABLE despesa IF EXISTS CASCADE;
DROP TABLE planejamento_mensal IF EXISTS;
DROP TABLE meta_mensal IF EXISTS;
DROP TABLE configuracoes IF EXISTS;
/* */
/* Deleta Sequencias*/
DROP SEQUENCE seq_renda IF EXISTS;
DROP SEQUENCE seq_forma_pagamento IF EXISTS;
DROP SEQUENCE seq_categoria IF EXISTS;
DROP SEQUENCE seq_despesa IF EXISTS;
DROP SEQUENCE seq_configuracoes IF EXISTS;