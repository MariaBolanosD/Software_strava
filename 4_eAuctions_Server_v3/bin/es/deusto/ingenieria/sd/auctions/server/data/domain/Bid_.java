package es.deusto.ingenieria.sd.auctions.server.data.domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(Bid.class)
public class Bid_
{
    public static volatile SingularAttribute<Bid, java.util.Date> date;
    public static volatile SingularAttribute<Bid, Float> amount;
    public static volatile SingularAttribute<Bid, es.deusto.ingenieria.sd.auctions.server.data.domain.Article> article;
    public static volatile SingularAttribute<Bid, es.deusto.ingenieria.sd.auctions.server.data.domain.User> user;
}
