/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports;

import com.era.easyretail.era_jasperreports.models.FacReportModel;
import com.era.easyretail.era_jasperreports.models.GenerateProperties;
import com.era.easyretail.era_jasperreports.models.TicketReportModel;
import com.era.models.BasDats;
import com.era.models.Company;
import com.era.models.Sales;
import com.era.utilities.UtilitiesFactory;

/**
 *
 * @author PC
 */
public class ReportManager {
    
    private static ReportManager ReportManager;
    
    private ReportManager(){        
    }
    
    final public static ReportManager getSingleton(){
        if(ReportManager==null){
            ReportManager = new ReportManager();
        }
        return ReportManager;
    }
    
    public void generateTicketPDF(final Sales Sale,final Company Company_, final BasDats BasDatsLocal) throws Exception {
        
        //Get import in words
        final String importInWords = UtilitiesFactory.getSingleton().getNumbersUtility().convertNumberToStringRepresentation(String.valueOf(Sale.getTotal()), Sale.getSerie(), Sale.getCoinCode(), true, true);
        
        final String subtotalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getSubtotal().doubleValue()));
        final String taxesFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTax().doubleValue()));
        final String disccountFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTotalDisccount().doubleValue()));
        final String totalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTotal().doubleValue()));
        
        //Crete the report model
        final TicketReportModel TicketReportModel = new TicketReportModel();
        TicketReportModel.setCity(Company_.getCiu());
        TicketReportModel.setCoin(Sale.getCoinCode());
        TicketReportModel.setColony(Company_.getCol());
        TicketReportModel.setCompanyName(Company_.getNom());
        TicketReportModel.setConsecutive(Sale.getReferenceNumber());
        TicketReportModel.setCountry(Company_.getPai());
        TicketReportModel.setDocumentDate(Sale.getDeliverDate().toString());
        TicketReportModel.setEstate(Company_.getEstad());
        TicketReportModel.setExternalNumber(Company_.getNoext());
        TicketReportModel.setImportWords(importInWords);
        TicketReportModel.setInternalNumber(Company_.getNoint());
        TicketReportModel.setPhone(Company_.getTel());
        TicketReportModel.setPostalCode(Company_.getCP());
        TicketReportModel.setRFC(Company_.getRFC());
        TicketReportModel.setSaleID(String.valueOf(Sale.getId()));        
        TicketReportModel.setStreet(Company_.getCalle());

        TicketReportModel.setSubtotal(subtotalFormat);
        TicketReportModel.setTax(taxesFormat);
        TicketReportModel.setDisccount(disccountFormat);
        TicketReportModel.setTotal(totalFormat);
        TicketReportModel.setWebPage(Company_.getPags());

        //Create the report properties
        GenerateProperties GenerateProperties = new GenerateProperties();
        GenerateProperties.setObjectModel(TicketReportModel);
        if(UtilitiesFactory.getSingleton().getPrintersUtility().userTicketPrinterExists()){
            GenerateProperties.setPrint(true);
        }        
        GenerateProperties.setExportToPDF(true);
        GenerateProperties.setPdfExportPath(UtilitiesFactory.getSingleton().getImagesUtility().getTicketsPath());
        GenerateProperties.setPdfFileName(String.valueOf(Sale.getId()));

        //Change the tickets printer
        if(UtilitiesFactory.getSingleton().getPrintersUtility().userTicketPrinterExists()){
            UtilitiesFactory.getSingleton().getPrintersUtility().changeDefaultUserTicketPrinter();
        }

        //Generate te report
        final TicketReportGenerator TicketReportGenerator = ReportsManager.getSingleton().getTicketReportGenerator();
        TicketReportGenerator.setLocalCompanyParams(true);
        TicketReportGenerator.setBaseReport(TicketReportModel);
        TicketReportGenerator.generate(GenerateProperties);
    }
    
    public void generateCFDIPDF(final Sales Sale,final Company Company_, final BasDats BasDatsLocal) throws Exception {
        
        //Get import in words
        final String importInWords = UtilitiesFactory.getSingleton().getNumbersUtility().convertNumberToStringRepresentation(String.valueOf(Sale.getTotal()), Sale.getSerie(), Sale.getCoinCode(), true, true);
        
        final String subtotalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getSubtotal().doubleValue()));
        final String taxesFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTax().doubleValue()));
        final String disccountFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTotalDisccount().doubleValue()));
        final String totalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTotal().doubleValue()));
        
        //Crete the report model
        final FacReportModel FacReportModel = new FacReportModel();
        FacReportModel.setCiu(Company_.getCiu());
        FacReportModel.setMon(Sale.getCoinCode());
        FacReportModel.setUsoCfdi(Sale.getUsocfdi());
        FacReportModel.setConsfac(Sale.getReferenceNumber());
        FacReportModel.setSer(Sale.getSerie());
        FacReportModel.setCol(Company_.getCol());
        FacReportModel.setObservaciones(Sale.getObservation());
        FacReportModel.setNomemp(Company_.getNom());
        FacReportModel.setVta(String.valueOf(Sale.getId()));        
        FacReportModel.setPai(Company_.getPai());        
        FacReportModel.setFdoc(Sale.getDeliverDate().toString());
        FacReportModel.setEstad(Company_.getEstad());
        FacReportModel.setDoment("");
        FacReportModel.setNoext(Company_.getNoext());
        FacReportModel.setImportWords(importInWords);
        FacReportModel.setNoint(Company_.getNoint());
        FacReportModel.setTel(Company_.getTel());
        FacReportModel.setMetopag(Sale.getPaymentMethod());
        FacReportModel.setCp(Company_.getCP());
        FacReportModel.setRfc(Company_.getRFC());                
        FacReportModel.setCall(Company_.getCalle());
        FacReportModel.setFormpag(Sale.getPaymentForm());
        FacReportModel.setQr("?re=" + BasDatsLocal.getRFC() + "?re=" + Company_.getRFC() + "&tt=”" + totalFormat + "&id=" + Sale.getFiscalFolio());

        FacReportModel.setSubtotal(subtotalFormat);
        FacReportModel.setImpue(taxesFormat);
        FacReportModel.setDescu(disccountFormat);
        FacReportModel.setTot(totalFormat);
        FacReportModel.setWebPage(Company_.getPags());

        //Create the report properties
        GenerateProperties GenerateProperties = new GenerateProperties();
        GenerateProperties.setObjectModel(FacReportModel);
        if(UtilitiesFactory.getSingleton().getPrintersUtility().userTicketPrinterExists()){
            GenerateProperties.setPrint(true);
        }        
        GenerateProperties.setExportToPDF(true);
        GenerateProperties.setPdfExportPath(UtilitiesFactory.getSingleton().getImagesUtility().getInvoicesPath());
        GenerateProperties.setPdfFileName("CFDI-" + BasDatsLocal.getRFC() + "-" + Sale.getSerie() + "-" + Sale.getReferenceNumber());

        //Change the tickets printer
        if(UtilitiesFactory.getSingleton().getPrintersUtility().userTicketPrinterExists()){
            UtilitiesFactory.getSingleton().getPrintersUtility().changeDefaultUserTicketPrinter();
        }

        //Generate te report
        final FacReportGenerator FacReportGenerator = ReportsManager.getSingleton().getFacReportGenerator();
        FacReportGenerator.setLocalCompanyParams(true);
        FacReportGenerator.setBaseReport(FacReportModel);
        FacReportGenerator.generate(GenerateProperties);
    }
}
