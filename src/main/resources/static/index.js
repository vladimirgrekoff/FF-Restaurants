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
        .when('/nutritionist', {
            templateUrl:'nutritionist/nutritionistTemplate.html',
            controller:'nutritionistController',
            controllerAs:'nutritionist'
        })
        .otherwise({
            redirectTo: '/welcome'
        });
    }
})();

angular.module('findFood').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {


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

});