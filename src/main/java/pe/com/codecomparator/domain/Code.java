package pe.com.codecomparator.domain;

import java.io.File;
import java.io.Serializable;

public class Code implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4910922867942133113L;
	private String name;
	private File file = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Code { name : " + name + " }";
	}

}
