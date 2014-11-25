package smcrepository.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Model implements Serializable {
	public Box parent;
	public Workspace parentw;
	public String name;
	public String namework;
	//resources
	public int idR;
	public String nameR,tipologiaR,contenutoR,pubblicoR;
	public List<Comment> commentiR;
	public int idWorkspace;
	//workspaces
	public int idW;
	public String nameW,descrizioneW,tipologiaW;
	public  List<Resource> resourcesW;

	public IDeltaListener listener = NullDeltaListener.getSoleInstance();

	public void fireAdd(Object added) {
		listener.add(new DeltaEvent(added));
	}

	public void fireRemove(Object removed) {
		listener.remove(new DeltaEvent(removed));
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setNameR(String nameR) {
		this.nameR = nameR;
	}
	public void setNameW(String nameW) {
		this.nameW = nameW;
	}

	public Box getParent() {
		return parent;
	}

	/*
	 * The receiver should visit the toVisit object and pass along the argument.
	 */


	public String getName() {
		return name;
	}
	public String getNamework() {
		return namework;
	}


	public void addListener(IDeltaListener listener) {
		this.listener = listener;
	}


	public Model() {

	}
    //Resources
	public Model(int idR, String nameR,String tipologiaR,String contenutoR,String pubblicoR, List<Comment> commentiR,int idWorkspace) {
		
		this.nameR = nameR;
		this.idR = idR;
		this.contenutoR=contenutoR;
		this.tipologiaR=tipologiaR;
		this.pubblicoR=pubblicoR;
		this.commentiR=new ArrayList();
		this.commentiR.addAll(commentiR);
		this.idWorkspace=idWorkspace;
	}
	//Workspaces
	public Model(int idW,String nameW,String descrizioneW,String tipologiaW,List<Resource> resourcesW)
	{
		this.nameW=nameW;
		this.idW=idW;
		this.descrizioneW=descrizioneW;
		this.tipologiaW=tipologiaW;
		this.resourcesW=new ArrayList();
		this.resourcesW.addAll(resourcesW);
		this.namework=nameW;
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
	public int getIdWorkspace(){
		return idWorkspace;
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

public void setContenutoR(String contenuto){
	this.contenutoR=contenuto;
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


}
