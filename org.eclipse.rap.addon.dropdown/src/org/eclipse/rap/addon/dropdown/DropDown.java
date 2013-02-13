/*******************************************************************************
 * Copyright (c) 2013 EclipseSource.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/

package org.eclipse.rap.addon.dropdown;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.lifecycle.WidgetUtil;
import org.eclipse.rap.rwt.remote.RemoteObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


public class DropDown {

  private static final String REMOTE_TYPE = "rwt.dropdown.DropDown";
  private RemoteObject remoteObject;
  private boolean disposed = false;

  public DropDown( Control control ) {
    remoteObject = RWT.getUISession().getConnection().createRemoteObject( REMOTE_TYPE );
    remoteObject.set( "linkedControl", WidgetUtil.getId( control ) );
    control.addListener( SWT.Dispose, new Listener() {
      public void handleEvent( Event event ) {
        DropDown.this.dispose();
      }
    } );
  }

  public void dispose() {
    remoteObject.destroy();
    remoteObject = null;
    disposed = true;
  }

  public void show() {
    checkDisposed();
    remoteObject.set( "visibility", true );
  }

  public void hide() {
    checkDisposed();
    remoteObject.set( "visibility", false );
  }

  private void checkDisposed() {
    if( disposed ) {
      throw new IllegalStateException( "DropDown is disposed" );
    }
  }

}
