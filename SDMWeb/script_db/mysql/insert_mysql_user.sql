delete from user where user='xweb';
delete from db where user='xweb';

insert into user (host,user,password)
	values('%','xweb',PASSWORD('xweb'));

insert into db (host, db, user, select_priv, insert_priv, update_priv, delete_priv)
	values('%','xweb', 'xweb' ,'Y','Y','Y','Y');


flush privileges;

