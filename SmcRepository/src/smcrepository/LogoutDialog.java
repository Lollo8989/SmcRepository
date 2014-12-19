package smcrepository;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class LogoutDialog extends Dialog {
	
	  public LogoutDialog(Shell parentShell) {
		    super(parentShell);
		  }

		  @Override
		  protected Control createDialogArea(Composite parent) {
		    Composite container = (Composite) super.createDialogArea(parent);
		    
		    

		    Label label = new Label(container, SWT.NONE);
		    label.setText("Do you want exit??");

		    

		    return container;
		  }

		  // overriding this methods allows you to set the
		  // title of the custom dialog
		  @Override
		  protected void configureShell(Shell newShell) {
		    super.configureShell(newShell);
		    newShell.setText("Logout");
		  }

		  @Override
		  protected Point getInitialSize() {
		    return new Point(300, 150);
		  }


}
