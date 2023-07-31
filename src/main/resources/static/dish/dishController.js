angular.module('findFood').controller('dishController', function ($rootScope, $scope, $http, $location, $localStorage, $window) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1/dishes';

    //включение дополнительных пунктов меню для страницы
    $rootScope.currentPage = 'dish';

    var restaurant;

    restaurant = $localStorage.currentRestaurant;

    $scope.showCurrentRestaurantTitle = function(){
        return $localStorage.currentRestaurant.title;
    };


    $scope.Dish;

     $scope.loadDishById = function () {
        var id;
        id = $localStorage.dishToEdit.id;
         $http.get(contextPath + '/' + id)
             .then(function (response) {
                 $scope.Dish = response.data;
                 $localStorage.dishToEdit = $scope.Dish;
             });
     };









    $scope.loadDishById();


    //переходы
    $rootScope.showDishesPage = function () {
        $location.path('dishes');
    };



});