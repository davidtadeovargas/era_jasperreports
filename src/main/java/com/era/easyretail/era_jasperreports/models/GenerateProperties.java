/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports.models;

/**
 *
 * @author PC
 */
public class GenerateProperties {
    
    private boolean show;
    private boolean print;
    private boolean withPrintDialog;
    private boolean exportToPDF;
    private Object ObjectModel;
    private String pdfExportPath;
    

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Object getObjectModel() {
        return ObjectModel;
    }

    public void setObjectModel(Object ObjectModel) {
        this.ObjectModel = ObjectModel;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public boolean isWithPrintDialog() {
        return withPrintDialog;
    }

    public void setWithPrintDialog(boolean withPrintDialog) {
        this.withPrintDialog = withPrintDialog;
    }

    public String getPdfExportPath() {
        return pdfExportPath;
    }

    public void setPdfExportPath(String pdfExportPath) {
        this.pdfExportPath = pdfExportPath;
    }

    public boolean isExportToPDF() {
        return exportToPDF;
    }

    public void setExportToPDF(boolean exportToPDF) {
        this.exportToPDF = exportToPDF;
    }        
}
