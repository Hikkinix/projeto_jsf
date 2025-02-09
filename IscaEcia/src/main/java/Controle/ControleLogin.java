/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Entidade.Usuario;
import Entidade.Permitir;
import Facade.FacadeUsuario;
import java.io.Serializable;
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
public class ControleLogin implements Serializable{
    
       @EJB
    private FacadeUsuario usuarioFacade;
    private Usuario usuario = new Usuario();
    
    public Boolean validaMenu(String menu){
//        for (Permitir p : usuario.getPerfil().getPermitir()) { // verifica permisoes do perfil do usuario 
//            if(menu.equals(p.getNome())){ /// compara com os menus liberanos nas permisoes
//                return true; /// retorna o que for altorizado ao usuario
//            }
//        }        
        return true;
    }
    
    public String logar(){
        usuario = usuarioFacade.validaUsuario(usuario);
        if(usuario.getPerfil() != null){
            return "index.xhtml";
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Usuário e/ou senha inválidos!",
                            null));
            return "login.xhtml";
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
