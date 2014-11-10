package smcrepository;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class MyEditorInput implements IEditorInput {

    private int id;
    private String name;
    private String contenuto;
    private String tipologia;
   
    public MyEditorInput(int id,String name,String contenuto,String tipologia) {
        this.id = id;
        this.name=name;
        this.contenuto=contenuto;
        this.tipologia=tipologia;
    }
    public String getNameR()
    {
    	return name;
    }
    public String getContenuto()
    {
    	return contenuto;
    }
    public String getTipologia()
    {
    	return tipologia;
    }
    
    
    public int getId() {
        return id;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return "Displays a resources";
    }

    @Override
    public Object getAdapter(Class adapter) {
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyEditorInput other = (MyEditorInput) obj;
        if (id != other.id)
            return false;
        return true;
    }
   

} 