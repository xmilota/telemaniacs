telemaniacsApp.controller('LoginController', [
    '$scope',
    '$rootScope',
    'AuthService',

    function ($scope, $rootScope, authService) {
        $scope.userAuthenticate = {
            email: '',
            password: ''
        };
        $scope.login = function (userAuthenticate) {
            console.log("LoginController" + userAuthenticate);
            authService.login(userAuthenticate);
            //     .then(function (user) {
            //     // $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            //     console.log("setting current user: " + user)
            //     $scope.setCurrentUser(user);
            // }, function () {
            //     // $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
            // });
        };
    }
])