package smcrepository;



import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import smcrepository.views.Model;
import smcrepository.views.MovingBox;
import smcrepository.views.Resource;

public class MyEditor extends EditorPart {
	
	 private MyEditorInput input;
	 public static final String ID = "SmcRepository.editor1";
	 public Text text;
	 public String contents;
	 protected boolean dirty = false;
	 
	 
	public void savecontents(String text){
		this.contents=text;
	}
	

	public MyEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
		setDirty(false);
	
			
		new UpdateFile(this.input.getId(),this.input.getIdWorkspace(),contents);
		

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
		setDirty(false);
		
			
		new UpdateFile(this.input.getId(),this.input.getIdWorkspace(),contents);

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException { 
			    if (!(input instanceof MyEditorInput)) {
			        throw new RuntimeException("Wrong input");
			      }

			      this.input = (MyEditorInput) input;
			      
			      setSite(site);
			      setInput(this.input);
			    //da il nome alle view
			      setPartName(this.input.getNameR()+ "." + this.input.getTipologia().toLowerCase());
			   
			      
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
	
		return dirty;
	}
	protected void setDirty(boolean value) {
         dirty = value;
         //System.out.println("setdirty"); 
         firePropertyChange(PROP_DIRTY);
      }
	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub

		return false;
	}
	
	
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
	
		
		text = new Text(parent, SWT.V_SCROLL|SWT.H_SCROLL);
		text.setEditable(true);

		text.setText(this.input.getContenuto());

		savecontents(text.getText());
	
		ModifyListener listener=new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				setDirty(true);
				savecontents(text.getText());			
			}
		};
		text.addModifyListener(listener);
	
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	
		
	}
	public void dispose(){
		
		
		super.dispose();
	}

}
