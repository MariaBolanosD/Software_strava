package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;

public class User {
	private TypeOfAccount tipoAut;
	private String nickname;
	private String password;
	private String email;
	private LocalDate birthDate;
	private double weight;
	private double height;
	private int maxHeartRate;
	private int restHeartRate;
	
	private List<Challenge> challenges = new ArrayList<>();
	private List<Challenge> updatedChallenges = new ArrayList<>();
	private List<Session> sessions = new ArrayList<>();
		
	public TypeOfAccount getTipoAut() {
		return tipoAut;
	}

	public void setTypeOfAccount(TypeOfAccount tipoAut) {
		this.tipoAut = tipoAut;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Challenge> getChallenges() {
		return challenges;
	}
	
	public void setChallenges(List<Challenge> challenges) {
		this.challenges = challenges;
	}
	
	public void addChallenge(Challenge challenge ) {
		if (challenge != null && !this.challenges.contains(challenge)) {
			
			Challenge challengeCop = new Challenge();
			challengeCop.setName(challenge.getName());
			challengeCop.setStartDate(challenge.getStartDate());
			challengeCop.setEndDate(challenge.getEndDate());
			challengeCop.setTarget(challenge.getTarget());
			challengeCop.setSport(challenge.getSport());
			challengeCop.setDistanceorTime(challenge.getDistanceorTime());
			this.updatedChallenges.add(challengeCop);
			this.challenges.add(challenge);
			
			
		}
	}
	
	public List<Session> getSessions() {
		return sessions;
	}
	
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	public void addSession(Session session) {
		if (session != null && !this.sessions.contains(session)) {
			this.sessions.add(session);
		}
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public int getMaxHeartRate() {
		return maxHeartRate;
	}
	
	public void setMaxHeartRate(int maxHeartRate) {
		this.maxHeartRate = maxHeartRate;
	}
	
	public int getRestHeartRate() {
		return restHeartRate;
	}
	
	public void setRestHeartRate(int restHeartRate) {
		this.restHeartRate = restHeartRate;
	}

	public boolean UpdateChallenges(Session session) {
		for (Challenge eachChal : updatedChallenges) {
			if((session.getStartDate().compareTo(eachChal.getStartDate())>= 0) && (session.getStartDate().compareTo(eachChal.getEndDate()) <= 0) && session.getSport() == eachChal.getSport())
			{
				if(eachChal.getDistanceorTime())// DISTANCE
				{
					
					eachChal.setTarget((float)(eachChal.getTarget()-session.getDistance()));
					
					
				}else {							// TIME
					eachChal.setTarget((float)(eachChal.getTarget()-session.getDuration()));
				}
				
				if(eachChal.getTarget() <= 0.0f)
				{
					for (Challenge eachChallenge : challenges) {
						if(eachChal == eachChallenge)
						{
							challenges.remove(eachChallenge);
							break;
						}
						
					}
					updatedChallenges.remove(eachChal);
				}
			}
			
			
		}
		return true;
	}
	
	
	
			
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		result.append(this.nickname);
		result.append(" - ");
		result.append(this.email);
		result.append(" - (");
		result.append(this.challenges.size());
		result.append(" challenges) - (");
		result.append(this.sessions.size());
		result.append(" sessions)");
		
		return result.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.email.equals(((User)obj).email);
		}
		
		return false;
	}

	
}