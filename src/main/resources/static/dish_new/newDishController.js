angular.module('findFood').controller('newDishController', function ($rootScope, $scope, $http, $location, $localStorage, $window) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1/dishes';

    //включение дополнительных пунктов меню для страницы
    $rootScope.currentPage = 'dish_new';

    var restaurant;

    restaurant = $localStorage.currentRestaurant;

    $scope.showCurrentRestaurantTitle = function(){
        return $localStorage.currentRestaurant.title;
    };

    $scope.newDish = {
        title:'',
        restaurant_title:'',
        description:'',
        price:null,
        calories:null,
        proteins:null,
        fats:null,
        carbohydrates:null,
        healthy:false,
        approved:false,
        category_title:''
    };

    $scope.newDish.restaurant_title = restaurant.title;
    $scope.groupList = $localStorage.groupDishList;
    $scope.categoriesList = $localStorage.categoriesDishList;




    //переходы
    $rootScope.showDishesPage = function () {
        $location.path('dishes');
    };

});