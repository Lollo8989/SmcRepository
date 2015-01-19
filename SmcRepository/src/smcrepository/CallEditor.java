package smcrepository;


import java.util.List;

import smcrepository.views.*;

import javax.inject.Inject;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.projectvariables.ParentVariableResolver;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.EditorPart;

import smcrepository.views.CommentView;
import smcrepository.views.Model;
//import de.vogella.rcp.editor.example.View;
import smcrepository.views.Resource;
import smcrepository.views.User;
//import de.vogella.rcp.editor.example.model.Person;

public class CallEditor extends AbstractHandler {
	
	//Importante	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@Inject
	private IEventBroker eventBroker;
	//Fine Importante	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
	Resource res;
	MyEditorInput input;
	

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
 
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
        res = (Resource) obj;
        input = new MyEditorInput(res.getidR(),res.getNameR(),res.getContenutoR(),res.getTipologiaR(),res.getIdWorkspace(), res.getCommentsR());
	    

        
        try {
	                 
		        	page.openEditor(input, MyEditor.ID);
		        	page.addPartListener(new IPartListener2() {
					
		        	  
					@Override
					public void partVisible(IWorkbenchPartReference partRef) {
						// TODO Auto-generated method stub						
						updateViewComment(partRef);
				
					}
					
					@Override
					public void partOpened(IWorkbenchPartReference partRef) {
						// TODO Auto-generated method stub
						updateViewComment(partRef);
			
						
					}
					
					@Override
					public void partInputChanged(IWorkbenchPartReference partRef) {
						// TODO Auto-generated method stub
					
					}
					
					@Override
					public void partHidden(IWorkbenchPartReference partRef) {
						// TODO Auto-generated method stub
				
					}
					
					@Override
					public void partDeactivated(IWorkbenchPartReference partRef) {
						// TODO Auto-generated method stub
				
					}
					
					@Override
					public void partClosed(IWorkbenchPartReference partRef) {
						// TODO Auto-generated method stub
					
						//Serve per conoscere il numero di editor aperti
						if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences().length == 0){
							updateViewComment(null);
			
						}
							
						
		
					}
					
					@Override
					public void partBroughtToTop(IWorkbenchPartReference partRef) {
						// TODO Auto-generated method stub
			
												
					}
					
					@Override
					public void partActivated(IWorkbenchPartReference partRef) {
						// TODO Auto-generated method stub
			
						
						updateViewComment(partRef);
						
				
					}
					
					public void updateViewComment(IWorkbenchPartReference partRef){
						
						List<Comment> comments = null;
						
						if(partRef != null){
							IWorkbenchPart part = partRef.getPart(false);
				            if (part instanceof IEditorPart)
				            {
				                IEditorPart editor = (IEditorPart) part;
				                input = (MyEditorInput) editor.getEditorInput();
				                comments = input.getCommentR();
				               
				            }		
						}
						eventBroker = (IEventBroker) PlatformUI.getWorkbench().getService(IEventBroker.class); 
				        eventBroker.send("CommentsUpdate",comments);
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
