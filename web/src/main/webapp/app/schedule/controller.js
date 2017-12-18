telemaniacsApp.controller('ScheduleListController', [
    '$scope',
    '$route',
    '$routeParams',
    '$location',
    '$filter',
    'PageService',

    function ($scope, $route, $routeParams, $location, $filter, pageService) {
        pageService.consumeMessages();
        pageService.setPageName('TV Schedule');

        if (pageService.isLoggedIn()) {
            // Followed channels
            pageService.getDataAsync('/user/' + pageService.getUser().id + '/channels').then(function (response) {
                var followed = [];
                if (response !== null) {
                    for (var i = 0; i < response.length; i++) {
                        followed.push(response[i].id);
                    }
                }
                $scope.followedChannels = followed;
            });

            // Followed transmissions
            pageService.getDataAsync('/user/' + pageService.getUser().id + '/transmissions').then(function (response) {
                var followed = [];
                if (response !== null) {
                    for (var i = 0; i < response.length; i++) {
                        followed.push(response[i].id);
                    }
                }
                $scope.followedTransmissions = followed;
            });
        }

        pageService.getDataAsync('schedule/date/123').then(function (response) {
            $scope.telemaniacs = telemaniacs;
            $scope.timeline = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23];

            // Group by time
            var schedules = response['channelSchedules'];
            if (schedules.length > 0) {
                for (var i = 0; i < schedules.length; i++) {
                    var channelSchedule = schedules[i];
                    schedules[i].byTime = [];
                    for (var t = 0; t < $scope.timeline.length; t++) {
                        schedules[i].byTime[t] = [];
                    }

                    if (channelSchedule.transmissionOccurrences.length > 0) {
                        for (var j = 0; j < channelSchedule.transmissionOccurrences.length; j++) {
                            var occurrence = channelSchedule.transmissionOccurrences[j];
                            var hour = $filter('date')(occurrence.startDate, 'H');
                            schedules[i].byTime[hour].push(occurrence);
                        }
                    }
                }
            }
            $scope.schedules = schedules;
        });


        $scope.followChannel = function (channelId) {
            var errorMessages = {
                'DataAccessException': 'Channel to follow does not exist!',
                'otherwise': 'Channel cannot be followed: {msg}'
            };

            pageService.sendDataAsync('channel/' + pageService.getUser().id + '/follow/' + channelId, 'POST', null, null,
                '/', errorMessages);
        };

        $scope.unfollowChannel = function (channelId) {
            var errorMessages = {
                'DataAccessException': 'Channel to unfollow does not exist!',
                'otherwise': 'Channel cannot be unfollowed: {msg}'
            };

            pageService.sendDataAsync('channel/' + pageService.getUser().id + '/unfollow/' + channelId, 'POST', null, null,
                '/', errorMessages);
        };

        $scope.followTransmission = function (transmissionId) {
            var errorMessages = {
                'DataAccessException': 'Transmission to follow does not exist!',
                'otherwise': 'Transmission cannot be followed: {msg}'
            };

            pageService.sendDataAsync('transmission/' + transmissionId + '/follow/' + pageService.getUser().id, 'POST', null, null,
                '/', errorMessages);
        };

        $scope.unfollowTransmission = function (transmissionId) {
            var errorMessages = {
                'DataAccessException': 'Transmission to unfollow does not exist!',
                'otherwise': 'Transmission cannot be unfollowed: {msg}'
            };

            pageService.sendDataAsync('transmission/' + transmissionId + '/unfollow/' + pageService.getUser().id, 'POST', null, null,
                '/', errorMessages);
        };
    }
]);