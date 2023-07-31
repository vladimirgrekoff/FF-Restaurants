angular.module('findFood').controller('restaurantRequestsController', function ($rootScope, $scope, $http, $location, $localStorage, $window) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1';

    //включение дополнительных пунктов меню для страницы
    $rootScope.currentPage = 'restaurant_requests';


    var restaurant;

    restaurant = $localStorage.currentRestaurant;

    $scope.showCurrentRestaurantTitle = function(){
        return $localStorage.currentRestaurant.title;
    };

    $localStorage.guestMailBoxId = $localStorage.currentRestaurant.title;///////////////////////////////////
    $scope.requestId;
    $scope.tempId;

//    if (!$localStorage.guestMailBoxId) {
//        $http.get('http://localhost:8189/ff-restaurants/api/v1/mail_box/generate_id')
//            .then(function (response) {
//            $localStorage.guestMailBoxId = response.data.value;
//            console.log('guestMailBoxId ' + localStorage.guestMailBoxId);///////////////////////////////////
//        });
//    }



    $scope.loadRestaurantRequest = function(){

        $http.get(contextPath + '/requests/'+ $localStorage.currentRestaurant.title)
            .then(function (response) {
                console.log('запрос записей запроса по имени ресторана ' + response.data.items);///////////////////////////////////////
                $scope.restaurantRequests = response.data;
            });
    };

    //управление элементами страницы
    $scope.showDetailsItems = function(request){
        $scope.requestItems = request.items;
        $scope.tempId = $scope.requestId;
        $scope.requestId = request.id;
    };

    $scope.showDetails = true;
    $scope.showDetailsTable = function () {
        if($scope.requestId != undefined && $scope.showDetails == false) {
            if($scope.tempId == $scope.requestId){
                return $scope.showDetails = true;
            } else {
                $scope.showDetails = true;
            }
        }
        if($scope.showDetails){
            $scope.showDetails = false;
            return $scope.showDetails;

        } else {
            $scope.showDetails = true;
            return $scope.showDetails;
        }
    };

    //переходы
    $scope.showDishVerificationPage = function (requestId, item) {
        $localStorage.requestId = requestId;
                console.log('id запроса до перехода ' + $localStorage.requestId);////////////////////////////
        $localStorage.dishId = item.dishId;
                console.log('id блюда до перехода ' + $localStorage.dishId);////////////////////////////
        $localStorage.item = item;
                console.log('id записи до перехода ' + $localStorage.item.id);////////////////////////////
        $location.path('restaurant_verification');
    };

    $scope.loadRestaurantRequest();
});