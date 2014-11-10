package smcrepository.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import smcrepository.Repository;

public class Serializer {
 
   //Ritorna il percorso di dove vengono salvati/caricati i file
	public static String getPath(){
		 String USER_DIR_KEY = "user.dir";
		   String currentDir = System.getProperty(USER_DIR_KEY); //Mi dice la directory attuale di lavoro
		   String currentDirUpdate = currentDir.replace("\\", "\\\\");
		   String savingFile = currentDirUpdate.concat("\\\\repository_offline.rp"); 
		   return savingFile;
	}
   
	
	
   //Ritorna true se la creazione/scrittura del file è avvenuta corretamente altrimenti ritorna false
   public static boolean saveFile(Repository repo){
			   	    
			   try{
					  File file = new File(getPath());
				      if (file.createNewFile()){
				        System.out.println("File is created!");
				      }else{
				        System.out.println("File already exists.");
				      }
		
		 			  FileOutputStream fout = new FileOutputStream(getPath());
					  ObjectOutputStream oos = new ObjectOutputStream(fout);   
					  oos.writeObject(repo);
					  oos.close();
					  System.out.println("Scrittura avvenuta con successo");
					  
			   }catch(Exception ex){
				   ex.printStackTrace();
				   System.err.println("Scrittura errata");
				   return false;
			   }
		return true;
   }
   
   
   public static Repository estrazione(){
 		 
	  Repository repo = null; 	  
  
	  try{
			     
		   FileInputStream fin = new FileInputStream(getPath());
		   ObjectInputStream ois = new ObjectInputStream(fin);
		   repo = (Repository) ois.readObject();
		   ois.close();
		   System.out.println("Caricamento avvenuto con successo");
		   return repo;
 
	   }catch(Exception ex){
		   ex.printStackTrace();
		   System.err.println("Errore nel caricamento");
		   return null;
	   } 
   
	}
   
   
}