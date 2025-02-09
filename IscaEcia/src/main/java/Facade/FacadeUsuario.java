/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidade.Cidade;
import Entidade.Estado;
import Entidade.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FacadeUsuario extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SGP")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeUsuario() {
        super(Usuario.class);
    }
    
    public Usuario validaUsuario(Usuario u) {/// verifica se existe usuario cadastrado no banco de dados
        String hql = "from Usuario obj where obj.nomeusuario = :filtro1 and obj.senha = :filtro2";                     
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("filtro1", u.getNomeusuario());
        q.setParameter("filtro2", u.getSenha());
        if(q.getResultList().isEmpty()){
            return u;
        }else{
            return (Usuario) q.getSingleResult();
        }
        
    }
}
