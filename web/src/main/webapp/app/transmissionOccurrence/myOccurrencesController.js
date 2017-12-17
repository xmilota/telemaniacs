telemaniacsApp.controller('TransmissionOccurrencesListController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('Occurrences Administration');

        pageService.getDataAsync('/transmission/'+ $routeParams.id).then(function (response) {
            $scope.occurrences = response.occurrences;
            console.log($scope.occurrences);
        });


    }
]);
