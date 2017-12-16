telemaniacsApp.controller('TransmissionOccurencesCreateController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    'PageService',

    function ($scope, $route, $routeParams, $location, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('Transmission Occurence Administration');

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
            pageService.getDataAsync('transmissionOccurence/' + $routeParams.id).then(function (transmissionOccurence) {
                if (transmissionOccurence == null) {
                    $location.path('admin/transmissionOccurence/create/');
                    return;
                }

                $scope.transmissionOccurence = transmissionOccurence;
            });
        }

        $scope.save = function (transmissionOccurence) {
            var errorMessages = {
                'TvManagerDataAccessException': 'Transmission occurence with the same name already exists.',
                'JpaSystemException': 'Transmission occurence with the same name already exists.',
                'ValidationException': 'Invalid transmission occurence state.',
                'otherwise': 'Category cannot be created: {msg}'
            };

            if (!pageService.isEditing($route)) {
                pageService.sendDataAsync('transmissionOccurence/add/', 'POST', transmissionOccurence, 'Transmission occurence was added.',
                    'admin/transmissionOccurences/', errorMessages);
            } else {
                pageService.sendDataAsync('transmissionOccurence/' + transmissionOccurence.id, 'PUT', transmissionOccurence,
                    'Transmission occurence was updated.', 'admin/transmissionOccurences/', errorMessages);
            }
        };
    }
]);
