package smcrepository.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class User extends ViewPart {
	
	protected Text text;
	protected TreeViewer treeViewer;
	//protected MovingBoxLaberProvider labelProvider;
	protected MovingBox root;
	
		
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
		 //labelProvider = new MovingBoxLabelProvider();
		 //treeViewer.setLabelProvider(labelProvider);
		 treeViewer.setUseHashlookup(true);
		 
		 treeViewer.setInput(getInitalInput());
		 treeViewer.expandAll();
		 
		 
		 

		 
		 
	}
	
	
	 public MovingBox getInitalInput() {
			root = new MovingBox();
			MovingBox someBooks = new MovingBox("Books");
			MovingBox games = new MovingBox("Games");
			MovingBox books = new MovingBox("More books");
			MovingBox games2 = new MovingBox("More games");
			
			root.add(someBooks);
			root.add(games);
			root.add(new MovingBox());
			
			someBooks.add(books);
			games.add(games2);
			
			books.add(new Book("The Lord of the Rings", "J.R.R.", "Tolkien"));
			books.add(new BoardGame("Taj Mahal", "Reiner", "Knizia"));
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
	

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
