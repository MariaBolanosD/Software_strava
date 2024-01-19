package es.deusto.ingenieria.sd.auctions.server.data.domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(Session.class)
public class Session_
{
    public static volatile SingularAttribute<Session, java.lang.String> title;
    public static volatile SingularAttribute<Session, es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum> sport;
    public static volatile SingularAttribute<Session, Double> distance;
    public static volatile SingularAttribute<Session, java.time.LocalDate> start_date;
    public static volatile SingularAttribute<Session, java.time.LocalTime> start_time;
    public static volatile SingularAttribute<Session, Double> duration;
    public static volatile SingularAttribute<Session, es.deusto.ingenieria.sd.auctions.server.data.domain.User> user;
}
