/**
 * Created by Marmik on 04/10/2016.
 */
var express = require('express');
var app = express();
var request = require('request');
app.get('/getText', function (req, res) {
    var result={

    };

    request('https://usercountry.com/v1.0/json/134.193.202.248?token=b4fb217e67ac8b8df70c61562d3862d76b65e6375b6ac258', function (error, response, body) {
        //Check for error
        if(error){
            return console.log('Error:', error);
        }

        //Check for right status code
        if(response.statusCode !== 200){
            return console.log('Invalid Status Code Returned:', response.statusCode);
        }
        //All is good. Print the body
        body = JSON.parse(body);
        //console.log(body.response.text);
        result = body;
        console.log(result);

        res.contentType('application/json');
        res.write(JSON.stringify(result));
        res.end();
    });
    console.log(result);


})

var server = app.listen(8081, function () {
    // var host = server.address().address
    var port = server.address().port

    console.log("Example app listening at http://127.0.0.1:%s",port)
})