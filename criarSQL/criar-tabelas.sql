create database hipertrofia;

use hipertrofia;

show databases;

create table produto (id int auto_increment primary key, 
referencia varchar(20) not null,
tamanho varchar(2) not null, 
preco float not null);

desc produto;

create table cartao (id int auto_increment primary key, 
numeroCartao int not null, 
cvc int(3) not null, 
dataVencimento int(4) not null);

desc cartao;

create table pedido (id int not null auto_increment primary key,
nomeProduto varchar(20) not null,
tamanhoP varchar(2) not null, 
precoP float not null,
formaPagamento int(16) not null);

alter table pedido add foreign key  (formaPagamento) references cartao(id);

desc pedido;

show tables;