package es.deusto.ingenieria.sd.auctions.server.data.domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(User.class)
public class User_
{
    public static volatile SingularAttribute<User, java.lang.String> email;
    public static volatile SingularAttribute<User, java.lang.String> nickname;
    public static volatile SingularAttribute<User, java.lang.String> password;
    public static volatile SetAttribute<User, es.deusto.ingenieria.sd.auctions.server.data.domain.Bid> bids;
    public static volatile SetAttribute<User, es.deusto.ingenieria.sd.auctions.server.data.domain.Article> articles;
}
