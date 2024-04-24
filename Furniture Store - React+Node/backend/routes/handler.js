const express = require('express');
const router = express.Router();
const pool = require('../config/db.js');


router.get('/shop', (req, res) => {
    pool.getConnection( (err,conn) => {
        if (err) throw err;
        try {
            const qry = 'SELECT * FROM products';
            conn.query(qry, (err, result) => {
                conn.release();
                if (err) throw err;
                res.send(JSON.stringify(result));
            });
        }
        catch (err) {
            console.log(err);
            res.end();
        }
    });
});

router.get('/product?', (req, res) => {  
    pool.getConnection( (err,conn) => {
        if (err) throw err;
        try {
            const qry = 'SELECT * FROM products ';
            conn.query(qry ,(err, result) => {
                conn.release();
                if (err) throw err;
                res.send(JSON.stringify(result));
            });
        }
        catch (err) {
            console.log(err);
            res.end();
        }
    });
});

 
router.get('/cart', (req, res) => {
    pool.getConnection( (err,conn) => {
        if (err) throw err;
        try {
            const qry = 'SELECT * FROM `cart`, `products` WHERE cart.product_ID = products.product_ID AND cart.status = "InCart" AND cart.client_ID = "1";';
            conn.query(qry , (err, result) => {
                conn.release();
                if (err) throw err;
                res.send(JSON.stringify(result));
            });
        }
        catch (err) {
            console.log(err);
            res.end();
        }
    });
    //res.end('NA');
});

router.post('/removeElement', async(req, res) => {
    const cart_ID = req.body.removeElement;
    pool.getConnection( (err,conn) => {
        if (err) throw err;
        const qry = 'DELETE FROM cart WHERE `cart`.`cart_ID` = '+cart_ID+';';
        conn.query(qry , (err, result) => {
            conn.release();
            if (err) throw err;
        });
        res.redirect('/cart');
        res.end();
    });
    //res.end('NA');
});

router.post('/updateNumAvailableProduct', async(req, res) => {
    const product_ID = req.body.productID;
    const numElements = req.body.numItems;
    pool.getConnection( (err,conn) => {
        if (err) throw err;
            const qry = 'INSERT INTO `cart`(`cart_ID`, `product_ID`, `num_of_items`, `client_ID`, `status`) VALUES (null,'+product_ID+','+numElements+',1,DEFAULT);';
            conn.query(qry , (err, result) => {
                conn.release();
                if (err) throw err;
            });
    });
    pool.getConnection( (err,conn) => {
        if (err) throw err;
            const qry = 'UPDATE `products` SET `number_of_pieces_available` = (number_of_pieces_available-'+numElements+') WHERE `products`.`product_ID` = '+product_ID+';';
            conn.query(qry , (err, result) => {
                conn.release();
                if (err) throw err;
            });
            res.redirect('/product?'+product_ID);
            res.end();
    });
});


module.exports = router;