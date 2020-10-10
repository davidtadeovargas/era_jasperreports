/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports.models;

import com.era.models.Company;

/**
 *
 * @author PC
 */
public class BaseReport {
    
    private String reportName;
    private String importInWords;
    private Company Company;
    
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getImportInWords() {
        return importInWords;
    }

    public void setImportInWords(String importInWords) {
        this.importInWords = importInWords;
    }        

    public Company getCompany() {
        return Company;
    }

    public void setCompany(Company Company) {
        this.Company = Company;
    }
}
