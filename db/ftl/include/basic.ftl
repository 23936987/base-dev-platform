<#macro code isState=true >
	/* 基础字段 */
	${更改键.en} varchar(50) not null comment '更改键', 
	${创建时间.en} datetime default now()  comment '创建时间',
	${修改时间.en} datetime default now() comment '修改时间', 
	${创建人.en} bigint(20) comment '创建人',
	${修改人.en} bigint(20) comment '修改人', 
	${创建人名称.en} varchar(100) comment '创建人名称',
	${修改人名称.en} varchar(100) comment '修改人名称', 
	${排序.en} int(10) comment '排序',
	${备注.en} varchar(500) comment '备注',
<#if isState>
	${状态.en} int(2) comment '2#状态#0 无效,1 生效,2 删除#state_type',
</#if>
</#macro>
 