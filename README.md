# ice-cream-project(açaí bh newton)

## Description
Uma aplicação extremamente simples com um CRUD de usuários e mensagens.

## Installation
PTBR: Instale o docker https://www.docker.com/get-started
faça download da imagem do postgres usando o comando:

ENG: Install docker https://www.docker.com/get-started and download the postgres image using the command:
```bash
$docker pull postgres
```
PTBR: Crie uma imagem do banco do açaí bh usando o comando:

ENG: Create a açaí bh database image using the command:
```bash
$docker build -t acai_bh .
```
PTBR: Crie um container do banco usando o comando:

ENG: Create a database container using the command:
```bash
$docker run -d -p 5432:5432 --name acai_bh_container acai_bh
```
PTBR:
Isso criará um contêiner PostgreSQL com o nome de usuário "postgres", senha "12345" e o banco de dados "acai_bh" com as tabelas definidas no script SQL.

ENG:
It'll create a PostgreSQL container with username "postgres", password "12345" and database "acai_bh" with the tables defined in the SQL script.
