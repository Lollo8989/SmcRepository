package smcrepository.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Workspace extends Model implements Serializable {

	public Workspace(int id,String name,String descrizione,String tipologia,List<Resource> resources)
	{
		
		super(id,name,descrizione,tipologia,resources);
	}	
	
	@Override
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		// TODO Auto-generated method stub
		visitor.visitWorkspaces(this, passAlongArgument);
	}
	

}
