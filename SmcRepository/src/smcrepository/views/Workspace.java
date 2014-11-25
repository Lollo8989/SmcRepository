package smcrepository.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Workspace extends Model implements Serializable {
	
	
	
	protected List resources;

	
	public Workspace() {

		resources = new ArrayList();
	}
	
	//public Workspace(String namework) {
		//this();
		//this.namework = namework;
	//}
	

	public Workspace(int id,String name,String descrizione,String tipologia,List<Resource> resources)
	{
		
		super(id,name,descrizione,tipologia,resources);
		this.resources = new ArrayList();
	}	
	
	
	/*private static class Remover implements IModelVisitor {

		// ************************************************************
		public void visitResources(Resource resources, Object argument) {
			((Box) argument).removeResources(resources);
		}
	}*/
	
	protected void addResources(Resource resource) {
		resources.add(resource);
		resource.parentw = this;
		fireAdd(resource);
	}
	
	public void add(Model toAdd) {
		if (toAdd instanceof Resource)
			addResources((Resource) toAdd);
	}
	
	
	public List getResource(){
		return resources;
	}
	
	public int size() {
		return  getResource().size();
				//getBooks().size() + getBoxes().size() + getGames().size()+getResources().size()+getWorkspaces().size();
	}

	

}
