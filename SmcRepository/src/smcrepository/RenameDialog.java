package smcrepository;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class RenameDialog extends Dialog {
	
	private Text newName;
	private String name = "";
	
	public RenameDialog(Shell parentShell) {
	    super(parentShell);
	  }
	
	 protected Control createDialogArea(Composite parent) {
		    Composite container = (Composite) super.createDialogArea(parent);
		    GridLayout layout = new GridLayout(2, false);
		    layout.marginRight = 5;
		    layout.marginLeft = 10;
		    container.setLayout(layout);

		    Label lblUser = new Label(container, SWT.NONE);
		    lblUser.setText("New Name:");

		    newName = new Text(container, SWT.BORDER);
		    newName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
		        1, 1));
		    newName.setText(name);
		    return container;
	 }
	 
	 @Override
	  protected void createButtonsForButtonBar(Composite parent) {
	    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	    createButton(parent, IDialogConstants.CANCEL_ID,
	        IDialogConstants.CANCEL_LABEL, false);
	  }
	 
	 protected Point getInitialSize() {
		    return new Point(300, 150);
		  }

		  @Override
		  public void okPressed() {
		    // Copy data from SWT widgets into fields on button press.
		    // Reading data from the widgets later will cause an SWT
		    // widget diposed exception.
		    name = newName.getText();
		    super.okPressed();
		  }

		  public String getName() {
		    return name;
		  }

		  public void setName(String name) {
		    this.name = name;
		  }
		  protected void configureShell(Shell newShell) {
			    super.configureShell(newShell);
			    newShell.setText("Rename");
			  }
}
	 
	 
	 
	 
	 
	 
