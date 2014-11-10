package smcrepository.views;

import java.util.ArrayList;
import java.util.List;

public class MovingBox extends Model {
	protected List boxes;

	protected List resources;
	protected List workspaces;

	private static IModelVisitor adder = new Adder();
	private static IModelVisitor remover = new Remover();

	public MovingBox() {
		boxes = new ArrayList();
		resources = new ArrayList();
		workspaces= new ArrayList();
	}
	private static class Adder implements IModelVisitor {

		public void visitResources(Resource resources, Object argument) {
			((MovingBox) argument).addResources(resources);
		}
		
		public void visitWorkspaces(Workspace workspaces, Object argument) {
			((MovingBox) argument).addWorkspaces(workspaces);
		}

		public void visitMovingBox(MovingBox box, Object argument) {
			((MovingBox) argument).addBox(box);
		}

	}

	private static class Remover implements IModelVisitor {

		// ************************************************************
		public void visitResources(Resource resources, Object argument) {
			((MovingBox) argument).removeResources(resources);
		}
		
		public void visitWorkspaces(Workspace workspaces, Object argument) {
			((MovingBox) argument).removeWorkspaces(workspaces);
		}
		
		public void visitMovingBox(MovingBox box, Object argument) {
			((MovingBox) argument).removeBox(box);
			box.addListener(NullDeltaListener.getSoleInstance());
		}

	}

	public MovingBox(String name) {
		this();
		this.name = name;
	}

	public List getBoxes() {
		return boxes;
	}

	protected void addBox(MovingBox box) {
		boxes.add(box);
		box.parent = this;
		fireAdd(box);
	}



	protected void addResources(Resource resource) {
		resources.add(resource);
		resource.parent = this;
		fireAdd(resource);
	}
	protected void addWorkspaces(Workspace workspace) {
		workspaces.add(workspace);
		workspace.parent = this;
		fireAdd(workspace);
	}

	public void remove(Model toRemove) {
		toRemove.accept(remover, this);
	}

	// ***********************************************
	protected void removeResources(Resource resource) {
		resources.remove(resource);
		resource.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(resource);
	}
	protected void removeWorkspaces(Workspace workspace) {
		workspaces.remove(workspace);
		workspace.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(workspace);
	}

	protected void removeBox(MovingBox box) {
		boxes.remove(box);
		box.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(box);
	}

	public void add(Model toAdd) {
		toAdd.accept(adder, this);
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

	/*
	 * @see Model#accept(ModelVisitorI, Object)
	 */
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		visitor.visitMovingBox(this, passAlongArgument);
	}

}
