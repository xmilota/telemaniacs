telemaniacsApp.factory('PageService', function($rootScope, $http, $location) {
    var _title = '';
    var _pageName = '';
    var _useSchedulerLayout = false;
    var _restPrefix = 'rest/';
    $rootScope.user = {
        'id': null,
        'username': '',
        'isAdmin': null
    };

    return {
        getTitle: function () {
            var title = 'Telemaniacs (TM)';
            if (_title !== '') {
                title = _title + ' - ' + title;
            }

            return title;
        },

        setTitle: function (title) { _title = title; },

        getPageName: function () { return _pageName; },

        setPageName: function (pageName) { _pageName = pageName; },

        isSchedulerLayoutUsed: function () { return _useSchedulerLayout; },

        useSchedulerLayout: function () { _useSchedulerLayout = true; },

        isEditing: function (route) {
            return route.current.$$route.edit === true;
        },

        getDataAsync: function (url) {
            return $http.get(_restPrefix + url).then(function (response) {
                console.log("user by email successfully");
                return response.data;
            }, function (reason) {
                console.log(reason);
                return null;
            });
        },

        sendDataAsync: function (url, method, data, successMessage, successUrl, errorMessages) {
            var _this = this;
            return $http({
                url: _restPrefix + url,
                method: method,
                data: data
            }).then(function (response) {
                _this.pushSuccessMessage(successMessage);
                $location.path(successUrl);
            }, function (reason) {
                var type = reason.data.type;
                console.log("Failure code: " + type);

                var message = 'Unknown error.';
                if (type in errorMessages) {
                    message = errorMessages[type];
                } else if ('otherwise' in errorMessages) {
                    message = errorMessages['otherwise'];
                }

                message = message.replace('{msg}', reason.data.message);
                _this.pushErrorMessage(message);
                _this.consumeMessages();
            });
        },

        pushSuccessMessage: function (msg) {
            $rootScope.successQueue = msg;
        },

        pushWarningMessage: function (msg) {
            $rootScope.warningQueue = msg;
        },

        pushErrorMessage: function (msg) {
            $rootScope.errorQueue = msg;
        },

        consumeMessages: function () {
            $rootScope.success = $rootScope.successQueue;
            $rootScope.successQueue = null;

            $rootScope.warning = $rootScope.warningQueue;
            $rootScope.warningQueue = null;

            $rootScope.error = $rootScope.errorQueue;
            $rootScope.errorQueue = null;
        },

        getUser: function () {
            return $rootScope.user;
        },

        setUser: function (user) {
            console.log('setting logged user '+ user.username);
            $rootScope.user = user;
        },

        isLoggedIn: function () {
            return typeof this.getUser() !== typeof undefined && this.getUser() !== null;
        },

        isAdministrator: function () {
            return this.isLoggedIn() && this.getUser().isAdmin;
        }
    };
});

// // TODO presunout do samostatneho souboru
// telemaniacsApp.factory('AuthService', function ($http, $rootScope) {
//
//         return {
//             login : function (userEmail, password) {
//                 return $http
//                     .post("/rest/user/authenticate", userEmail, password)
//                     .then(function (response) {
//
//                     })
//                 // {
//                 //     url: "/rest/user/authenticate,
//                 //     method: "GET",
//                 //     data: data
//             }
//         }
//             dataFactory
//         }.getPrincipal = function (success, error) {
//             return $http.get(urlUser).then(success, error);
//         };
//
//         return dataFactory;
//     }
// ]);