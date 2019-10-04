package com.genetor.sence.crud;

import lombok.Data;

import java.sql.Connection;

@Data
public class CrudConfig {
    protected String scene;
    protected String schema;
	protected String tableName;
    protected String project;
    protected String outpath;
    protected String author;
    protected String version;
    protected String exclude;
    protected String scenceName;
    protected String prefix;
    protected String parent;
    protected Connection conn;
}
