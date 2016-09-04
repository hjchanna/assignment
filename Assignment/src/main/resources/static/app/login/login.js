(function () {
    angular.module("loginModule", ["ngAnimate"]);

    var loginController = function ($scope, $location, authenticationFactory) {
        $scope.loginError = false;

        $scope.login = function () {

            var loginRequest = {
                "username": $scope.user.username,
                "password": $scope.user.password
            };

            authenticationFactory.attemptLogin(loginRequest)
                    .success(function (data, status, headers) {
                        $scope.loginError = false;
                        authenticationFactory.login(data);

                        $scope.checkLogin();
                    })
                    .error(function (data, status, headers) {
                        console.log(data);
                        $scope.user = {};
                        $scope.loginError = true;
                    });
        };

        $scope.checkLogin = function () {
            if (authenticationFactory.isLoggedIn()) {
                if (authenticationFactory.getLoginType() === "CUSTOMER") {
                    $location.path('/place-order');
                } else {
                    $location.path('/confirm-order');
                }
            }
        };

        $scope.checkLogin();
    };


    angular.module("loginModule")
            .controller("loginController", loginController);
}());