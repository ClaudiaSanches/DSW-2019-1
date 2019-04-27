    
create table Site(
  email varchar (100) not null,
  nome varchar (100) not null,
  url varchar (100) not null,
  telefone varchar (100) not null,

  constraint Site_pk primary key (url)
);

create table Teatro(
  email varchar (100) not null,
  cnpj varchar (20) not null,
  nome varchar (100) not null,
  cidade varchar (100) not null,

  constraint Teatro_pk primary key (cnpj)
);

create table Promocao(
  id int not null generated always as identity (start with 1, increment by 1),
  peça varchar (100) not null,
  preço float not null,
  diahorario varchar (100) not null,
  site varchar (100) not null,
  teatro varchar (20) not null,

  constraint Promocao_pk primary key (id),
  constraint Promocao_fk1 foreign key (site) references Site(url),
  constraint Promocao_fk2 foreign key (teatro) references Teatro(cnpj)
);

create table Usuario(
  email varchar (100) not null,
  senha varchar (20) not null,
  ativo boolean not null,

  constraint Usuario_pk primary key (email)
);

create table Papel(
  id int not null generated always as identity (start with 1, increment by 1),
  email varchar (100) not null,
  nome varchar (50) not null,

  constraint Papel_pk primary key (id),
  constraint Papel_fk foreign key (email) references Usuario(email)
);