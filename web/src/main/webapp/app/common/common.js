telemaniacsApp.controller('CommonController', [
    '$scope',
    '$timeout',
    'PageService',
    'AuthService',


    function ($scope, $timeout, pageService, authService) {
        $scope.currentUser = null;
        // $scope.userRoles = USER_ROLES;
        $scope.isAuthorized = authService.isAuthorized;
        $scope.pageService = pageService;
        $scope.authService = authService;

        $scope.$on('$viewContentLoaded', function () {
            $timeout(function () {
                telemaniacs.boot();
            });
        });
        $scope.setCurrentUser = function (user) {
            $scope.currentUser = user;
        };
    }
]);

telemaniacsApp.component('tmHeader', {
    templateUrl: 'app/common/header.html',
    bindings: {
        pageService: '<'
    }
});

telemaniacsApp.constant('AUTH_EVENTS', {
    loginSuccess: 'auth-login-success',
    loginFailed: 'auth-login-failed',
    logoutSuccess: 'auth-logout-success',
    sessionTimeout: 'auth-session-timeout',
    notAuthenticated: 'auth-not-authenticated',
    notAuthorized: 'auth-not-authorized'
});

telemaniacsApp.constant('USER_ROLES', {
    all: '*',
    admin: 'admin',
    guest: 'guest'
})

telemaniacsApp.directive('tmAlerts', function () {
    return {
        templateUrl: 'app/common/alerts.html'
    };
});