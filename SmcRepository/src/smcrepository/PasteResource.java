package smcrepository;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import smcrepository.views.Resource;

public class PasteResource extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		ISelection sel = HandlerUtil.getActiveWorkbenchWindow(event)
				.getSelectionService().getSelection();
				if (sel instanceof IStructuredSelection) {
				Object value = ((IStructuredSelection)sel).getFirstElement();
				if (value instanceof Resource) {
					
				}
		
			}
		return null;
	}

}
