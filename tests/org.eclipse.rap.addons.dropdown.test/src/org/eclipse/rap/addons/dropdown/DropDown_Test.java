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

package org.eclipse.rap.addons.dropdown;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.rap.addons.dropdown.DropDown;
import org.eclipse.rap.rwt.lifecycle.PhaseId;
import org.eclipse.rap.rwt.lifecycle.WidgetUtil;
import org.eclipse.rap.rwt.remote.Connection;
import org.eclipse.rap.rwt.remote.RemoteObject;
import org.eclipse.rap.rwt.testfixture.Fixture;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DropDown_Test {

  private Display display;
  private Text text;
  private DropDown dropdown;
  private RemoteObject remoteObject;
  private Connection connection;

  @Before
  public void setUp() {
    Fixture.setUp();
    display = new Display();
    Shell shell = new Shell( display );
    text = new Text( shell, SWT.NONE );
    Fixture.fakeNewRequest();
    remoteObject = mock( RemoteObject.class );
    connection = mock( Connection.class );
    when( connection.createRemoteObject( anyString() ) ).thenReturn( remoteObject );
    Fixture.fakeConnection( connection );
    dropdown = new DropDown( text );
  }

  @After
  public void tearDown() {
    Fixture.tearDown();
  }

  @Test
  public void testContructor_CreatesRemoteObjectWithCorrentType() {
    verify( connection ).createRemoteObject( "rwt.dropdown.DropDown" );
  }

  @Test
  public void testContructor_SetsReferenceWidget() {
    verify( remoteObject ).set( "linkedControl", WidgetUtil.getId( text ) );
  }

  @Test
  public void testDipose_RendersDetroy() {
    dropdown.dispose();
    verify( remoteObject ).destroy();
  }

  @Test
  public void testDipose_CalledOnControlDispose() {
    Fixture.fakePhase( PhaseId.PROCESS_ACTION );

    text.dispose();

    verify( remoteObject ).destroy();
  }

  @Test
  public void testShow_RendersVisibilityTrue() {
    dropdown.show();
    verify( remoteObject ).set( "visibility", true );
  }

  @Test
  public void testShow_ThrowsExceptionIfDisposed() {
    dropdown.dispose();
    try {
      dropdown.show();
      fail();
    } catch( IllegalStateException ex ) {
      // expected
    }
  }

  @Test
  public void testHide_RendersVisibilityFalse() {
    dropdown.hide();
    verify( remoteObject ).set( "visibility", false );
  }

  @Test
  public void testHide_ThrowsExceptionIfDisposed() {
    dropdown.dispose();
    try {
      dropdown.hide();
      fail();
    } catch( IllegalStateException ex ) {
      // expected
    }
  }
}
