/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports;

import com.era.easyretail.era_jasperreports.models.TicketReportModel;
import com.era.repositories.utils.HibernateUtil;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class TicketReportGenerator extends BaseReportGenerator {

    @Override
    public String getReportName(){
        return "rptTickVta";
    }        
    
    @Override
    public Map<String, String> getMap() {
        
        //Cast model
        final TicketReportModel TicketReportModel = (TicketReportModel)BaseReport;
        
        Map <String,String> params = new HashMap<>();
        
        params.put("CODTIK", TicketReportModel.getConsecutive());
        params.put("VTA", TicketReportModel.getSaleID());
        params.put("SUBTOT", TicketReportModel.getSubtotal());
        params.put("IMPUE", TicketReportModel.getTax());
        params.put("FDOC", TicketReportModel.getDocumentDate());
        params.put("NOMEMP", TicketReportModel.getCompanyName());
        params.put("TEL", TicketReportModel.getPhone());
        params.put("CALL", TicketReportModel.getStreet());
        params.put("COL", TicketReportModel.getColony());
        params.put("CP", TicketReportModel.getPostalCode());
        params.put("NOEXT", TicketReportModel.getExternalNumber());
        params.put("NOINT", TicketReportModel.getInternalNumber());
        params.put("MON", TicketReportModel.getCoin());
        params.put("CIU", TicketReportModel.getCity());
        params.put("ESTAD", TicketReportModel.getEstate());
        params.put("RFC", TicketReportModel.getRFC());
        params.put("IMPLET", TicketReportModel.getImportWords());
        params.put("TOT", TicketReportModel.getTotal());        
        params.put("WEB", TicketReportModel.getWebPage());

        return params;
    }

    @Override
    public Connection getConnection() throws Exception {
        return HibernateUtil.getSingleton().getLocalJDBCConnection();
    }
}