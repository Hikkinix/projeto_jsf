/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Converter.ConverterGenerico;
import Converter.MoneyConverter;
import Entidade.GrupoProduto;
import Entidade.Produto;
import Facade.FacadeGrupoProduto;
import Facade.FacadeProduto;
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
public class ControleProduto implements Serializable{
 
    @EJB
    private FacadeProduto facadeproduto;
    private Produto prod;
    
    private MoneyConverter moneyConverter;
    
    @EJB
    private FacadeGrupoProduto facadegrupo;
    private GrupoProduto grupo;
    
    private ConverterGenerico grupoconver;

    public FacadeProduto getFacadeproduto() {
        return facadeproduto;
    }

    public void setFacadeproduto(FacadeProduto facadeproduto) {
        this.facadeproduto = facadeproduto;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

    public FacadeGrupoProduto getFacadegrupo() {
        return facadegrupo;
    }

    public void setFacadegrupo(FacadeGrupoProduto facadegrupo) {
        this.facadegrupo = facadegrupo;
    }

    public GrupoProduto getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoProduto grupo) {
        this.grupo = grupo;
    }

    public ConverterGenerico getGrupoconver() {
        if (grupoconver == null) {
            
            grupoconver = new ConverterGenerico(facadegrupo);
        }
        
        return grupoconver;
    }

    public void setGrupoconver(ConverterGenerico grupoconver) {
        this.grupoconver = grupoconver;
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }
    
    public void novo(){
        
        prod = new Produto();
    }
    
    public void editar(Produto p){
        
        prod = p;
    }
    
    public void excluir(Produto p){
   
        facadeproduto.remove(p);
    }
    
    public void salvar(){
    
        facadeproduto.save(prod);
    }
    
    public List<Produto> getLista(){
        return facadeproduto.findAll();
    }
    
    public List<GrupoProduto> getListaGrupoFiltrando(String filtro){
        return facadegrupo.listaFiltrando(filtro, "Descricao");
    }
    
    
     public List<Produto> getfiltroMaterial(String filtro, String atributos){
        return facadeproduto.listaFiltro("tipo", "MateriaPrima");
    }
    
     public List<Produto> getfiltroProdutovenda(String filtro, String atributos){
        return facadeproduto.listaFiltro("tipo", "Produto Venda");
    }
   
    
}