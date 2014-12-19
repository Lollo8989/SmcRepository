package smcrepository.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.Perspective;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.part.ViewPart;

import perspective.SmcRepository;
import smcrepository.LogoutDialog;
import smcrepository.NewResourceDialog;
import smcrepository.NewWorkspaceDialog;
import smcrepository.PasswordDialog;
import smcrepository.Repository;

public class User extends ViewPart {

	protected Text text;
	protected static TreeViewer treeViewer;
	protected BoxLabelProvider labelProvider;
	protected static Box root;
	public static final String ID = "smcrepository.views.User";
	public URL url,url2,url3;
	protected String nameR,contenutoR,tipologiaR,pubblicoR;
	protected String nameW,descrizioneW,tipologiaW;
	protected int wsId;
	List<Comment> comment=new ArrayList();

	//Importante	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@Inject
	private IEventBroker eventBroker;
	//Fine Importante	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	
	// NUOVO
	//protected ViewerFilter onlyBoardGamesFilter, atLeastThreeFilter;
	//protected ViewerSorter booksBoxesGamesSorter, noArticleSorter;
	//protected Action onlyBoardGamesAction, atLeatThreeItems;
	//protected Action booksBoxesGamesAction, noArticleAction;
	protected Action addResourceAction,addWorkspaceAction,logout;
	// FINE NUOVO

