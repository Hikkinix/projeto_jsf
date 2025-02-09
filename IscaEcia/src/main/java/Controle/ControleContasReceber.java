/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Converter.ConverterGenerico;
import Converter.MoneyConverter;
import Entidade.BaixaReceber;
import Entidade.ContasReceber;
import Entidade.FormaPagamento;
import Entidade.Pessoa;
import Facade.FacadeContasReceber;
import Facade.FacadeFormapag;
import Facade.FacadePessoa;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @author Olimp
 */

@ManagedBean
@SessionScoped
public class ControleContasReceber implements Serializable{
    
   // CONTAS
    @EJB
    private FacadePessoa clienteFacade;
    private ConverterGenerico clienteconver;
    
    @EJB
    private FacadeContasReceber receberFacade;
    private ContasReceber cr;
    private MoneyConverter moneyconverter;
    private BaixaReceber baixa;
    
    @EJB
    private FacadeFormapag formaFacade;
    private ConverterGenerico formaconver;
   // CONTAS RECEBER

     public void baixar(){
         
        if(cr.getValorTotal() >=( cr.getValorPago() + baixa.getValor())){
        baixa.setReceber(cr);
        cr.getBaixaReceber().add(baixa);  
        baixa = new BaixaReceber();
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O valor baixado é maior que o valor do contas a receber!",
                            null));
        }
    }
    
    public void novaBaixa(ContasReceber cr){
        this.cr = cr;
        baixa = new BaixaReceber();
    }
    
    public void removerBaixa(BaixaReceber b){
        cr.getBaixaReceber().remove(b);
    }
    
    public String getCorValor(){        
        if(Objects.equals(cr.getValorPago(), cr.getValorTotal())){
            return "green";
        }else{
            return "red";
        }
    }
    
    
    ///
    
    
    public FacadePessoa getClienteFacade() {
        return clienteFacade;
    }

        public ConverterGenerico getFormaconver() {
        if (formaconver == null) {
            
            formaconver = new ConverterGenerico(formaFacade);
        
        }
        return formaconver;
    }

    public void setFormaconver(ConverterGenerico formaconver) {
        this.formaconver = formaconver;
    }
    
    public void setClienteFacade(FacadePessoa clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public ConverterGenerico getClienteconver() {
        if (clienteconver == null) {
            
            clienteconver = new ConverterGenerico(clienteFacade);
        }
        return clienteconver;
    }

    public void setClienteconver(ConverterGenerico clienteconver) {
        this.clienteconver = clienteconver;
    }

    public FacadeContasReceber getReceberFacade() {
        return receberFacade;
    }

    public void setReceberFacade(FacadeContasReceber receberFacade) {
        this.receberFacade = receberFacade;
    }

    public ContasReceber getCr() {
        return cr;
    }

    public void setCr(ContasReceber cr) {
        this.cr = cr;
    }

    public MoneyConverter getMoneyconverter() {
        if (moneyconverter == null) {
            moneyconverter = new MoneyConverter();
        }
        return moneyconverter;
    }

    public void setMoneyconverter(MoneyConverter moneyconverter) {
        this.moneyconverter = moneyconverter;
    }

    public BaixaReceber getBaixa() {
        return baixa;
    }

    public void setBaixa(BaixaReceber baixa) {
        this.baixa = baixa;
    }

    public FacadeFormapag getFormaFacade() {
        return formaFacade;
    }

    public void setFormaFacade(FacadeFormapag formaFacade) {
        this.formaFacade = formaFacade;
    }

  


    public void novo(){
        
        cr = new ContasReceber();
        
    }
    
    public void editar(ContasReceber e){
    
        cr = e;
        
    }
    
    public void excluir (ContasReceber e){
    
        receberFacade.remove(e);
    
    }
    
    public void salvar(){
    
        receberFacade.save(cr);
    
    }
    
    public List<ContasReceber> getLista() {
        return receberFacade.findAll();
    }

    public List<Pessoa> getListaClientesFiltrando(String filtro) {
        return clienteFacade.listaFiltrando(filtro, "nome");
    }
    
    public List<FormaPagamento> getListaFormasFiltrando(String filtro) {
        return formaFacade.listaFiltrando(filtro, "nome");
    }
    
}
