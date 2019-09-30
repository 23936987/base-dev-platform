drop table if exists sys_contrast;
create table sys_contrast ( 
	id varchar(36) not null comment '主键',
	name_en  varchar(100) comment '英文名称',
	name_cn  varchar(100) comment '中文名称', 
	/*场景字段*/ 
	/*约束*/ 
	primary key(id)
) DEFAULT CHARSET=utf8;
alter table sys_contrast  comment '中英对照表';
