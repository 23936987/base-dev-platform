
var sqlite3 = require('sqlite3').verbose();

let query = function( querySql, values ) {
    // 返回一个 Promise
    return new Promise(( resolve, reject ) => {
        var file = "db/data.db";
        var db = new sqlite3.Database(file);
        db.all(querySql, values, function (err, rows) {
            if (err) {
                reject( err )
            } else {
                resolve( rows )
            }
            db.close();
        });
    })
};


module.exports = {
    query:query
};