package Entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasReceber.class)
public abstract class ContasReceber_ {

	public static volatile SingularAttribute<ContasReceber, String> situacao;
	public static volatile SingularAttribute<ContasReceber, Pessoa> pessoa;
	public static volatile SingularAttribute<ContasReceber, Double> ValorTotal;
	public static volatile SingularAttribute<ContasReceber, Double> valorPago;
	public static volatile SingularAttribute<ContasReceber, Date> dtLancamento;
	public static volatile SingularAttribute<ContasReceber, FormaPagamento> forma;
	public static volatile ListAttribute<ContasReceber, BaixaReceber> baixaReceber;
	public static volatile SingularAttribute<ContasReceber, String> Descricao;
	public static volatile SingularAttribute<ContasReceber, Double> valorParcela;
	public static volatile SingularAttribute<ContasReceber, Venda> venda;
	public static volatile SingularAttribute<ContasReceber, String> Tipo;
	public static volatile SingularAttribute<ContasReceber, Long> id;
	public static volatile SingularAttribute<ContasReceber, String> Parcelas;
	public static volatile SingularAttribute<ContasReceber, Date> Vencimento;

}

