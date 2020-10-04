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
        params.put("TEL", facReportModel.getTel()==null?"":facReportModel.getTel());
        params.put("CALL", facReportModel.getCall()==null?"":facReportModel.getCall());
        params.put("COL", facReportModel.getCol()==null?"":facReportModel.getCol());
        params.put("CP", facReportModel.getCp());
        params.put("NOEXT", facReportModel.getNoext()==null?"":facReportModel.getNoext());
        params.put("NOINT", facReportModel.getNoint()==null?"":facReportModel.getNoint());
        params.put("PAI", facReportModel.getPai()==null?"":facReportModel.getPai());
        params.put("CIU", facReportModel.getCiu()==null?"":facReportModel.getCiu());
        params.put("MON", facReportModel.getMon());
        params.put("ESTAD", facReportModel.getEstad()==null?"":facReportModel.getEstad());
        params.put("RFC", facReportModel.getRfc());
        params.put("CORR", facReportModel.getCorr()==null?"":facReportModel.getCorr());
        params.put("IMPLET", facReportModel.getImportWords());
        params.put("SUBTOT", facReportModel.getSubtotal());
        params.put("IMPUE", facReportModel.getImpue());
        params.put("TOT_RETEN", facReportModel.getTotalRetention());
        params.put("DESCU", facReportModel.getDescu());
        params.put("TOT", facReportModel.getTot());
        params.put("METPAG", facReportModel.getMetopag());
        params.put("CTA", facReportModel.getCta());
        params.put("FORMPAG", facReportModel.getFormpag());        
        params.put("DOMENT", facReportModel.getDoment());
        params.put("SELL", "fG7oFwSA9mIunoO2L6Rn1TexjfJljwuAD1VNq2WY04J85xt/VF1b57BvTAD5C3uI85VVPzFMuFGg8urlDHPWUHHOQ47Q\n" +
"0+wE69+Tf0o4T0baFQTeXH94ntP+0TpqhD7CymLojMyu4T0czYqZK08T+mdokPK9n3+zW4kxhjrtisdMDBp2Lo1QgHBx\n" +
"fi0Qyc9UrUA5GueX+LEUk+bXr2knG+Ho2i31jC2lb3v6oyNkiQ7Y9pG/4KQud3aTM5b/SMwL+ca/MFdLi7fMKS6H6XsB\n" +
"2jTCaFLTItDXUhHYVjI5zXMX41Brtyw4P4sA322x/QSrvuoS3RPdLeTuadWy8whOUw=="); //facReportModel.getSell()
        params.put("SELLSAT", "p7hl2xt2i5M8ZxMuJbiPmX13Ztm0Cn7NzGIz7KHz4eypxHomubDoOIaowTtunleJ5QQQDCZvYoKu6rEFCsszqh9XweR6\n" +
"k6FmDREI9Mvla9TqC8jK/Wnr6RbP3R7A7N8q+Ap1CiCN3awmecGspHyX03bNRxGZLYKibF9CxklFYZfDUdoguv4w2zoA\n" +
"Ddt97obdiITQ212K1/m+/JyQb+XM+UH7G1YVHLYnsATGIVvOAXYVPYuXWRw91zqTRaPmZGtFEmFuR+wqO9gbCeXT9Poo\n" +
"NSokwItYSp+IdR5bMAXi/PM6ssBPBrWsDYHBthHw9p+t4I9+sAgLZMAafUG8La4k2Q=="); //facReportModel.getSellsat()
        params.put("CADORI", "||1.0|81a313e0-ded9-417d-bd0a-7443aecc1c79|2017-07-08T11:59:19|fG7oFwSA9mIunoO2L6Rn1TexjfJlj\n" +
"wuAD1VNq2WY04J85xt/VF1b57BvTAD5C3uI85VVPzFMuFGg8urlDHPWUHHOQ47Q0+wE69+Tf0o4T0baFQTeXH94ntP+0\n" +
"TpqhD7CymLojMyu4T0czYqZK08T+mdokPK9n3+zW4kxhjrtisdMDBp2Lo1QgHBxfi0Qyc9UrUA5GueX+LEUk+bXr2knG\n" +
"+Ho2i31jC2lb3v6oyNkiQ7Y9pG/4KQud3aTM5b/SMwL+ca/MFdLi7fMKS6H6XsB2jTCaFLTItDXUhHYVjI5zXMX41Brt\n" +
"yw4P4sA322x/QSrvuoS3RPdLeTuadWy8whOUw==|00001000000404614920||"); //facReportModel.getCadori()
        params.put("FOLFISC", "81a313e0-ded9-417d-bd0a-7443aecc1c79"); //facReportModel.getFolfisc()
        params.put("LUGEXP", facReportModel.getLugexp());
        params.put("REGFIS", facReportModel.getRegfis());
        params.put("CTAPRED", facReportModel.getCtapred()==null?"":facReportModel.getCtapred());
        params.put("CERTSAT", "00001000000404614920"); //facReportModel.getCertsat()
        params.put("VENDEDOR", facReportModel.getVendedor());
        params.put("USO_CFDI", facReportModel.getUsoCfdi()==null?"":facReportModel.getUsoCfdi());
        params.put("QR", facReportModel.getQr());
        
        return params;
    }

    @Override
    public Connection getConnection() throws Exception {
        return HibernateUtil.getSingleton().getLocalJDBCConnection();
    }
}