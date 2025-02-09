package Entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Compra.class)
public abstract class Compra_ {

	public static volatile SingularAttribute<Compra, Double> total;
	public static volatile ListAttribute<Compra, ItensCompra> itens;
	public static volatile ListAttribute<Compra, ContasPagar> contasPagar;
	public static volatile SingularAttribute<Compra, Date> datacompra;
	public static volatile SingularAttribute<Compra, Long> id;
	public static volatile SingularAttribute<Compra, Pessoa> fornecedor;

}

