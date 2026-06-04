create database dbproduto;
use dbproduto;

create table produto (
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    valor DOUBLE NOT NULL,
    PRIMARY KEY (id)
);

select * from produto;