	// ********************************************
	protected Repository repository;
	protected static List<Resource> resources;
	protected static List<Workspace> workspaces;
	public String token;

	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.MULTI| SWT.H_SCROLL |SWT.V_SCROLL);
		
		//setta il gestore del contenuto
		treeViewer.setContentProvider(new BoxContentProvider()); 
		
														
		labelProvider = new BoxLabelProvider();
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setUseHashlookup(true);
	 
		
		//Ascoltatore per aggiornare la View "Comments"
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				  
					List<Comment> listaCommenti=new ArrayList();
				
				  	IStructuredSelection selection =  (IStructuredSelection)treeViewer.getSelection();
				  	
				  	//Se nell'albero è selezionato più di un'elemento, nella view "Comments" non viene visualizzato niente.
				  	if(selection.size() > 1)
				  	{
				  		comment=new ArrayList();
				  		listaCommenti = comment; 
				  		} 
				  	else{
						  	Object selezionato = selection.getFirstElement();
						  //	System.out.println(selezionato.getClass());
						  					  	
			                if (selezionato instanceof Resource) {
			                	
			                	listaCommenti=((Resource) selezionato).getCommentsR();
						       
								System.out.println("Messaggio mandato");
			                }
			                else {
			                	System.out.println("Niente");
			                	
			                	listaCommenti = comment;
			                }
				  	}
	                eventBroker = (IEventBroker) PlatformUI.getWorkbench().getService(IEventBroker.class);
	                eventBroker.send("CommentsUpdate",listaCommenti);
			}
		});
		//Fine ascoltatore per aggiornare la View "Comments"
		
		
		
		getSite().setSelectionProvider(treeViewer);
		hookDoubleClickCommand();
		hookContextMenu(treeViewer);
		// Create menu, toolbars, filters, sorters.
		//createFiltersAndSorters();
		
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
		    			      
		    			      
		    			      token=directTokenRequest(user, pw);
		    			      System.out.println(token);
		    			      
		    			      if(token!=null)
		    			      {
		    			    	  createActions();
		    			  		  //createMenus();
		    			  		  createToolbar();
		    			    	  repository=new Repository();
		    			    	  treeViewer.setInput(getInitalInput(repository));
		    			    	  treeViewer.expandAll();
		    			    	  Serializer.saveFile(repository);
		    			      }
		    		   }
		    			      
		    	  }
		    	   else {
		    		   Repository addr2 = Serializer.estrazione();
 						createActions();
 			  		   //createMenus();
 			  		   createToolbar();
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
/*	protected void removeSelected() {
		if (treeViewer.getSelection().isEmpty()) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
		/*
		 * Tell the tree to not redraw until we finish removing all the selected
		 * children.
		 */
	/*	treeViewer.getTree().setRedraw(false);
		for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
			Model model = (Model) iterator.next();
			Box parent = model.getParent();
			parent.remove(model);
		}
		treeViewer.getTree().setRedraw(true);
	}
*/
	protected void logout(){
		
		Shell shell=new Shell();

		LogoutDialog dialog = new LogoutDialog(shell);
		
		if (dialog.open() == Window.OK) {
			
			
			Serializer.deleteFile();
			
			//IWorkbenchPage page=Workbench.getInstance().getActiveWorkbenchWindow().getActivePage();
	
			IWorkbench wb=PlatformUI.getWorkbench();
			
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();

			IWorkbenchPage page = win.getActivePage();

			IPerspectiveDescriptor perspective = page.getPerspective();
			
	
			page.closePerspective(perspective, false, true);
			
			//Perspective perspective = page.getPerspective();
			//page.closePerspective((IPerspectiveDescriptor) perspective, false, true);

			//tring viewId = "smcrepository.views.User"; 

			//get the reference for your viewId
			//IViewReference ref = page.findViewReference(viewId);

					//release the view
			//perspective.getViewFactory.releaseView(ref);

			
		}
		
		
		
		
	}
	
	
	protected void addNewWorkspace(){
		
		Shell shell=new Shell();

		NewWorkspaceDialog dialog = new NewWorkspaceDialog(shell);
		
		if (dialog.open() == Window.OK && !dialog.getName().equals("")) {
			
			nameW=dialog.getName();
			descrizioneW=dialog.getDescrizione();
			tipologiaW=dialog.getTipologia();
			List<Resource> resources=new ArrayList();

			Repository rep = Serializer.estrazione();
			
			int max=0;
			for(int i=0;i<rep.getWorkspaceList().size();i++){
				if(rep.getWorkspaceList().get(i).getidW()>max)
						max=rep.getWorkspaceList().get(i).getidW();	
				
			}
			rep.getWorkspaceList().add(new Workspace(max+1,nameW,descrizioneW,tipologiaW,resources));
			
			Serializer.saveFile(rep);
			User.restartView(rep);
		}
	}
	
	
	
	protected void addNewResource() {
		//Box receivingBox=root;
		
		Shell shell=new Shell();

		NewResourceDialog dialog = new NewResourceDialog(shell);
		
		if (dialog.open() == Window.OK && !dialog.getName().equals("")) {
			
			nameR=dialog.getName();
			contenutoR=dialog.getContenuto();
			tipologiaR=dialog.getTipologia();
			pubblicoR=dialog.getPubblico();
			wsId=dialog.getIDWorkspace();
			List<Comment> comments=new ArrayList();
			
			Repository rep = Serializer.estrazione();
			
			int max=0;
			for(int i=0;i<rep.getResourcesList().size();i++)
			{
				if(rep.getResourcesList().get(i).getidR()>max)
					max=rep.getResourcesList().get(i).getidR();
			}
			for(int i=0;i<rep.getWorkspaceList().size();i++){
				for(int j=0;j<rep.getWorkspaceList().get(i).getResourcesW().size();j++){
					if(rep.getWorkspaceList().get(i).getResourcesW().get(j).getidR()>max)
						max=rep.getWorkspaceList().get(i).getResourcesW().get(j).getidR();
				}
			}
			
			if (wsId==0){
				rep.getResourcesList().add(new Resource(max+1,nameR,tipologiaR,contenutoR,pubblicoR,comments,wsId));
			}
			else {
				for(int i=0;i<rep.getWorkspaceList().size();i++){
					if (rep.getWorkspaceList().get(i).getidW()==wsId){
						rep.getWorkspaceList().get(i).getResourcesW().add(new Resource(max+1,nameR,tipologiaR,contenutoR,pubblicoR,comments,wsId));
					}
				}
			}
			
			Serializer.saveFile(rep);
			User.restartView(rep);
			//treeViewer.setInput(getInitalInput(rep));
 		    //treeViewer.expandAll();
 		   //Serializer.saveFile(repository);
			
		}
	}
		
		/*if (treeViewer.getSelection().isEmpty()) {
			receivingBox = root;
		} else {
			IStructuredSelection selection = (IStructuredSelection) treeViewer
					.getSelection();
			Model selectedDomainObject = (Model) selection.getFirstElement();
			if (!(selectedDomainObject instanceof Box)) {
				receivingBox = selectedDomainObject.getParent();
			} else {
				receivingBox = (Box) selectedDomainObject;
			}
		}
		
		receivingBox.add(Book.newBook());
	}*/

	protected void createActions() {
		//onlyBoardGamesAction = new Action("Only Board Games") {
			//public void run() {
				//updateFilter(onlyBoardGamesAction);
			//}
		//};
		//onlyBoardGamesAction.setChecked(false);

		//atLeatThreeItems = new Action("Boxes With At Least Three Items") {
			//public void run() {
				//updateFilter(atLeatThreeItems);
			//}
		//};
		//atLeatThreeItems.setChecked(false);

		//booksBoxesGamesAction = new Action("Books, Boxes, Games") {
			//public void run() {
				//updateSorter(booksBoxesGamesAction);
			//}
		//};
		//booksBoxesGamesAction.setChecked(false);

		//noArticleAction = new Action("Ignoring Articles") {
			//public void run() {
				//updateSorter(noArticleAction);
		//	}
		//};
		//noArticleAction.setChecked(false);
		logout = new Action("Logout") {
			public void run() {
				logout();
			}
		};
		
		
		addResourceAction = new Action("Add Resource") {
			public void run() {
				addNewResource();
			}
		};
		
		addWorkspaceAction = new Action("Add Workspace") {
			public void run() {
				addNewWorkspace();
			}
		};


	
		//NON FUNZIONA (cercare di farlo funzionare con la classe "TreeViewerPlugin"
		//addBookAction.setToolTipText("Add a New Book");
		//addBookAction.setImageDescriptor(TreeViewerPlugin.getImageDescriptor("newBook.gif"));
		
		
	
		url = getClass().getResource("/icons/logout.gif");
		logout.setToolTipText("Logout");
		logout.setImageDescriptor(ImageDescriptor.createFromURL(url));
		
	
		

		url2 = getClass().getResource("/icons/resource+.gif");
		addResourceAction.setToolTipText("Add a New Resource");
		addResourceAction.setImageDescriptor(ImageDescriptor.createFromURL(url2));

		
		url3 = getClass().getResource("/icons/ws+.gif");
		addWorkspaceAction.setToolTipText("Add a New Workspace");
		addWorkspaceAction.setImageDescriptor(ImageDescriptor.createFromURL(url3));
	
	}
		
			
		
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

	public static  Box getInitalInput(Repository rep) {
		root = new Box();
		Box res = new Box("Resources");
		Box ws=new Box("Workspaces");
		Box asts = new Box("ASTS");
		Box anctl = new Box("AnCTL");
		Box ontologie = new Box("Ontologie");

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
						resources.get(i).getContenutoR(),resources.get(i).getPubblicoR(),resources.get(i).getCommentsR(),resources.get(i).getIdWorkspace()));
			}
			if (resources.get(i).getTipologiaR().equals("AnCTL"))
			{
				anctl.add(new Resource(resources.get(i).getidR(),resources.get(i).getNameR(),resources.get(i).getTipologiaR(),
						resources.get(i).getContenutoR(),resources.get(i).getPubblicoR(),resources.get(i).getCommentsR(),resources.get(i).getIdWorkspace()));
			}
			if (resources.get(i).getTipologiaR().equals("Ontologia"))
			{
				ontologie.add(new Resource(resources.get(i).getidR(),resources.get(i).getNameR(),resources.get(i).getTipologiaR(),
						resources.get(i).getContenutoR(),resources.get(i).getPubblicoR(),resources.get(i).getCommentsR(),resources.get(i).getIdWorkspace()));
			} 
		  }
		  
		  for (int i=0;i<workspaces.size();i++) {
			  
			 // Workspace workspace= new Workspace(workspaces.get(i).getidW(),workspaces.get(i).getNameW(),workspaces.get(i).getDescrizioneW(),workspaces.get(i).getTipologiaW(),workspaces.get(i).getResourcesW());
			  //Box workspace=new Box("WS"+workspaces.get(i).getidW());  
			  //Box workspace=new Box(workspaces.get(i).getidW());
			  //Workspace workspace=new Workspace(workspaces.get(i).getNameW());
			  Workspace workspace=new Workspace(workspaces.get(i).getidW(),workspaces.get(i).getNameW(),workspaces.get(i).getDescrizioneW(),workspaces.get(i).getTipologiaW(),workspaces.get(i).getResourcesW());
			  
			  ws.add(workspace);
			  //workspace.add(new Workspace(workspaces.get(i).getidW(),workspaces.get(i).getNameW(),workspaces.get(i).getDescrizioneW(),workspaces.get(i).getTipologiaW(),workspaces.get(i).getResourcesW()));
			  List<Resource> resourcesList=null;
			  resourcesList=workspaces.get(i).getResourcesW();
			  
			  for(int j=0;j<resourcesList.size();j++)
			  {
				  workspace.add(new Resource(resourcesList.get(j).getidR(),resourcesList.get(j).getNameR(),resourcesList.get(j).getTipologiaR(),
							resourcesList.get(j).getContenutoR(),resourcesList.get(j).getPubblicoR(),resourcesList.get(j).getCommentsR(),resourcesList.get(j).getIdWorkspace()));
			  }
			  
		  }
		 
		//Serializer.saveFile(rep); 
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
	*/

	protected void createToolbar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(addResourceAction);
		toolbarManager.add(addWorkspaceAction);
		toolbarManager.add(logout);
		//toolbarManager.add(removeAction);
	}
