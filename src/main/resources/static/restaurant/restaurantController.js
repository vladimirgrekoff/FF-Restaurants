angular.module('findFood').controller('restaurantController', function ($rootScope, $scope, $http, $location, $localStorage, $window) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1/restaurants';

    //включение дополнительных пунктов меню для страницы
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
        $http.get(contextPath + '/info/restaurant/'+ id)
            .then(function (response) {
                $scope.restaurantInfo = response.data;
            });
    };

    //обработка выбора пунктов меню
    $scope.showDishes = true;
    $rootScope.showDishesList = function () {
        if(!$scope.showInfo){
            //Если откыта информация, закрыть
            $rootScope.showRestaurantInfo();
        }
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
        if(!$scope.showDishes){
            //Если откыт список блюд, закрыть
            $rootScope.showDishesList();
        }
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