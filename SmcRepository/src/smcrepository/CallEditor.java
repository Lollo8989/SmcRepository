package smcrepository;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

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
    User user= (User) page.findView(User.ID);
  
    
    //View view = (View) page.findView(View.ID);
    // get the selection
    ISelection selection = user.getSite().getSelectionProvider().getSelection();
    if (selection != null && selection instanceof IStructuredSelection) {
      Object obj = ((IStructuredSelection) selection).getFirstElement();
      // if we had a selection lets open the editor
      if (obj instanceof Resource) {
        Resource res = (Resource) obj;
        MyEditorInput input = new MyEditorInput(res.getidR(),res.getNameR(),res.getContenutoR(),res.getTipologiaR());
        try {
         
       
          page.openEditor(input, MyEditor.ID);
          

        } catch (PartInitException e) {
          throw new RuntimeException(e);
        }
      }
    }
    return null;
  }

} 
