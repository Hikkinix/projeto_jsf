package Entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, Double> estoque;
	public static volatile SingularAttribute<Produto, String> tipo;
	public static volatile SingularAttribute<Produto, Double> valor;
	public static volatile SingularAttribute<Produto, String> nome;
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, GrupoProduto> gprod;

}

