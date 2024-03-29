/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports;

/**
 *
 * @author PC
 */
public class ReportsManager {
    
    private static ReportsManager ReportsManager;
    
    public static ReportsManager getSingleton(){
        if(ReportsManager==null){
            ReportsManager = new ReportsManager();
        }
        return ReportsManager;
    }
        
    public ProductsDownMinReportGenerator getProductsDownMinReportGenerator(){
        return new ProductsDownMinReportGenerator();
    }
    public ProductsUpMaxReportGenerator getProductsUpMaxReportGenerator(){
        return new ProductsUpMaxReportGenerator();
    }
    public TicketReportGenerator getTicketReportGenerator(){
        return new TicketReportGenerator();
    }
    public TestReportGenerator getTestReportGenerator(){
        return new TestReportGenerator();
    }
    public FacReportGenerator getFacReportGenerator(){
        return new FacReportGenerator();
    }
    public RemisionReportGenerator getRemisionReportGenerator(){
        return new RemisionReportGenerator();
    }
    public CancelSaleReportGenerator getCancelSaleReportGenerator(){
        return new CancelSaleReportGenerator();
    }
    public WarehousesReportGenerator getWarehousesReportGenerator(){
        return new WarehousesReportGenerator();
    }
    public CustomerReportGenerator getCustomerReportGenerator(){
        return new CustomerReportGenerator();
    }
    public TickPagoReportGenerator getTickPagoReportGenerator(){
        return new TickPagoReportGenerator();
    }        
}
