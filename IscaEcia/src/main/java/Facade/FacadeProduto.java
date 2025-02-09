/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidade.Produto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FacadeProduto extends AbstractFacade<Produto> {

    @PersistenceContext(unitName = "SGP")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeProduto() {
        super(Produto.class);
    }

}
