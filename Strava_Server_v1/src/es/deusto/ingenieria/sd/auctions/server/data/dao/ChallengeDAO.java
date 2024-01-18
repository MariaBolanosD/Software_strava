package es.deusto.ingenieria.sd.auctions.server.data.dao;

import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;

public class ChallengeDAO extends DataAccessObjectBase implements IDataAccessObject<Challenge> {

	private static ChallengeDAO instance;
	
	private ChallengeDAO() {}
	
	public static ChallengeDAO getInstance()
	{
		if(instance == null) {
			instance = new ChallengeDAO();
		}
		return instance;
	}
	
	@Override
	public void store(Challenge object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Challenge object) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Challenge> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Challenge find(String param) {
		// TODO Auto-generated method stub
		return null;
	}

}