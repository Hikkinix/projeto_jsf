package Entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Venda.class)
public abstract class Venda_ {

	public static volatile SingularAttribute<Venda, Pessoa> cliente;
	public static volatile SingularAttribute<Venda, Double> total;
	public static volatile ListAttribute<Venda, ItensVenda> itens;
	public static volatile SingularAttribute<Venda, Date> datavenda;
	public static volatile ListAttribute<Venda, ContasReceber> contasReceber;
	public static volatile SingularAttribute<Venda, Long> id;

}

