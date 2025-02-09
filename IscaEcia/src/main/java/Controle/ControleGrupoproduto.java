/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Entidade.GrupoProduto;
import Facade.FacadeGrupoProduto;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Olimp
 */
@ManagedBean
@SessionScoped
public class ControleGrupoproduto implements Serializable {
    
    @EJB
    private FacadeGrupoProduto facadegrupo;
    private GrupoProduto gprod;

    public FacadeGrupoProduto getFacadegrupo() {
        return facadegrupo;
    }

    public void setFacadegrupo(FacadeGrupoProduto facadegrupo) {
        this.facadegrupo = facadegrupo;
    }

    public GrupoProduto getGprod() {
        return gprod;
    }

    public void setGprod(GrupoProduto gprod) {
        this.gprod = gprod;
    }
    
    public void novo(){
        
        gprod = new GrupoProduto();
    }
    
    public void edita(GrupoProduto e){
        
        gprod = e;
    }
    
    public void excluir(GrupoProduto e){
        
        facadegrupo.remove(e);
    }
    
    public void salvar(){
        
        facadegrupo.save(gprod);
    }
    
     public List<GrupoProduto> getLista(){
        return facadegrupo.findAll();
    }

}

