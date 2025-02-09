/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Olimp
 */
@Entity
public class BaixaReceber implements Serializable, ClassePai {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double valor;
    
    @Temporal(TemporalType.DATE)
    private Date databaixa;
    
    @ManyToOne
    private ContasReceber receber;
    

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDatabaixa() {
        return databaixa;
    }

    public void setDatabaixa(Date databaixa) {
        this.databaixa = databaixa;
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
        if (!(object instanceof BaixaReceber)) {
            return false;
        }
        BaixaReceber other = (BaixaReceber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.BaixaReceber[ id=" + id + " ]";
    }

    public ContasReceber getReceber() {
        return receber;
    }

    public void setReceber(ContasReceber receber) {
        this.receber = receber;
    }
    
}
