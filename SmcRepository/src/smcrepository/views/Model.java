package smcrepository.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Model implements Serializable {
	protected MovingBox parent;
	protected String name;
	//resources
	protected int idR;
	protected String nameR,tipologiaR,contenutoR,pubblicoR;
	protected List<Comment> commentiR;
	//workspaces
	protected int idW;
	protected String nameW,descrizioneW,tipologiaW;
	protected  List<Resource> resourcesW;
	
	//protected String authorGivenName, authorSirName;
	protected IDeltaListener listener = NullDeltaListener.getSoleInstance();

	protected void fireAdd(Object added) {
		listener.add(new DeltaEvent(added));
	}

	protected void fireRemove(Object removed) {
		listener.remove(new DeltaEvent(removed));
	}

	public void setName(String name) {
		this.name = name;
	}

	public MovingBox getParent() {
		return parent;
	}

	/*
	 * The receiver should visit the toVisit object and pass along the argument.
	 */
	public abstract void accept(IModelVisitor visitor, Object passAlongArgument);

	public String getName() {
		return name;
	}


	public void addListener(IDeltaListener listener) {
		this.listener = listener;
	}

	/*public Model(String title, String authorGivenName, String authorSirName) {
		this.name = title;
		this.authorGivenName = authorGivenName;
		this.authorSirName = authorSirName;
	}*/

	// **********************************************
	public Model() {

	}

	public Model(int idR, String nameR,String tipologiaR,String contenutoR,String pubblicoR, List<Comment> commentiR) {
		
		this.nameR = nameR;
		this.idR = idR;
		this.contenutoR=contenutoR;
		this.tipologiaR=tipologiaR;
		this.pubblicoR=pubblicoR;
		this.commentiR=new ArrayList();
		this.commentiR.addAll(commentiR);
	}
	
	public Model(int idW,String nameW,String descrizioneW,String tipologiaW,List<Resource> resourcesW)
	{
		this.nameW=nameW;
		this.idW=idW;
		this.descrizioneW=descrizioneW;
		this.tipologiaW=tipologiaW;
		this.resourcesW=new ArrayList();
		this.resourcesW.addAll(resourcesW);
	}
	
	public String getNameW()
	{
		return nameW;
	}
	public String getDescrizioneW(){
		return descrizioneW;
	}
	public String getTipologiaW(){
		return tipologiaW;
	}
	public List<Resource> getResourcesW(){
		return resourcesW;
	}
	
	public String getTipologiaR() {
		return tipologiaR;
	}

public String getContenutoR() {
		return contenutoR;
	}



	public String getPubblicoR() {
		return pubblicoR;
	}


	

	 public List<Comment> getCommentsR() {
		 return commentiR; 
	} 
	 

	public int getidW() {
		return idW;
	}
	public String getNameR() {
		return nameR;
	}
	
	public int getidR() {
		return idR;
	}
	

	// ***********************************************
	public void removeListener(IDeltaListener listener) {
		if (this.listener.equals(listener)) {
			this.listener = NullDeltaListener.getSoleInstance();
		}
	}

	/*public String authorGivenName() {
		return authorGivenName;
	}

	public String authorSirName() {
		return authorSirName;
	}

	public String getTitle() {
		return name;
	}*/

}
