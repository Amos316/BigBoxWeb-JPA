package databaseClass;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import businessClass.User;

public class UserDB {
	public static void insert(businessClass.User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(user));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static User selectUser(String userName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM User u " + "WHERE u.userName = :userName";
		TypedQuery<User> q = em.createQuery(qString, User.class);
		q.setParameter("userName", userName);

		try {
			User user = q.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}

	}

	public static boolean UserNameExists(String userName) {
		User u = selectUser(userName);

		return u != null;

	}

	public static List<User> selectUsers() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u from User u";
		TypedQuery<User> q = em.createQuery(qString, User.class);
		List<User> users;
		try {
			users = q.getResultList();
			if (users == null || users.isEmpty())
				users = null;
		} finally {
			em.close();
		}
		return users;
	}

}
