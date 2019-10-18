var express = require('express');
var router = express.Router();

let query =require("../db/SqliteConnection").query;




router.get('/getItems/:code', async function(req, res) {

    var params =  req.params;



    var querySql =  "select t.code,t.name " +
        " from sys_dict_list t " +
        " join sys_dict d on d.id=t.dict_id" +
        " where d.code=?";

    var rows =  await query(querySql,[params.code]);

    res.send({
        "errorCode":"0",
        "errorMsg":"成功",
        "body":{
            "list":rows
        }
    });
});
module.exports = router;
