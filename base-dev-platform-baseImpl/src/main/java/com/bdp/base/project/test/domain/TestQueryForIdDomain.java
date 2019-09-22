package com.bdp.base.project.test.domain;

import com.bdp.base.project.test.dto.TestQueryDTO;
import com.bdp.exception.Assert;
import com.bdp.jdbc.cmd.Command;
import com.bdp.jdbc.cmd.ExecuteQueryForObjectCmd;
import com.bdp.jdbc.db.Domain;
import com.bdp.jdbc.db.JdbcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @ClassName: TestQueryForIdDomain
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/22 23:34
 * @version : 1.0
 */
@Service
public class TestQueryForIdDomain implements Domain {

    @Autowired
    @Qualifier("primaryJdbcContext")
    private JdbcContext primaryJdbcContext;
    public TestQueryDTO queryForId(String id) throws Exception {
        //参数获取
        //参数校验
        Assert.isNotNull(id,"${0}不为空",id);
        //业务逻辑
        String sql = "select id, code, name, table_name tableName from t_test where id=:id";
        Map<String,Object> wheres = new HashMap<>();
        wheres.put("id",id);

        Command<TestQueryDTO> command = new ExecuteQueryForObjectCmd<TestQueryDTO>(sql,wheres,TestQueryDTO.class);
        //返回值处理
        return command.execute(primaryJdbcContext);
    }
}



