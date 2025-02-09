/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ContasReceber implements Serializable, ClassePai {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Descricao;
    private String Tipo;
    private Double ValorTotal;
    private String Parcelas;
    private Double valorParcela;
    private String situacao;
    private Double valorPago;
    
    @Temporal(TemporalType.DATE)
    private Date dtLancamento;
    
    @Temporal(TemporalType.DATE)
    private Date Vencimento;

    @ManyToOne
    private Venda venda;
    
    @ManyToOne
    private Pessoa pessoa;
    
    @ManyToOne
    private FormaPagamento forma;
    
    @OneToMany(cascade = CascadeType.ALL, 
            orphanRemoval = true, 
            fetch = FetchType.EAGER, 
            mappedBy = "receber")
    private List<BaixaReceber> baixaReceber;
    
    

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
        for(BaixaReceber b : baixaReceber){
            valorPago = valorPago + b.getValor();
        }
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public FormaPagamento getForma() {
        return forma;
    }

    public void setForma(FormaPagamento forma) {
        this.forma = forma;
    }

    public List<BaixaReceber> getBaixaReceber() {
        return baixaReceber;
    }

    public void setBaixaReceber(List<BaixaReceber> baixaReceber) {
        this.baixaReceber = baixaReceber;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        if (!(object instanceof ContasReceber)) {
            return false;
        }
        ContasReceber other = (ContasReceber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.ContasReceber[ id=" + id + " ]";
    }
    
}
