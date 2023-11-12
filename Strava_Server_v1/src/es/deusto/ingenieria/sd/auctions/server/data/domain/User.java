package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {	
	private String nickname;
	private String password;
	private String email;
	private LocalDate birthDate;
	private double weight;
	private double height;
	private int maxHeartRate;
	private int restHeartRate;
	
	private List<Challenge> challenges = new ArrayList<>();
	private List<Session> sessions = new ArrayList<>();
		
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