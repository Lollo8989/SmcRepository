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

public class BoxLabelProvider extends LabelProvider {
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

		// Other
		url = getClass().getResource("/icons/sample.gif");
		ir.put("Other", ImageDescriptor.createFromURL(url));

		//resources
		url=getClass().getResource("/icons/resource.gif");
		ir.put("Resources", ImageDescriptor.createFromURL(url));
		//workspace
		url=getClass().getResource("/icons/ws.gif");
		ir.put("Workspaces", ImageDescriptor.createFromURL(url));
		
        if (element instanceof Resource) {
        	return ir.get("Resources");
        }
        else if (element instanceof Workspace) {
        	
        	return ir.get("Workspaces");
        }
        else if (element instanceof Box) {
			return ir.get("MovingBox");
		}
		else {
			return ir.get("Other");
		}

	}

	/*
	 * @see ILabelProvider#getText(Object)
	 */
	public String getText(Object element) {
		if (element instanceof Box) {
			if (((Box) element).getName() == null) {
				return "Box";
			} else {
				return ((Box) element).getName();
			}
		} else if (element instanceof Resource) {
			return (((Resource) element).getidR() + "-" + ((Resource) element)
					.getNameR());
		}
			else if (element instanceof Workspace) {
			return (((Workspace) element).getNamework());

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
