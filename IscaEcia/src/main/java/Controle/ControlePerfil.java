/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Entidade.Perfil;
import Entidade.Permitir;
import Facade.FacadePerfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Olimp
 */
@ManagedBean
@SessionScoped
public class ControlePerfil implements Serializable{
    
    @EJB
    private FacadePerfil perfilFacade;
    private Perfil per;
    private Permitir permissoes;

    public Permitir getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permitir permissoes) {
        this.permissoes = permissoes;
    }

    public void adicionar(){
        permissoes.setPerfil(per);
        per.getPermitir().add(permissoes);
        permissoes = new Permitir();
    }
    
    public void novo() {
        per = new Perfil();
        permissoes = new Permitir();
        per.setPermitir(new ArrayList<Permitir>());
    }

    public void salvar() {
        perfilFacade.save(per);
    }

    public void excluir(Perfil e) {
        perfilFacade.remove(e);
    }

    public void editar(Perfil e) {
        per = e;
    }

    public Perfil getPer() {
        return per;
    }

    public void setPer(Perfil per) {
        this.per = per;
    }

    public List<Perfil> getLista() {
        return perfilFacade.findAll();
    }

}
