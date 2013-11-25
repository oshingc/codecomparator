package controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


@ManagedBean(name = "login")
@ViewScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -25595180939938054L;
	private String username;
	private String password; 
	
	public LoginController(){   
	}
	
	 /*public StreamedContent getFondo() {
		 File file =new File("/img/gallery.jpg");
		 fondo = file.
	     return fondo;  
	 } */
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void init(ActionEvent actionEvent) throws IOException{
		System.out.println("init");
		if(this.username.equals("")&&this.password.equals("")){
			addWarn(actionEvent,"Ingrese todos los campos");
		}
		else{
			if(this.password.equals("")){
				addWarn(actionEvent,"Ingrese una contraseña");
			}else{
				if(this.username.equals("")){
					addWarn(actionEvent,"Ingrese nombre de usuario");
					
				}else{

					//busqueda en la db
					FacesContext.getCurrentInstance().getExternalContext().redirect("upload.xhtml");
					
				}
			}
			
		}
	}
	public void addWarn(ActionEvent actionEvent,String message) {  
        FacesContext.getCurrentInstance().addMessage(null, 
        		new FacesMessage(FacesMessage.SEVERITY_WARN,"Error:", message));  
    }
	
	/*public void validation(){
		System.out.println("aDd");
		if(userName.equals("test")&&password.equals("test")){
			
		}else{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Usuario incorrecto");
			facesContext.addMessage("primeForm:inputid", facesMessage);
		}
	}*/

	/*public void login() {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean loggedIn = false;  
          
        if(userName != null  && userName.equals("admin") && password != null  && password.equals("admin")) {  
            loggedIn = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", userName);  
        } else {  
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");  
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn);  
    }*/  
	
}
