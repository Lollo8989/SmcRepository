package smcrepository.views;

import org.eclipse.jface.viewers.ViewerSorter;
/* Ordinare gli elementi*/
public class BookBoxBoardSorter extends ViewerSorter {

	/*
	 * @see ViewerSorter#category(Object)
	 */
	/**
	 * Orders the items in such a way that books appear before moving boxes,
	 * which appear before board games.
	 */
	public int category(Object element) {
		if (element instanceof Book)
			return 1;
		if (element instanceof MovingBox)
			return 2;
		// ***********************************************
		if (element instanceof Resources)
			return 4;
		if (element instanceof Resources)
			return 5;
		// **********************************************
		return 3;
	}

}