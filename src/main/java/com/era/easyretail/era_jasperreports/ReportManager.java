/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports;

import com.era.easyretail.era_jasperreports.models.CancelSaleReportModel;
import com.era.easyretail.era_jasperreports.models.CustomerReportModel;
import com.era.easyretail.era_jasperreports.models.FacReportModel;
import com.era.easyretail.era_jasperreports.models.GenerateProperties;
import com.era.easyretail.era_jasperreports.models.RemReportModel;
import com.era.easyretail.era_jasperreports.models.TickPagoReportModel;
import com.era.easyretail.era_jasperreports.models.TicketReportModel;
import com.era.models.BasDats;
import com.era.models.Company;
import com.era.models.Payment;
import com.era.models.Sales;
import com.era.utilities.DialogPropertiesUitlity;
import com.era.utilities.UtilitiesFactory;
import java.math.BigDecimal;
import java.util.Date;

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
    
    public void generateTicketPagoPDF(final Payment Payment,final Company Company_, final BigDecimal pending, final BigDecimal totalAboned) throws Exception {
                
        //Get import in words
        final String importInWords = UtilitiesFactory.getSingleton().getNumbersUtility().convertNumberToStringRepresentation(String.valueOf(Payment.getImporte()), null, null, true, true);
        
        final String totalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(totalAboned.doubleValue()));
        final String pendingFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(pending.doubleValue()));
        
        //Crete the report model
        final TickPagoReportModel TickPagoReportModel = new TickPagoReportModel();
        TickPagoReportModel.setDocumentDate(Payment.getFalt().toString());
        TickPagoReportModel.setFolio(Payment.getFolio());
        TickPagoReportModel.setPending(pendingFormat);
        TickPagoReportModel.setSerie(Payment.getSerie());
        TickPagoReportModel.setTotal(totalFormat);
        TickPagoReportModel.setImportInWords(importInWords);
        TickPagoReportModel.setCompany(Company_);        

        //Create the report properties
        GenerateProperties GenerateProperties = new GenerateProperties();
        GenerateProperties.setObjectModel(TickPagoReportModel);                
        GenerateProperties.setShow(true);

        //Generate te report
        final TickPagoReportGenerator TickPagoReportGenerator = ReportsManager.getSingleton().getTickPagoReportGenerator();
        TickPagoReportGenerator.setLocalCompanyParams(true);
        TickPagoReportGenerator.setCompanyParams(true);
        TickPagoReportGenerator.setBaseReport(TickPagoReportModel);
        TickPagoReportGenerator.generate(GenerateProperties);
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
    
    public void generateProductsDownMinPDF() throws Exception {
        
        //Create the report properties
        GenerateProperties GenerateProperties = new GenerateProperties();        
        GenerateProperties.setShow(true);

        //Generate te report
        final ProductsDownMinReportGenerator ProductsDownMinReportGenerator = ReportsManager.getSingleton().getProductsDownMinReportGenerator();
        ProductsDownMinReportGenerator.generate(GenerateProperties);
    }        
            
    public void generateWarehousesPDF() throws Exception {
        
        //Create the report properties
        GenerateProperties GenerateProperties = new GenerateProperties();        
        GenerateProperties.setShow(true);

        //Generate te report
        final WarehousesReportGenerator WarehousesReportGenerator = ReportsManager.getSingleton().getWarehousesReportGenerator();
        WarehousesReportGenerator.generate(GenerateProperties);
    }
    
    public void generateAllCustomersPDF() throws Exception {
        
        final CustomerReportModel CustomerReportModel = new CustomerReportModel();
        CustomerReportModel.setCustomer("");
        
        //Create the report properties
        GenerateProperties GenerateProperties = new GenerateProperties();        
        GenerateProperties.setShow(true);

        //Generate te report
        final CustomerReportGenerator CustomerReportGenerator = ReportsManager.getSingleton().getCustomerReportGenerator();        
        CustomerReportGenerator.setBaseReport(CustomerReportModel);
        CustomerReportGenerator.generate(GenerateProperties);
    }
    public void generateCustomersPDF(final String customerCode) throws Exception {
        
        final CustomerReportModel CustomerReportModel = new CustomerReportModel();
        CustomerReportModel.setCustomer(customerCode);
        
        //Create the report properties
        GenerateProperties GenerateProperties = new GenerateProperties();        
        GenerateProperties.setShow(true);                
        
        //Generate te report
        final CustomerReportGenerator CustomerReportGenerator = ReportsManager.getSingleton().getCustomerReportGenerator();
        CustomerReportGenerator.setBaseReport(CustomerReportModel);
        CustomerReportGenerator.generate(GenerateProperties);
    }
    
    public void generateProductsUpMaxPDF() throws Exception {
        
        //Create the report properties
        GenerateProperties GenerateProperties = new GenerateProperties();        
        GenerateProperties.setShow(true);

        //Generate te report
        final ProductsUpMaxReportGenerator ProductsUpMaxReportGenerator = ReportsManager.getSingleton().getProductsUpMaxReportGenerator();
        ProductsUpMaxReportGenerator.generate(GenerateProperties);
    }
    
    public void generateCFDIPDF(final Sales Sale,final Company Company_, final BasDats BasDatsLocal) throws Exception {
        
        //Get import in words
        final String importInWords = UtilitiesFactory.getSingleton().getNumbersUtility().convertNumberToStringRepresentation(String.valueOf(Sale.getTotal()), Sale.getSerie(), Sale.getCoinCode(), true, true);
        
        final String subtotalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getSubtotal().doubleValue()));
        final String taxesFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTax().doubleValue()));
        final String disccountFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTotalDisccount().doubleValue()));
        final String totalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTotal().doubleValue()));
        final String totalRetencion = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTotalRetention().doubleValue()));        
        
        //Crete the report model
        final FacReportModel FacReportModel = new FacReportModel();
        FacReportModel.setCiu(Company_.getCiu());
        FacReportModel.setMon(Sale.getCoinCode());
        FacReportModel.setUsoCfdi(Sale.getUsocfdi());
        FacReportModel.setConsfac(Sale.getReferenceNumber());
        FacReportModel.setSer(Sale.getSerie());
        FacReportModel.setCol(Company_.getCol());
        FacReportModel.setObservaciones(Sale.getObservation());
        FacReportModel.setTotalRetention(totalRetencion);
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
        FacReportModel.setQr("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?id=" + Sale.getFiscalFolio() + "\n" +
"&re=" + BasDatsLocal.getRFC() + "&rr=" + Company_.getRFC() + "&tt=" + Sale.getTotal().longValue() + "&fe=8whOUw==");
        
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
        if(UtilitiesFactory.getSingleton().getPrintersUtility().userInvoicePrinterExists()){
            UtilitiesFactory.getSingleton().getPrintersUtility().changeDefaultUserInvoicePrinter();
        }

        //Generate te report
        final FacReportGenerator FacReportGenerator = ReportsManager.getSingleton().getFacReportGenerator();
        FacReportGenerator.setLocalCompanyParams(true);
        FacReportGenerator.setBaseReport(FacReportModel);
        FacReportGenerator.generate(GenerateProperties);
    }
    
    public void generateRemPDF(final Sales Sale,final Company Company_, final BasDats BasDatsLocal) throws Exception {
        
        //Get import in words
        final String importInWords = UtilitiesFactory.getSingleton().getNumbersUtility().convertNumberToStringRepresentation(String.valueOf(Sale.getTotal()), Sale.getSerie(), Sale.getCoinCode(), true, true);
        
        final String subtotalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getSubtotal().doubleValue()));
        final String taxesFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTax().doubleValue()));
        final String disccountFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTotalDisccount().doubleValue()));
        final String totalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTotal().doubleValue()));
        
        //Crete the report model
        final RemReportModel RemReportModel = new RemReportModel();
        RemReportModel.setCiu(Company_.getCiu());
        RemReportModel.setMon(Sale.getCoinCode());
        RemReportModel.setNoreference(Sale.getReferenceNumber());
        RemReportModel.setSer(Sale.getSerie());
        RemReportModel.setCol(Company_.getCol());
        RemReportModel.setObservaciones(Sale.getObservation());        
        RemReportModel.setNomemp(Company_.getNom());
        RemReportModel.setVta(String.valueOf(Sale.getId()));        
        RemReportModel.setPai(Company_.getPai());        
        RemReportModel.setFdoc(Sale.getDeliverDate().toString());
        RemReportModel.setEstad(Company_.getEstad());
        RemReportModel.setDoment("");
        RemReportModel.setNoext(Company_.getNoext());
        RemReportModel.setImportWords(importInWords);
        RemReportModel.setNoint(Company_.getNoint());
        RemReportModel.setTel(Company_.getTel());
        RemReportModel.setMetopag(Sale.getPaymentMethod());
        RemReportModel.setCp(Company_.getCP());
        RemReportModel.setVendedor(Sale.getEstac());
        RemReportModel.setRfc(Company_.getRFC());                
        RemReportModel.setCall(Company_.getCalle());
        RemReportModel.setFormpag(Sale.getPaymentForm());
        RemReportModel.setSubtotal(subtotalFormat);
        RemReportModel.setImpue(taxesFormat);
        RemReportModel.setDescu(disccountFormat);
        RemReportModel.setTot(totalFormat);
        RemReportModel.setWebPage(Company_.getPags());

        //Create the report properties
        GenerateProperties GenerateProperties = new GenerateProperties();
        GenerateProperties.setObjectModel(RemReportModel);
        if(UtilitiesFactory.getSingleton().getPrintersUtility().userTicketPrinterExists()){
            GenerateProperties.setPrint(true);
        }        
        GenerateProperties.setExportToPDF(true);
        GenerateProperties.setPdfExportPath(UtilitiesFactory.getSingleton().getImagesUtility().getRemsPath());
        GenerateProperties.setPdfFileName("REM-" + Company_.getRFC() + "-" + Sale.getSerie() + "-" + Sale.getReferenceNumber());

        //Change the printer
        if(UtilitiesFactory.getSingleton().getPrintersUtility().userInvoicePrinterExists()){
            UtilitiesFactory.getSingleton().getPrintersUtility().changeDefaultUserInvoicePrinter();
        }

        //Generate te report
        final RemisionReportGenerator RemisionReportGenerator = ReportsManager.getSingleton().getRemisionReportGenerator();
        RemisionReportGenerator.setLocalCompanyParams(true);
        RemisionReportGenerator.setBaseReport(RemReportModel);
        RemisionReportGenerator.generate(GenerateProperties);
    }
    
    public void generateCancelSalePDF(final Sales Sale,final Company Company_, final boolean showPDF) throws Exception {
        
        final String subtotalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getSubtotal().doubleValue()));
        final String taxesFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTax().doubleValue()));
        final String totalFormat = UtilitiesFactory.getSingleton().getNumbersUtility().toMoneyFormat(String.valueOf(Sale.getTotal().doubleValue()));
        
        //Crete the report model
        final CancelSaleReportModel CancelSaleReportModel = new CancelSaleReportModel();
        CancelSaleReportModel.setVta(String.valueOf(Sale.getId()));
        CancelSaleReportModel.setReferenceNumber(Sale.getReferenceNumber());
        CancelSaleReportModel.setSerie(Sale.getSerie());
        CancelSaleReportModel.setDocumentType(Sale.getDocumentType());
        CancelSaleReportModel.setCompanyCode(Sale.getCompanyCode());
        CancelSaleReportModel.setCompanyName(Company_.getNom());
        CancelSaleReportModel.setEmisionDate(Sale.getEmisionDate().toString());
        CancelSaleReportModel.setCancelDate(new Date().toString());
        CancelSaleReportModel.setSubtotal(subtotalFormat);
        CancelSaleReportModel.setTaxes(taxesFormat);
        CancelSaleReportModel.setTotal(totalFormat);
        
        final String title = DialogPropertiesUitlity.getSingleton().getString("sale_cancelation");
        
        CancelSaleReportModel.setTitle(title);

        //Create the report properties
        GenerateProperties GenerateProperties = new GenerateProperties();
        GenerateProperties.setObjectModel(CancelSaleReportModel);
        GenerateProperties.setShow(showPDF);
        GenerateProperties.setExportToPDF(true);
        GenerateProperties.setPdfExportPath(UtilitiesFactory.getSingleton().getImagesUtility().getCancelsPath());
        GenerateProperties.setPdfFileName("CAN-" + Company_.getRFC() + "-" + Sale.getSerie() + "-" + Sale.getReferenceNumber());

        //Change the printer
        if(UtilitiesFactory.getSingleton().getPrintersUtility().userInvoicePrinterExists()){
            UtilitiesFactory.getSingleton().getPrintersUtility().changeDefaultUserInvoicePrinter();
        }

        //Generate te report
        final CancelSaleReportGenerator CancelSaleReportGenerator = ReportsManager.getSingleton().getCancelSaleReportGenerator();
        CancelSaleReportGenerator.setLocalCompanyParams(true);
        CancelSaleReportGenerator.setBaseReport(CancelSaleReportModel);
        CancelSaleReportGenerator.generate(GenerateProperties);
    }
}
