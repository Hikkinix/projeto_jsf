/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;


import Facade.AbstractFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import Entidade.ClassePai;

/**
 * @author jaimedias
 */
public class ConverterGenerico implements Converter{
    
    public AbstractFacade abstractFacade;

    public ConverterGenerico(AbstractFacade abstractFacade) {
        this.abstractFacade = abstractFacade;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return abstractFacade.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((ClassePai)value).getId().toString();
    }
    
}
