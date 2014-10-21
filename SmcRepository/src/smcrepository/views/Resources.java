package smcrepository.views;

import java.util.List;

public class Resources extends Model {

	private int id;
	private String name;
	private String tipologia;
	private String contenuto;
	private String pubblico;
	private List<Comments> comments;
	private List<Resources> resources;
	private List<Comments> commenti;
	
	public Resources(int id, String name)
	{
		super(id,name);
	}


	public Resources(int id, String name, String tipologia, String contenuto,
			String pubblico, List<Comments> commenti) {
		
		this.id = id;
		this.name = name;
		this.tipologia = tipologia;
		this.contenuto = contenuto;
		this.pubblico = pubblico;
		this.commenti = (List<Comments>) commenti;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public String getPubblico() {
		return pubblico;
	}

	public void setPubblico(String pubblico) {
		this.pubblico = pubblico;
	}
	
	

	/*
	 * public Comments getCommenti() { return commenti; } public void
	 * setCommenti(Comments commenti) { this.commenti = commenti; }
	 */

	@Override
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		// TODO Auto-generated method stub
		visitor.visitResources(this, passAlongArgument);
	}

}
