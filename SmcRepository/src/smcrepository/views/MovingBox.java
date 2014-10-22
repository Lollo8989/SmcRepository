package smcrepository.views;

import java.util.ArrayList;
import java.util.List;

public class MovingBox extends Model {
	protected List boxes;
	protected List games;
	protected List books;
	protected List resources;
	protected List workspaces;

	private static IModelVisitor adder = new Adder();
	private static IModelVisitor remover = new Remover();

	public MovingBox() {
		boxes = new ArrayList();
		games = new ArrayList();
		books = new ArrayList();
		resources = new ArrayList();
		workspaces= new ArrayList();
	}

	private static class Adder implements IModelVisitor {

		/*
		 * @see ModelVisitorI#visitBoardgame(BoardGame)
		 */

		/*
		 * @see ModelVisitorI#visitBook(MovingBox)
		 */

		/*
		 * @see ModelVisitorI#visitMovingBox(MovingBox)
		 */

		/*
		 * @see ModelVisitorI#visitBoardgame(BoardGame, Object)
		 */

		// *******************************************
		public void visitResources(Resources resources, Object argument) {
			((MovingBox) argument).addResources(resources);
		}
		
		public void visitWorkspaces(Workspaces workspaces, Object argument) {
			((MovingBox) argument).addWorkspaces(workspaces);
		}
		
		// *********************************************
		public void visitBoardgame(BoardGame boardgame, Object argument) {
			((MovingBox) argument).addBoardGame(boardgame);
		}

		/*
		 * @see ModelVisitorI#visitBook(MovingBox, Object)
		 */
		public void visitBook(Book book, Object argument) {
			((MovingBox) argument).addBook(book);
		}

		/*
		 * @see ModelVisitorI#visitMovingBox(MovingBox, Object)
		 */
		public void visitMovingBox(MovingBox box, Object argument) {
			((MovingBox) argument).addBox(box);
		}

	}

	private static class Remover implements IModelVisitor {

		// ************************************************************
		public void visitResources(Resources resources, Object argument) {
			((MovingBox) argument).removeResources(resources);
		}
		
		public void visitWorkspaces(Workspaces workspaces, Object argument) {
			((MovingBox) argument).removeWorkspaces(workspaces);
		}
		// **********************************************************

		public void visitBoardgame(BoardGame boardgame, Object argument) {
			((MovingBox) argument).removeBoardGame(boardgame);
		}

		/*
		 * @see ModelVisitorI#visitBook(MovingBox, Object)
		 */
		public void visitBook(Book book, Object argument) {
			((MovingBox) argument).removeBook(book);
		}

		/*
		 * @see ModelVisitorI#visitMovingBox(MovingBox, Object)
		 */
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

	// *********************************************

	protected void addResources(Resources resource) {
		resources.add(resource);
		resource.parent = this;
		fireAdd(resource);
	}
	protected void addWorkspaces(Workspaces workspace) {
		workspaces.add(workspace);
		workspace.parent = this;
		fireAdd(workspace);
	}

	// *********************************************

	protected void addBook(Book book) {
		books.add(book);
		book.parent = this;
		fireAdd(book);
	}

	protected void addBoardGame(BoardGame game) {
		games.add(game);
		game.parent = this;
		fireAdd(game);
	}

	public List getBooks() {
		return books;
	}

	public void remove(Model toRemove) {
		toRemove.accept(remover, this);
	}

	// ***********************************************
	protected void removeResources(Resources resource) {
		resources.remove(resource);
		resource.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(resource);
	}
	protected void removeWorkspaces(Workspaces workspace) {
		workspaces.remove(workspace);
		workspace.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(workspace);
	}

	// ************************************************
	protected void removeBoardGame(BoardGame boardGame) {
		games.remove(boardGame);
		boardGame.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(boardGame);
	}

	protected void removeBook(Book book) {
		books.remove(book);
		book.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(book);
	}

	protected void removeBox(MovingBox box) {
		boxes.remove(box);
		box.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(box);
	}

	public void add(Model toAdd) {
		toAdd.accept(adder, this);
	}

	public List getGames() {
		return games;
	}
	//****************************************
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
		return getBooks().size() + getBoxes().size() + getGames().size()+getResources().size()+getWorkspaces().size();
	}

	/*
	 * @see Model#accept(ModelVisitorI, Object)
	 */
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		visitor.visitMovingBox(this, passAlongArgument);
	}

}
