
package Controle;


import Entidade.FormaPagamento;
import Facade.FacadeFormapag;
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
public class ControleFormpag implements Serializable

{
    /// Atributos
    private FormaPagamento Formpag;
    @EJB
    private FacadeFormapag facadepag;

    public FormaPagamento getFormpag() {
        return Formpag;
    }

    // get e set
    public void setFormpag(FormaPagamento Formpag) {
        this.Formpag = Formpag;
    }

    public FacadeFormapag getFacadepag() {
        return facadepag;
    }

    public void setFacadepag(FacadeFormapag facadepag) {
        this.facadepag = facadepag;
    }
    
    // METODOS
    public void novo(){
        Formpag = new FormaPagamento();
    }
    
    public void salvar()
    {
        facadepag.save(Formpag);
    }
    
    public void excluir(FormaPagamento e){
        facadepag.remove(e);
    }
    
    public void editar(FormaPagamento e){
        Formpag = e;
    }
    
        public List<FormaPagamento> getLista(){
        return facadepag.findAll();

    }
}
