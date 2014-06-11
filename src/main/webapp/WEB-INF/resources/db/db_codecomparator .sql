/*Database name : page */

create table t_users( n_id_user int not null auto_increment, t_username varchar(40) not null, t_password varchar(40) not null, primary key ( n_id_user ), t_email varchar(40) not null);

create table reports( id int not null auto_increment, t_id_user varchar(40) not null, t_codigo1 varchar(40) not null, t_codigo2 varchar(40) not null, primary key ( id ));
