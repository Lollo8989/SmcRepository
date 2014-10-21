package smcrepository.views;

import java.net.MalformedURLException;

import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The main plugin class to be used in the desktop.
 */
public class TreeViewerPlugin extends AbstractUIPlugin {
	// The shared instance.
	private static TreeViewerPlugin plugin;
	// Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public TreeViewerPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		plugin = this;
		try {
			resourceBundle = ResourceBundle
					.getBundle("cbg.article.treeviewer.TreeviewerPluginResource");
		} catch (MissingResourceException x) {
			resourceBundle = null;
			System.out.println("Errore");
		}
	}

	/**
	 * Returns the shared instance.
	 */
	public static TreeViewerPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = TreeViewerPlugin.getDefault()
				.getResourceBundle();
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}

	public static ImageDescriptor getImageDescriptor(String name) {
		String iconPath = "icons/";
		try {
			System.out.println("Ciao22222222222222222222222222222");
			// URL installURL = getDefault().getDescriptor().getInstallURL();

			//System.out.println("Ciao22222222222222222222222222222");
			//URL installURL = getDefault().getDescriptor().getInstallURL();

			String percorso = iconPath + name;

			// URL sample = get

			//System.out.println("IL percorso vale :" + percorso);
			//URL sample = get

			URL url = new URL(percorso);
			return ImageDescriptor.createFromURL(url);
			// } catch (MalformedURLException e) {
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CiaoCatchhhhhhhhhhhhhhhhhhhhhhhhhhh");
			// should not happen
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	public static String Stampa(String text) {
		return text;
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}
