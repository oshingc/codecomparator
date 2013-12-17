package pe.com.codecomparator.domain;

public class CodeComparatorConstants {

	// Constantes para identificar el tipo de IDE utilizado en el proyecto
	public static final int WITHOUT_ID = 0;
	public static final int ECLIPSE_PROJECT = 1;
	public static final int NETBEANS_PROJECT = 2;

	// Constantes para la manipulación de los archivos físicos
	public static final String BACKSLASH_PATTERN = "\\\\";
	public static final String FORWARDSLASH = "/";
	public static final String POINT = "\\.";
	public static final String JAVA_FILE_EXTENSION = ".java";

	// Constantes para manejar archivos de proyectos java en Eclipse
	public static final String NAME_XML_CLASSPATH_ECLIPSE = ".classpath";
	public static final String TAG_NAME_CLASSPATH_ECLIPSE = "classpathentry";
	public static final String NODE_NAME_ATTRIBUTE_SRC_ECLIPSE = "path";

	// Constantes para manejar archivos de proyectos java en Netbeans
	public static final String NAME_BUILD_XML_FILE_NETBEANS = "build";
	public static final String NAME_NBPROJECT_DIRECTORY_NETBEANS = "nbproject";
	public static final String PROPERTY_SRC_NETBEANS = "src.java.dir";
	public static final String PROPERTY_FILE_SRC_NETBEANS = "project.properties";

}
