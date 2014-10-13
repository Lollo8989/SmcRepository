package smcrepository.views;

import java.util.Iterator;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

public class MovingBoxContentProvider implements ITreeContentProvider, IDeltaListener{

	private static Object[] EMPTY_ARRAY = new Object[0];
	protected TreeViewer viewer;
	
	public void dispose() {}
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput){
		this.viewer = (TreeViewer)viewer;
		if(oldInput != null) {
			removeListenerFrom((MovingBox)oldInput);
		}
		if(newInput != null) {
			addListenerTo((MovingBox)newInput);
		}
	}
	
	protected void removeListenerFrom(MovingBox box) {
		box.removeListener(this);
		for (Iterator iterator = box.getBoxes().iterator(); iterator.hasNext();) {
			MovingBox aBox = (MovingBox) iterator.next();
			removeListenerFrom(aBox);
		}
	}
	
	
	protected void addListenerTo(MovingBox box) {
		box.addListener(this);
		for (Iterator iterator = box.getBoxes().iterator(); iterator.hasNext();) {
			MovingBox aBox = (MovingBox) iterator.next();
			addListenerTo(aBox);
		}
	}
	
	
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof MovingBox) {
			MovingBox box = (MovingBox)parentElement;
			return concat(box.getBoxes().toArray(), 
				box.getBooks().toArray(), box.getGames().toArray());
		}
		return EMPTY_ARRAY;
	}
	
	
	protected Object[] concat(Object[] object, Object[] more, Object[] more2) {
		Object[] both = new Object[object.length + more.length + more2.length];
		System.arraycopy(object, 0, both, 0, object.length);
		System.arraycopy(more, 0, both, object.length, more.length);
		System.arraycopy(more2, 0, both, object.length + more.length, more2.length);		
		return both;
	}
	
	
	public Object getParent(Object element) {
		if(element instanceof Model) {
			return ((Model)element).getParent();
		}
		return null;
	}

	/*
	 * @see ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	/*
	 * @see IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/*
	 * @see IDeltaListener#add(DeltaEvent)
	 */
	public void add(DeltaEvent event) {
		Object movingBox = ((Model)event.receiver()).getParent();
		viewer.refresh(movingBox, false);
	}

	/*
	 * @see IDeltaListener#remove(DeltaEvent)
	 */
	public void remove(DeltaEvent event) {
		add(event);
	}
	

}
