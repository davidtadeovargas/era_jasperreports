/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports;

import com.era.easyretail.era_jasperreports.models.CustomerReportModel;
import com.era.repositories.utils.HibernateUtil;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class CustomerReportGenerator extends BaseReportGenerator {

    @Override
    public String getReportName(){
        return "rptEmps";
    }        
    
    @Override
    public Map<String, String> getMap() {
        
        final CustomerReportModel CustomerReportModel = (CustomerReportModel)BaseReport;
        
        Map <String,String> params = new HashMap<>();
        params.put("CLI", CustomerReportModel.getCustomer());
        return params;
    }

    @Override
    public Connection getConnection() throws Exception {
        return HibernateUtil.getSingleton().getLocalJDBCConnection();
    }
}