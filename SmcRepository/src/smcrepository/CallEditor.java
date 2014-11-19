package smcrepository;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;







import smcrepository.views.CommentView;
import smcrepository.views.Model;
//import de.vogella.rcp.editor.example.View;
import smcrepository.views.Resource;
import smcrepository.views.User;
//import de.vogella.rcp.editor.example.model.Person;

public class CallEditor extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    System.out.println("called");
    // get the view
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
    IWorkbenchPage page = window.getActivePage();
    final IWorkbenchPart part=page.getActiveEditor();
    User user= (User) page.findView(User.ID);
  
    
    //View view = (View) page.findView(View.ID);
    // get the selection
    final ISelection selection = user.getSite().getSelectionProvider().getSelection();
    if (selection != null && selection instanceof IStructuredSelection) {
      Object obj = ((IStructuredSelection) selection).getFirstElement();
      // if we had a selection lets open the editor
      if (obj instanceof Resource) {
        Resource res = (Resource) obj;
        MyEditorInput input = new MyEditorInput(res.getidR(),res.getNameR(),res.getContenutoR(),res.getTipologiaR(),res.getIdWorkspace());
        try {
         
         
          page.openEditor(input, MyEditor.ID);
          page.addPartListener(new IPartListener2() {
			
			@Override
			public void partVisible(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				//CommentView commentView=new CommentView();
				//commentView.selectionChanged(part,selection);
				System.out.println("partvisible");
			}
			
			@Override
			public void partOpened(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				System.out.println("partopen");
			}
			
			@Override
			public void partInputChanged(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				System.out.println("partinputchanged");
			}
			
			@Override
			public void partHidden(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				System.out.println("partHidden");
			}
			
			@Override
			public void partDeactivated(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				System.out.println("partDeactivated");
			}
			
			@Override
			public void partClosed(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				System.out.println("partClosed");
			}
			
			@Override
			public void partBroughtToTop(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void partActivated(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				System.out.println("partActivated");
				
				
		
			}
		});
          

        } catch (PartInitException e) {
          throw new RuntimeException(e);
        }
      }
    }
    return null;
  }

} 
