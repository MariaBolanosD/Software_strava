package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;

@Entity
public class User {
	@Id
	private String email;
	private TypeOfAccount tipoAut;
	private String nickname;
	private LocalDate birthDate;
	private double weight;
	private double height;
	private int maxHeartRate;
	private int restHeartRate;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "user_challenge", joinColumns = @JoinColumn(name = "user_email"), inverseJoinColumns = @JoinColumn(name = "challenge_name"))
	private List<Challenge> challenges = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
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

	public void addChallenge(Challenge challenge) {
		System.out.println("added challenge outside user");
		if (challenge != null && !this.challenges.contains(challenge)) {

			System.out.println("added challenge inside user");
			this.challenges.add(challenge);
			UpdateChallenges();
			
		}
	}

	public List<Session> getSessions() {
		if (sessions == null) {
			sessions = new ArrayList<>();
		}
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public void addSession(Session session) {
		if (session != null && !this.sessions.contains(session)) {
			this.sessions.add(session);
			session.setUser(this);
			UpdateChallenges();
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

	public boolean UpdateChallenges() {
		float targetmFloat;
		for (Challenge eachChal : challenges) {
			targetmFloat = eachChal.getTarget();
			for (Session session : sessions) {

				if ((session.getStartDate().compareTo(eachChal.getStartDate()) >= 0)
						&& (session.getStartDate().compareTo(eachChal.getEndDate()) <= 0)
						&& session.getSport() == eachChal.getSport()) {
					if (eachChal.getDistanceorTime())// DISTANCE
					{
						targetmFloat -=(float)session.getDistance();

					} else { // TIME
						targetmFloat -=(float)session.getDuration();
					}

					if (targetmFloat <= 0.0f) {
						challenges.remove(eachChal);
						System.out.println("challenge deleted");
						break;
					}
				}
			}

		}
		return true;
	}

	public void removeSession(Session session) {
		sessions.remove(session);
		session.setUser(null);
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
			return this.email.equals(((User) obj).email);
		}

		return false;
	}

}