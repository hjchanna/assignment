(function () {
    angular.module("indexModule", ["ngRoute", "homeModule", "loginModule", "placeOrderModule", "confirmOrderModule"]);

    //constants
    angular.module("indexModule").constant("systemConfig", {
        apiUrl: "http://hjchanna.herokuapp.com"
    });

    //route config
    angular.module("indexModule").config(function ($routeProvider) {
        $routeProvider
                .when("/", {
                    controller: "homeController",
                    templateUrl: "app/home/home.html"
                })

                .when("/login", {
                    controller: "loginController",
                    templateUrl: "app/login/login.html"
                })

                .when("/place-order", {
                    controller: "placeOrderController",
                    templateUrl: "app/place-order/place-order.html"
                })

                .when("/confirm-order", {
                    controller: "confirmOrderController",
                    templateUrl: "app/confirm-order/confirm-order.html"
                })

                .otherwise({redirectTo: "/"});

    });


    //factories
    angular.module("indexModule").factory("authenticationFactory", function ($rootScope, $http, systemConfig) {
        var factory = {};

        factory.attemptLogin = function (loginRequest) {
            var url = systemConfig.apiUrl + "/api/login";
            var config = factory.getConfig(loginRequest.username, loginRequest.password);

            return $http.post(url, JSON.stringify(loginRequest), config);
        };

        factory.login = function (loginRespond) {
            $rootScope.user = {};
            $rootScope.user.indexNo = loginRespond.indexNo;
            $rootScope.user.token = loginRespond.token;
            $rootScope.user.type = loginRespond.type;
        };

        factory.getSavedConfig = function () {
            if (factory.isLoggedIn()) {
                var config = {
                    "headers": {
                        "authorization": $rootScope.user.token,
                        "content-type": "application/json",
                        "X-Requested-With": 'XMLHttpRequest'
                    }
                };
                return config;
            } else {
                return null;
            }
        };

        factory.getLoginType = function () {
            if (factory.isLoggedIn()) {
                return $rootScope.user.type;
            } else {
                return null;
            }
        };

        factory.getUserId = function () {
            if (factory.isLoggedIn()) {
                return $rootScope.user.indexNo;
            } else {
                return null;
            }
        };

        factory.getConfig = function (username, password) {
            var token = username + ":" + password;
            var base64 = btoa(token);

            var config = {
                "headers": {
                    "authorization": "Basic " + base64,
                    "content-type": "application/json",
                    "X-Requested-With": 'XMLHttpRequest'
                }
            };
            return config;
        };

        factory.isLoggedIn = function () {
            if (angular.isObject($rootScope.user)) {
                return true;
            } else {
                return false;
            }
        };

        factory.logout = function () {
            $rootScope.user = null;
        };

        return factory;
    });

    angular.module("indexModule").factory("notificationFactory", function ($rootScope, $http, systemConfig, authenticationFactory) {
        var factory = {};

        factory.checkNotification = function (callback) {
            if (authenticationFactory.isLoggedIn()) {
                var config = authenticationFactory.getSavedConfig();

                var url = systemConfig.apiUrl + "/api/notifications";

                $http.post(url, config)
                        .success(function (data, status, headers) {
                            if (data.available) {
                                callback(data);
                            }
                        })
                        .error(function (data, status, headers) {
                            console.log("error :" + data + "-" + status + "-" + headers);
                        });
            }

            return null;
        };

        factory.consumeNotification = function () {
            var config = authenticationFactory.getSavedConfig();

            var url = systemConfig.apiUrl + "/api/consume";

            $http.post(url, config)
                    .success(function (data, status, headers) {
                        console.log("notification consumed");
                    })
                    .error(function (data, status, headers) {
                        console.log("error consume notification");
                    });
        };

        factory.listenAlert = function (callback) {
            var userId = authenticationFactory.getUserId();

            if (userId) {
                var socket = new SockJS('/alert');
                var stompClient = Stomp.over(socket);


                stompClient.connect({}, function (frame) {
                    stompClient.subscribe('/alert/' + userId, function (data) {
                        var body = data.body;
                        var message = JSON.parse(body);
                        console.log(message);

                        //check notification
                        factory.checkNotification(callback);
                    });
                });
            }
        };

        return factory;
    });
}());