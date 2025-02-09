package Entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaixaPagar.class)
public abstract class BaixaPagar_ {

	public static volatile SingularAttribute<BaixaPagar, ContasPagar> pagar;
	public static volatile SingularAttribute<BaixaPagar, Date> databaixa;
	public static volatile SingularAttribute<BaixaPagar, Double> valor;
	public static volatile SingularAttribute<BaixaPagar, Long> id;

}

