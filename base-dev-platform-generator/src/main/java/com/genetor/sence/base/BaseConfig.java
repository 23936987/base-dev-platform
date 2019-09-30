package com.genetor.sence.base;

import lombok.Data;

import java.sql.Connection;

@Data
public class BaseConfig {
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
