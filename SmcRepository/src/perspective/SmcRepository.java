package perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class SmcRepository implements IPerspectiveFactory {
	
	//private static String ID_USER = "org.eclipse.ui.views.User";
	 private static final String VIEW_ID =
		        "smcrepository.views.User";
	 private static IPageLayout layout;
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
	
	
		layout.setFixed(true);
		String editorArea = layout.getEditorArea();
		
		
		
		IFolderLayout left =
                layout.createFolder("left", IPageLayout.LEFT, (float) 0.26, editorArea);
        left.addView(VIEW_ID);

	}
	
	
}
