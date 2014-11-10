package smcrepository.views;

import java.util.List;

public class CommentTextColumn extends CommentColumn {

	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		
		if (element instanceof Comment) {
			
			return ((Comment) element).getTesto();
		}
		else
		{
			return "";
		}
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "TESTO";
	}
	

}
