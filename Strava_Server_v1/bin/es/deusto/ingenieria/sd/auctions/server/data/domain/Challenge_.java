package es.deusto.ingenieria.sd.auctions.server.data.domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(Challenge.class)
public class Challenge_
{
    public static volatile SingularAttribute<Challenge, java.lang.String> name;
    public static volatile SingularAttribute<Challenge, java.time.LocalDate> start_date;
    public static volatile SingularAttribute<Challenge, java.time.LocalDate> end_date;
    public static volatile SingularAttribute<Challenge, Float> target;
    public static volatile SingularAttribute<Challenge, es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum> sport;
    public static volatile SingularAttribute<Challenge, Boolean> distance_or_time;
}
