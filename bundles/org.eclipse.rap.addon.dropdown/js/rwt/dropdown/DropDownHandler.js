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

  rap.registerTypeHandler( "rwt.dropdown.DropDown", {

    factory : function( properties ) {
      var control = rwt.remote.ObjectRegistry.getObject( properties.linkedControl );
      return new rwt.dropdown.DropDown( control );
    },

    properties : [ "visibility" ]

  } );

}());