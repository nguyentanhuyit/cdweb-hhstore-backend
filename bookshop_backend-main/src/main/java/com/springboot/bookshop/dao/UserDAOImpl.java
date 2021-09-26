package com.springboot.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookshop.entity.User;
import com.springboot.bookshop.util.BCryptEncodeUtil;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<User> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> query = currentSession.createQuery("from User");
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	public User findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		User user = currentSession.get(User.class, id);
		return user;
	}

	@Override
	public void saveOrUpdate(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(user);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("delete from User where id=:userId");
//		query.setParameter("userId", id);
//		query.executeUpdate();
		User user = findById(id);
		currentSession.delete(user);
	}

	
	// login
	@Override
	public User checkUserLogin(User userLogin) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query queryPassword = currentSession.createQuery("select password from User where username=:userLoginUsername");
		queryPassword.setParameter("userLoginUsername", userLogin.getUsername());
		String encodedPasswordDB = (String) queryPassword.uniqueResult();
		if (BCryptEncodeUtil.matches(userLogin.getPassword(), encodedPasswordDB)) {
			
			Query query = 
					currentSession.createQuery("select u from User u where u.username=:userLoginUsername");
			query.setParameter("userLoginUsername", userLogin.getUsername());
			User user = (User) query.uniqueResult();
			
			if (user != null) {
				User responseUser = new User(
						user.getId(),
						user.getUsername(),
						user.getPassword(),
						user.getEmail(),
						user.getPhoneNumber(),
						user.getAddress(),
						user.getVerified(),
						user.getRoles(),
						user.getCartItems(),
						user.getReviews(),
						user.getOrderTables()
						);
						// except password
				return responseUser;
			} else {
				return null;
			}	
			
		} else {
			return null;
		}
	}

	@Override
	public boolean checkUserName(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
//		Query query = currentSession.createQuery("select u from User u where u.username=:name");
//		query.setParameter("name", userName);
//		User user = (User) query.uniqueResult();
//		if (user != null) {
//			return true;
//		}
//		return false;
		Query query = currentSession.createQuery("select u from User u where u.username=:name");
		query.setParameter("name", userName);
		List<User> users = query.getResultList();
		if (users.size() == 1) {
			return true;
		} else if (users.size() == 0) {
			return false;
		} else {
			throw new RuntimeException("More than 1 user in database: " + userName);
		}
	}

	@Override
	public void updateAccountVerify(int userId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("update User set verified=:verifyStatus where id=:idUser");
		query.setParameter("verifyStatus", 1);
		query.setParameter("idUser", userId);
		query.executeUpdate();
		
	}

	@Override
	public List<String> getAllUsername() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<String> query = currentSession.createQuery("select username from User");
		List<String> usernames = query.getResultList();
		return usernames;
	}

	@Override
	public User findByUsername(String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("select u from User u where u.username=:username");
		query.setParameter("username", username);
		User user = (User) query.uniqueResult();
		return user;
	}
	
}
