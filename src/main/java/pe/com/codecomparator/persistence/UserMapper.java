package pe.com.codecomparator.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pe.com.codecomparator.domain.User;

public interface UserMapper {

	@Select("SELECT * FROM users WHERE t_username = #{t_username} AND t_password = #{t_password}")
	public User getUser(@Param("t_username") String t_username,
			@Param("t_password") String t_password);

	@Insert("INSERT INTO users (t_username, t_password)  VALUES (#{t_username}, #{t_password})")
	public void createUser(@Param("t_username") String t_username,
			@Param("t_password") String t_password);

}