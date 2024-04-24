const express = require('express');
const bodyParser = require('body-parser');
const routersHandler = require('./routes/handler.js');
//const pool = require('./config/db.js');


const app = express();
app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());
app.use('/', routersHandler);

const PORT = 4000; // backend routing port
app.listen(PORT, () => {
    console.log(`server is running on port ${PORT}`)
});
