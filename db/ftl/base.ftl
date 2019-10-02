<#import "/include/base.ftl" as base>
<#import "/include/basic.ftl" as basic>
<#import "/include/tree.ftl" as tree>


drop table if exists ${中英对照表.en};
create table ${中英对照表.en} ( 
	<@base.code/>  
	${英文名称.en}  varchar(100) comment '英文名称',
	${中文名称.en}  varchar(100) comment '中文名称', 
	/*场景字段*/ 

	/*约束*/ 
	primary key(${主键.en})
) DEFAULT CHARSET=utf8;
alter table ${中英对照表.en}  comment '中英对照表';

drop table if exists ${数据字典表.en};
create table ${数据字典表.en} (
	<@base.code/>
	${编码.en} 		varchar(100) 	comment '编码',
	${名称.en} 		varchar(100) 	comment '名称',
	${类型.en} 		int(2) 			comment '2#类型#1 普通 2、其它表 #dict_type',
	${表名称.en} 	varchar(20) 	comment '表名称',
	${值字段.en} 		varchar(20) 	comment '值字段',
	${名称字段.en} 		varchar(20) 	comment '名称字段',
	/*basic场景字段*/ 
	/*约束*/
	primary key(${主键.en})
) DEFAULT CHARSET=utf8;
alter table ${数据字典表.en}  comment '数据字典表';

drop table if exists ${数据字典明细表.en};
create table ${数据字典明细表.en} (
	<@base.code/>
	${编码.en} 		varchar(100) 	comment '编码',
	${名称.en} 		varchar(100) 	comment '名称',
	${数据字典主键.en}  bigint(20) 		comment '1#数据字典主键#${主键.en}#${数据字典表.en}#${名称.en}',
	/*basic场景字段*/ 
	foreign key(${数据字典主键.en}) references ${数据字典表.en}(${主键.en}),
	primary key(${主键.en})
)DEFAULT CHARSET=utf8;
alter table ${数据字典明细表.en}  comment '数据字典明细表';


drop table if exists ${参数配置表.en};
create table ${参数配置表.en}
(
<@base.code/>
	${参数编码.en} 	varchar(50) comment '参数编码',
	${参数值.en} 	text 		comment '参数值',
	/*basic场景字段*/ 
	/*约束*/
	primary key(${主键.en})
) DEFAULT CHARSET=utf8;
alter table ${参数配置表.en}  comment '参数配置表';