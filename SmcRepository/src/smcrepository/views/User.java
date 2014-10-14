package smcrepository.views;

import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class User extends ViewPart {
	
	protected Text text;
	protected TreeViewer treeViewer;
	protected MovingBoxLabelProvider labelProvider;
	protected MovingBox root;
	
	//NUOVO
	protected ViewerFilter onlyBoardGamesFilter, atLeastThreeFilter;
	protected ViewerSorter booksBoxesGamesSorter, noArticleSorter;
	protected Action onlyBoardGamesAction, atLeatThreeItems;
	protected Action booksBoxesGamesAction, noArticleAction;
	protected Action addBookAction, removeAction;
	//FINE NUOVO
	
	
	public void createPartControl(Composite parent) {
		 treeViewer= new TreeViewer(parent, SWT.MULTI|SWT.H_SCROLL|SWT.V_SCROLL);
		 
		
		 GridLayout layout = new GridLayout();
		 layout.numColumns = 1;
		 layout.verticalSpacing = 2;
		 layout.marginWidth = 0;
		 layout.marginHeight = 2;
		 parent.setLayout(layout);
		 
		 text = new Text(parent, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
		 GridData layoutData = new GridData();
		 layoutData.grabExcessHorizontalSpace = true;
		 layoutData.horizontalAlignment = GridData.FILL;
		 text.setLayoutData(layoutData);
		 text.setText("Prova");
	
		 treeViewer = new TreeViewer(parent);
		 treeViewer.setContentProvider(new MovingBoxContentProvider());	//Setta il gestore del contenuto
		 labelProvider = new MovingBoxLabelProvider();
		 treeViewer.setLabelProvider(labelProvider);
		 treeViewer.setUseHashlookup(true);
		 
		 
		 //NUOVO
		// Create menu, toolbars, filters, sorters.
		createFiltersAndSorters();
		createActions();
		createMenus();
		createToolbar();
		hookListeners();
		//FINE NUOVO
		 
		 
		 
		 treeViewer.setInput(getInitalInput());
		 treeViewer.expandAll();
		 
		 
		 

		 
		 
	}
	
	
	//NUOVO
	protected void createFiltersAndSorters() {
		atLeastThreeFilter = new ThreeItemFilter();
		onlyBoardGamesFilter = new BoardgameFilter();
		booksBoxesGamesSorter = new BookBoxBoardSorter();
		noArticleSorter = new NoArticleSorter();
	}
	
	
	
	/* Multiple filters can be enabled at a time. */
	protected void updateFilter(Action action) {
		if(action == atLeatThreeItems) {
			if(action.isChecked()) {
				treeViewer.addFilter(atLeastThreeFilter);
			} else {
				treeViewer.removeFilter(atLeastThreeFilter);
			}
		} else if(action == onlyBoardGamesAction) {
			if(action.isChecked()) {
				treeViewer.addFilter(onlyBoardGamesFilter);
			} else {
				treeViewer.removeFilter(onlyBoardGamesFilter);
			}
		}
	}	
	
	
	
	protected void updateSorter(Action action) {
		if(action == booksBoxesGamesAction) {
			noArticleAction.setChecked(!booksBoxesGamesAction.isChecked());
			if(action.isChecked()) {
				treeViewer.setSorter(booksBoxesGamesSorter);
			} else {
				treeViewer.setSorter(null);
			}
		} else if(action == noArticleAction) {
			booksBoxesGamesAction.setChecked(!noArticleAction.isChecked());
			if(action.isChecked()) {
				treeViewer.setSorter(noArticleSorter);
			} else {
				treeViewer.setSorter(null);
			}
		}
			
	}
	
	
	
	protected void removeSelected() {
		if (treeViewer.getSelection().isEmpty()) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
		/* Tell the tree to not redraw until we finish
		 * removing all the selected children. */
		treeViewer.getTree().setRedraw(false);
		for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
			Model model = (Model) iterator.next();
			MovingBox parent = model.getParent();
			parent.remove(model);
		}
		treeViewer.getTree().setRedraw(true);
	}
	
	
	
	protected void addNewBook() {
		MovingBox receivingBox;
		if (treeViewer.getSelection().isEmpty()) {
			receivingBox = root;
		} else {
			IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
			Model selectedDomainObject = (Model) selection.getFirstElement();
			if (!(selectedDomainObject instanceof MovingBox)) {
				receivingBox = selectedDomainObject.getParent();
			} else {
				receivingBox = (MovingBox) selectedDomainObject;
			}
		}
		receivingBox.add(Book.newBook());
	}
	
	
	
	protected void createActions() {
		onlyBoardGamesAction = new Action("Only Board Games") {
			public void run() {
				updateFilter(onlyBoardGamesAction);
			}
		};
		onlyBoardGamesAction.setChecked(false);
		
		atLeatThreeItems = new Action("Boxes With At Least Three Items") {
			public void run() {
				updateFilter(atLeatThreeItems);
			}
		};
		atLeatThreeItems.setChecked(false);
		
		booksBoxesGamesAction = new Action("Books, Boxes, Games") {
			public void run() {
				updateSorter(booksBoxesGamesAction);
			}
		};
		booksBoxesGamesAction.setChecked(false);
		
		noArticleAction = new Action("Ignoring Articles") {
			public void run() {
				updateSorter(noArticleAction);
			}
		};
		noArticleAction.setChecked(false);
		
		addBookAction = new Action("Add Book") {
			public void run() {
				addNewBook();
			}			
		};
		addBookAction.setToolTipText("Add a New Book");
		addBookAction.setImageDescriptor(TreeViewerPlugin.getImageDescriptor("newBook.gif"));

		removeAction = new Action("Delete") {
			public void run() {
				removeSelected();
			}			
		};
		removeAction.setToolTipText("Delete");
		removeAction.setImageDescriptor(TreeViewerPlugin.getImageDescriptor("remove.gif"));		
	}
	//FINE NUOVO
	
	
	
	 public MovingBox getInitalInput() {
			root = new MovingBox();
			MovingBox someBooks = new MovingBox("Books");
			MovingBox games = new MovingBox("Games");
			MovingBox books = new MovingBox("More books");
			MovingBox games2 = new MovingBox("More games");
			MovingBox thriller = new MovingBox("Thriller");
			
			root.add(someBooks);
			root.add(games);
			root.add(new MovingBox());	//???
			
			
			someBooks.add(books);
			someBooks.add(thriller);
			games.add(games2);
			
			books.add(new Book("The Lord of the Rings", "J.R.R.", "Tolkien"));
			games2.add(new BoardGame("Taj Mahal", "Reiner", "Knizia"));
			books.add(new Book("Cryptonomicon", "Neal", "Stephenson"));
			books.add(new Book("Smalltalk, Objects, and Design", "Chamond", "Liu"));
			books.add(new Book("A Game of Thrones", "George R. R.", " Martin"));
			books.add(new Book("The Hacker Ethic", "Pekka", "Himanen"));
			//books.add(new MovingBox());
			
			books.add(new Book("The Code Book", "Simon", "Singh"));
			books.add(new Book("The Chronicles of Narnia", "C. S.", "Lewis"));
			books.add(new Book("The Screwtape Letters", "C. S.", "Lewis"));
			books.add(new Book("Mere Christianity ", "C. S.", "Lewis"));
			games.add(new BoardGame("Tigris & Euphrates", "Reiner", "Knizia"));		
			games.add(new BoardGame("La Citta", "Gerd", "Fenchel"));
			games.add(new BoardGame("El Grande", "Wolfgang", "Kramer"));
			games.add(new BoardGame("The Princes of Florence", "Richard", "Ulrich"));
			games.add(new BoardGame("The Traders of Genoa", "Rudiger", "Dorn"));
			games2.add(new BoardGame("Tikal", "M.", "Kiesling"));
			games2.add(new BoardGame("Modern Art", "Reiner", "Knizia"));		
			return root;
		}
	 
	 
	 	//NUOVO
		protected void fillMenu(IMenuManager rootMenuManager) {
			IMenuManager filterSubmenu = new MenuManager("Filters");
			rootMenuManager.add(filterSubmenu);
			filterSubmenu.add(onlyBoardGamesAction);
			filterSubmenu.add(atLeatThreeItems);
			
			IMenuManager sortSubmenu = new MenuManager("Sort By");
			rootMenuManager.add(sortSubmenu);
			sortSubmenu.add(booksBoxesGamesAction);
			sortSubmenu.add(noArticleAction);
		}
	 
	 
		protected void createMenus() {
			IMenuManager rootMenuManager = getViewSite().getActionBars().getMenuManager();
			rootMenuManager.setRemoveAllWhenShown(true);
			rootMenuManager.addMenuListener(new IMenuListener() {
				public void menuAboutToShow(IMenuManager mgr) {
					fillMenu(mgr);
				}
			});
			fillMenu(rootMenuManager);
		}
		
		
		
		protected void createToolbar() {
			IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
			toolbarManager.add(addBookAction);
			toolbarManager.add(removeAction);
		}
		
		
		
		protected void hookListeners() {
			treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					// if the selection is empty clear the label
					if(event.getSelection().isEmpty()) {
						text.setText("");
						return;
					}
					if(event.getSelection() instanceof IStructuredSelection) {
						IStructuredSelection selection = (IStructuredSelection)event.getSelection();
						StringBuffer toShow = new StringBuffer();
						for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
							Object domain = (Model) iterator.next();
							String value = labelProvider.getText(domain);
							toShow.append(value);
							toShow.append(", ");
						}
						// remove the trailing comma space pair
						if(toShow.length() > 0) {
							toShow.setLength(toShow.length() - 2);
						}
						text.setText(toShow.toString());
					}
				}
			});
		}
		//FINE NUOVO
		
	

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
 //CIAO
	//CIAO2
	//CIAO3
}
