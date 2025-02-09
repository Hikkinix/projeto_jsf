/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidade.ContasPagar;
import Entidade.Estado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FacadeContasPagar extends AbstractFacade<ContasPagar> {

    @PersistenceContext(unitName = "SGP")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeContasPagar() {
        super(ContasPagar.class);
    }

}
