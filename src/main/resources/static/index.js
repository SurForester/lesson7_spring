angular.module('app', []).controller('productsController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                sort_col: $scope.filter ? $scope.filter.sort_col : null
            }
        })
            .then(function (response) {
                $scope.ProductsList = response.data.content;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.createProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (responce) {
                $scope.loadProducts();
            });
    }

    $scope.loadProducts();

});