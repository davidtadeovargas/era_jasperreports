/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports;

import com.era.easyretail.era_jasperreports.models.TickPagoReportModel;
import com.era.repositories.utils.HibernateUtil;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class TickPagoReportGenerator extends BaseReportGenerator {

    @Override
    public String getReportName(){
        return "rptTickPago";
    }        
    
    @Override
    public Map<String, String> getMap() {
        
        //Cast model
        final TickPagoReportModel TickPagoReportModel = (TickPagoReportModel)BaseReport;
                
        Map <String,String> params = new HashMap<>();                
        params.put("fecha_documento", TickPagoReportModel.getDocumentDate());
        params.put("FOLIO", TickPagoReportModel.getFolio());
        params.put("SERIE", TickPagoReportModel.getSerie());
        params.put("TOTAL", TickPagoReportModel.getTotal());
        params.put("PENDIENTE", TickPagoReportModel.getPending());
        
        return params;
    }

    @Override
    public Connection getConnection() throws Exception {
        return HibernateUtil.getSingleton().getLocalJDBCConnection();
    }
}