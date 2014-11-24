package smcrepository.views;

public interface IModelVisitor {
	public void visitMovingBox(Box box, Object passAlongArgument);
	public void visitResources(Resource resources, Object passAlongArgument);
	public void visitWorkspaces(Workspace workspaces,Object passAlongArgument);

}
