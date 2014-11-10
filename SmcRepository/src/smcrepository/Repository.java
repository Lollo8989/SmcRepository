package smcrepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




import smcrepository.views.Comment;
//import smcrepository.views.Comments;
import smcrepository.views.Resource;
import smcrepository.views.Workspace;

public class Repository implements Serializable {
	
	private List<Resource> resourcesList;
	private List<Workspace> workspacesList;
	private List<Comment> comments;
	//private List<Resources> resources;
	
	
	//box di risorse che il servizioweb restituisce

	public Repository()
	{
		//this.resourcesList.add(new Resources(3, "ris1", "ASTS", "ciao", "Si",null));
		//this.resourcesList.add(new Resources(3, "ris2", "AnCTL", "booo", "No", null));
		//this.resourcesList.add(new Resources(4,"ris1","AnCTL","mi piace","Si",null));
		comments=new ArrayList();
		resourcesList=new ArrayList();
		comments.add(new Comment("21/10/14","Paola","non mi piace"));
		comments.add(new Comment("21/10/14","Lollo","va bene"));
		resourcesList.add(new Resource(1, "RisASTS", "ASTS", "risorsa asts", "Si",comments));
		resourcesList.add(new Resource(1, "RisAnCTL", "AnCTL", "booo", "No", comments));
		resourcesList.add(new Resource(2,"RisAnCTL","AnCTL","risorsa anctl","Si",comments));
		resourcesList.add(new Resource(1,"Ontologia","Ontologia","ontologia","Si",comments));
		workspacesList=new ArrayList();
		workspacesList.add(new Workspace(1,"WS1","workspace1","pubblico",resourcesList));
		workspacesList.add(new Workspace(2,"WS2","workspace2","privato",resourcesList));
	}
	
	public Repository(List<Resource> resourcesList) {
		this.resourcesList.addAll(resourcesList);
	}

	public List<Resource> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(List<Resource> resourcesList) {
		this.resourcesList = resourcesList;
	}
	
	public List<Workspace> getWorkspaceList(){
		return workspacesList;
	}

	/*public List<Resources> boxresources() {
				
				comments.add(new Comments("11/10/14", "Paola", "Non mi piace"));
				comments.add(new Comments("14/10/14", "Paola", "Non va bene"));

				
				resources.add(new Resources(3, "ris1", "ASTS", "ciao", "Si",
						comments));
				resources.add(new Resources(3, "ris2", "AnCTL", "booo", "No", null));
				
				return resources;
	}
	*/

}
