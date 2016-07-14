package data;

import vk.core.api.CompilationUnit;

/**
 * Dieses Interface erfordert alle Gegebenheiten, die kompilierbarer
 * Code in unserem Projekt aufweisen muss.
 * 
 * @author Daniela Prigge, Lukas Rose, Rebecca Wagner
 */

public interface Code {
	public CompilationUnit getCompilationUnit();
	public String getContent();
	public String getName();
	public void backToOldCode();
	public void setCode(String code);
	public void overrideOldCode();
}
