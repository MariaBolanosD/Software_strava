package es.deusto.ingenieria.sd.auctions.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;



public class SessionDAO extends DataAccessObjectBase implements IDataAccessObject<Session> {

	private static SessionDAO instance;

	private SessionDAO() {
	}

	public static SessionDAO getInstance() {
		if (instance == null) {
			instance = new SessionDAO();
		}
		return instance;
	}

	@Override
	public void store(Session object) {
		
		Session storedObject = null;
		if(object.getId() != null)
		{
			storedObject = instance.find(String.valueOf(String.valueOf(object.getId())));
		}
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			if (storedObject != null) {
				em.merge(object);
			} else {
				em.persist(object);			
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error storing Session: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}
	}

	@Override
	public void delete(Session object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			// Retrieve the managed session
			System.out.println("Object Id " + object.getId());
			Session managedSession = em.find(Session.class, String.valueOf(object.getId()));
			// Remove associated user_session records
			if (managedSession != null) {
                em.remove(managedSession);
            }

			tx.commit();
		} catch (Exception ex) {
			
			System.out.println("  $ Error removing an Session: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Session> findAll() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		List<Session> session = new ArrayList<>();

		try {
			tx.begin();

			Query q = em.createQuery("SELECT b FROM Session b");
			session = (List<Session>) q.getResultList();

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Session: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

		return session;
	}

	public List<Session> getSessionsForUser(User user) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Session> sessions = null;
		
		try {
			tx.begin();
			
			TypedQuery<Session> query = null;
			// Get sessions for the given user
			query = em.createQuery("SELECT s FROM Session s WHERE s.user = :user",
					Session.class);
			query.setParameter("user", user);
			sessions = query.getResultList();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Session for User: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}
		return sessions;
	}

	@Override
	public Session find(String param) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		Session result = null;

		try {
			tx.begin();
			
			 if (param != null) {
		            Long sessionId = Long.parseLong(param);
		            result = (Session) em.find(Session.class, sessionId);
		        } else {
		            // Handle the case where param is null (e.g., log a message or throw an exception)
		            System.out.println("  $ Error: Session ID is null");
		        }

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying an Session by Id: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

		return result;
	}

}
