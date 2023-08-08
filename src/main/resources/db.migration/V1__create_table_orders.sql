
CREATE TABLE ORDERS (
    CD_PEDIDO MEDIUMINT NOT NULL AUTO_INCREMENT,
    VR_PEDIDO FLOAT(10,7) NOT NULL,
    STATUS VARCHAR(20) NOT NULL,
    DT_CRIACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    DT_ATUALIZACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (CD_PEDIDO)
);