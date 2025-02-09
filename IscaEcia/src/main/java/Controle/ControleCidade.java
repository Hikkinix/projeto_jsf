
package Controle;

import Converter.ConverterGenerico;
import Entidade.Cidade;
import Entidade.Estado;
import Facade.FacadeCidade;
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
public class ControleCidade implements Serializable
{
    /// Atributos 
    
    private Cidade cid;
    @EJB
    private FacadeCidade facadecidade;
    @EJB
    private FacadeEstado facadeestado;
    private ConverterGenerico estadoconver;

    
    /// Get e set 
    public Cidade getCid() {
        return cid;
    }

    public void setCid(Cidade cid) {
        this.cid = cid;
    }

    public FacadeCidade getFacadecidade() {
        return facadecidade;
    }

    public void setFacadecidade(FacadeCidade facadecidade) {
        this.facadecidade = facadecidade;
    }

    public FacadeEstado getFacadeestado() {
        return facadeestado;
    }

    public void setFacadeestado(FacadeEstado facadeestado) {
        this.facadeestado = facadeestado;
    }

    public ConverterGenerico getEstadoconver() {
        if(estadoconver == null)
        {
            estadoconver = new ConverterGenerico(facadeestado); 
        }
        return estadoconver;
    }

    public void setEstadoconver(ConverterGenerico estadoconver) {
        this.estadoconver = estadoconver;
    }
    
    ///  Metodos 
    
    public void salvar()
    {
        facadecidade.save(cid);
    }
    
    public void excluir(Cidade e){
        facadecidade.remove(e);
    }
    
    public void editar(Cidade e){
        cid = e;
    }
    
    public void setEst(Cidade cid) {
        this.cid = cid;
    }
    
     public void novo(){
        cid = new Cidade();
    }
 
    /// Listas
    
      public List<Cidade> getLista(){
        return facadecidade.findAll();
    }
    
    public List<Estado> getListaestadoFiltrando(String filtro){
        return facadeestado.listaFiltrando(filtro, "nome");
    }
    
}
