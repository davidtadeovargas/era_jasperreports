/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports;

import com.era.easyretail.era_jasperreports.models.FacReportModel;
import com.era.repositories.utils.HibernateUtil;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class FacReportGenerator extends BaseReportGenerator {

    @Override
    public String getReportName(){
        return "rptFac_2D";
    }        
    
    @Override
    public Map<String, String> getMap() {
        
        //Cast model
        final FacReportModel facReportModel = (FacReportModel)BaseReport;
        
        //47
        Map <String,String> params = new HashMap<>();                
        params.put("CONSFAC", facReportModel.getConsfac());
        params.put("OBSERVACIONES", facReportModel.getObservaciones());
        params.put("VTA", facReportModel.getVta());
        params.put("SER", facReportModel.getSer());
        params.put("FDOC", facReportModel.getFdoc());
        params.put("NOMEMP", facReportModel.getNomemp());
        params.put("TEL", facReportModel.getTel());
        params.put("CALL", facReportModel.getCall());
        params.put("COL", facReportModel.getCol());
        params.put("CP", facReportModel.getCp());
        params.put("NOEXT", facReportModel.getNoext());
        params.put("NOINT", facReportModel.getNoint());
        params.put("PAI", facReportModel.getPai());
        params.put("CIU", facReportModel.getCiu());
        params.put("MON", facReportModel.getMon());
        params.put("ESTAD", facReportModel.getEstad());
        params.put("RFC", facReportModel.getRfc());
        params.put("CORR", facReportModel.getCorr());
        params.put("IMPLET", facReportModel.getImplet());
        params.put("SUBTOT", facReportModel.getSubtotal());
        params.put("IMPUE", facReportModel.getImpue());
        params.put("TOT", facReportModel.getTot());
        params.put("METPAG", facReportModel.getMetopag());
        params.put("CTA", facReportModel.getCta());
        params.put("FORMPAG", facReportModel.getFormpag());        
        params.put("DOMENT", facReportModel.getDoment());
        params.put("SELL", facReportModel.getSell());
        params.put("SELLSAT", facReportModel.getSellsat());
        params.put("CADORI", facReportModel.getCadori());
        params.put("FOLFISC", facReportModel.getFolfisc());
        params.put("LUGEXP", facReportModel.getLugexp());
        params.put("REGFIS", facReportModel.getRegfis());
        params.put("CTAPRED", facReportModel.getCtapred());
        params.put("CERTSAT", facReportModel.getCertsat());
        params.put("VENDEDOR", facReportModel.getVendedor());
        
        return params;
    }

    @Override
    public Connection getConnection() throws Exception {
        return HibernateUtil.getSingleton().getLocalJDBCConnection();
    }
}