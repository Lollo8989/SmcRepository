package smcrepository;

import java.util.ArrayList;
import java.util.List;



import smcrepository.views.Comments;
//import smcrepository.views.Comments;
import smcrepository.views.Resources;
import smcrepository.views.Workspaces;

public class Repository {
	
	private List<Resources> resourcesList;
	private List<Workspaces> workspacesList;
	private List<Comments> comments;
	//private List<Resources> resources;
	
	
	//box di risorse che il servizioweb restituisce

	public Repository()
	{
		//this.resourcesList.add(new Resources(3, "ris1", "ASTS", "ciao", "Si",null));
		//this.resourcesList.add(new Resources(3, "ris2", "AnCTL", "booo", "No", null));
		//this.resourcesList.add(new Resources(4,"ris1","AnCTL","mi piace","Si",null));
		comments=new ArrayList();
		resourcesList=new ArrayList();
		comments.add(new Comments("21/10/14","Paola","non mi piace"));
		comments.add(new Comments("21/10/14","Lollo","va bene"));
		resourcesList.add(new Resources(1, "RisASTS", "ASTS", "risorsa asts", "Si",comments));
		resourcesList.add(new Resources(1, "RisAnCTL", "AnCTL", "booo", "No", comments));
		resourcesList.add(new Resources(2,"RisAnCTL","AnCTL","risorsa anctl","Si",comments));
		resourcesList.add(new Resources(1,"Ontologia","Ontologia","ontologia","Si",comments));
		workspacesList=new ArrayList();
		workspacesList.add(new Workspaces(1,"WS1","workspace1","pubblico",resourcesList));
		workspacesList.add(new Workspaces(2,"WS2","workspace2","privato",resourcesList));
	}
	
	public Repository(List<Resources> resourcesList) {
		this.resourcesList.addAll(resourcesList);
	}

	public List<Resources> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(List<Resources> resourcesList) {
		this.resourcesList = resourcesList;
	}
	
	public List<Workspaces> getWorkspaceList(){
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
