package model.command.application.service.impl;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.Part;

import model.command.application.service.ServiceFile;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ServiceFileImpl implements ServiceFile {
	
		 
	  public void unzip(String source ,String destination,String password){
	      
	        try {
	             ZipFile zipFile = new ZipFile(source);
	             if (zipFile.isEncrypted()) {
	                zipFile.setPassword(password);
	             }
	             zipFile.extractAll(destination);
	        } catch (ZipException e) {
	            e.printStackTrace();
	        }
	    }
	   
	   public void listf(String directoryName, ArrayList<File> files, String endWith) {
		    File directory = new File(directoryName);

		    File[] fList = directory.listFiles();
		    for (File file : fList) {
		        if (file.isFile() && file.getName().endsWith(endWith)) {
		            files.add(file);
		        } else if (file.isDirectory()) {
		            listf(file.getAbsolutePath(), files,endWith);
		        }
		    }
	  }
	  public void deleteContentFolder(String path) {
			
			File directory = new File(path);
			
			File[] files = directory.listFiles();
			
			for (File file : files) {
			   
			   if(file.isDirectory()) {
				   deleteDirectory(file);
			   }
			   else if (!file.delete()) {
			       System.out.println("Failed to delete "+file);
			   }
			} 	
	   }	  
	   public boolean deleteDirectory(File path) {
		  if( path.exists() ) {
		      File[] files = path.listFiles();
		      for(int i=0; i<files.length; i++) {
		         if(files[i].isDirectory()) {
		           deleteDirectory(files[i]);
		         }
		         else {
		           files[i].delete();
		         }
		      }
		    }
		    return( path.delete() );
	  }
	  
	  public  String getFilename(Part part) {  
		    for (String cd : part.getHeader("content-disposition").split(";")) {  
		         if (cd.trim().startsWith("filename")) {  
		             String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");  
		             return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
		          }  
		     }  
		     return null;  
	  } 

}
