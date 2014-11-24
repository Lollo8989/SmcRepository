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

public class NewWorkspaceDialog extends Dialog{

  private Text txtname;
  private Text txtdescrizione;
  private Combo txttipologia;

  
  
  private String name = "";
  private String tipologia;
  private String descrizione="";

  
  
  public NewWorkspaceDialog(Shell parentShell) {
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
    lblName.setText("Name:");

    txtname = new Text(container, SWT.BORDER);
    txtname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
        1, 1));
    txtname.setText(name);

    Label lblDescrizione = new Label(container, SWT.NONE);
    GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false,
        false, 1, 1);
    gd_lblNewLabel.horizontalIndent = 1;
    lblDescrizione.setLayoutData(gd_lblNewLabel);
    lblDescrizione.setText("Descrizione:");

    txtdescrizione = new Text(container, SWT.BORDER);
    txtdescrizione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
        false, 1, 1));
    txtdescrizione.setText(descrizione);
    
    
    
    Label lblTipologia = new Label(container, SWT.NONE);
    GridData gd_lblNewLabel2 = new GridData(SWT.LEFT, SWT.CENTER, false,
        false, 1, 1);
    gd_lblNewLabel2.horizontalIndent = 2;
    lblTipologia.setLayoutData(gd_lblNewLabel2);
    lblTipologia.setText("Select tipology:");
    
    txttipologia = new Combo(container,SWT.NONE);
   
  
    txttipologia.add("Pubblico");
    txttipologia.add("Privato");
    txttipologia.add("Condiviso");
    //tipologia=txttipologia.getSelection().toString();
    
    return container;
  }

  // override method to use "Login" as label for the OK button
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, "New Workspace", true);
    createButton(parent, IDialogConstants.CANCEL_ID,
        IDialogConstants.CANCEL_LABEL, false);
  }

  @Override
  protected Point getInitialSize() {
    return new Point(300, 250);
  }

  @Override
  public void okPressed() {
    // Copy data from SWT widgets into fields on button press.
    // Reading data from the widgets later will cause an SWT
    // widget diposed exception.
    name = txtname.getText();
    descrizione = txtdescrizione.getText();
    tipologia=txttipologia.getText();   
    
    		
    super.okPressed();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String contenuto) {
    this.descrizione = contenuto;
  }
  public String getTipologia(){
	  return tipologia;
  }

  
  protected void configureShell(Shell newShell) {
	    super.configureShell(newShell);
	    newShell.setText("New Workspace");
	  }

} 


