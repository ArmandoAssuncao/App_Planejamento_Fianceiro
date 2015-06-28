/* Cria Tabelas */
/* Renda */
CREATE TABLE IF NOT EXISTS renda(
	idRenda INTEGER NOT NULL,
	descricao VARCHAR_IGNORECASE(100) NOT NULL UNIQUE CHECK(LENGTH(descricao) > 0),
	CONSTRAINT pk_renda PRIMARY KEY (idRenda)
);
/* Renda Mensal */
CREATE TABLE IF NOT EXISTS renda_mensal(
    idRenda INTEGER NOT NULL,
    dataRenda VARCHAR_IGNORECASE(10),
	valor DOUBLE,
    CONSTRAINT fk_renda_mensal FOREIGN KEY (idRenda) REFERENCES renda(idRenda) ON DELETE CASCADE,
	CONSTRAINT pk_renda_mesal PRIMARY KEY (idRenda, dataRenda)
);
/* Forma Pagamento */
CREATE TABLE IF NOT EXISTS forma_pagamento(
	idFormaPagamento INTEGER NOT NULL,
	descricao VARCHAR_IGNORECASE(100) NOT NULL UNIQUE CHECK(LENGTH(descricao) > 0),
	CONSTRAINT pk_forma_pagamento PRIMARY KEY (idFormaPagamento)
);
/* Categoria */
CREATE TABLE IF NOT EXISTS categoria(
	idCategoria INTEGER NOT NULL,
	descricao VARCHAR_IGNORECASE(100) NOT NULL UNIQUE CHECK(LENGTH(descricao) > 0),
	CONSTRAINT pk_categoria PRIMARY KEY (idCategoria)
);
/* Despesa */
CREATE TABLE IF NOT EXISTS despesa(
	idDespesa INTEGER NOT NULL,
	descricao VARCHAR_IGNORECASE(100) NOT NULL CHECK(LENGTH(descricao) > 0),
    idCategoria INTEGER NOT NULL,
    dataDespesa VARCHAR_IGNORECASE(10),
    dataPagamento VARCHAR_IGNORECASE(10),
    idFormaPagamento INTEGER NOT NULL,
    numeroCheque VARCHAR_IGNORECASE(100),
    valor DOUBLE,
    numeroDeParcelas INTEGER,
	CONSTRAINT fk_despesa_categoria FOREIGN KEY (idCategoria) REFERENCES categoria(idCategoria) ON DELETE CASCADE,
    CONSTRAINT fk_despesa_pagamento FOREIGN KEY (idFormaPagamento) REFERENCES forma_pagamento(idFormaPagamento),
    CONSTRAINT pk_despesa PRIMARY KEY (idDespesa)
);
/* Planejamento Mensal */
CREATE TABLE IF NOT EXISTS planejamento_mensal(
	mesAnoPlanejamento VARCHAR_IGNORECASE(10),
    idDespesa INTEGER NOT NULL,
    CONSTRAINT fk_planejamento_despesa FOREIGN KEY (idDespesa) REFERENCES despesa(idDespesa) ON DELETE CASCADE,
	CONSTRAINT pk_planejamento_mensal PRIMARY KEY (mesAnoPlanejamento, idDespesa)
);
/* Meta Mensal */
CREATE TABLE IF NOT EXISTS meta_mensal(
    idCategoria INTEGER NOT NULL,
	mesAnoMeta VARCHAR_IGNORECASE(10),
    valor DOUBLE,
    alerta DOUBLE,
    CONSTRAINT fk_meta_mensal_despesa FOREIGN KEY (idCategoria) REFERENCES categoria(idCategoria) ON DELETE CASCADE,
	CONSTRAINT pk_meta_mensal PRIMARY KEY (mesAnoMeta, idCategoria)
);
/* */
/* Configuracoes */
CREATE TABLE IF NOT EXISTS configuracoes(
	idConfiguracao INTEGER NOT NULL,
	nome VARCHAR(50),
	valor VARCHAR(50),
	CONSTRAINT pk_configuracoes PRIMARY KEY (nome)
);
/* */
/* Cria Sequencias */
CREATE SEQUENCE seq_renda START WITH 1;
CREATE SEQUENCE seq_forma_pagamento START WITH 1;
CREATE SEQUENCE seq_categoria START WITH 1;
CREATE SEQUENCE seq_despesa START WITH 1;
CREATE SEQUENCE seq_configuracoes START WITH 1;
/* usar nextval('seq_candidato') para usar o proximo id valido no postgres*/
/* NEXT VALUE FOR seq_candidato no hsql*/
/* */
/* INSERE AS CONFIGURACOES */
/*INSERT INTO configuracoes VALUES (NEXT VALUE FOR seq_configuracoes, 'senha', '123456');*/