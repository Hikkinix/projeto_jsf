package Entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * @author Olimp
 */
@Entity
public class Compra implements Serializable, ClassePai {

    /// Atributos
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date datacompra;
    
    @ManyToOne
    private Pessoa fornecedor;
    private Double total;
    
    @OneToMany(cascade = CascadeType.ALL, /// quaqer alteração no atributo pai 
               fetch = FetchType.EAGER,
               mappedBy = "compra",
               orphanRemoval = true
            )
    private List<ItensCompra> itens;
    
    
    @OneToMany(cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, 
            mappedBy = "compra", 
            orphanRemoval = true)
    private List<ContasPagar> contasPagar;
    
    ///
    public Compra(){
        itens = new ArrayList<ItensCompra>();
        datacompra = new Date();
    }
    
    
    /// Gets e Sets
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatacompra() {
        return datacompra;
    }

    public void setDatacompra(Date datacompra) {
        this.datacompra = datacompra;
    }

    public Pessoa getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Pessoa fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Double getTotal() {
          total = 0d;
        for(ItensCompra it : itens){
            total = total + it.getSubtotal();
        }
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ItensCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItensCompra> itens) {
        this.itens = itens;
    }

    public List<ContasPagar> getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(List<ContasPagar> contasPagar) {
        this.contasPagar = contasPagar;
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
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade.compra[ id=" + id + " ]";
    }
    
}
