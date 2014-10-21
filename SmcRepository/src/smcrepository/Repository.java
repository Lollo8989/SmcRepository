package smcrepository;

import java.util.ArrayList;
import java.util.List;

//import smcrepository.views.Comments;
import smcrepository.views.Resources;

public class Repository {
	
	private List<Resources> resourcesList;
	//private List<Comments> comments;
	//private List<Resources> resources;
	
	
	//box di risorse che il servizioweb restituisce

	public Repository()
	{
		//this.resourcesList.add(new Resources(3, "ris1", "ASTS", "ciao", "Si",null));
		//this.resourcesList.add(new Resources(3, "ris2", "AnCTL", "booo", "No", null));
		//this.resourcesList.add(new Resources(4,"ris1","AnCTL","mi piace","Si",null));
		resourcesList=new ArrayList();
	
		resourcesList.add(new Resources(3, "ris1", "ASTS", "ciao", "Si",null));
		resourcesList.add(new Resources(3, "ris2", "AnCTL", "booo", "No", null));
		resourcesList.add(new Resources(4,"ris1","AnCTL","mi piace","Si",null));
		resourcesList.add(new Resources(1,"onto","Ontologia",null,"Si",null));
		
	}
	
	public Repository(List<Resources> resourcesList) {
		this.resourcesList=resourcesList;
	}

	public List<Resources> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(List<Resources> resourcesList) {
		this.resourcesList = resourcesList;
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
