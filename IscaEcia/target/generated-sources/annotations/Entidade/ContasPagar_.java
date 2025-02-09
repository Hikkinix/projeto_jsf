package Entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasPagar.class)
public abstract class ContasPagar_ {

	public static volatile SingularAttribute<ContasPagar, Compra> compra;
	public static volatile SingularAttribute<ContasPagar, String> tipo;
	public static volatile SingularAttribute<ContasPagar, String> situacao;
	public static volatile SingularAttribute<ContasPagar, Pessoa> pessoa;
	public static volatile SingularAttribute<ContasPagar, Double> ValorTotal;
	public static volatile SingularAttribute<ContasPagar, Double> valorPago;
	public static volatile SingularAttribute<ContasPagar, Date> dtLancamento;
	public static volatile SingularAttribute<ContasPagar, FormaPagamento> forma;
	public static volatile SingularAttribute<ContasPagar, String> Descricao;
	public static volatile SingularAttribute<ContasPagar, Double> valorParcela;
	public static volatile ListAttribute<ContasPagar, BaixaPagar> baixaPagar;
	public static volatile SingularAttribute<ContasPagar, Long> id;
	public static volatile SingularAttribute<ContasPagar, String> Parcelas;
	public static volatile SingularAttribute<ContasPagar, Date> Vencimento;

}

