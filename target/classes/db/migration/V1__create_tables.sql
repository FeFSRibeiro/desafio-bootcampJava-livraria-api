CREATE TABLE autores (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  nome varchar(255),
  email varchar(255),
  data_nascimento date,
  mini_curriculo varchar(500)  
);

CREATE TABLE livros (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  titulo varchar(255),
  data_lancamento date,
  quantidade_paginas int
  );
  
  alter table livros add column  autor_id bigint not null ;
  alter table livros add foreign key(autor_id) references autores (id);

