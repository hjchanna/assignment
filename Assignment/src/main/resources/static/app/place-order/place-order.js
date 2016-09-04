(function () {
    angular.module("placeOrderModule", ["ngAnimate"]);
    //factory
    angular.module("placeOrderModule")
            .factory("placeOrderFactory", function ($http, authenticationFactory, systemConfig) {
                var factory = {};
                factory.loadItems = function (callback) {
                    var config = authenticationFactory.getSavedConfig();

                    if (config) {
                        var url = systemConfig.apiUrl + "/api/item";

                        $http.post(url, config)
                                .success(function (data, status, headers) {
                                    callback(data);
                                })
                                .error(function (data, status, headers) {
                                });
                    }
                };

                factory.processOrder = function (orders, callback) {
                    var config = authenticationFactory.getSavedConfig();

                    if (config) {
                        var url = systemConfig.apiUrl + "/api/place-order";

                        $http.post(url, JSON.stringify(orders), config)
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
    angular.module("placeOrderModule")
            .controller("placeOrderController", function ($scope, $location, $interval, authenticationFactory, notificationFactory, placeOrderFactory) {
                $scope.items = [];
                $scope.orderItems = [];
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
                $scope.mobileDisplayType = 0;

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

                //display type
                $scope.toggleMobileDisplayType = function () {
                    if ($scope.mobileDisplayType === 0) {
                        $scope.mobileDisplayType = 1;
                    } else {
                        $scope.mobileDisplayType = 0;
                    }
                };

                //add new item to the order
                $scope.addItem = function (item) {
                    var orderItem = null;
                    //find in existing items
                    angular.forEach($scope.orderItems, function (value, key) {
                        if (value.item.code === item.code) {
                            orderItem = value;
                        }
                    });

                    //not exist in order items
                    if (!orderItem) {
                        orderItem = {};
                        orderItem.item = item;
                        orderItem.quantity = 0;
                        $scope.orderItems.push(orderItem);
                    }

                    //place order quantity
                    orderItem.quantity++;
                };

                $scope.removeItem = function (orderItem, index) {
                    orderItem.quantity--;
                    if (orderItem.quantity === 0) {
                        $scope.orderItems.splice(index, 1);
                    }
                };

                //update order summary
                $scope.orderSummary = function () {
                    var orderSummary = 0.0;
                    angular.forEach($scope.orderItems, function (value) {
                        orderSummary = orderSummary + (value.item.price * value.quantity);
                    });
                    return orderSummary;
                };

                //submit order
                $scope.submitOrder = function () {
                    var orders = [];
                    angular.forEach($scope.orderItems, function (value) {
                        console.log(value.supplier);
                        orders.push({
                            "item": value.item.code,
                            "quantity": value.quantity,
                            "supplier": value.item.supplier
                        });
                    });

                    console.log(JSON.stringify(orders));

                    placeOrderFactory.processOrder(orders, function (data) {
                        $scope.modal.showModal("ORDER SUBMITTED", data.message, function () {});
                    });
                    $scope.orderItems = [];
                };

                //check login
                $scope.checkLogin();

                //load item information
                $scope.items = [];
                placeOrderFactory.loadItems(function (data) {
                    $scope.items = data.items;

                });

                //notification

                $scope.consumeNotification = function () {
                    notificationFactory.consumeNotification();
                };
                $scope.notificationDialog = function (data) {
                    $scope.modal.showModal("NOTIFICATION", data.message, $scope.consumeNotification);
                };
                //start notification service
                notificationFactory.listenAlert($scope.notificationDialog);
                //check old notifications
                notificationFactory.checkNotification($scope.notificationDialog);
            });
}());