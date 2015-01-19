package smcrepository.views;



//import smcrepository.CallEditor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import smcrepository.CallEditor;

public class CommentView extends ViewPart{

	public static TableViewer tableViewer;
	List<Comment> comments;
	public static final String ID = "smcrepository.views.Commenti";
	
	private Label label;
	//private CommentsDataColumn data;
	
	
	//Importante!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 @Inject
	 private IEventBroker eventBroker;
	 //Fine Importante!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
    //Importante	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 	EventHandler handlerComments = new EventHandler() {
 		public void handleEvent(Event event) {
       	  	List<Comment> commenti = (List<Comment>) event.getProperty(IEventBroker.DATA);
    	  	tableViewer.setInput(commenti);
    	  	
    	
 		}
    };
    //Fine Importante	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


	 
	
	
	
	
	
  
    
	@Override
	public void createPartControl(Composite parent) {
		
		tableViewer=new TableViewer(parent,SWT.V_SCROLL);
		//rende visibile l'intestazione della tabella
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.setContentProvider(
		ArrayContentProvider.getInstance());

        //getViewSite().getPage().addSelectionListener(this);
        
        
        eventBroker = (IEventBroker) PlatformUI.getWorkbench().getService(IEventBroker.class); 
	    eventBroker.subscribe("CommentsUpdate",handlerComments);
        

        
     
  
        new CommentDataColumn().addColumnTo(tableViewer);  //colonna per la data
        new CommentOraColumn().addColumnTo(tableViewer);  //colonna per l'ora
        new CommentUserColumn().addColumnTo(tableViewer);  //colonna per l'autore
        new CommentTextColumn().addColumnTo(tableViewer);  //colonna per il testo
      
    }
	
	
	/*
	 public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	
        if (selection instanceof IStructuredSelection) {
                Object first = ((IStructuredSelection)selection).getFirstElement();
                if (first instanceof Resource) {
                	
                	 comments=new ArrayList();
                	 comments.addAll(((Resource) first).getCommentsR());    
                }
                else {
                	//tableViewer.setInput(null);
                	System.out.println("Niente");
                }
                
             
        }
       
        
	}
	*/
	 
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		tableViewer.getControl().setFocus();
	}




}