(function () {
    angular.module("confirmOrderModule", ["ngAnimate"]);
    //factory
    angular.module("confirmOrderModule")
            .factory("confirmOrderFactory", function ($http, authenticationFactory, systemConfig) {
                var factory = {};
                factory.loadOrders = function (callback) {
                    var config = authenticationFactory.getSavedConfig();

                    if (config) {
                        var url = systemConfig.apiUrl + "/api/order-information";

                        $http.post(url, config)
                                .success(function (data, status, headers) {
                                    callback(data);
                                })
                                .error(function (data, status, headers) {
                                    console.log("error :" + data + "-" + status + "-" + headers);
                                });
                    }
                };

                factory.confirmOrder = function (order, callback) {
                    var config = authenticationFactory.getSavedConfig();

                    if (config) {
                        var url = systemConfig.apiUrl + "/api/confirm-order";

                        $http.post(url, JSON.stringify(order), config)
                                .success(function (data, status, headers) {
                                    callback(data);
                                })
                                .error(function (data, status, headers) {
                                    console.log("error :" + data + "-" + status + "-" + headers);
                                });
                    }
                };
                return factory;
            });
    //controller
    angular.module("confirmOrderModule")
            .controller("confirmOrderController", function ($scope, $location, $interval, authenticationFactory, notificationFactory, confirmOrderFactory) {
                $scope.orders = [];
                //
                $scope.modal = {};
                $scope.modal.showModal = function (title, content, onClick) {
                    $scope.modal.title = title;
                    $scope.modal.content = content;
                    $scope.modal.show = true;
                    $scope.modal.onClick = onClick;
                };
                $scope.modal.hideModal = function () {
                    $scope.modal.onClick();
                    $scope.modal.show = false;
                };

                //functions
                //check login
                $scope.checkLogin = function () {
                    if (!authenticationFactory.isLoggedIn()) {
                        $location.path("/");
                    }
                };

                //logout
                $scope.logout = function () {
                    authenticationFactory.logout();
                    $scope.checkLogin();
                };

                //submit order
                $scope.submitOrder = function (order) {
                    var confirmRequest = {
                        "order": order.indexNo,
                        "customer": order.customer.indexNo
                    };

                    var callback = function (data) {
                        $scope.modal.showModal("ORDER CONFIRMED", data.message, function () {
                            $scope.orders.splice($scope.orders.indexOf(order), 1);
                        });
                    };
                    confirmOrderFactory.confirmOrder(confirmRequest, callback);
                };

                //check login
                $scope.checkLogin();

                //load orders information
                $scope.reloadItems = function () {
                    $scope.orders = [];
                    confirmOrderFactory.loadOrders(function (data) {
                        $scope.orders = data.orders;
                    });
                };

                //load orders
                $scope.reloadItems();

                //notification

                $scope.consumeNotification = function () {
                    notificationFactory.consumeNotification();
                };
                $scope.notificationDialog = function (data) {
                    $scope.modal.showModal("NOTIFICATION", data.message, $scope.consumeNotification);

                    $scope.reloadItems();
                };

                //start notification service
                notificationFactory.listenAlert($scope.notificationDialog);
                //check old notifications
                notificationFactory.checkNotification($scope.notificationDialog);
            });
}());