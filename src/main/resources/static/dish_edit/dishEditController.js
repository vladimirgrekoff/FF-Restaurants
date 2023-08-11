angular.module('findFood').controller('dishEditController', function ($rootScope, $scope, $http, $location, $localStorage, $window) {

    const contextPath = 'http://localhost:8189/ff-restaurants/api/v1/dishes';

    //включение дополнительных пунктов меню для страницы
    $rootScope.currentPage = 'dish_edit';

    var restaurant;

    restaurant = $localStorage.currentRestaurant;

    $scope.showCurrentRestaurantTitle = function(){
        return $localStorage.currentRestaurant.title;
    };


    $scope.beforeEditDish = $localStorage.dishToEdit;
    $scope.editedDish = $localStorage.dishToEdit;
    $scope.groupList = $localStorage.groupDishList;
    $scope.categoriesList = $localStorage.categoriesDishList;

    //переменные для проверки внесеия изменений
    $scope.title = $scope.beforeEditDish.title;
    $scope.description = $scope.beforeEditDish.description;
    $scope.group_dish_title = $scope.beforeEditDish.groupDishTitle;
    $scope.category_title = $scope.beforeEditDish.categoryTitle;
    $scope.calories = $scope.beforeEditDish.calories;
    $scope.fats = $scope.beforeEditDish.fats;
    $scope.proteins = $scope.beforeEditDish.proteins;
    $scope.carbohydrates = $scope.beforeEditDish.carbohydrates;
    $scope.price = $scope.beforeEditDish.price;

    $scope.isInitGroupValue = function(){
        for(i=0; i<$scope.groupList.length; i++){
            if($scope.groupList[i].title == $scope.beforeEditDish.groupDishTitle){
                return $scope.groupDishTitle = $scope.groupList[i].title;
            }
        }
    };


    $scope.isInitCategoryValue = function(){
        for(j=0; j<$scope.categoriesList.length; j++){
            if($scope.categoriesList[j].title == $scope.beforeEditDish.categoryTitle){
                return $scope.categoryTitle = $scope.categoriesList[j].title;
            }
        }
    };

    $scope.updateDish = function(){
        if ($scope.isEmptyDishData() == false){
            $scope.editedDish.groupDishTitle = $scope.groupDishTitle;
            $scope.editedDish.categoryTitle = $scope.categoryTitle;
            var editedDish = $scope.editedDish;
            if($scope.checkForChanges(editedDish)){
                $http.put(contextPath, editedDish)
                    .then(function (response) {
                        $scope.editedDish.title = null;
                        $scope.editedDish.description = null;
                        $scope.editedDish.groupDishTitle = null;
                        $scope.editedDish.categoryTitle = null;
                        $scope.editedDish.calories = null;
                        $scope.editedDish.fats = null;
                        $scope.editedDish.proteins = null;
                        $scope.editedDish.carbohydrates = null;
                        $scope.editedDish.price = null;
                        $scope.groupDishTitle = '';
                        $scope.categoryTitle = '';
                        alert("Изменения в БД внесены");
                });
            }
        }
    };

    $scope.checkForChanges = function(after){
        if($scope.title == after.title &&
        $scope.description == after.description &&
        $scope.group_dish_title == after.groupDishTitle &&
        $scope.category_title == after.categoryTitle &&
        $scope.calories == after.calories &&
        $scope.fats == after.fats &&
        $scope.proteins == after.proteins &&
        $scope.carbohydrates == after.carbohydrates &&
        $scope.price == after.price){
                alert("При выполнении правки информации о блюде " +
                    "\nне внесено ни одного изменения! " +
                    "\nВыполнять обращение к базе данных не имеет смысла. " +
                    "\nВы можете вернуться на страницу списка блюд и " +
                    "\nвыбрать другое блюдо для внесения изменений.");
                return false;
           }
        return true;
    };

    $scope.undoChanges = function(){
        if($scope.title != $scope.editedDish.title){
            $scope.editedDish.title = $scope.title;
        }
        if($scope.description != $scope.editedDish.description){
            $scope.editedDish.description = $scope.description;
        }
        if($scope.group_dish_title != $scope.groupDishTitle){
            $scope.groupDishTitle = $scope.group_dish_title;
        }
        if($scope.category_title != $scope.categoryTitle){
            $scope.categoryTitle = $scope.category_title;
        }
        if($scope.calories != $scope.editedDish.calories){
            $scope.editedDish.calories = $scope.calories;
        }
        if($scope.fats != $scope.editedDish.fats){
            $scope.editedDish.fats = $scope.fats;
        }
        if($scope.proteins != $scope.editedDish.proteins){
            $scope.editedDish.proteins = $scope.proteins;
        }
        if($scope.carbohydrates != $scope.editedDish.carbohydrates){
            $scope.editedDish.carbohydrates = $scope.carbohydrates;
        }
        if($scope.price != $scope.editedDish.price){
            $scope.editedDish.price = $scope.price;
        }
    };

    $scope.isEmptyDishData = function(){
        if($scope.editedDish == undefined || $scope.editedDish == null){
            alert("При правке информации о блюде все поля должны быть заполнены!");
            return true;
        }
        if($scope.editedDish.title == undefined || $scope.editedDish.title == null || $scope.editedDish.title == ''){
            alert("Поле 'Название' должно быть заполнено!");
            return true;
        }
        if($scope.editedDish.description == undefined || $scope.editedDish.description == null || $scope.editedDish.description == ''){
            alert("Поле 'Описание' должно быть заполнено!");
            return true;
        }
        if($scope.groupDishTitle == undefined || $scope.groupDishTitle == ''){
            alert("'Группа блюд' должна иметь одно из значений в списке 'Выбор группы'!");
            return true;
        }
        if($scope.categoryTitle == undefined || $scope.categoryTitle == ''){
            alert("'Категория блюда' должна иметь одно из значений в списке 'Выбор категории'!");
            return true;
        }
        if($scope.editedDish.calories == undefined || $scope.editedDish.calories == null || $scope.editedDish.calories == ''){
            if(angular.isNumber($scope.editedDish.calories) == false){
                alert("Поле 'Калории' должно быть заполнено!");
                return true;
            }
        }
        if($scope.editedDish.fats == undefined || $scope.editedDish.fats == null || $scope.editedDish.fats == ''){
            if(angular.isNumber($scope.editedDish.fats) == false){
                alert("Поле 'Жиры' должно быть заполнено!");
                return true;
            }
        }
        if($scope.editedDish.proteins == undefined || $scope.editedDish.proteins == null || $scope.editedDish.proteins == ''){
            if(angular.isNumber($scope.editedDish.proteins) == false){
                alert("Поле 'Белки' должно быть заполнено!");
                return true;
            }
        }
        if($scope.editedDish.carbohydrates == undefined || $scope.editedDish.carbohydrates == null || $scope.editedDish.carbohydrates == ''){
            if(angular.isNumber($scope.editedDish.carbohydrates) == false){
                alert("Поле 'Углеводы' должно быть заполнено!");
                return true;
            }
        }
        if($scope.editedDish.price == undefined || $scope.editedDish.price == null || $scope.editedDish.price == ''){
            alert("Поле 'Стоимость' должно быть заполнено!");
            return true;
        }
        return false;
    };


    //переходы
    $rootScope.showDishPage = function () {
        $location.path('dish');
    };

});