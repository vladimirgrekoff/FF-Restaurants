angular.module('findFood').controller('nutritionistController', function ($rootScope, $scope, $http, $location, $localStorage, $window) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1/restaurants';

    //включение дополнительных пунктов меню для страницы
    $rootScope.currentPage = 'nutritionist';




//    $localStorage.guestMailBoxId = $localStorage.currentRestaurant.title;///////////////////////////////////



//    $scope.loadRestaurantInfo = function(restaurant){
//        var id;
//        id = $localStorage.currentRestaurant.id;
//        $http.get(contextPath + '/info/restaurant/'+ id)
//            .then(function (response) {
//                $scope.restaurantInfo = response.data;
//            });
//    };

//    $scope.loadRestaurantInfo();
});