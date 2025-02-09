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
import Entidade.ItensVenda;
import Entidade.Pessoa;
import Entidade.Produto;
import Entidade.Venda;
import Facade.FacadePessoa;
import Facade.FacadeProduto;
import Facade.FacadeVenda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class ControleVenda implements Serializable{
    
    private Integer parc;
    private ContasReceber cr;
    private FormaPagamento forma;
    private BaixaReceber baixa;
    
    /// Venda
    @EJB
    private FacadeVenda vendaFacade;
    private ItensVenda itens;
    private Venda venda;
    private MoneyConverter moneyconverter;
    
    ///Produto
    @EJB
    private FacadeProduto produtoFacade;
    private Produto produto;
    private ConverterGenerico produtoConver;
    
    ///Cliente
    @EJB
    private FacadePessoa clienteFacade;
    private Pessoa cliente;
    private ConverterGenerico clienteConver;
    
    
    
    /// listas
    public List<Venda> getLista(){
        return vendaFacade.findAll();
    }
    
     public List<Pessoa> getListClienteFiltrando(String filtro){
        return clienteFacade.listaFiltrando(filtro, "nome");
    }
    
    public List<Produto> getListProdutosFiltrando(String filtro){
        return produtoFacade.listaFiltrando(filtro, "nome");
    }
    ///
     public void gerarParcelas() {
        venda.setContasReceber(new ArrayList<ContasReceber>());
        Double valor = venda.getTotal() / parc;
        Date dataVen = venda.getDatavenda();
        for (Integer i = 1; i <= parc ; i++) {
            cr = new ContasReceber();
            cr.setDtLancamento(venda.getDatavenda());
            cr.setDescricao(venda.getCliente().getNome());
            cr.setParcelas(i.toString() + "/" + parc.toString());
            cr.setForma(forma);
            cr.setValorParcela(valor);
            cr.setValorTotal(venda.getTotal());
            cr.setVencimento(dataVen);
            cr.setVenda(venda);
            if(venda.getCliente() != null){
                cr.setPessoa(venda.getCliente());
            }            
            venda.getContasReceber().add(cr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataVen);
            cal.add(Calendar.MONTH, 1);
            dataVen = cal.getTime();
        }
    }
    
    
     /// metodos
    public void novo(){
        venda = new Venda();
        itens= new ItensVenda();
        parc = 1;
        venda.setContasReceber(new ArrayList<ContasReceber>());
    }
    
    public void salvar(){
        vendaFacade.save(venda);
    }
    
    public void conf(ContasReceber cr){
        
        vendaFacade.save(venda);
        this.cr = cr;
        baixa = new BaixaReceber();
    }
    public void editar(Venda e){
        venda = e;
    }
    
    public void excluir(Venda e){
        vendaFacade.remove(e);
    }
    
    public void atualizaPrecoProduto() {
    itens.setPreco(itens.getProduto().getValor());
    }
    
     public void Quantidadeproduto() {
         if (produto.getGprod().getMedida().equals("12")) {
             itens.setQuantidade(Double.parseDouble("12"));
         }
         
    }
    
    public void addproduto(){
       if (itens.getQuantidade() == null || itens.getPreco() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage( 
                                    FacesMessage.SEVERITY_ERROR,
                                    "Preencha todos os campos!",
                                    null));
        } else {
            Double estoque = itens.getProduto().getEstoque();
            ItensVenda itentemp = null;
                for (ItensVenda it: venda.getItens()){
                    if(it.getProduto().equals(itens.getProduto())){
                        itentemp = it;
                        estoque = estoque - it.getQuantidade();
                    }
                }
                
            if(estoque - itens.getQuantidade() <0){
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Estoque insuficiente!",
                                "Restam apenas "
                                + estoque
                                + " unidades."));
            } else {
                if (itentemp == null){
                itens.setVenda(venda);
                venda.getItens().add(itens);
                }   else {
                    itentemp.setQuantidade(itentemp.getQuantidade() + itens.getQuantidade());
                }
                itens = new ItensVenda();
                }
            }
        }
        
    public void remove(ItensVenda it){
        venda.getItens().remove(it);
    }

    public FacadeVenda getVendaFacade() {
        return vendaFacade;
    }

    public void setVendaFacade(FacadeVenda vendaFacade) {
        this.vendaFacade = vendaFacade;
    }
    public Integer getParc() {
        return parc;
    }

    public void setParc(Integer parc) {
        this.parc = parc;
    }
    

    public ItensVenda getItens() {
        return itens;
    }

    public void setItens(ItensVenda itens) {
        this.itens = itens;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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

    public FacadeProduto getProdutoFacade() {
        return produtoFacade;
    }

    public void setProdutoFacade(FacadeProduto produtoFacade) {
        this.produtoFacade = produtoFacade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ConverterGenerico getProdutoConver() {
        if (produtoConver == null) {
            produtoConver = new ConverterGenerico(produtoFacade);
        }
        return produtoConver;
    }

    public void setProdutoConver(ConverterGenerico produtoConver) {
        this.produtoConver = produtoConver;
    }

    public FacadePessoa getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(FacadePessoa clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public ConverterGenerico getClienteConver() {
        if (clienteConver == null) {
            clienteConver = new ConverterGenerico(clienteFacade);
        }
        return clienteConver;
    }

    public void setClienteConver(ConverterGenerico clienteConver) {
        this.clienteConver = clienteConver;
    }

    public ContasReceber getCr() {
        return cr;
    }

    public void setCr(ContasReceber cr) {
        this.cr = cr;
    }

    public FormaPagamento getForma() {
        return forma;
    }

    public void setForma(FormaPagamento forma) {
        this.forma = forma;
    }

    public BaixaReceber getBaixa() {
        return baixa;
    }

    public void setBaixa(BaixaReceber baixa) {
        this.baixa = baixa;
    }
    
    
}
