package smcrepository;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import smcrepository.views.Resource;
import smcrepository.views.Serializer;
import smcrepository.views.User;
import smcrepository.views.Workspace;

public class Remove extends AbstractHandler {
	
	
	public Repository rep;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		ISelection sel = HandlerUtil.getActiveWorkbenchWindow(event)
				.getSelectionService().getSelection();
				if (sel instanceof IStructuredSelection) {
				Object value = ((IStructuredSelection)sel).getFirstElement();
				if (value instanceof Resource) {
					Shell shell=new Shell();
					//shell.setLayout(layout);
					RemoveDialog dialog = new RemoveDialog(shell);
					if (dialog.open() == Window.OK) {
						rep = Serializer.estrazione();
						if (((Resource) value).getIdWorkspace()==0)
						{
							for(int i=0; i<rep.getResourcesList().size();i++)
							{
								if(rep.getResourcesList().get(i).getidR()==((Resource) value).getidR())
								{
									rep.getResourcesList().remove(i);
								}
								
							}
						}
						else
						{
							for (int i=0;i<rep.getWorkspaceList().size();i++)
							{
								for(int j=0;j<rep.getWorkspaceList().get(i).getResourcesW().size();j++)
								{
									if(rep.getWorkspaceList().get(i).getResourcesW().get(j).getidR()==((Resource) value).getidR())
									{
										rep.getWorkspaceList().get(i).getResourcesW().remove(j);
									}
								}
							}
						}
						Serializer.saveFile(rep);
						User.restartView(rep);
					}
					
				}
				else if (value instanceof Workspace)
				{
					Shell shell=new Shell();
					RemoveDialog dialog = new RemoveDialog(shell);
					if (dialog.open() == Window.OK) {
						rep = Serializer.estrazione();
						for (int i=0;i<rep.getWorkspaceList().size();i++)
						{
							if(rep.getWorkspaceList().get(i).getidW()==((Workspace) value).getidW())
							{
								rep.getWorkspaceList().remove(i);
							}
						}
						Serializer.saveFile(rep);
						User.restartView(rep);
					}
				}
		
			}
		
		
		return null;
	}

}
