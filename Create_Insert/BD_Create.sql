--criacao tabelas
create table livro_autor(id_livro int, id_autor int, autor_principal boolean);

create table livro(id int, titulo varchar(200), descricao varchar(600), ano date, isbn char(13), id_genero int);

create table autor(id int, nome varchar(200), bio varchar(500));

create table genero(id int, nome varchar(100), descricao varchar(500));

create table auditLivro(id int, titulo varchar(200), descricao varchar(600), ano date, isbn char(13),
						id_genero int, usuario name, horario timestamp); 


-- pk's
alter table livro_autor add constraint livro_autor_pk primary key (id_livro, id_autor);
alter table autor add constraint autor_pk primary key (id);
alter table genero add constraint genero_pk primary key (id);
alter table livro add constraint livro_pk primary key (id);

-- fk's
alter table livro_autor add constraint livro_autor_fk foreign key (id_livro) references livro(id);
alter table livro_autor add constraint autor_livro_fk foreign key (id_autor) references autor(id);
alter table livro add constraint genero_livro_fk foreign key (id_genero) references genero(id);


-- criacao indices
create index indLivro on Livro(id);
create index indAutor on Autor(id);
create index indGen on Genero(id);
create index indLiAu on Livro_Autor(id_livro);


-- trigger pra auditoria, delete em livro eh add na tabela de backup
create or replace function auditLog() returns trigger as $$
	begin
		delete from livro_autor where id_livro = old.id;
		insert into auditLivro values (old.id, old.titulo, old.descricao, old.ano, old.isbn, old.id_genero, current_user, current_timestamp);

		return old;
end; $$ language PLPGSQL;

create trigger auditLog before delete on livro for each row execute procedure auditLog();


--criacao view -> autor, livro, genero
create view relatoriolivros as
	select au.id Id_Autor, l.id Id_Livro, g.id Id_Genero, au.nome Autor, l.titulo Livro, g.nome Genero, l.ano, l.isbn, l.descricao
	from Autor au join livro_autor la on au.id=la.id_autor join livro l on l.id=la.id_livro join genero g on l.id_genero=g.id;
	

-- trigger pra adicionar na view
create or replace function insertRelat() returns trigger as $$
begin
	if new.id_autor not in (select id from autor) then
		insert into autor values (new.id_autor, new.autor, null);
	end if;
	if new.id_genero not in (select id from genero) then
		insert into genero values (new.id_genero, new.genero, null);
	end if;
	
	insert into livro values (new.id_livro, new.livro, new.descricao, new.ano, new.isbn, new.id_genero);
	insert into livro_autor values (new.id_livro, new.id_autor, true);
	
	return new;
end; $$ language plpgsql security definer;

create trigger insertRelat instead of insert on relatoriolivros for each row execute procedure insertRelat();	


--criacao usuario, privilegio de leitura p/ tabelas
create role usuario with login password 'senha';
grant select on livro, livro_autor, genero, autor, relatoriolivros to usuario;
grant insert on relatoriolivros to usuario;



