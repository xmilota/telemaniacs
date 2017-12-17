telemaniacsApp.config(function($routeProvider) {
    $routeProvider
        .when('/schedule', {
            controller: 'ScheduleListController',
            templateUrl: 'app/schedule/list.html'
        })

        .when('/details/:id', {
            controller: 'TransmissionDetailsController',
            templateUrl: 'app/transmission/detail.html'
        })

        .when('/admin/channels/create', {
            controller: 'ChannelsCreateController',
            templateUrl: 'app/channels/edit.html',
            data: {
                authorizedRoles: []
            }
        })

        .when('/admin/channels/edit/:id', {
            controller: 'ChannelsCreateController',
            templateUrl: 'app/channels/edit.html',
            edit: true
        })
        
        .when('/admin/channels', {
            controller: 'ChannelsListController',
            templateUrl: 'app/channels/list.html'
        })

        .when('/admin/transmissions/create', {
            controller: 'TransmissionsCreateController',
            templateUrl: 'app/transmission/edit.html'
        })

        .when('/admin/transmissions/edit/:id', {
            controller: 'TransmissionsCreateController',
            templateUrl: 'app/transmission/edit.html',
            edit: true
        })

        .when('/admin/transmissions', {
            controller: 'TransmissionsListController',
            templateUrl: 'app/transmission/list.html'
        })

        .when('/admin/occurrences/:id', {
            controller: 'TransmissionOccurrencesListController',
            templateUrl: 'app/transmissionOccurrence/list.html'
        })

        .when('/admin/occurrences/edit/:id', {
            controller: 'TransmissionOccurrencesCreateController',
            templateUrl: 'app/transmissionOccurrence/edit.html',
            edit: true
        })
        
        .when('/shows/find', {
            controller: 'TransmissionsFindController',
            templateUrl: 'app/transmission/find.html'
        })

        .when('/user-profile/channels', {
            controller: 'MyChannelController',
            templateUrl: 'app/channels/myChannelList.html'
        })

        .when('/user-profile/shows', {
            controller: 'MyTransmissionController',
            templateUrl: 'app/transmission/myTransmissionList.html'
        })

        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'app/login/login.html'
        })

        .otherwise({ redirectTo: '/schedule' });
});

// telemaniacsApp.run(function ($rootScope, $location, $window, loggedUserFactory) {
//
//     loggedUserFactory.getPrincipal(
//         function (response) {
//
//             var values = JSON.parse(response.data);
//
//             $rootScope.principal = values.username;
//             $rootScope.role = values.role;
//         },
//         function (response) {
//             alert("An error occurred when getting the logged user.");
//         }
//     );
//
//     $rootScope.unsuccessfulResponse = function (response) {
//         if (response.status === 403) {
//             $rootScope.page = $location.path();
//             $location.path("/forbidden");
//         } else if (response.status === 401) {
//             $window.location.href = "login.html";
//         } else if (response.status === 400 || response.status === 409) {
//             document.getElementById('errorOutput').style.display = 'block';
//             setTimeout(function () {
//                 document.getElementById('errorOutput').style.display = 'none';
//             }, 3000);
//         }
//     };
//
// });