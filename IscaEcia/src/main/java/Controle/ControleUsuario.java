/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Converter.ConverterGenerico;
import Entidade.Perfil;
import Entidade.Pessoa;
import Entidade.Usuario;
import Facade.FacadePerfil;
import Facade.FacadePessoa;
import Facade.FacadeUsuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Olimp
 */
@ManagedBean
@SessionScoped
public class ControleUsuario implements Serializable{
    
    @EJB
    private FacadePessoa fucionarioFacade;
    private ConverterGenerico funcionarioConverter;
    @EJB
    private FacadeUsuario usuarioFacade;
    @EJB
    private FacadePerfil perfilFacade;
    private Usuario usuario;    
    private ConverterGenerico converterPerfil;

    public List<Perfil> listaPerfis(){
        return perfilFacade.findAll();
    }
    
    public ConverterGenerico getConverterPerfil() {
        if(converterPerfil == null){
            converterPerfil = new ConverterGenerico(perfilFacade);
        }
        return converterPerfil;
    }

    public void setConverterPerfil(ConverterGenerico converterPerfil) {
        this.converterPerfil = converterPerfil;
    }

    public void novo() {
        usuario = new Usuario();
    }

    public void salvar() {
        if (!usuario.getSenha().equals(usuario.getComfirmarsenha())) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "As senhas são diferente!",
                            null));
        } else {
            usuarioFacade.save(usuario);
        }
    }

    public void excluir(Usuario e) {
        usuarioFacade.remove(e);
    }

    public void editar(Usuario e) {
        usuario = e;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLista() {
        return usuarioFacade.findAll();
    }

    public FacadePessoa getFucionarioFacade() {
        return fucionarioFacade;
    }

    public void setFucionarioFacade(FacadePessoa fucionarioFacade) {
        this.fucionarioFacade = fucionarioFacade;
    }

    public ConverterGenerico getFuncionarioConverter() {
        if (funcionarioConverter == null) {
            funcionarioConverter = new ConverterGenerico(fucionarioFacade);
            
        }
        return funcionarioConverter;
    }

    public void setFuncionarioConverter(ConverterGenerico funcionarioConverter) {
        this.funcionarioConverter = funcionarioConverter;
    }

    public FacadeUsuario getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(FacadeUsuario usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public FacadePerfil getPerfilFacade() {
        return perfilFacade;
    }

    public void setPerfilFacade(FacadePerfil perfilFacade) {
        this.perfilFacade = perfilFacade;
    }
    
    public List<Pessoa> getListFuncionarioFiltrando(String filtro){
        return fucionarioFacade.listaFiltrando(filtro, "nome");
    }
    
     public List<Pessoa> getfiltrofuncionario(String filtro, String atributos){
        return fucionarioFacade.listaFiltro("categoria", "Funcionario");
    }
     
}
