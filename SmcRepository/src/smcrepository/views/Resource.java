package smcrepository.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resource extends Model implements Serializable {


	public Resource(int id, String name, String tipologia, String contenuto,
			String pubblico, List<Comment> commenti,int idWorkspace) {
		
		super(id,name,tipologia,contenuto,pubblico,commenti,idWorkspace);
	}

	@Override
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		// TODO Auto-generated method stub
		visitor.visitResources(this, passAlongArgument);
	}

}
