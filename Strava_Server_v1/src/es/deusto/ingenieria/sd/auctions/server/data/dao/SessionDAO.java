package es.deusto.ingenieria.sd.auctions.server.data.dao;

import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;

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
		// TODO Auto-generated method stub
		

	}

	@Override
	public void delete(Session object) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Session> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session find(String param) {
		// TODO Auto-generated method stub
		return null;
	}

}
