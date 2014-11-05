package smcrepository.views;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class MovingBoxLabelProvider extends LabelProvider {
	private Map imageCache = new HashMap(11);

	/*
	 * @see ILabelProvider#getImage(Object)
	 */
	public Image getImage(Object element) {

		ImageRegistry ir = new ImageRegistry();
		URL url = null;

		// MovingBox
		url = getClass().getResource("/icons/movingBox.gif");
		ir.put("MovingBox", ImageDescriptor.createFromURL(url));

		// Book
		url = getClass().getResource("/icons/book.gif");
		ir.put("Book", ImageDescriptor.createFromURL(url));

		// BoardGame
		url = getClass().getResource("/icons/gameboard.gif");
		ir.put("BoardGame", ImageDescriptor.createFromURL(url));

		// Other
		url = getClass().getResource("/icons/sample.gif");
		ir.put("Other", ImageDescriptor.createFromURL(url));
		
		//********************************************************
		//resources
		url=getClass().getResource("/icons/sample.gif");
		ir.put("Resources", ImageDescriptor.createFromURL(url));
		url=getClass().getResource("/icons/sample.gif");
		ir.put("Workspaces", ImageDescriptor.createFromURL(url));
		
        if (element instanceof Resources) {
        	return ir.get("Resources");
        }
        else if (element instanceof Workspaces) {
        	
        	return ir.get("Workspaces");
        }
        //********************************************************	
        else if (element instanceof MovingBox) {
			return ir.get("MovingBox");
		}

		else if (element instanceof Book) {
			return ir.get("Book");
		}

		else if (element instanceof BoardGame) {
			return ir.get("BoardGame");
		}

		else {
			return ir.get("Other");
		}

	}

	/*
	 * @see ILabelProvider#getText(Object)
	 */
	public String getText(Object element) {
		if (element instanceof MovingBox) {
			if (((MovingBox) element).getName() == null) {
				return "Box";
			} else {
				return ((MovingBox) element).getName();
			}
		} else if (element instanceof Book) {
			return ((Book) element).getTitle();
		} else if (element instanceof BoardGame) {
			return ((BoardGame) element).getTitle();
			// ************************************************
		} else if (element instanceof Resources) {
			return (((Resources) element).getidR() + "-" + ((Resources) element)
					.getNameR());
		}
			else if (element instanceof Workspaces) {
			return (((Workspaces) element).getidW() + "-" + ((Workspaces) element)
					.getNameW());
			// ***************************************************
		} else
			throw unknownElement(element);
	}

	public void dispose() {
		for (Iterator i = imageCache.values().iterator(); i.hasNext();) {
			((Image) i.next()).dispose();
		}
		imageCache.clear();
	}

	protected RuntimeException unknownElement(Object element) {
		return new RuntimeException("Unknown type of element in tree of type "
				+ element.getClass().getName());
	}

}
