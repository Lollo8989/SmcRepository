package smcrepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




import smcrepository.views.Comment;
//import smcrepository.views.Comments;
import smcrepository.views.Resource;
import smcrepository.views.Workspace;

public class Repository implements Serializable {
	
	private List<Resource> resourcesList,resourcesList2,resourcesList3;
	private List<Workspace> workspacesList;
	private List<Comment> comments1;
	private List<Comment> comments2;
	private List<Comment> comments3;
	//private List<Resources> resources;
	
	
	//box di risorse che il servizioweb restituisce

	public Repository()
	{
		//this.resourcesList.add(new Resources(3, "ris1", "ASTS", "ciao", "Si",null));
		//this.resourcesList.add(new Resources(3, "ris2", "AnCTL", "booo", "No", null));
		//this.resourcesList.add(new Resources(4,"ris1","AnCTL","mi piace","Si",null));
		comments1=new ArrayList();
		comments2=new ArrayList();
		comments3=new ArrayList();
		resourcesList=new ArrayList();
		resourcesList2=new ArrayList();
		resourcesList3=new ArrayList();
		comments1.add(new Comment("21/10/14","20:15","Paola","è una risorsa ASTS con id=1"));
		comments1.add(new Comment("21/10/14","17:09","Lorenzo","è una risorsa ASTS"));
		comments1.add(new Comment("21/10/14","13:08","Lorenzo","c'è un errore!"));
		comments2.add(new Comment("11/11/14","15:35","Paola","è una risorsa AnCTL"));
		resourcesList.add(new Resource(1, "RisASTS", "ASTS", "risorsa asts", "No",comments1,0));
		resourcesList.add(new Resource(2, "RisAnCTL", "AnCTL", "risorsa anctl prova1", "No", comments2,0));
		resourcesList.add(new Resource(3,"RisAnCTL","AnCTL","risorsa anctl prova2","Si",comments2,0));
		resourcesList.add(new Resource(4,"Ontologia","Ontologia","ontologia","Si",comments3,0));
		
		resourcesList2.add(new Resource(5,"RisASTS","ASTS","risorsa asts","Si",comments3,1));
		resourcesList2.add(new Resource(6,"RisOntologia","Ontologia","risorsa di tipo ontologia","Si",comments3,1));
		resourcesList3.add(new Resource(7,"RisANCTL","AnCTL","risorsa di tipo non saprei","Si",comments3,2));
		workspacesList=new ArrayList();
		workspacesList.add(new Workspace(1,"Workspace1","workspace1","pubblico",resourcesList2));
		workspacesList.add(new Workspace(2,"Workspace2","workspace2","privato",resourcesList3));
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
