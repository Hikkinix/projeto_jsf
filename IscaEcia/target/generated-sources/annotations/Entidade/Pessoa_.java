package Entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, String> tipo;
	public static volatile SingularAttribute<Pessoa, Cidade> cidade;
	public static volatile SingularAttribute<Pessoa, String> Categoria;
	public static volatile SingularAttribute<Pessoa, String> Registro;
	public static volatile SingularAttribute<Pessoa, String> Contato;
	public static volatile SingularAttribute<Pessoa, Date> dtnasc;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, Long> id;
	public static volatile SingularAttribute<Pessoa, String> Bairro;
	public static volatile SingularAttribute<Pessoa, String> Rua;
	public static volatile SingularAttribute<Pessoa, String> CEP;
	public static volatile SingularAttribute<Pessoa, String> descricao;

}

