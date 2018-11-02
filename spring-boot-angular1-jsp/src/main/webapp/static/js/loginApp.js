'use strict';

var app = angular.module('app', [
    'toaster',
]);

app.config(['$httpProvider', function($httpProvider) {  
    $httpProvider.defaults.headers.post["Content-Type"] =  
        "application/x-www-form-urlencoded";  
 $httpProvider.defaults.  
     transformRequest.unshift(function(data,headersGetter) {  
    var key, result = [];  
    for (key in data) {  
     if (data.hasOwnProperty(key)) {  
         result.push(encodeURIComponent(key) + "="  
                + encodeURIComponent(data[key]));  
     }  
 }  
 return result.join("&");  
});  
}]); 
