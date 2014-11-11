package smcrepository.views;




import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;






import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import smcrepository.PasswordDialog;
import smcrepository.Repository;

public class User extends ViewPart {

	protected Text text;
	protected TreeViewer treeViewer;
	protected MovingBoxLabelProvider labelProvider;
	protected MovingBox root;
	public static final String ID = "smcrepository.views.User";


	// NUOVO
	//protected ViewerFilter onlyBoardGamesFilter, atLeastThreeFilter;
	//protected ViewerSorter booksBoxesGamesSorter, noArticleSorter;
	//protected Action onlyBoardGamesAction, atLeatThreeItems;
	//protected Action booksBoxesGamesAction, noArticleAction;
	//protected Action addBookAction, removeAction;
	// FINE NUOVO

	// ********************************************
	protected Repository repository;
	protected List<Resource> resources;
	protected List<Workspace> workspaces;

	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.MULTI| SWT.H_SCROLL |SWT.V_SCROLL);
		

		//GridLayout layout = new GridLayout();
		//layout.numColumns=1;
		//layout.verticalSpacing = 0;
		//layout.marginWidth = 0;
		//layout.marginHeight = 0;
	
		//parent.setLayout(layout);
	

		//text = new Text(parent, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
		//GridData layoutData = new GridData();
		//layoutData.horizontalSpan=0;
		//layoutData.verticalSpan=0;
		
		//parent.setLayoutData(layoutData);
		//layoutData.grabExcessHorizontalSpace = true;
		//layoutData.horizontalAlignment = GridData.FILL;
		//text.setLayoutData(layoutData);
		//text.setText("Prova");

		//treeViewer = new TreeViewer(parent);
		//setta il gestore del contenuto
		treeViewer.setContentProvider(new MovingBoxContentProvider()); 
		
														
		labelProvider = new MovingBoxLabelProvider();
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setUseHashlookup(true);
		//comment=new Comment();

		
		getSite().setSelectionProvider(treeViewer);
		hookDoubleClickCommand();
		// Create menu, toolbars, filters, sorters.
		//createFiltersAndSorters();
		//createActions();
		//createMenus();
		//createToolbar();
		//hookListeners();
	
		Shell shell=new Shell();
		//shell.setLayout(layout);
		PasswordDialog dialog = new PasswordDialog(shell);
		//se il login è corretto vado a vedere se il file esiste già oppure se è danneggiato
					
		File file=new File(Serializer.getPath());
		if(!file.exists())
		    	  {
		    		   if (dialog.open() == Window.OK) {
		    			      String user = dialog.getUser();
		    			      String pw = dialog.getPassword();
		    			      System.out.println(user);
		    			      System.out.println(pw);
		    			      if(user.equals("paola") && pw.equals("123"))
		    			      {
		    			    	  repository=new Repository();
		    			    	  treeViewer.setInput(getInitalInput(repository));
		    			    	  treeViewer.expandAll();
		    			      }
		    		   }
		    			      
		    	  }
		    	   else {
		    		   Repository addr2 = Serializer.estrazione();
		    		   treeViewer.setInput(getInitalInput(addr2));
		    		   treeViewer.expandAll();
		    	   }
		    	 
		      }
		    
		

	// NUOVO
	/*protected void createFiltersAndSorters() {
		atLeastThreeFilter = new ThreeItemFilter();
		onlyBoardGamesFilter = new BoardgameFilter();
		booksBoxesGamesSorter = new BookBoxBoardSorter();
		noArticleSorter = new NoArticleSorter();
	}*/

	/* Multiple filters can be enabled at a time. */
	/*protected void updateFilter(Action action) {
		if (action == atLeatThreeItems) {
			if (action.isChecked()) {
				treeViewer.addFilter(atLeastThreeFilter);
			} else {
				treeViewer.removeFilter(atLeastThreeFilter);
			}
		} else if (action == onlyBoardGamesAction) {
			if (action.isChecked()) {
				treeViewer.addFilter(onlyBoardGamesFilter);
			} else {
				treeViewer.removeFilter(onlyBoardGamesFilter);
			}
		}
	}
*/
	/*protected void updateSorter(Action action) {
		if (action == booksBoxesGamesAction) {
			noArticleAction.setChecked(!booksBoxesGamesAction.isChecked());
			if (action.isChecked()) {
				treeViewer.setSorter(booksBoxesGamesSorter);
			} else {
				treeViewer.setSorter(null);
			}
		} else if (action == noArticleAction) {
			booksBoxesGamesAction.setChecked(!noArticleAction.isChecked());
			if (action.isChecked()) {
				treeViewer.setSorter(noArticleSorter);
			} else {
				treeViewer.setSorter(null);
			}
		}

	}
*/
	protected void removeSelected() {
		if (treeViewer.getSelection().isEmpty()) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) treeViewer
				.getSelection();
		/*
		 * Tell the tree to not redraw until we finish removing all the selected
		 * children.
		 */
		treeViewer.getTree().setRedraw(false);
		for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
			Model model = (Model) iterator.next();
			MovingBox parent = model.getParent();
			parent.remove(model);
		}
		treeViewer.getTree().setRedraw(true);
	}

	/*protected void addNewBook() {
		MovingBox receivingBox;
		if (treeViewer.getSelection().isEmpty()) {
			receivingBox = root;
		} else {
			IStructuredSelection selection = (IStructuredSelection) treeViewer
					.getSelection();
			Model selectedDomainObject = (Model) selection.getFirstElement();
			if (!(selectedDomainObject instanceof MovingBox)) {
				receivingBox = selectedDomainObject.getParent();
			} else {
				receivingBox = (MovingBox) selectedDomainObject;
			}
		}
		receivingBox.add(Book.newBook());
	}*/
