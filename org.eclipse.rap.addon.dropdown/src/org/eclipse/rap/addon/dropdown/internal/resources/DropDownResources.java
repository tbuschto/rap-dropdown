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
package org.eclipse.rap.addon.dropdown.internal.resources;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;


public final class DropDownResources {

  public static final String DROP_DOWN_JS = "DropDown.js";
  private static final String LOCAL_PATH = "rwt/dropdown/";

  public static void ensure() {
    ensureRegistered();
    ensureLoaded();
  }

  private static void ensureRegistered() {
    if( !RWT.getResourceManager().isRegistered( DROP_DOWN_JS ) ) {
      try {
        register();
      } catch( IOException exception ) {
        throw new RuntimeException( "Failed to register resources", exception );
      }
    }
  }

  private static void ensureLoaded() {
    JavaScriptLoader loader = RWT.getClient().getService( JavaScriptLoader.class );
    loader.require( RWT.getResourceManager().getLocation( DROP_DOWN_JS ) );
  }

  private static void register() throws IOException {
    InputStream inputStream = getResourceAsStream( DROP_DOWN_JS );
    try {
      RWT.getResourceManager().register( DROP_DOWN_JS, inputStream );
    } finally {
      inputStream.close();
    }
  }

  public static InputStream getResourceAsStream( String resourceName ) {
    ClassLoader classLoader = DropDownResources.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream( LOCAL_PATH + resourceName );
    if( inputStream == null ) {
      throw new RuntimeException( "Resource not found: " + resourceName );
    }
    return inputStream;
  }

  private DropDownResources() {
  }

}