/*
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

	public static void restartView(Repository rep)
	{
		treeViewer.setInput(getInitalInput(rep));
		treeViewer.expandAll();
		
	}
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
	
	 private void hookContextMenu(Viewer viewer) {
		 MenuManager manager = new MenuManager("#PopupMenu");
		 Menu menu = manager.createContextMenu(viewer.getControl());
		 viewer.getControl().setMenu(menu);
		 getSite().registerContextMenu(manager, viewer);
	}
	
	
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	
	
	 private static String directTokenRequest(String user, String pass) {
	    	
	    	
	        try {
	        	 URL url2;
				try {
					//url2 = new URL("http://localhost:8080/de.vogella.jersey.todo");
					
					/*
					//Prova GET
					
					final String USER_AGENT = "Mozilla/5.0";
					
					String url = "http://localhost:8080/oauth2/api/token";
					 
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 
					// optional default is GET
					con.setRequestMethod("POST");
			 
					//add request header
					con.setRequestProperty("User-Agent", USER_AGENT);
			 
					int responseCode = con.getResponseCode();
					System.out.println("\nSending 'GET' request to URL : " + url);
					System.out.println("Response Code : " + responseCode);
			 
					BufferedReader in = new BufferedReader(
					        new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
			 
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
			 
					//print result
					System.out.println(response.toString());
			 
					
					// Fine prova GET
					
					*/
					
					url2 = new URL("http://localhost:8080/oauth2/api");
					OAuthClientRequest request = OAuthClientRequest
		                    .tokenLocation(url2.toString() + "/token")
		                    .setGrantType(GrantType.PASSWORD)
		                    .setClientId("oauth2test")
		                    .setClientSecret("oauth2clientsecret")
		                    .setUsername(user)
		                    .setPassword(pass)
		                    .buildBodyMessage();
					
					OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		            OAuthAccessTokenResponse oauthResponse = oAuthClient.accessToken(request);
		            System.out.println(oauthResponse.getAccessToken().toString());
		            return oauthResponse.getAccessToken().toString();
		            
		            
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
	        } catch (OAuthSystemException | OAuthProblemException ex ) {
	            //Logger.getLogger(AuthTest.class.getName()).log(Level.SEVERE, null, ex);
	        	ex.printStackTrace();
	        } catch (Exception e)
	        {
	        	e.printStackTrace();
	        }
	        return null; 
	    
	    }
}
