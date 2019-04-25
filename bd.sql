create table Site(
    email varchar (100) not null,
    url varchar (100) not null,
    nome varchar (100) not null,
    telefone varchar (20),

    CONSTRAINT Site_PK PRIMARY KEY (url)
);

create table Teatro(
    email varchar (100) not null,
    cnpj varchar (20) not null,
    nome varchar (100) not null,
    cidade varchar (50),

    CONSTRAINT Teatro_PK PRIMARY KEY (cnpj)
);

create table Promocao(
    id int not null,
    url varchar (100) not null,
    cnpj varchar (20) not null,
    nome varchar (100) not null,
    preco decimal (13, 2) not null,
    diahorario timestamp,

    CONSTRAINT Promocao_PK PRIMARY KEY (id),
    CONSTRAINT Promocao_FK1 FOREIGN KEY (url) REFERENCES Site(url),
    CONSTRAINT Promocao_FK2 FOREIGN KEY (cnpj) REFERENCES Teatro(cnpj)
);

create table PAPEL(
    id int not null generated always as identity,
    email varchar (100) not null,
    nome varchar (100) not null,

    CONSTRAINT Papel_PK PRIMARY KEY (id),
    CONSTRAINT Papel_FK FOREIGN KEY (email) REFERENCES Usuario(email)
);

create table USUARIO(
    id int not null generated always as identity,
    email varchar (100) not null,
    senha varchar (100) not null,
    ativo smallint not null,

    CONSTRAINT Usuario_PK PRIMARY KEY (id)
);
