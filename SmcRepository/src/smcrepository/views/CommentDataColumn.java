package smcrepository.views;

import java.util.List;
import java.util.TimeZone;

public class CommentDataColumn extends CommentColumn {
	
	public String getText(Object element) {
		if (element instanceof Comment) {

			return ((Comment) element).getData();
		} else {
				return "";
			}
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "DATA";
	}

}
