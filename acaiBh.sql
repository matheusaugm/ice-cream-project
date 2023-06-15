create table ingredientes
(
    id   serial
        constraint ingredientes_pkey
            primary key,
    nome varchar(100) not null
);

alter table ingredientes
    owner to postgres;

create table produtos
(
    id    serial
        constraint produtos_pkey
            primary key,
    nome  varchar(100)   not null,
    preco numeric(10, 2) not null
);

alter table produtos
    owner to postgres;

create table clientes
(
    id       serial
        constraint clientes_pkey
            primary key,
    nome     varchar(100) not null,
    telefone varchar(20)  not null,
    senha    varchar(50)  not null
);

alter table clientes
    owner to postgres;

create unique index clientes_telefone_uindex
    on clientes (telefone);

create table pedidos
(
    id         serial
        constraint pedidos_pkey
            primary key,
    cliente_id integer
        constraint pedidos_cliente_id_fkey
            references clientes,
    produto_id integer
        constraint pedidos_produto_id_fkey
            references produtos,
    quantidade integer not null
);

alter table pedidos
    owner to postgres;

create table produtos_ingredientes
(
    produto_id     integer not null
        constraint produtos_ingredientes_produto_id_fkey
            references produtos,
    ingrediente_id integer not null
        constraint produtos_ingredientes_ingrediente_id_fkey
            references ingredientes,
    constraint produtos_ingredientes_pkey
        primary key (produto_id, ingrediente_id)
);

alter table produtos_ingredientes
    owner to postgres;

create table adicionais
(
    id    serial
        constraint adicionais_pkey
            primary key,
    nome  varchar(100)   not null,
    preco numeric(10, 2) not null
);

alter table adicionais
    owner to postgres;

create table pedidos_adicionais
(
    pedido_id    integer not null
        constraint pedidos_adicionais_pedido_id_fkey
            references pedidos,
    adicional_id integer not null
        constraint pedidos_adicionais_adicional_id_fkey
            references adicionais,
    constraint pedidos_adicionais_pkey
        primary key (pedido_id, adicional_id)
);

alter table pedidos_adicionais
    owner to postgres;

