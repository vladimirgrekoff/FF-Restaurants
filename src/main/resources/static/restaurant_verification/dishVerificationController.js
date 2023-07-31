angular.module('findFood').controller('dishVerificationController', function ($rootScope, $scope, $http, $location, $localStorage, $window) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1';

    //включение дополнительных пунктов меню для страницы
    $rootScope.currentPage = 'restaurant_verification';

    var restaurant;

    restaurant = $localStorage.currentRestaurant;

    $scope.showCurrentRestaurantTitle = function(){
        return $localStorage.currentRestaurant.title;
    };

    $scope.item = $localStorage.item;

     $scope.loadDishById = function () {
        var id;
        id = $localStorage.dishId;
         $http.get(contextPath + '/dishes/' + id)
             .then(function (response) {
                 $scope.dish = response.data;
             });
     };

    $scope.dishApproved;
//    $scope.dishHealthy;

    $scope.updateRequestItem = function() {
            $scope.item.verified = true;
            $scope.item.dishHealthy = $scope.dishHealthy;
            $scope.item.dishApproved = $scope.dishApproved;
            $http.put(contextPath + '/requests', $scope.item)
                .then(function (response) {
                    alert("Изменения в БД внесены");
            });
    };



    $scope.updateDish = function(){
        if ($scope.isEmptyDishData() == false){
//            $scope.dish.healthy = $scope.dishHealthy;
            $scope.dish.approved = $scope.dishApproved;
//            var editedDish = $scope.dish;
                $http.put(contextPath + '/dishes', $scope.dish)
                    .then(function (response) {
                        $scope.loadDishById();
                        $scope.updateRequestItem();
                });
        }
    };


    $scope.isEmptyDishData = function(){
        if($scope.dish == undefined || $scope.dish == null){
            alert("При оценке качества блюда все поля должны быть заполнены!");
            return true;
        }
//        if($scope.dishHealthy == undefined || $scope.groupDishTitle == ''){
//            alert("Поле 'Здоровое питание' должно иметь одно из значений в списке 'Выбор оценки'!");
//            return true;
//        }
        if($scope.dishApproved == undefined || $scope.groupDishTitle == ''){
            alert("Поле 'Одобрено диетологом' должно иметь одно из значений в списке 'Выбор оценки'!");
            return true;
        }
        return false;
    };


    //переходы
    $rootScope.showRestaurantRequestsPage = function () {
        $location.path('restaurant_requests');
    };

    $scope.loadDishById();
});