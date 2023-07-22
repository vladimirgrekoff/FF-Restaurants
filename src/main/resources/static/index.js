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
        .otherwise({
            redirectTo: '/welcome'
        });
    }
})();

angular.module('findFood').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {

    $rootScope.isCurrentPageRestaurants = function () {
        if ($rootScope.currentPage == 'restaurants') {
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


});