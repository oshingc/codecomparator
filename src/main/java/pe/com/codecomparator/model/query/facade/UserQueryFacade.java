package pe.com.codecomparator.model.query.facade;

import pe.com.codecomparator.domain.User;

public interface UserQueryFacade {
	
	public User validateUser(User user);
	public User userExists(User user);
	public User emailExists(User user);

}
