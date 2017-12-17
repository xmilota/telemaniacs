telemaniacsApp.controller('ScheduleListController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.setPageName('TV Schedule');
        pageService.useSchedulerLayout();

        pageService.getDataAsync('schedule/date/123').then(function (response) {
            $scope.telemaniacs = telemaniacs;
            $scope.schedules = response['channelSchedules'];
        });
    }
]);