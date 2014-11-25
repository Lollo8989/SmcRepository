package smcrepository.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resource extends Model implements Serializable {


	public Resource(int id, String name, String tipologia, String contenuto,
			String pubblico, List<Comment> commenti,int idWorkspace) {
		
		super(id,name,tipologia,contenuto,pubblico,commenti,idWorkspace);
	}


}
