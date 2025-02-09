/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Converter.ConverterGenerico;
import Converter.MoneyConverter;
import Entidade.BaixaPagar;
import Entidade.ContasPagar;
import Entidade.FormaPagamento;
import Entidade.Pessoa;
import Facade.FacadeContasPagar;
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
public class ControleContasPagar implements Serializable{
    
   // CONTAS
    @EJB
    private FacadePessoa fornecedorFacade;
    private ConverterGenerico fornecedorconver;
    
    @EJB
    private FacadeContasPagar pagarFacade;
    private ContasPagar cp;
    private MoneyConverter moneyconverter;
    private BaixaPagar baixa;
    
    @EJB
    private FacadeFormapag formaFacade;
    private ConverterGenerico formaconver;
   // CONTAS RECEBER

     public void baixar(){
         
        if(cp.getValorTotal() >=( cp.getValorPago() + baixa.getValor())){
        baixa.setPagar(cp);
        cp.getBaixaPagar().add(baixa);  
        baixa = new BaixaPagar();
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O valor baixado é maior que o valor do contas a pagar!",
                            null));
        }
    }
    
    public void novaBaixa(ContasPagar cp){
        this.cp = cp;
        baixa = new BaixaPagar();
    }
    
    public void removerBaixa(BaixaPagar b){
        cp.getBaixaPagar().remove(b);
    }
    
    public String getCorValor(){        
        if(Objects.equals(cp.getValorPago(), cp.getValorTotal())){
            return "green";
        }else{
            return "red";
        }
    }
    
    
    ///
    
    
    public FacadePessoa getFornecedorFacade() {
        return fornecedorFacade;
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
    
    public void setFornecedorFacade(FacadePessoa fornecedorFacade) {
        this.fornecedorFacade = fornecedorFacade;
    }

    public ConverterGenerico getFornecedorconver() {
        if (fornecedorconver == null) {
            
            fornecedorconver = new ConverterGenerico(fornecedorFacade);
        }
        return fornecedorconver;
    }

    public void setFornecedorconver(ConverterGenerico fornecedorconver) {
        this.fornecedorconver = fornecedorconver;
    }

    public FacadeContasPagar getPagarFacade() {
        return pagarFacade;
    }

    public void setPagarFacade(FacadeContasPagar pagarFacade) {
        this.pagarFacade = pagarFacade;
    }

    public ContasPagar getCp() {
        return cp;
    }

    public void setCp(ContasPagar cp) {
        this.cp = cp;
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

    public BaixaPagar getBaixa() {
        return baixa;
    }

    public void setBaixa(BaixaPagar baixa) {
        this.baixa = baixa;
    }

    public FacadeFormapag getFormaFacade() {
        return formaFacade;
    }

    public void setFormaFacade(FacadeFormapag formaFacade) {
        this.formaFacade = formaFacade;
    }

    public void novo(){
        
        cp = new ContasPagar();
        
    }
    
    public void editar(ContasPagar e){
    
        cp = e;
        
    }
    
    public void excluir (ContasPagar e){
    
        pagarFacade.remove(e);
    
    }
    
    public void salvar(){
        pagarFacade.save(cp);
    }
    
    public List<ContasPagar> getLista() {
        return pagarFacade.findAll();
    }

    public List<Pessoa> getListaFornecedorsFiltrando(String filtro) {
        return fornecedorFacade.listaFiltrando(filtro, "nome");
    }
    
    public List<FormaPagamento> getListaFormasFiltrando(String filtro) {
        return formaFacade.listaFiltrando(filtro, "nome");
    }
    
}