/*
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
		};*/


	
		//NON FUNZIONA (cercare di farlo funzionare con la classe "TreeViewerPlugin"
		//addBookAction.setToolTipText("Add a New Book");
		//addBookAction.setImageDescriptor(TreeViewerPlugin.getImageDescriptor("newBook.gif"));
		
		

		
		
		
		


		//addBookAction.setToolTipText("Add a New Book");

		//addBookAction.setImageDescriptor(ImageDescriptor.createFromURL(url));

	
	
		
			
		
/*

		removeAction = new Action("Delete") {
			public void run() {
				removeSelected();
			}
		};*/


		
		
		//ICONA REMOVE

		//url2 = getClass().getResource("/icons/remove.gif");
		//removeAction.setToolTipText("Delete");
		//removeAction.setImageDescriptor(ImageDescriptor.createFromURL(url2));

		
			

	//}

	// FINE NUOVO

	public MovingBox getInitalInput(Repository rep) {
		root = new MovingBox();
		MovingBox res = new MovingBox("Resources");
		MovingBox ws=new MovingBox("Workspaces");
		MovingBox asts = new MovingBox("ASTS");
		MovingBox anctl = new MovingBox("AnCTL");
		MovingBox ontologie = new MovingBox("Ontologie");

		root.add(res);
		root.add(ws);
		
		res.add(asts);
		res.add(anctl);
		res.add(ontologie);
		 
		
		resources=rep.getResourcesList();
		
		workspaces = rep.getWorkspaceList();
		
		
		  for(int i=0;i<resources.size();i++) {
			  
			if (resources.get(i).getTipologiaR().equals("ASTS"))
			{
				asts.add(new Resource(resources.get(i).getidR(),resources.get(i).getNameR(),resources.get(i).getTipologiaR(),
						resources.get(i).getContenutoR(),resources.get(i).getPubblicoR(),resources.get(i).getCommentsR()));
			}
			if (resources.get(i).getTipologiaR().equals("AnCTL"))
			{
				anctl.add(new Resource(resources.get(i).getidR(),resources.get(i).getNameR(),resources.get(i).getTipologiaR(),
						resources.get(i).getContenutoR(),resources.get(i).getPubblicoR(),resources.get(i).getCommentsR()));
			}
			if (resources.get(i).getTipologiaR().equals("Ontologia"))
			{
				ontologie.add(new Resource(resources.get(i).getidR(),resources.get(i).getNameR(),resources.get(i).getTipologiaR(),
						resources.get(i).getContenutoR(),resources.get(i).getPubblicoR(),resources.get(i).getCommentsR()));
			} 
		  }
		  
		  for (int i=0;i<workspaces.size();i++) {
			  
			  MovingBox workspace=new MovingBox(workspaces.get(i).getidW() + "-" + workspaces.get(i).getNameW());
			  ws.add(workspace);
			  
			  List<Resource> resourcesList=null;
			  resourcesList=workspaces.get(i).getResourcesW();
			  
			  for(int j=0;j<resourcesList.size();j++)
			  {
				  workspace.add(new Resource(resourcesList.get(j).getidR(),resourcesList.get(j).getNameR(),resourcesList.get(i).getTipologiaR(),
							resourcesList.get(j).getContenutoR(),resourcesList.get(j).getPubblicoR(),resourcesList.get(j).getCommentsR()));
			  }
			  
		  }
		 
		Serializer.saveFile(rep); 
		return root;
	}

	// NUOVO
	/*protected void fillMenu(IMenuManager rootMenuManager) {
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
		IMenuManager rootMenuManager = getViewSite().getActionBars()
				.getMenuManager();
		rootMenuManager.setRemoveAllWhenShown(true);
		rootMenuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				fillMenu(mgr);
			}
		});
		fillMenu(rootMenuManager);
	}

	protected void createToolbar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(addBookAction);
		toolbarManager.add(removeAction);
	}

	protected void hookListeners() {
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				// if the selection is empty clear the label
				if (event.getSelection().isEmpty()) {
					text.setText("");
					return;
				}
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event
							.getSelection();
					StringBuffer toShow = new StringBuffer();
					for (Iterator iterator = selection.iterator(); iterator
							.hasNext();) {
						Object domain = (Model) iterator.next();
						String value = labelProvider.getText(domain);
						toShow.append(value);
						toShow.append(", ");
					}
					// remove the trailing comma space pair
					if (toShow.length() > 0) {
						toShow.setLength(toShow.length() - 2);
					}
					text.setText(toShow.toString());
				}
			}
		});
	}*/

	// FINE NUOVO


	 private void hookDoubleClickCommand() {
		    treeViewer.addDoubleClickListener(new IDoubleClickListener() {
		      public void doubleClick(DoubleClickEvent event) {
		        IHandlerService handlerService = (IHandlerService) getSite()
		            .getService(IHandlerService.class);
		        try {
		          handlerService.executeCommand("SmcRepository.command1", null);
		        } catch (Exception ex) {
		          throw new RuntimeException("smcrepository.CallEditor not found");
		        }
		      }
		    });
		  }
	
	
	
	
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
