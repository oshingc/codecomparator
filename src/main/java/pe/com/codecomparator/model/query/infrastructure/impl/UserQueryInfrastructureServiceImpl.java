package pe.com.codecomparator.model.query.infrastructure.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.User;
import pe.com.codecomparator.model.query.infrastructure.UserQueryInfrastructureService;
import pe.com.codecomparator.persistence.UserMapper;

public class UserQueryInfrastructureServiceImpl implements
		UserQueryInfrastructureService, Serializable {

	private static final long serialVersionUID = 1694756513943705889L;

	@Autowired
	private UserMapper userMapper;

	public User validateUser(User user) {
		User _user = userMapper.getUser(user.getT_username(),
				user.getT_password());
		return _user;
	}

}
