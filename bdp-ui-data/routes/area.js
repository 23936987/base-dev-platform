var express = require('express');
var router = express.Router();

let query =require("../db/SqliteConnection").query;




router.get('/nodes', async function(req, res) {

    var querySql =  " select  cast(area_code as int)  areaCode,area_name areaName, cast(parent_code as int)  parentCode from area   ";
    var rows =  await query(querySql,[]);

    res.send({
        "errorCode":"0",
        "errorMsg":"成功",
        "body":{
            "list":rows
        }
    });
});


router.get('/nodesByParentCode', async function(req, res) {

    var parentCode = req.query.pId;

    if(parentCode == null){
        parentCode = '52'
    }
    var querySql =  " select area_code areaCode,area_name areaName,parent_code parentCode, 'true' isParent from area where parent_code=?";
    var rows =  await query(querySql,[parentCode]);

    res.send({
        "errorCode":"0",
        "errorMsg":"成功",
        "body":{
            "list":rows
        }
    });
});

module.exports = router;