function Hello($scope, $http) {
    $http.get('http://localhost:8080/myproject/rest/login').
        success(function(data) {
            $scope.login = data;
        });
}