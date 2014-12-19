package smcrepository;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

import smcrepository.views.Serializer;

public class MoveDialog extends Dialog{

 
  private Combo txtWorkspace;

  private String ws;
  private int wsID=0;
  
  
  public MoveDialog(Shell parentShell) {
    super(parentShell);
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite container = (Composite) super.createDialogArea(parent);
    GridLayout layout = new GridLayout(2, false);
    layout.marginRight = 5;
    layout.marginLeft = 10;
    container.setLayout(layout);

    Label lblName = new Label(container, SWT.NONE);
    lblName.setText("Move to:");
    
    Repository rep= Serializer.estrazione();
    
    txtWorkspace = new Combo(container,SWT.READ_ONLY);
    
    for(int i=0;i<rep.getWorkspaceList().size();i++)
    {
    	//int ws=rep.getWorkspaceList().get(i).getidW();
    	txtWorkspace.add(rep.getWorkspaceList().get(i).getNameW());
    }
    

    return container;
  }

  // override method to use "Login" as label for the OK button
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, "Move", true);
    createButton(parent, IDialogConstants.CANCEL_ID,
        IDialogConstants.CANCEL_LABEL, false);
  }

  @Override
  protected Point getInitialSize() {
    return new Point(240, 150);
  }

  @Override
  public void okPressed() {
    // Copy data from SWT widgets into fields on button press.
    // Reading data from the widgets later will cause an SWT
    // widget diposed exception.
   
    ws = txtWorkspace.getText();
    
    Repository rep= Serializer.estrazione();
    
    for(int i=0;i<rep.getWorkspaceList().size();i++){
    	    	
    	    if (rep.getWorkspaceList().get(i).getNameW().equals(ws)){
    	    	wsID=rep.getWorkspaceList().get(i).getidW();
    	    }
    	    	
     }    
    		
    super.okPressed();
  }

  public int getIDWorkspace(){
	  return wsID;
  }
  
  protected void configureShell(Shell newShell) {
	    super.configureShell(newShell);
	    newShell.setText("Move to...");
	  }

} 


