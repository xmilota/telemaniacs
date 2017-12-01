telemaniacsApp.controller('ChannelsCreateController', [
    '$scope',
    'PageService',

    function ($scope, pageService) {
        pageService.setPageName('Channel Administration');

        $scope.channelTypes = [ 'Movie', 'Documentary', 'Commerce', 'Music', 'Sport', 'Children' ];
        $scope.channel = {
            'name': '',
            'channelType': $scope.channelTypes[0],
            'language': 'EN'
        };

        $scope.create = function (channel) {
            alert("OK");
        };
    }
]);