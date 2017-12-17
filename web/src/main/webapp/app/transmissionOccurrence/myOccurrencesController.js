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
            $scope.transmission = response;
            $scope.occurrences = response.occurrences;
            console.log($scope.occurrences);
        });

        $scope.delete = function (occurrence) {
            console.log('Delete');
            var errorMessages = {
                'DataAccessException': 'Occurrence for deletion does not exist!',
                'otherwise': 'Occurrence cannot be deleted: {msg}'
            };

            pageService.sendDataAsync('occurrence/' + occurrence.id, 'DELETE', occurrence, 'Occurrence was deleted.',
                'admin/occurrences/', errorMessages);
        };

    }
]);
