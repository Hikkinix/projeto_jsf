/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidade.Cidade;
import Entidade.Estado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FacadeCidade extends AbstractFacade<Cidade> {

    @PersistenceContext(unitName = "SGP")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeCidade() {
        super(Cidade.class);
    }

    public List<Cidade> listaFiltrandoEstado(Estado est) {
        String hql = "from Cidade obj ";
        if (est != null) {
            hql += "where obj.estado = :filtro";
        }
        Query q = getEntityManager().createQuery(hql);
        if (est != null) {
            q.setParameter("filtro", est);
        }
        return q.getResultList();
    }

    public Cidade listaFiltrandoporNome(String nome) {
  
        String hql = "from Cidade obj where obj.nome = '"+ nome +"'";
        Query q = getEntityManager().createQuery(hql);
  
        if(q.getResultList().isEmpty()){
            return null;
        }
        return (Cidade)q.getSingleResult();
    }
    
    public Cidade salvaRetornando(Cidade c){
        return em.merge(c);
    }

}
