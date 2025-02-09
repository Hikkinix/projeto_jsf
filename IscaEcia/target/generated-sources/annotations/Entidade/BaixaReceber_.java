package Entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaixaReceber.class)
public abstract class BaixaReceber_ {

	public static volatile SingularAttribute<BaixaReceber, Date> databaixa;
	public static volatile SingularAttribute<BaixaReceber, Double> valor;
	public static volatile SingularAttribute<BaixaReceber, ContasReceber> receber;
	public static volatile SingularAttribute<BaixaReceber, Long> id;

}

