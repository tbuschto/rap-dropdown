/*******************************************************************************
 * Copyright (c) 2013 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/

(function(){
  'use strict';

  rwt.dropdown = {};

  rwt.dropdown.DropDown = function( linkedControl ) {
    this._ = {};
    this._.popup = createPopup();
    this._.linkedControl = linkedControl;
    linkedControl.addEventListener( "appear", onAppear, this );
    this._.visibility = false;
  };

  rwt.dropdown.DropDown.prototype = {

    setVisibility : function( value ) {
      if( value ) {
        this.show();
      } else {
        this.hide();
      }
    },

    show : function() {
      checkDisposed( this );
      this._.visibility = true;
      if( this._.linkedControl.isCreated() && !this._.popup.isSeeable() ) {
        var yOffset = this._.linkedControl.getHeight();
        this._.popup.positionRelativeTo( this._.linkedControl, 0, yOffset );
        this._.popup.setWidth( this._.linkedControl.getWidth() );
        this._.popup.show();
      }
    },

    hide : function() {
      checkDisposed( this );
      this._.visibility = false;
      this._.popup.hide();
    },

    destroy : function() {
      if( !this.isDisposed() ) {
        this._.popup.destroy();
        for( var key in this._ ) {
          this._[ key ] = null;
        }
        this._ = null;
      }
    },

    isDisposed : function() {
      return this._ === null;
    },

    toString : function() {
      return "DropDown";
    }

  };

  var onAppear = function() {
    if( this._.visibility ) {
      this.show();
    }
  };

  var createPopup = function() {
    var result = new rwt.widgets.base.Popup();
    result.addToDocument();
    result.setBorder( defaultBorder );
    result.setBackgroundColor( "#ffffff" );
    // just for testing:
    result.setHeight( 50 );
    result.setRestrictToPageOnOpen( false );
    return result;
  };

  var checkDisposed = function( dropdown ) {
    if( dropdown.isDisposed() ) {
      throw new Error( "DropDown is disposed" );
    }
  };

  var defaultBorder = new rwt.html.Border( 1, "solid", "#000000" );


}());
