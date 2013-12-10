package pe.com.codecomparator.model.command.application.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import pe.com.codecomparator.model.command.application.service.TokenizadorMetodos;

public class TokenizadorMetodosImpl implements TokenizadorMetodos {

	public boolean isCorrectFile(String path, String extension)
			throws IOException {
		File f = new File(path);
		String ext = Files.probeContentType(f.toPath());

		return ext.equalsIgnoreCase(extension);
	}

}
