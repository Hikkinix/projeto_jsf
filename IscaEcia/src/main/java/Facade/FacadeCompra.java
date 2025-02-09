package Facade;

import Entidade.Compra;
import Entidade.ItensCompra;
import Entidade.Produto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class FacadeCompra extends AbstractFacade<Compra> {

    @PersistenceContext(unitName = "SGP")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeCompra() {
        super(Compra.class);
    }
    
    @Override
    public void save(Compra entity){
        for(ItensCompra it : entity.getItens()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque()+ it.getQuantidade());
            em.merge(p);
        }
        super.save(entity); 
    }
    
    @Override
    public void remove(Compra entity) {
        for(ItensCompra it : entity.getItens()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque()- it.getQuantidade());
            em.merge(p);
        }
        super.remove(entity);
    }
}
