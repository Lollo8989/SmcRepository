package smcrepository;

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

public class ChangeVisibility extends AbstractHandler {
	
	public String visibility;
	public String visws;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		ISelection sel = HandlerUtil.getActiveWorkbenchWindow(event)
				.getSelectionService().getSelection();
	
		
				if (sel instanceof IStructuredSelection) {
				Object value = ((IStructuredSelection)sel).getFirstElement();
				if (value instanceof Resource) {
					
					Resource res=((Resource) value);
					System.out.println(res.getPubblicoR());
					Shell shell=new Shell();

					ChangeDialog dialog = new ChangeDialog(shell);
					
					if (dialog.open() == Window.OK) {
						visibility=dialog.getvisibility();
						
						Repository rep = Serializer.estrazione();
						
						if(res.getIdWorkspace()==0)
						{
							for(int i=0;i<rep.getResourcesList().size();i++) {
								if(rep.getResourcesList().get(i).getidR()==res.getidR()) {
									rep.getResourcesList().get(i).setVisibility(visibility);	
									System.out.println(rep.getResourcesList().get(i).getPubblicoR());
								}
							}
						}
						else {
							for (int i=0;i<rep.getWorkspaceList().size();i++) {
								if(rep.getWorkspaceList().get(i).getidW()==res.getIdWorkspace()) {
									for(int j=0;j<rep.getWorkspaceList().get(i).getResourcesW().size();j++) {
										if (rep.getWorkspaceList().get(i).getResourcesW().get(j).getidR()==res.getidR())
											rep.getWorkspaceList().get(i).getResourcesW().get(j).setVisibility(visibility);;
									}
								}
								
				
							}
						}
						
						
						
						Serializer.saveFile(rep);
						User.restartView(rep);	
					}
		
		
		
		
				}
				else if (value instanceof Workspace) {
					Workspace ws=((Workspace) value);
					System.out.println(ws.getTipologiaW());
					Shell shell=new Shell();
					
					ChangeDialogWs dialog = new ChangeDialogWs(shell);
					
					if (dialog.open() == Window.OK) {
						
						visws=dialog.getvisibility();
						
						Repository rep = Serializer.estrazione();
						
						for (int i=0;i<rep.getWorkspaceList().size();i++) {
							if(rep.getWorkspaceList().get(i).getidW()==ws.getidW())
							{
								rep.getWorkspaceList().get(i).setVisibilityWs(visws);
								System.out.println(rep.getWorkspaceList().get(i).getTipologiaW());
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
