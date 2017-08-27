package app.card.model.user.dao;

import app.card.model.user.UserVO;

public interface UserDao {

	public UserVO getUserData(String username);

	public void saveUser(UserVO userVO);

	public void updateUser(UserVO userVO);
}
