/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.easyretail.era_jasperreports.models;

import com.era.models.Sales;

/**
 *
 * @author PC
 */
public class TicketReportModel extends BaseReport {
    
    private Sales Sales;

    
    
    public Sales getSales() {
        return Sales;
    }

    public void setSales(Sales Sales) {
        this.Sales = Sales;
    }
}
