angular.module('findFood').controller("createRequestController", function($rootScope, $scope, $http, $location, $window, $localStorage) {
    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1';

    //включение дополнительных пунктов меню для страницы
    $rootScope.currentPage = 'request_create';


    if($rootScope.currentPage != 'restaurant'){
        $rootScope.activeContent = '';
    }

    var restaurant;

    restaurant = $localStorage.currentRestaurant;

    $scope.showCurrentRestaurantTitle = function(){
        return $localStorage.currentRestaurant.title;
    };




//    $scope.loadDishes = function (offset, limit) {
//            $http({
//                url: contextPath + '/dishes',
//                method: 'GET',
//                params: {
//                    min_price: $scope.filter ? $scope.filter.min_price : null,
//                    max_price: $scope.filter ? $scope.filter.max_price : null,
//                    part_title: $scope.filter ? $scope.filter.part_title : null,
//                    offset: offset,
//                    limit: limit
//                }
//            }).then(function (response) {
//                $scope.DishesList = response.data.content;
//        });
//    };

     $scope.loadAllDishesByRestaurantId = function () {
        var id;
        id = $localStorage.currentRestaurant.id;
         $http.get(contextPath + '/dishes/restaurant/' + id)
             .then(function (response) {
                 $scope.DishesList = response.data;
             });
     };

//    console.log('ящик ' + $localStorage.guestMailBoxId);/////////////////////////////////



    $scope.loadMailBox = function () {
            $http.get(contextPath + '/mail_box/' + $localStorage.guestMailBoxId)
                .then(function (response) {
                $scope.mailBox = response.data;
        });
    };


    $scope.addDishToMailBox = function (dishId) {
        if($scope.isDishInList(dishId)){
            alert('Блюдо уже есть в списке!!!\nПопробуйте выбрать другое.');
            return;
        }
        $http.get(contextPath + '/mail_box/' + $localStorage.guestMailBoxId + '/add/' +dishId)
            .then(function (response) {
                dishId = null;
                $scope.loadMailBox();
            });
    };

    $scope.isDishInList = function (varId) {
        if($scope.mailBox.items.length > 0){
            for (i=0; i<$scope.mailBox.items.length; i++){
                if ($scope.mailBox.items[i].dishId == varId){
                    return true;
                }
            }
            return false;
        }
    }




    $scope.loadAllDishesByRestaurantId();
    $scope.loadMailBox();

});