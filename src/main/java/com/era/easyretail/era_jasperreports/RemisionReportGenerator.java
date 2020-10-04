/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports;

import com.era.easyretail.era_jasperreports.models.RemReportModel;
import com.era.repositories.utils.HibernateUtil;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class RemisionReportGenerator extends BaseReportGenerator {

    @Override
    public String getReportName(){
        return "rptRem";
    }        
    
    @Override
    public Map<String, String> getMap() {
        
        //Cast model
        final RemReportModel RemReportModel = (RemReportModel)BaseReport;
        
        //47
        Map <String,String> params = new HashMap<>();
        params.put("OBSERVACIONES", RemReportModel.getObservaciones());
        params.put("VTA", RemReportModel.getVta());
        params.put("SER", RemReportModel.getSer());
        params.put("FDOC", RemReportModel.getFdoc());
        params.put("NOMEMP", RemReportModel.getNomemp());
        params.put("TEL", RemReportModel.getTel()==null?"":RemReportModel.getTel());
        params.put("CALL", RemReportModel.getCall()==null?"":RemReportModel.getCall());
        params.put("COL", RemReportModel.getCol()==null?"":RemReportModel.getCol());
        params.put("CP", RemReportModel.getCp()==null?"":RemReportModel.getCp());
        params.put("NOEXT", RemReportModel.getNoext()==null?"":RemReportModel.getNoext());
        params.put("NOINT", RemReportModel.getNoint()==null?"":RemReportModel.getNoint());
        params.put("PAI", RemReportModel.getPai()==null?"":RemReportModel.getPai());
        params.put("CIU", RemReportModel.getCiu()==null?"":RemReportModel.getCiu());
        params.put("MON", RemReportModel.getMon());
        params.put("ESTAD", RemReportModel.getEstad()==null?"":RemReportModel.getEstad());
        params.put("RFC", RemReportModel.getRfc());
        params.put("CORR", RemReportModel.getCorr()==null?"":RemReportModel.getCorr());
        params.put("IMPLET", RemReportModel.getImportWords());
        params.put("SUBTOT", RemReportModel.getSubtotal());
        params.put("IMPUE", RemReportModel.getImpue());        
        params.put("DESCU", RemReportModel.getDescu());
        params.put("TOT", RemReportModel.getTot());
        params.put("METPAG", RemReportModel.getMetopag());
        params.put("CATGRAL", "");
        params.put("FORMPAG", RemReportModel.getFormpag());        
        params.put("DOMENT", RemReportModel.getDoment());                                        
        params.put("VENDEDOR", RemReportModel.getVendedor());
        
        return params;
    }

    @Override
    public Connection getConnection() throws Exception {
        return HibernateUtil.getSingleton().getLocalJDBCConnection();
    }
}