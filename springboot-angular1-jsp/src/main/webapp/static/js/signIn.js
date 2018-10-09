/**
 *@author :hly
 *@github :github.com/SiriusHly
 @blog :blog.csdn.net/Sirius_hly
 *@date :2018/10/5
 */
'use strict';
angular.module('app').controller('SignInFormController',['$scope', '$http', 'toaster','$window',function($scope, $http, toaster,$window) {

    $scope.user ={
        "userId":'',
        "password":''
    };

    $scope.Login = function () {

        var user ={
            "userId": $scope.user.userId,
            "password": $scope.user.password
        };

        var url = "/login";

        $http.post(url,user).success(function (data) {
            $scope.result = data.result;
            if($scope.result=="0"){
                $scope.message="用户名或密码错误";
            }else {
                $scope.message="验证通过";
                $window.location.href='/index.jsp';
            }
        });
    };

}]);