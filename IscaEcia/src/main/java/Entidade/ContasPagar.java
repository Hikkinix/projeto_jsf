package Entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Olimp
 */
@Entity
public class ContasPagar implements Serializable, ClassePai {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Descricao;
    private Double ValorTotal;
    private String Parcelas;
     private String tipo;
    private Double valorParcela;
    private String situacao;
    private Double valorPago;

    @Temporal(TemporalType.DATE)
    private Date dtLancamento;
    
    @Temporal(TemporalType.DATE)
    private Date Vencimento;
    
    @ManyToOne
    private Compra compra;
    
    @ManyToOne
    private FormaPagamento forma;
    
     @OneToMany(cascade = CascadeType.ALL, 
            orphanRemoval = true, 
            fetch = FetchType.EAGER, 
            mappedBy = "pagar")
    private List<BaixaPagar> baixaPagar;
    
    @ManyToOne
    private Pessoa pessoa;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(Double ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public String getParcelas() {
        return Parcelas;
    }

    public void setParcelas(String Parcelas) {
        this.Parcelas = Parcelas;
    }


    public Double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(Double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public FormaPagamento getForma() {
        return forma;
    }

    public void setForma(FormaPagamento forma) {
        this.forma = forma;
    }

    public String getSituacao() {
           if(Objects.equals(getValorTotal(), getValorPago())){
            situacao = "Baixado";
        }else{
            situacao = "Aberto";
        }
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Double getValorPago() {
              valorPago = 0d;
        for(BaixaPagar b : baixaPagar){
            valorPago = valorPago + b.getValor();
        }
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public List<BaixaPagar> getBaixaPagar() {
        return baixaPagar;
    }

    public void setBaixaPagar(List<BaixaPagar> baixaPagar) {
        this.baixaPagar = baixaPagar;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(Date dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public Date getVencimento() {
        return Vencimento;
    }

    public void setVencimento(Date Vencimento) {
        this.Vencimento = Vencimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContasPagar)) {
            return false;
        }
        ContasPagar other = (ContasPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.Pagamento[ id=" + id + " ]";
    }
    
}
