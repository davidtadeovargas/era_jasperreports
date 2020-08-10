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
    
    public TicketReportGenerator getTicketReportGenerator(){
        return new TicketReportGenerator();
    }
}
