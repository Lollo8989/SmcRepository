package smcrepository.views;



import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class CommentView extends ViewPart implements ISelectionListener {
	private TableViewer tableViewer;
	List<Comment> comments;
	
	private Label label;
	//private CommentsDataColumn data;
  
    
	@Override
	public void createPartControl(Composite parent) {
		tableViewer=new TableViewer(parent,SWT.H_SCROLL|SWT.V_SCROLL);
		//rende visibile l'intestazione della tabella
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.setContentProvider(
		ArrayContentProvider.getInstance());

        getViewSite().getPage().addSelectionListener(this);
     
  
        new CommentDataColumn().addColumnTo(tableViewer);  //colonna per la data
        new CommentUserColumn().addColumnTo(tableViewer);  //colonna per l'autore
        new CommentTextColumn().addColumnTo(tableViewer);  //colonna per il testo
      
    }
	
	
	
	 public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	
        if (selection instanceof IStructuredSelection) {
                Object first = ((IStructuredSelection)selection).getFirstElement();
                if (first instanceof Resource) {
                	
                	 comments=new ArrayList();
                	 comments.addAll(((Resource) first).getCommentsR());
                  	 tableViewer.setInput(comments);    
                }
                else {
                	tableViewer.setInput(null);
                }
             
        }
       
        
}
	 
	 
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		tableViewer.getControl().setFocus();
	}




}