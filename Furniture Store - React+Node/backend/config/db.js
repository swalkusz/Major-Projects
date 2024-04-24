// połączenie z bazą danych MySQL
const mysql = require('mysql');
//require('dotenv/config');

const pool = mysql.createPool({
    host: "localhost",
    user: "root",
    password: "",
    database: "furniturestore"
});

module.exports = pool;
