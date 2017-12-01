var telemaniacs = {
    /**
     * AngularJS application
     */
    app: function () {
        return angular.module('telemaniacsApp', [ 'ngRoute' ]);
    },

    /**
     * Adjusts containers top padding after resizing
     */
    adjustContent: function() {
        this.resizeSchedule();
        if ($('#schedule-layout').find('main').length !== 0) {
            this.resizeSchedule();
            $(window).resize(this.resizeSchedule);
        }
    },

    resizeSchedule: function () {
        $('#schedule-layout').find('main').css('padding-top', $('header').height());
    },

    /**
     * Place navigation
     */
    placeNavigation: function () {
        // Custom Header
        var customHeader = $('section#custom-header');
        var customHeaderPlaceholder = $('#custom-header-placeholder');
        var customHeaderSize = $(customHeader).attr('data-size');

        customHeaderPlaceholder.html('');
        if (customHeader.length === 1) {
            if (typeof customHeaderSize === typeof undefined || customHeaderSize === null) {
                customHeaderSize = 0;
            }

            $('#page-header-name')
                .removeClass()
                .addClass('col-md-' + (12 - customHeaderSize));

            $('#page-header-custom')
                .removeClass()
                .addClass('col-md-' + customHeaderSize);

            customHeaderPlaceholder.html(customHeader.html());
            customHeader.remove();
        }

        // Custom Panel
        var customPanel = $('section#custom-panel');
        var customPanelPlaceholder = $('#custom-panel-placeholder');

        customPanelPlaceholder.html('');
        if (customPanel.length === 1) {
            customPanelPlaceholder.html(customPanel.html());
            customPanel.remove();
        }
    },

    /**
     * Loads scripts
     */
    boot: function () {
        this.placeNavigation();
        this.adjustContent();

        // Initializes Bootstrap tooltips
        $('[data-toggle="tooltip"]').tooltip();
    }
};

var telemaniacsApp = telemaniacs.app();