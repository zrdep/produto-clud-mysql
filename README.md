use dbalunos;

create table aluno (
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    curso VARCHAR(100) NOT NULL,
    matricula VARCHAR(100) NOT NULL,
    periodoAtual INT NOT NULL,
    PRIMARY KEY (id)
);

select * from aluno;
