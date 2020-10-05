/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports;

import com.era.easyretail.era_jasperreports.models.CancelSaleReportModel;
import com.era.repositories.utils.HibernateUtil;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class CancelSaleReportGenerator extends BaseReportGenerator {

    @Override
    public String getReportName(){
        return "rptCanVta";
    }        
    
    @Override
    public Map<String, String> getMap() {
        
        //Cast model
        final CancelSaleReportModel CancelSaleReportModel = (CancelSaleReportModel)BaseReport;
                
        Map <String,String> params = new HashMap<>();                
        params.put("VTA", CancelSaleReportModel.getVta());
        params.put("FOL", CancelSaleReportModel.getReferenceNumber());
        params.put("NOSER", CancelSaleReportModel.getSerie());
        params.put("TIPDOC", CancelSaleReportModel.getDocumentType());
        params.put("EMP", CancelSaleReportModel.getCompanyCode());        
        params.put("NOM", CancelSaleReportModel.getCompanyName());
        params.put("FEMI", CancelSaleReportModel.getEmisionDate());
        params.put("FCAN", CancelSaleReportModel.getCancelDate());
        params.put("SUBTOT", CancelSaleReportModel.getSubtotal());
        params.put("IMPUE", CancelSaleReportModel.getTaxes());
        params.put("TOT", CancelSaleReportModel.getTotal());
        params.put("TIT", CancelSaleReportModel.getTitle());
        
        return params;
    }

    @Override
    public Connection getConnection() throws Exception {
        return HibernateUtil.getSingleton().getLocalJDBCConnection();
    }
}