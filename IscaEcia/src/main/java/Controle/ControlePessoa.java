/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Converter.ConverterGenerico;
import Entidade.Cidade;
import Entidade.Estado;
import Entidade.Pessoa;
import Facade.FacadePessoa;
import Facade.FacadeCidade;
import Facade.FacadeEstado;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author Olimp
 */

@ManagedBean
@SessionScoped
public class ControlePessoa implements Serializable{
   
     @EJB
    private FacadeEstado facadeestado;
    private ConverterGenerico estadoconver;
    
    @EJB
    private FacadeCidade cidadeFacade;
    private ConverterGenerico cidconver;
    
    private Estado estado;
    private String cid;
    @EJB
    private FacadePessoa pessoaFacade;
    private Pessoa pes;         
    
    public void novo(){
        pes = new Pessoa();
    }

    public void salvar(){     
        Cidade c = cidadeFacade.listaFiltrandoporNome(cid);
        if(c == null){
            c = new Cidade();
            c.setNome(cid);
            c.setEstado(estado);
            c = cidadeFacade.salvaRetornando(c);
        }
        pes.setCidade(c);
        pessoaFacade.save(pes);
    }
    
    public void excluir(Pessoa e){
        pessoaFacade.remove(e);
    }
    
    public void editar(Pessoa e){        
        pes = e;
    }

    public Pessoa getCli() {
        return pes;
    }

    public void setCli(Pessoa pes) {
        this.pes = pes;
    }

    public FacadeCidade getFacadeCidade() {
        return cidadeFacade;
    }

    public void setFacadeCidade(FacadeCidade cidadeFacade) {
        this.cidadeFacade = cidadeFacade;
    }

    public ConverterGenerico getCidconver() {
        if (cidconver == null) {
            cidconver = new ConverterGenerico(cidadeFacade);
        }
        return cidconver;
    }

    public void setCidconver(ConverterGenerico cidconver) {
        this.cidconver = cidconver;
    }
    

    public FacadePessoa getFacadePessoa() {
        return pessoaFacade;
    }

    public void setFacadePessoa(FacadePessoa pessoaFacade) {
        this.pessoaFacade = pessoaFacade;
    }

    public Pessoa getPes() {
        return pes;
    }

    public void setPes(Pessoa pes) {
        this.pes = pes;
    }

    
    public List<Pessoa> getLista(){
        return pessoaFacade.findAll();
    }

     public List<Cidade> getListaCidadesFiltrando(String filtro){
        return cidadeFacade.listaFiltrando(filtro, "nome");
    }

    public FacadeCidade getCidadeFacade() {
        return cidadeFacade;
    }

    public void setCidadeFacade(FacadeCidade cidadeFacade) {
        this.cidadeFacade = cidadeFacade;
    }

    public FacadePessoa getPessoaFacade() {
        return pessoaFacade;
    }

    public void setPessoaFacade(FacadePessoa pessoaFacade) {
        this.pessoaFacade = pessoaFacade;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<Estado> getListaestadoFiltrando(String filtro){
        return facadeestado.listaFiltrando(filtro, "nome");
    }
        public ConverterGenerico getEstadoconver() {
        if(estadoconver == null)
        {
            estadoconver = new ConverterGenerico(facadeestado); 
        }
        return estadoconver;
    }

    public FacadeEstado getFacadeestado() {
        return facadeestado;
    }

    public void setFacadeestado(FacadeEstado facadeestado) {
        this.facadeestado = facadeestado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
        
    public List<Pessoa> getfiltroCliente(String filtro, String atributos){
        return pessoaFacade.listaFiltro("categoria", "Cliente");
    }
    
    public List<Pessoa> getfiltrofuncionario(String filtro, String atributos){
        return pessoaFacade.listaFiltro("categoria", "Funcionario");
    }
    
    public List<Pessoa> getfiltrofornecedor(String filtro, String atributos){
        return pessoaFacade.listaFiltro("categoria", "Fornecedor");
    }

  
        
}