package smcrepository.views;

import java.util.ArrayList;
import java.util.List;

public class Box extends Model {
	
	protected List boxes;
	protected List resources;
	protected List workspaces;


	public Box() {
		boxes = new ArrayList();
		resources = new ArrayList();
		workspaces= new ArrayList();
	}
	


	public Box(String name) {
		this();
		this.name = name;
	}

	public List getBoxes() {
		return boxes;
	}

	protected void addBox(Box box) {
		boxes.add(box);
		box.parent = this;
		fireAdd(box);
	}



	protected void addResources(Resource toAdd) {
		resources.add(toAdd);
		toAdd.parent = this;
		fireAdd(toAdd);
	}
	protected void addWorkspaces(Workspace workspace) {
		workspaces.add(workspace);
		workspace.parent = this;
		fireAdd(workspace);
	}


	public void add(Model toAdd) {
		if (toAdd instanceof Resource)
			addResources((Resource) toAdd);
		else if (toAdd instanceof Workspace)
			addWorkspaces((Workspace) toAdd);
			else if (toAdd instanceof Box)
				addBox((Box) toAdd);
	
	}

	
	public List getResources(){
		return resources;
	}
	public List getWorkspaces(){
		return workspaces;
	}

	/**
	 * Answer the total number of items the receiver contains.
	 */
	public int size() {
		return  getResources().size() + getBoxes().size() +getWorkspaces().size();
				//getBooks().size() + getBoxes().size() + getGames().size()+getResources().size()+getWorkspaces().size();
	}


}
