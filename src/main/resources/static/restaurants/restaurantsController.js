angular.module('findFood').controller('restaurantsController', function ($rootScope, $scope, $http, $location, $localStorage) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1/restaurants';


    $rootScope.currentPage = 'restaurants';

    $scope.loadAllRestaurants = function () {

        $http.get(contextPath + '/all')
            .then(function (response) {
                $scope.RestaurantsList = response.data;
            });
    };


    $scope.showRestaurantPage = function (restaurant) {
              $localStorage.currentRestaurant = restaurant;
        $location.path('restaurant');
    };
    $scope.loadAllRestaurants();
});