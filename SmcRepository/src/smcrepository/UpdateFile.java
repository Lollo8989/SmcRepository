package smcrepository;

import java.util.Iterator;

import smcrepository.views.Resource;
import smcrepository.views.User;
import smcrepository.views.Serializer;
import smcrepository.views.User;
import smcrepository.views.Workspace;

public class UpdateFile {
	Repository rep;
	
	public UpdateFile(int idR,int idW,String contnuovo)
	{
		rep = Serializer.estrazione();
		if(idW==0)
		{
			for(int i=0;i<rep.getResourcesList().size();i++)
			{
				if(rep.getResourcesList().get(i).getidR()==idR)
				{
					rep.getResourcesList().get(i).setContenutoR(contnuovo);
				}
			}
		}
		else
		{
			for(int i=0;i<rep.getWorkspaceList().size();i++)
			{
				if(rep.getWorkspaceList().get(i).getidW()==idW)
				{
					for(int j=0;j<rep.getWorkspaceList().get(i).getResourcesW().size();j++)
					{
						if(rep.getWorkspaceList().get(i).getResourcesW().get(j).getidR()==idR)
						{
							rep.getWorkspaceList().get(i).getResourcesW().get(j).setContenutoR(contnuovo);
						}
					}
					
				}
			}
		}
		Serializer.saveFile(rep);
		User.restartView(rep);
		
	}
}