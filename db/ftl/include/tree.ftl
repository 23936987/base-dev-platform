
<#macro code isState=true isDelete=true isParent=false>
	${更改键.en} varchar(50) not null comment '更改键',	 
	${创建时间.en} datetime default now()  comment '创建时间',
	${修改时间.en} datetime default now()  comment '修改时间', 
	${创建人.en} BIGINT(20) comment '创建人',
	${修改人.en} BIGINT(20) comment '修改人',
	${创建人名称.en} varchar(100) comment '创建人名称',
	${修改人名称.en} varchar(100) comment '修改人名称', 
	${排序.en} int(10) comment '排序',
	${备注.en} varchar(500) comment '备注', 
	
	${名称.en} varchar(500) comment '名称', 
	${名称路径.en} varchar(500) comment '名称路径', 
	${级别.en} int(2) comment '级别',
	${左值.en} int(11) comment '左值',
	${右值.en} int(11) comment '右值',
	${叶子.en} int(1) comment '2#叶子#0 否,1 是#yesOrNo', 
<#if isState>
	${状态.en} int(2) comment '2#状态#0 无效,1 生效#state_type',
</#if>
<#if isDelete>
	${删除标志.en} int(2) comment '2#删除标志#0 无效,1 生效#delete_flag',
</#if>	 
<#if isParent>
	${上级.en} BIGINT(20) comment '上级',
</#if>	 
</#macro>