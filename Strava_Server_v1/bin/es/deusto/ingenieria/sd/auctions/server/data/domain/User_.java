package es.deusto.ingenieria.sd.auctions.server.data.domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(User.class)
public class User_
{
    public static volatile SingularAttribute<User, java.lang.String> email;
    public static volatile SingularAttribute<User, es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount> tipoAut;
    public static volatile SingularAttribute<User, java.lang.String> nickname;
    public static volatile SingularAttribute<User, java.time.LocalDate> birthDate;
    public static volatile SingularAttribute<User, Double> weight;
    public static volatile SingularAttribute<User, Double> height;
    public static volatile SingularAttribute<User, Integer> maxHeartRate;
    public static volatile SingularAttribute<User, Integer> restHeartRate;
    public static volatile ListAttribute<User, es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge> challenges;
    public static volatile ListAttribute<User, es.deusto.ingenieria.sd.auctions.server.data.domain.Session> sessions;
}
