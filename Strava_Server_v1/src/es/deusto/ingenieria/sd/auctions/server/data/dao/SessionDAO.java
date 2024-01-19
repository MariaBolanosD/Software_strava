package es.deusto.ingenieria.sd.auctions.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;

public class SessionDAO extends DataAccessObjectBase implements IDataAccessObject<Session> {

	private static SessionDAO instance;

	private SessionDAO() {}

	public static SessionDAO getInstance() {
		if (instance == null) {
			instance = new SessionDAO();
		}
		return instance;
	}

	@Override
	public void store(Session object) {
		Session storedObject = instance.find(String.valueOf(object.getTitle()));

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
		}	}

	@Override
	public void delete(Session object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
            tx.begin();

            // Retrieve the managed session
            Session managedSession = em.find(Session.class, object.getTitle());

         // Remove associated user_session records
        


            em.remove(managedSession);

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

	@Override
	public Session find(String param) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Session result = null; 

		try {
			tx.begin();
						
			result = (Session) em.find(Session.class, param);
			
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
