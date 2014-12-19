package smcrepository;

import java.nio.file.CopyOption;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;


import smcrepository.views.Resource;
import smcrepository.views.Serializer;
import smcrepository.views.User;

public class MoveResource extends AbstractHandler  {

	int wsId;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		ISelection sel = HandlerUtil.getActiveWorkbenchWindow(event)
				.getSelectionService().getSelection();
	
		
				if (sel instanceof IStructuredSelection) {
				Object value = ((IStructuredSelection)sel).getFirstElement();
				if (value instanceof Resource) {
					
					Resource res=((Resource) value);
					
					Shell shell=new Shell();

					MoveDialog dialog = new MoveDialog(shell);
					
					if (dialog.open() == Window.OK && dialog.getIDWorkspace()!=0) {
						
						wsId=dialog.getIDWorkspace();
						
						Repository rep = Serializer.estrazione();
						
						if(res.getIdWorkspace()==0)
						{
							for(int i=0;i<rep.getResourcesList().size();i++) {
								if(rep.getResourcesList().get(i).getidR()==res.getidR()) {
									rep.getResourcesList().remove(i);
									for(int j=0;j<rep.getWorkspaceList().size();j++) {
										
										if(rep.getWorkspaceList().get(j).getidW()==wsId) {
											rep.getWorkspaceList().get(j).getResourcesW().add(new Resource(res.getidR(),res.getNameR(),res.getTipologiaR(),res.getContenutoR(),res.getPubblicoR(),res.getCommentsR(),wsId));
										}
									}
										
								}
							}
						}
						else {
							for (int i=0;i<rep.getWorkspaceList().size();i++) {
								if(rep.getWorkspaceList().get(i).getidW()==res.getIdWorkspace()) {
									for(int j=0;j<rep.getWorkspaceList().get(i).getResourcesW().size();j++) {
										if (rep.getWorkspaceList().get(i).getResourcesW().get(j).getidR()==res.getidR())
											rep.getWorkspaceList().get(i).getResourcesW().remove(j);
									}
								}
								
								if(rep.getWorkspaceList().get(i).getidW()==wsId) {
									
									rep.getWorkspaceList().get(i).getResourcesW().add(new Resource(res.getidR(),res.getNameR(),res.getTipologiaR(),res.getContenutoR(),res.getPubblicoR(),res.getCommentsR(),wsId));
								}
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
