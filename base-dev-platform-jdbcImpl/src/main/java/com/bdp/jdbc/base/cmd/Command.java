package com.bdp.jdbc.base.cmd;


import com.bdp.jdbc.db.JdbcContext;

public interface Command<R> {
     R execute(JdbcContext context) throws Exception;
}