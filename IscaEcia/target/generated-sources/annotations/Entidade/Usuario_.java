package Entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile SingularAttribute<Usuario, Pessoa> funcionario;
	public static volatile SingularAttribute<Usuario, String> comfirmarsenha;
	public static volatile SingularAttribute<Usuario, String> nomeusuario;
	public static volatile SingularAttribute<Usuario, Perfil> perfil;

}

