package pe.com.codecomparator.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pe.com.codecomparator.domain.User;

public interface UserMapper {

	@Select("SELECT * FROM t_users WHERE t_username = #{t_username} AND t_password = #{t_password}")
	public User getUser(@Param("t_username") String t_username,
			@Param("t_password") String t_password);
	

	@Select("SELECT * FROM t_users WHERE t_username = #{t_username}")
	public User userExists(@Param("t_username") String t_username);
	
	@Select("SELECT * FROM t_users WHERE t_email = #{t_email}")
	public User emailExists(@Param("t_email") String t_email);

	/*@Insert("INSERT INTO users (t_username, t_password)  VALUES (#{t_username}, #{t_password})")
	public void createUser(@Param("t_username") String t_username,
			@Param("t_password") String t_password);*/

	@Insert("INSERT INTO t_users (t_username, t_password, t_email)  VALUES (#{t_username}, #{t_password}, #{t_email})")
	public void createUser(@Param("t_username") String t_username,
			@Param("t_password") String t_password, @Param("t_email") String email);
}