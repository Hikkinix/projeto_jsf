package Controle;

import Converter.ConverterGenerico;
import Converter.MoneyConverter;
import Entidade.BaixaPagar;
import Entidade.BaixaReceber;
import Entidade.Compra;
import Entidade.ContasPagar;
import Entidade.ContasReceber;
import Entidade.FormaPagamento;
import Entidade.ItensCompra;
import Entidade.Pessoa;
import Entidade.Produto;
import Facade.FacadeCompra;
import Facade.FacadeContasPagar;
import Facade.FacadePessoa;
import Facade.FacadeProduto;
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
 **/

@ManagedBean
@SessionScoped
public class ControleCompra implements Serializable{
    
    private Integer parc;
    private FormaPagamento forma;
    private BaixaPagar baixa;
    
    @EJB
    private FacadeCompra compraFacade;
    private Compra compra;
    private MoneyConverter moneyConverter;
    private ItensCompra itens; 
    private ContasPagar cp;
    @EJB
    private FacadeProduto produtoFacade;
    private Produto produto;
    private ConverterGenerico produtoConverter;
    
    @EJB
    private FacadePessoa fornecedorFacade;
    private Pessoa Fornecedor;
    private ConverterGenerico fornecedorConver;
    
    @EJB
    private FacadeContasPagar cpfacade;

    public FacadeCompra getCompraFacade() {
        return compraFacade;
    }

    public void setCompraFacade(FacadeCompra compraFacade) {
        this.compraFacade = compraFacade;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
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

    public ItensCompra getItens() {
        return itens;
    }

    public void setItens(ItensCompra itens) {
        this.itens = itens;
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

    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(produtoFacade);
        }
        return produtoConverter;
    }

    public void setProdutoConverter(ConverterGenerico produtoConverter) {
        this.produtoConverter = produtoConverter;
    }

    public Integer getParc() {
        return parc;
    }

    public void setParc(Integer parc) {
        this.parc = parc;
    }

    public FormaPagamento getForma() {
        return forma;
    }

    public void setForma(FormaPagamento forma) {
        this.forma = forma;
    }

    public BaixaPagar getBaixa() {
        return baixa;
    }

    public void setBaixa(BaixaPagar baixa) {
        this.baixa = baixa;
    }

    public ContasPagar getCp() {
        return cp;
    }

    public void setCp(ContasPagar cp) {
        this.cp = cp;
    }

       
    
    public FacadePessoa getFornecedorFacade() {
        return fornecedorFacade;
    }

    public void setFornecedorFacade(FacadePessoa fornecedorFacade) {
        this.fornecedorFacade = fornecedorFacade;
    }

    public Pessoa getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(Pessoa Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public ConverterGenerico getFornecedorConver() {
        if (fornecedorConver == null) {
            fornecedorConver = new ConverterGenerico(fornecedorFacade);
        }
        return fornecedorConver;
    }

    public void setFornecedorConver(ConverterGenerico fornecedorConver) {
        this.fornecedorConver = fornecedorConver;
    }
    
     public void novo(){
         compra = new Compra();
         itens = new ItensCompra();
    }
    
    public void editar(Compra c){
        compra = c;
    }
    
    public void excluir(Compra c){
        compraFacade.remove(c);
    }
    
    public void salvar(){
        compraFacade.save(compra);
    }
    
     public void entradabaixa(ContasPagar cp){
        cpfacade.save(this.cp);
        this.cp = cp;
        baixa = new BaixaPagar();
    }
    
    public List<Compra> getLista(){
        return compraFacade.findAll();
    }
    
     public List<Pessoa> getListFornecedorFiltrando(String filtro){
        return fornecedorFacade.listaFiltrando(filtro, "nome");
    }
    
    public List<Produto> getListProdutosFiltrando(String filtro){
        return produtoFacade.listaFiltrando(filtro, "nome");
    }
    
    public void atualizaPrecoProduto() {
    itens.setPreco(itens.getProduto().getValor());
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
            ItensCompra itentemp = null;
                for (ItensCompra it: compra.getItens()){
                    if(it.getProduto().equals(itens.getProduto())){
                        itentemp = it;
                        estoque = estoque - it.getQuantidade();
                    }
                } 
           
                if (itentemp == null){
                itens.setCompra(compra);
                compra.getItens().add(itens);
                }   else {
                    itentemp.setQuantidade(itentemp.getQuantidade() + itens.getQuantidade());
                }
                itens = new ItensCompra();
                
            }
        }
        
    public void remove(ItensCompra it){
        compra.getItens().remove(it);
    }
    
      ///
     public void gerarParcelas() {
        compra.setContasPagar(new ArrayList<ContasPagar>());
        Double valor = compra.getTotal() / parc;
        Date dataVen = compra.getDatacompra();
        for (Integer i = 1; i <= parc ; i++) {
            
            cp = new ContasPagar();
            cp.setDtLancamento(compra.getDatacompra());
            cp.setDescricao(compra.getFornecedor().getNome());
            cp.setParcelas(i.toString() + "/" + parc.toString());
            cp.setForma(forma);
            cp.setValorParcela(valor);
            cp.setValorTotal(compra.getTotal());
            cp.setVencimento(dataVen);
            cp.setCompra(compra);
            
         if(compra.getFornecedor() != null){
            cp.setPessoa(compra.getFornecedor());
            }            
            compra.getContasPagar().add(cp);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataVen);
            cal.add(Calendar.MONTH, 1);
            dataVen = cal.getTime();
        }
    }
    
      public List<Pessoa> getfiltrofuncionario(String filtro, String atributos){
        return fornecedorFacade.listaFiltro("categoria", "Funcionario");
    }
     
}
