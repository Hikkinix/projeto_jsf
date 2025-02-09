package Facade;

import Entidade.ItensVenda;
import Entidade.Produto;
import Entidade.Venda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Jaime
 */
@Stateless
public class FacadeVenda extends AbstractFacade<Venda> {

    @PersistenceContext(unitName = "SGP")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeVenda() {
        super(Venda.class);
    }
    
       @Override
    public void save(Venda entity){
        for(ItensVenda it : entity.getItens()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque()- it.getQuantidade());
            em.merge(p);
        }
        super.save(entity); 
    }
    
    @Override
    public void remove(Venda entity) {
        for(ItensVenda it : entity.getItens()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque()+ it.getQuantidade());
            em.merge(p);
        }
        super.remove(entity);
    }
    
}
