angular.module('findFood').controller('restaurantController', function ($rootScope, $scope, $http, $location, $localStorage, $window) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1/restaurants';


    $rootScope.currentPage = 'restaurant';

    var restaurant;

    restaurant = $localStorage.currentRestaurant;

    $scope.showCurrentRestaurantTitle = function(){
        return $localStorage.currentRestaurant.title;
    };


    $scope.showRestaurantDishes = function () {
        return $localStorage.currentRestaurant.dishesList;
    };

    $scope.loadRestaurantInfo = function(restaurant){
        var id;
        id = $localStorage.currentRestaurant.id;
        $http.get('http://localhost:8189/ff-restaurants/api/v1/restaurants/info/restaurant/'+ id)
            .then(function (response) {
                $scope.restaurantInfo = response.data;
            });
    };

    $scope.showDishes = true;
    $rootScope.showDishesList = function () {
        if($scope.showDishes){
            $scope.showDishes = false;
            return $scope.showDishes;
        } else {
            $scope.showDishes = true;
            return $scope.showDishes;
        }
    };

    $scope.showInfo = true;
    $rootScope.showRestaurantInfo = function () {
        if($scope.showInfo){
            $scope.showInfo = false;
            return $scope.showInfo;
        } else {
            $scope.showInfo = true;
            return $scope.showInfo;
        }
    };


    $scope.showRestaurantPage = function () {
        $location.path('restaurant');
    };
    $scope.loadRestaurantInfo();
});