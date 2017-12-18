telemaniacsApp.factory('AuthService', function ($rootScope, $http, $location, PageService) {
    var authService = {};
    var _userRestPrefix = 'rest/user'

    authService.login = function (userAuthenticate) {
        console.log("AuthService" + userAuthenticate);

        return $http({
            url: _userRestPrefix + '/authenticate',
            method: 'POST',
            data: userAuthenticate
        }).then(function (response) {
            console.log('user authenticated ' + userAuthenticate.email);
            var user = PageService.getDataAsync('user/email/' + userAuthenticate.email);
            // var user = $http.get(_userRestPrefix + '/email/' + userAuthenticate.email).then(function (response) {
            //     return response.data;
            // });
            console.log(user.id);
            PageService.setUser(user);

        }, function (reason) {
            console.log(reason);
        })



        // return $http
        //     .post('/rest/transmission/add', userAuthenticate)
        //     // .data(userAuthenticate)
        //     .then(function (res) {
        //         return $http.get('/rest/user/email/' + userAuthenticate.email);
        //
        //         // TODO pokud success -> ziskat uzivatele podle emailu a nastavit ho do session
        //         // Session.create(res.data.id, res.data.user.id,
        //         //     res.data.user.role);
        //         // return res.data.user;
        //     });
    };

    authService.isAuthenticated = function () {
        return !!Session.userId;
    };

    authService.isAuthorized = function (authorizedRoles) {
        if (!angular.isArray(authorizedRoles)) {
            authorizedRoles = [authorizedRoles];
        }
        return (authService.isAuthenticated() &&
            authorizedRoles.indexOf(Session.userRole) !== -1);
    };

    return authService;
})