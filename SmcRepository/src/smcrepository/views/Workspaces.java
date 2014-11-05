package smcrepository.views;

import java.util.ArrayList;
import java.util.List;

public class Workspaces extends Model{
	
	/*private int id;
	private String name;
	private String descrizione;
	private String tipologia;
	private List<Resources> resources;
	*/
	//costructor

	
	public Workspaces(int id,String name,String descrizione,String tipologia,List<Resources> resources)
	{
		
		super(id,name,descrizione,tipologia,resources);
	}
		/*this.id=id;
		this.name=name;
		this.descrizione=descrizione;
		this.tipologia=tipologia;
		this.resources=new ArrayList();
		this.resources.addAll(resources);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setNome(String name) {
		this.name = name;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione=descrizione;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public List<Resources> getResources() {
		return resources;
	}
	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}*/
	
	
	
	@Override
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		// TODO Auto-generated method stub
		visitor.visitWorkspaces(this, passAlongArgument);
	}
	

}
