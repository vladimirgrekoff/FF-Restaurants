angular.module('findFood').controller('dishController', function ($rootScope, $scope, $http, $location, $localStorage, $window) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1/dishes';

    //включение дополнительных пунктов меню для страницы
    $rootScope.currentPage = 'dish';

    var restaurant;

    restaurant = $localStorage.currentRestaurant;

    $scope.showCurrentRestaurantTitle = function(){
        return $localStorage.currentRestaurant.title;
    };


    //загруженное блюдо
    $scope.Dish = $localStorage.dishToEdit;












    //переходы
    $rootScope.showDishesPage = function () {
        $location.path('dishes');
    };

});