/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports;

import com.era.easyretail.era_jasperreports.models.BaseReport;
import com.era.easyretail.era_jasperreports.models.GenerateProperties;
import com.era.models.BasDats;
import com.era.models.User;
import com.era.utilities.UtilitiesFactory;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author PC
 */
public abstract class BaseReportGenerator {
    
    protected boolean localCompanyParams;
    
    protected BaseReport BaseReport;
    
    public abstract Map<String,String> getMap();
    public abstract String getReportName();
    public abstract Connection getConnection() throws Exception;

    public void setBaseReport(BaseReport BaseReport) {
        this.BaseReport = BaseReport;
    }        
    
    final public void generate(final GenerateProperties GenerateProperties) throws Exception{
                
        final String report = getReportName() + ".jrxml";        
        final InputStream reportStream = BaseReport.class.getClassLoader().getResourceAsStream(report);
        JasperReport ja = JasperCompileManager.compileReport(reportStream);
        
        Map <String,String> params = new HashMap<>();
        
        if(localCompanyParams){
        
            final BasDats BasDats = UtilitiesFactory.getSingleton().getSessionUtility().getBasDats();
            
            String localCompany = BasDats.getNom();
            String localPhone = BasDats.getTel();
            String localColony = BasDats.getCol();
            String localStreet = BasDats.getCalle();
            String localCP = BasDats.getCP();
            String localCity = BasDats.getCiu();
            String localEstate = BasDats.getEstad();
            String localCountry = BasDats.getPai();
            String localRFC = BasDats.getRFC();
            String localInternalNumber = BasDats.getNoint();
            String localExternalNumber = BasDats.getNoext();
            
            params.put("EMPLOC", localCompany);
            params.put("TELLOC", localPhone);
            params.put("COLLOC", localColony);
            params.put("CALLLOC", localStreet);
            params.put("NOINTLOC", localInternalNumber);
            params.put("NOEXTLOC", localExternalNumber);        
            params.put("CPLOC", localCP);
            params.put("CIULOC", localCity);
            params.put("ESTADLOC", localEstate);
            params.put("PAILOC", localCountry);
            params.put("RFCLOC", localRFC);
            params.put("LUGEXP", BasDats.getLugexp());
            params.put("REGFIS", BasDats.getRegfisc());
            params.put("MAILLOC", BasDats.getCorr());
        }
        
        final User User = UtilitiesFactory.getSingleton().getSessionUtility().getUser();
        
        params.put("USER", User.getCode());
        
        //Logo        
        //params.put("LOGNOM", TicketReportModel);
        
        JasperPrint pr = JasperFillManager.fillReport(ja, (Map)getMap(), getConnection());
        
        //If has to show the PDF file
        if(GenerateProperties.isShow()){
                        
            JasperViewer v  = new JasperViewer(pr, false);
            v.setExtendedState(JasperViewer.MAXIMIZED_BOTH);                    
            v.setVisible(true);
        }
        
        //If has to print the PDF file
        if(GenerateProperties.isPrint()){
            JasperPrintManager.printReport(pr,GenerateProperties.isShowPrintingDialog());
        }
        
        //If has to export the PDF file
        if(GenerateProperties.isExportToPDF()){
            JasperExportManager.exportReportToPdfFile(pr, GenerateProperties.getPdfExportPath() + "\\" + GenerateProperties.getPdfFileName() + ".pdf");
        }
    }
}
