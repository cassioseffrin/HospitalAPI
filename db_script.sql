create database unc_hospital;

use unc_hospital;

create table pessoa (
id int not null primary key AUTO_INCREMENT,
nome varchar(50),
rg int not null,
cpf varchar(14) not null,
sexo char(1) not null,
dtNascimento date not null,
telefone varchar(20)
);

create table paciente (
id int not null primary key AUTO_INCREMENT,
idPessoa int not null,
gravidade smallint
);

create table medico (
id int not null primary key AUTO_INCREMENT,
idPessoa int not null,
especialidade varchar(25)
);

create table enfermeiro (
id int not null primary key AUTO_INCREMENT,
idPessoa int not null,
dataAdmissao date not null
);

create table agenda (
id int not null primary key AUTO_INCREMENT,
dtAgenda date not null,
horario time not null,
idPaciente int not null
);

create table internacao (
id int not null primary key AUTO_INCREMENT,
idPaciente int not null,
horario time not null,
quarto varchar(10),
idPessoaAcompanhante int
);

create table servico (
id int not null primary key AUTO_INCREMENT,
descricao varchar(100),
idMedico int not null,
idPaciente int not null,
idEnfermeiro int not null
);

alter table paciente add constraint fk_pessoa_paciente foreign key (idPessoa) references pessoa(id);
alter table medico add constraint fk_pessoa_medico foreign key (idPessoa) references pessoa(id);
alter table enfermeiro add constraint fk_pessoa_enfermeiro foreign key (idPessoa) references pessoa(id);
alter table agenda add constraint fk_paciente_agenda foreign key (idPaciente) references paciente(id);
alter table internacao add constraint fk_paciente_internacao foreign key (idPaciente) references paciente(id);
alter table internacao add constraint fk_pessoa_internacao foreign key (idPessoaAcompanhante) references pessoa(id);
alter table servico add constraint fk_medico_servico foreign key (idMedico) references medico(id);
alter table servico add constraint fk_paciente_servico foreign key (idPaciente) references paciente(id);
alter table servico add constraint fk_enfermeiro_servico foreign key (idEnfermeiro) references enfermeiro(id);
