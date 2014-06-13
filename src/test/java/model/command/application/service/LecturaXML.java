package model.command.application.service;

//import static org.junit.Assert.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import org.junit.Test;

public class LecturaXML {

	@Test
	public void testFindSourcePathInEclipseProject() {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			File f = new File("src/test/java/resources/");
			File myFile = null;
			for (File file : f.listFiles()) {
				if (file.isHidden()) {
					myFile = file;
					System.out.println("Archivo con el 'source path' :: "
							+ myFile.getName());
				}
			}
			Document doc = dBuilder.parse(myFile);
			doc.getDocumentElement().normalize();

			System.out.println("El elemento raiz es :: "
					+ doc.getDocumentElement().getNodeName());
			System.out.println("Primer nodo es :: classpathentry");

			NodeList classpathentry = doc
					.getElementsByTagName("classpathentry");

			System.out.println("'classpathentry' tiene :: "
					+ classpathentry.getLength() + " atributos");
			NamedNodeMap nnm = classpathentry.item(0).getAttributes();
			String sourcePath = null;
			for (int i = 0; i < nnm.getLength(); i++) {
				System.out.println((i + 1) + "N° atributo :: "
						+ nnm.item(i).getNodeName());
				if (nnm.item(i).getNodeName().equals("path")) {
					sourcePath = nnm.item(i).getNodeValue();
					break;
				}
			}
			System.out.println("Ruta del 'source path' :: " + sourcePath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
