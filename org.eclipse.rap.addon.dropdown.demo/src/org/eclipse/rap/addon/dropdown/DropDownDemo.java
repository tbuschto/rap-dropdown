package org.eclipse.rap.addon.dropdown;

import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;


public class DropDownDemo extends AbstractEntryPoint {

  @Override
  protected void createContents( Composite parent ) {
    Text text = new Text( parent, SWT.BORDER );
    text.setLayoutData( new GridData( 200, 23 ) );
    DropDown dropdown = new DropDown( text );
    System.out.println( dropdown );
  }
}
