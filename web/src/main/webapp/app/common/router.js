telemaniacsApp.config(function($routeProvider) {
    $routeProvider
        .when('/schedule', {
            controller: 'ScheduleListController',
            templateUrl: 'app/schedule/list.html'
        })

        .when('/admin/channels/create', {
            controller: 'ChannelsCreateController',
            templateUrl: 'app/channels/create.html'
        })

        .otherwise({ redirectTo: '/schedule' });
});