package smcrepository;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;



public class ChangeDialogWs extends Dialog {

	protected ChangeDialogWs(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	 private Combo visibility;
	 private String vis;
	 
	 
	 protected Control createDialogArea(Composite parent) {
		    Composite container = (Composite) super.createDialogArea(parent);
		    GridLayout layout = new GridLayout(2, false);
		    layout.marginRight = 5;
		    layout.marginLeft = 10;
		    container.setLayout(layout);

		    Label lblName = new Label(container, SWT.NONE);
		    lblName.setText("Change visibility");
		    		    
		    visibility = new Combo(container,SWT.READ_ONLY);
		    
		
		    
		    visibility.add("privato");
		    visibility.select(0);
		    visibility.add("pubblico");
		    visibility.add("condiviso");
		    

		    return container;
		  }

	 @Override
	  protected void createButtonsForButtonBar(Composite parent) {
	    createButton(parent, IDialogConstants.OK_ID, "Change", true);
	    createButton(parent, IDialogConstants.CANCEL_ID,
	        IDialogConstants.CANCEL_LABEL, false);
	  }

	  @Override
	  protected Point getInitialSize() {
	    return new Point(240, 110);
	  }

	  @Override
	  public void okPressed() {
	    // Copy data from SWT widgets into fields on button press.
	    // Reading data from the widgets later will cause an SWT
	    // widget diposed exception.
	   
	    vis = visibility.getText();
	    
	    super.okPressed();
	  }

	  public String getvisibility(){
		  return vis;
	  }
	  
	  protected void configureShell(Shell newShell) {
		    super.configureShell(newShell);
		    newShell.setText("Change visibility...");
		  }

	

}
