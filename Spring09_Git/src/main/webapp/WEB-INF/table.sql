create table client_account
(id varchar2(100) primary key,
balance number check(balance >= 0),
bonusPoint number check(bonusPoint >=0));