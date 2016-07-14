package data;

import vk.core.api.CompilationUnit;

/**
 * Interface, welches von Class und Test genutzt wird.
 *
 */
public interface Code {
	public CompilationUnit getCompilationUnit();
	public String getContent();
	public String getName();
	public void backToOldCode();
	public void setCode(String code);
	public void overrideOldCode();
}
