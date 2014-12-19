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

public class NewResourceDialog extends Dialog{

  private Text txtname;
  private Text txtcontenuto;
  private Combo txtWorkspace;

  private Combo txtpubblico;
  private Combo txttipologia;
  
  
  private String name = "";
  private String tipologia;
  private String contenuto="";
  private String pubblico;
  private String ws;
  private int wsID;
  
  
  public NewResourceDialog(Shell parentShell) {
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

    Label lblContenuto = new Label(container, SWT.NONE);
    GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false,
        false, 1, 1);
    gd_lblNewLabel.horizontalIndent = 1;
    lblContenuto.setLayoutData(gd_lblNewLabel);
    lblContenuto.setText("Contenuto:");

    txtcontenuto = new Text(container, SWT.BORDER);
    txtcontenuto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
        false, 1, 1));
    txtcontenuto.setText(contenuto);
    
    Label lblPubblico = new Label(container, SWT.NONE);
    GridData gd_lblNewLabel1 = new GridData(SWT.LEFT, SWT.CENTER, false,
        false, 1, 1);
    gd_lblNewLabel1.horizontalIndent = 2;
    lblPubblico.setLayoutData(gd_lblNewLabel1);
    lblPubblico.setText("Pubblic?");
    
    txtpubblico = new Combo(container,SWT.READ_ONLY);
    
    txtpubblico.add("Si");
    txtpubblico.select(0);
    txtpubblico.add("No");
    //pubblico=txtpubblico.getSelection().toString();
    
    
    Label lblTipologia = new Label(container, SWT.NONE);
    GridData gd_lblNewLabel2 = new GridData(SWT.LEFT, SWT.CENTER, false,
        false, 1, 1);
    gd_lblNewLabel2.horizontalIndent = 2;
    lblTipologia.setLayoutData(gd_lblNewLabel2);
    lblTipologia.setText("Select tipology:");
    
    txttipologia = new Combo(container,SWT.READ_ONLY);
   
  
    txttipologia.add("ASTS");
    txttipologia.select(0);
    txttipologia.add("AnCTL");
    txttipologia.add("Ontologia");
    //tipologia=txttipologia.getSelection().toString();
    
    Label lblWorkspace = new Label(container, SWT.NONE);
    GridData gd_lblNewLabel3 = new GridData(SWT.LEFT, SWT.CENTER, false,
        false, 1, 1);
    gd_lblNewLabel3.horizontalIndent = 3;
    lblWorkspace.setLayoutData(gd_lblNewLabel3);
    lblWorkspace.setText("Select workspace:");
    
    Repository rep= Serializer.estrazione();
    txtWorkspace = new Combo(container,SWT.READ_ONLY);
   
    txtWorkspace.add("no workspace");
    txtWorkspace.select(0);
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
    createButton(parent, IDialogConstants.OK_ID, "New Resource", true);
    createButton(parent, IDialogConstants.CANCEL_ID,
        IDialogConstants.CANCEL_LABEL, false);
  }

  @Override
  protected Point getInitialSize() {
    return new Point(400, 350);
  }

  @Override
  public void okPressed() {
    // Copy data from SWT widgets into fields on button press.
    // Reading data from the widgets later will cause an SWT
    // widget diposed exception.
    name = txtname.getText();
    contenuto = txtcontenuto.getText();
    pubblico = txtpubblico.getText();
    tipologia=txttipologia.getText();
    ws = txtWorkspace.getText();
    
    Repository rep= Serializer.estrazione();
    if(ws.equals("no workspace")) {
    	wsID=0;
    }else {
    	 for(int i=0;i<rep.getWorkspaceList().size();i++){
    	    	
    	    	if (rep.getWorkspaceList().get(i).getNameW().equals(ws)){
    	    		wsID=rep.getWorkspaceList().get(i).getidW();
    	    	}
    	    	
    	    }
    }
   
    
    		
    super.okPressed();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContenuto() {
    return contenuto;
  }

  public void setContenuto(String contenuto) {
    this.contenuto = contenuto;
  }
  public String getTipologia(){
	  return tipologia;
  }
  
  public String getPubblico(){
	  return pubblico;
  }
  public int getIDWorkspace(){
	  return wsID;
  }
  
  protected void configureShell(Shell newShell) {
	    super.configureShell(newShell);
	    newShell.setText("New Resource");
	  }

} 


