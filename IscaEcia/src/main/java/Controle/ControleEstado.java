
package Controle;

import Entidade.Estado;
import Facade.FacadeEstado;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * @author Olimp
 */

@ManagedBean
@SessionScoped
public class ControleEstado implements Serializable

{
    /// Atributos
    private Estado est;
    @EJB
    private FacadeEstado facadeestado;
    private Long id;
    
    
    public Long getId() {    
        return id;
    }

    // get e set
    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEst() {
        return est;
    }
    
    public void setEst(Estado est) {
        this.est = est;
    }

    public FacadeEstado getFacadeestado() {
        return facadeestado;
    }

    public void setFacadeestado(FacadeEstado facadeestado) {
        this.facadeestado = facadeestado;
    }
    
    // METODOS
   public void novo(){
        est = new Estado();
    }
    
    public void salvar()
    {
        facadeestado.save(est);
    }
    
    public void excluir(Estado e){
        facadeestado.remove(e);
    }
    
   public void editarPeloid(Estado e){
        est = facadeestado.find(id);
    }
    
    public void editar(Estado e){
        est = e;
    }
    
        public List<Estado> getLista(){
        return facadeestado.findAll();

    }

}
