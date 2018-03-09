var request = require('request');
request("https://usercountry.com/v1.0/json/136.33.35.253?token=b4fb217e67ac8b8df70c61562d3862d76b65e6375b6ac258", function (error, response, body) {
    //Check for error
    if(error){
        return console.log('Error:', error);
    }

    //Check for right status code
    if(response.statusCode !== 200){
        return console.log('Invalid Status Code Returned:', response.statusCode);
    }
//	console.log(body);
    //All is good. Print the body
    body = JSON.parse(body);

        console.log(body.country.name);
        console.log(body.language.name);
        console.log(body.language.code);
        console.log(body.region.city);
        console.log(body.region.state);


});