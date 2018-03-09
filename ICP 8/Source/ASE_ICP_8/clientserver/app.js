'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [])


    .controller('View1Ctrl', function ($scope, $http) {
        $scope.getText = function () {
            var srctxt = document.getElementById("ip").value;

            if (srctxt != "") {


               var handler = $http.get("https://usercountry.com/v1.0/json/"+srctxt+"?token=b4fb217e67ac8b8df70c61562d3862d76b65e6375b6ac258");

                handler.success(function (data) {
                  console.log(data);
                  //alert(data.text);
                    var c = data.country.name;
                    var l = data.language.name;
                    var lc = data.language.code;
                    var ct = data.region.city;
                    var st = data.region.state;
                    document.getElementById("notes").innerHTML = c +"</br>"+ l+"</br>" + lc  +"</br>"+ ct +"<br/>"+ st;


                })
                handler.error(function (data) {
                    alert("There was some error processing your request. Please try after some time.");
                });
            }
        }


    });
