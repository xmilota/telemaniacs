telemaniacsApp.controller('TransmissionOccurrencesCreateController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('Transmission Occurrence Administration');

        $scope.channel = {
            'id': null,
            'name': '',
            'channelType': 'MOVIE',
            'language': 'EN'
        };

        $scope.transmissionOccurence = {
            'id': null,
            'channel': '',
            'transmission': '',
            'partName': '',
            'startTime': ''
        };


        if (pageService.isEditing($route)) {
            pageService.getDataAsync('transmissionOccurrence/' + $routeParams.id).then(function (transmissionOccurence) {
                if (transmissionOccurence == null) {
                    $location.path('admin/transmissionOccurrence/create/');
                    return;
                }

                $scope.transmissionOccurence = transmissionOccurence;
            });
        }

        $scope.save = function (transmissionOccurrence) {
            var errorMessages = {
                'TvManagerDataAccessException': 'Transmission occurrence with the same name already exists.',
                'JpaSystemException': 'Transmission occurrence with the same name already exists.',
                'ValidationException': 'Invalid transmission occurrence state.',
                'otherwise': 'Category cannot be created: {msg}'
            };

            if (!pageService.isEditing($route)) {
                pageService.sendDataAsync('transmissionOccurrence/add/', 'POST', transmissionOccurrence, 'Transmission occurrence was added.',
                    'admin/transmissionOccurrences/', errorMessages);
            } else {
                pageService.sendDataAsync('transmissionOccurrence/' + transmissionOccurrence.id, 'PUT', transmissionOccurrence,
                    'Transmission occurrence was updated.', 'admin/transmissionOccurrences/', errorMessages);
            }
        };
    }
]);
