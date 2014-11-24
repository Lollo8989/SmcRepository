package smcrepository;

import java.nio.file.CopyOption;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.gef.ui.actions.*;

import smcrepository.views.Resource;
import smcrepository.views.User;

public class CopyResource extends AbstractHandler  {
	public CopyTemplateAction action;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		ISelection sel = HandlerUtil.getActiveWorkbenchWindow(event)
				.getSelectionService().getSelection();
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		final IWorkbenchPart part=page.getActiveEditor();
		User user= (User) page.findView(User.ID);
		
				if (sel instanceof IStructuredSelection) {
				Object value = ((IStructuredSelection)sel).getFirstElement();
				if (value instanceof Resource) {
					int id=((Resource) value).getidR();
					
					 
					action = new CopyTemplateAction(page.getActiveEditor());
					action.setId(String.valueOf(id));
				}
		
			}
		return null;
	}
	public String getID(){
		return action.getId();
	}

}
