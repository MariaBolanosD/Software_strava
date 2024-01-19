package es.deusto.ingenieria.sd.auctions.server.data.domain;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(Article.class)
public class Article_
{
    public static volatile SingularAttribute<Article, Integer> number;
    public static volatile SingularAttribute<Article, java.lang.String> title;
    public static volatile SingularAttribute<Article, Float> initialPrice;
    public static volatile SingularAttribute<Article, java.util.Date> auctionEnd;
    public static volatile SingularAttribute<Article, es.deusto.ingenieria.sd.auctions.server.data.domain.Category> category;
    public static volatile SingularAttribute<Article, es.deusto.ingenieria.sd.auctions.server.data.domain.User> owner;
    public static volatile SetAttribute<Article, es.deusto.ingenieria.sd.auctions.server.data.domain.Bid> bids;
}
