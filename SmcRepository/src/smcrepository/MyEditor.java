package smcrepository;



import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import smcrepository.views.Model;
import smcrepository.views.MovingBox;
import smcrepository.views.Resource;

public class MyEditor extends EditorPart {
	
	 private MyEditorInput input;
	 public static final String ID = "SmcRepository.editor1";
	 //private Resource res;
	 
	
	

	public MyEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException { 
			    if (!(input instanceof MyEditorInput)) {
			        throw new RuntimeException("Wrong input");
			      }

			      MyEditorInput new_name = (MyEditorInput) input;
			      this.input = (MyEditorInput) input;
			      setSite(site);
			      setInput(this.input);
			      //res = MovingBox.getResById(this.input.getId());
			      setPartName(this.input.getNameR()+ "." + this.input.getTipologia().toLowerCase());
			      //da il nome alle view
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		//GridLayout layout = new GridLayout();
		//parent.setLayout(layout);
		
		Text text = new Text(parent, SWT.V_SCROLL|SWT.H_SCROLL);
		//text.setSize(300, 400);
		text.setEditable(true);
		 //Label label1 = new Label(parent, SWT.NONE);
		 //label1.setEnabled(enabled);.setText(this.input.getContenuto());
		text.setText(this.input.getContenuto());
		//TextEditor text=new TextEditor();
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	public void dispose(){
		System.out.println("chiusura!!");
		super.dispose();
	}

}
