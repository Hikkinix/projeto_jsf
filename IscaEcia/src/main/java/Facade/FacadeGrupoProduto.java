/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidade.GrupoProduto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FacadeGrupoProduto extends AbstractFacade<GrupoProduto> {

    @PersistenceContext(unitName = "SGP")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeGrupoProduto() {
        super(GrupoProduto.class);
    }

}
