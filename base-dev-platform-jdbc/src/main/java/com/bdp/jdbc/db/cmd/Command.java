package com.bdp.jdbc.db.cmd;


import com.bdp.jdbc.db.JdbcContext;

public interface Command<R> {
     R execute(JdbcContext context) throws Exception;
}