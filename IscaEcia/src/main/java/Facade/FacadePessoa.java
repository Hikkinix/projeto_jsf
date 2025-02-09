/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;


import Entidade.Pessoa;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FacadePessoa extends AbstractFacade<Pessoa> {

    @PersistenceContext(unitName = "SGP")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadePessoa() {
        super(Pessoa.class);
    }

}
