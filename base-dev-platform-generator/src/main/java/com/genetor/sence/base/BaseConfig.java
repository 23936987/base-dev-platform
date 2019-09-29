package com.genetor.sence.base;

import lombok.Data;

import java.sql.Connection;

@Data
public class BaseConfig {
    protected String scene;
    protected String schema;
	protected String tableName;
    protected String parent;
    protected String outpath;
    protected String author;
    protected String version;
    protected String exclude;
    protected String scenceName;
    protected Connection conn;
}
