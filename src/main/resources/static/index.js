(function () {
    angular
        .module('findFood', ['ngRoute', 'ngStorage'])
        .config(config)
//        .run(run)
;

    function config($routeProvider, $httpProvider) {

        $routeProvider.when('/welcome', {
            templateUrl:'welcome/welcomeTemplate.html',
            controller:'welcomeController',
            controllerAs:'welcome'

        })
        .when('/restaurants', {
            templateUrl:'restaurants/restaurantsTemplate.html',
            controller:'restaurantsController',
            controllerAs:'restaurants'
        })
        .when('/restaurant', {
            templateUrl:'restaurant/restaurantTemplate.html',
            controller:'restaurantController',
            controllerAs:'restaurant'
        })
        .when('/restaurant_info', {
            templateUrl:'restaurant_info/restaurantInfoTemplate.html',
            controller:'restaurantInfoController',
            controllerAs:'restaurant_info'
        })
        .when('/restaurant_edit_info', {
            templateUrl:'restaurant_edit_info/restaurantEditInfoTemplate.html',
            controller:'restaurantEditInfoController',
            controllerAs:'restaurant_edit_info'
        })
        .when('/dishes', {
            templateUrl:'dishes/dishesTemplate.html',
            controller:'dishesController',
            controllerAs:'dishes'
        })
        .when('/dish', {
            templateUrl:'dish/dishTemplate.html',
            controller:'dishController',
            controllerAs:'dish'
        })
        .when('/dish_edit', {
            templateUrl:'dish_edit/dishEditTemplate.html',
            controller:'dishEditController',
            controllerAs:'dish_edit'
        })
        .when('/dish_new', {
            templateUrl:'dish_new/newDishTemplate.html',
            controller:'newDishController',
            controllerAs:'dish_new'
        })
        .when('/request_create', {
            templateUrl:'request_create/createRequestTemplate.html',
            controller:'createRequestController',
            controllerAs:'request_create'
        })
        .when('/request_send', {
            templateUrl:'request_send/sendRequestTemplate.html',
            controller:'sendRequestController',
            controllerAs:'request_send'
        })
        .when('/restaurant_requests', {
            templateUrl:'restaurant_requests/restaurantRequestsTemplate.html',
            controller:'restaurantRequestsController',
            controllerAs:'restaurant_requests'
        })
        .when('/restaurant_verification', {
            templateUrl:'restaurant_verification/dishVerificationTemplate.html',
            controller:'dishVerificationController',
            controllerAs:'restaurant_verification'
        })
        .when('/nutritionist', {
            templateUrl:'nutritionist/nutritionistTemplate.html',
            controller:'nutritionistController',
            controllerAs:'nutritionist'
        })
        .when('/nutritionist_select', {
            templateUrl:'nutritionist_select/selectRestaurantTemplate.html',
            controller:'selectRestaurantController',
            controllerAs:'nutritionist_select'
        })
        .otherwise({
            redirectTo: '/welcome'
        });
    }
})();

angular.module('findFood').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {

    $rootScope.isCurrentPageNutritionistSelect = function () {
        if ($rootScope.currentPage == 'nutritionist_select') {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isCurrentPageNutritionist = function () {
        if ($rootScope.currentPage == 'nutritionist') {
            return true;
        } else {
            return false;
        }
    };


    $rootScope.isCurrentPageDish = function () {
        if ($rootScope.currentPage == 'dish') {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isCurrentPageEditDish = function () {
        if ($rootScope.currentPage == 'dish_edit') {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isCurrentPageAddNewDish = function () {
        if ($rootScope.currentPage == 'dish_new') {
            return true;
        } else {
            return false;
        }
    };


    $rootScope.isCurrentPageDishes = function () {
        if ($rootScope.currentPage == 'dishes') {
            return true;
        } else {
            return false;
        }
    };


    $rootScope.isCurrentPageRestaurantEditInfo = function () {
        if ($rootScope.currentPage == 'restaurant_edit_info') {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isCurrentPageRestaurantInfo = function () {
        if ($rootScope.currentPage == 'restaurant_info') {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isCurrentPageRestaurant = function () {
        if ($rootScope.currentPage == 'restaurant') {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isCurrentPageRestaurants = function () {
        if ($rootScope.currentPage == 'restaurants') {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isCurrentPageCreateRequest = function () {
        if ($rootScope.currentPage == 'request_create') {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isCurrentPageSendRequest = function () {
        if ($rootScope.currentPage == 'request_send') {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isCurrentPageRestaurantRequests = function () {
        if ($rootScope.currentPage == 'restaurant_requests') {
            return true;
        } else {
            return false;
        }
    };



    $rootScope.isCurrentPageDishVerification = function () {
        if ($rootScope.currentPage == 'restaurant_verification') {
            return true;
        } else {
            return false;
        }
    };


});