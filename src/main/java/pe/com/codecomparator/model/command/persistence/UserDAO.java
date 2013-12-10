package pe.com.codecomparator.model.command.persistence;

import java.sql.*;


public class UserDAO {

	
	Connection cn;
	
	public UserDAO() throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/page";
		cn = DriverManager.getConnection(url,"root","123qwe/");
        System.out.println("Conexion Establecida");
        
	}
	
	public UserDTO consultarAcceso(String username, String password) throws SQLException{
		UserDTO use = null;
		
		String sql="SELECT n_id_user, t_username, t_password "
                + "FROM page.users "
                + "WHERE t_username = ? AND t_password = ?;";
		
		PreparedStatement pst=cn.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, password);
        
        System.out.println(sql);
        ResultSet rs=pst.executeQuery();
        
        while(rs.next()){
            use=new UserDTO();
            use.setId(rs.getInt(1));
            use.setUsername(rs.getString(2));
            use.setPassword(rs.getString(3));
           
        }
        
		return use;
	}
	
public void crearUsuario(String username, String password) throws SQLException{
		
//		UserDTO user=new UserDTO();
		
		String sql = "insert into users (t_username, t_password)  values (?, ?);";
		
		PreparedStatement pst=(PreparedStatement) cn.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, password);
        
        cn.prepareStatement(sql);
        
       pst.executeUpdate();
       
	}
	
	
}
