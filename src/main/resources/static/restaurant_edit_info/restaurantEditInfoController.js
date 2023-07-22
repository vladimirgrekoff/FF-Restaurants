angular.module('findFood').controller('restaurantEditInfoController', function ($rootScope, $scope, $http, $location, $localStorage, $window) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1/restaurants';

    //включение дополнительных пунктов меню для страницы
    $rootScope.currentPage = 'restaurant_edit_info';

    var restaurant;

    restaurant = $localStorage.currentRestaurant;

    $scope.showCurrentRestaurantTitle = function(){
        return $localStorage.currentRestaurant.title;
    };

    $scope.beforeEditRestaurantInfo = $localStorage.restaurantInfoToEdit;
    $scope.editedRestaurantInfo = $localStorage.restaurantInfoToEdit;

    //переменные для проверки внесеия изменений
    $scope.description = $scope.beforeEditRestaurantInfo.description;
    $scope.cuisines = $scope.beforeEditRestaurantInfo.cuisines;
    $scope.address = $scope.beforeEditRestaurantInfo.address;
    $scope.phoneNumber = $scope.beforeEditRestaurantInfo.phoneNumber;
    $scope.email = $scope.beforeEditRestaurantInfo.email;
    $scope.openHours = $scope.beforeEditRestaurantInfo.openHours;

    $scope.updateRestaurantInfo = function(){
        if ($scope.isEmptyRestaurantInfoData() == false){
            var editedRestaurantInfo = $scope.editedRestaurantInfo;
            if($scope.checkForChanges(editedRestaurantInfo)){
                $http.put(contextPath + '/info', editedRestaurantInfo)
                    .then(function (response) {
                        $scope.editedRestaurantInfo.description = null;
                        $scope.editedRestaurantInfo.cuisines = null;
                        $scope.editedRestaurantInfo.address = null;
                        $scope.editedRestaurantInfo.phoneNumber = null;
                        $scope.editedRestaurantInfo.email = null;
                        $scope.editedRestaurantInfo.openHours = null;
                        alert("Изменения в БД внесены");
                });
            }
        }
    };



    //переходы
    $scope.showRestaurantInfoPage = function () {
        $location.path('restaurant_info');
    };

});