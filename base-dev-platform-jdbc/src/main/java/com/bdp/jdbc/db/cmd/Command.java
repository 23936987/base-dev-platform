package com.bdp.jdbc.db.cmd;


import com.bdp.jdbc.db.JdbcContext;

public interface Command<T> {
     T execute(JdbcContext context) throws Exception;
}