var goobiWorkflowJS = ( function( goobiWorkflow ) {
    'use strict';
    
    var _debug = false;
    var _defaults = {
        navigationSelector: '#navigation',
        navigationActionSelector: '#navigationActions'
    };
    
    goobiWorkflow.menu = {
        /**
         * @description Method to initialize the menu module.
         * @method init
         */
    	init: function() {
            if ( _debug ) {
                console.log( 'Initializing: goobiWorkflow.menu.init' );
            }

            _setSubmenuToggleEvent();
        }
    };

    /**
     * @description Method to set the event listener to toggle box body.
     * @method _setSubmenuToggleEvent
     */
    function _setSubmenuToggleEvent() {
        if ( _debug ) {
            console.log( 'EXECUTE: _setSubmenuToggleEvent' );
        }

        $( '[data-show="submenu"]' ).off( 'click' ).on( 'click', function ( event ) {            
            if ( $( this ).next().is( ':visible' ) ) {
                $( '.submenu' ).hide();
                $( this ).next().hide();
            }
            else {
                $( '.submenu' ).hide();
                $( this ).next().show();
            }
        } );

        // reset all menus by clicking on body
        $( 'body' ).off( 'click' ).on( 'click', function( event ) {
            if ( 
                event.target.id == 'navigation' || 
                $( event.target ).closest( _defaults.navigationSelector ).length || 
                event.target.id == 'navigationActions' || 
                $( event.target ).closest( _defaults.navigationActionSelector ).length 
            ) {
                return;
            }
            else {
                $( '.submenu' ).hide();
            }
        } );
    }
    
    return goobiWorkflow;
    
} )( goobiWorkflowJS || {}, jQuery );