/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorios;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import util.Conexao;

/**
 *
 * @author Olimp
 */
@ManagedBean
@SessionScoped
public class ControleRelatorio implements Serializable{
    
    private String estado;
    private String categoriapessoa;
    private String grupoproduto;
    private String tipoproduto;
    
    private String filtro;
    private Date dtinicio;
    private Date dtfim;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCategoriapessoa() {
        return categoriapessoa;
    }

    public void setCategoriapessoa(String categoriapessoa) {
        this.categoriapessoa = categoriapessoa;
    }

    public String getGrupoproduto() {
        return grupoproduto;
    }

    public void setGrupoproduto(String grupoproduto) {
        this.grupoproduto = grupoproduto;
    }

    public String getTipoproduto() {
        return tipoproduto;
    }

    public void setTipoproduto(String tipoproduto) {
        this.tipoproduto = tipoproduto;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Date getDtinicio() {
        return dtinicio;
    }

    public void setDtinicio(Date dtinicio) {
        this.dtinicio = dtinicio;
    }

    public Date getDtfim() {
        return dtfim;
    }

    public void setDtfim(Date dtfim) {
        this.dtfim = dtfim;
    }
    
     public void gerarRelatorioCidadeEstado() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "relCidest.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            p.put("Estado", estado);
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/cadastro/cidade/" + arquivoJasper), p, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = "RelatorioCidadePDF";
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
    
}
