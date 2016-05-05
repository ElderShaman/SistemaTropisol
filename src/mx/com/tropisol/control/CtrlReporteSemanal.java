/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.tropisol.control;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mx.com.tropisol.modelo.ReporteSemanal;
import mx.com.tropisol.configuracion.ConfiguracionBD;
/**
 *
 * @author Rodrigo
 */
public class CtrlReporteSemanal {
      public boolean AgregarReporteSemanal(ReporteSemanal rs){
        boolean respuesta=false;
    
            try {
             ConfiguracionBD base = new ConfiguracionBD();
             CallableStatement procedimiento = null;
             procedimiento = 
             base.getConexion().prepareCall("insert into reportesemanal(semana, periodo, "
                     + "fecharecepcion, folioinsp, descripcionmaterial,"
                     + "medidacolortamaño, proveedor, facturafolio,"
                     + "cantidadrecibida, cantidadinspeccionada,"
                     + "porcentaje, problema, "
                     + "resultadoaprobadorechazado,"
                     + "resumenauditoria, observaciones) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
             procedimiento.setString(1,rs.getSemana());
             procedimiento.setString(2,rs.getPeriodo());
             procedimiento.setString(3,rs.getFecharecepcion());
             procedimiento.setString(4,rs.getFolioinsp());
             procedimiento.setString(5,rs.getDescripcionmaterial());
             procedimiento.setString(6,rs.getMedidacolortamaño());
             procedimiento.setString(7,rs.getProveedor());
             procedimiento.setString(8,rs.getFacturafolio());
             procedimiento.setString(9,rs.getCantidadrecibida());
             procedimiento.setString(10,rs.getCantidadinspeccionada());
             procedimiento.setString(11,rs.getPorcentaje());
             procedimiento.setString(12,rs.getProblema());
             procedimiento.setString(13,rs.getResultadoaprobadorechazado());
             procedimiento.setString(14,rs.getResumenauditoria());
             procedimiento.setString(15,rs.getObservaciones());
             
             int va1= procedimiento.executeUpdate();
             if(va1>0){
                 base.getConexion().commit();
                 respuesta= true;
                 
             JOptionPane.showMessageDialog(null, "Se agrego correctamente");
             }
             else
            {
                
            JOptionPane.showMessageDialog(null, "A Ocurrido Un Error");
                base.getConexion().rollback();
            }
            
        }
     catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog(null, "A Ocurrido Un Error");
            System.out.println(ex.getMessage());
            respuesta= false;
        }
        finally
        {
            
        }
         return respuesta;
    }
      
      public List<ReporteSemanal> ConsultarPorSemana(String semana) {
          List<ReporteSemanal> reportes= new ArrayList<>();

        try {

            ConfiguracionBD base = new ConfiguracionBD();
            CallableStatement procedimiento = null;
            procedimiento = base.getConexion().prepareCall("select * from reportesemanal where semana =?;");     
            procedimiento.setString(1, semana);
            ResultSet va1 = procedimiento.executeQuery();
            while (va1.next()) {
              ReporteSemanal report= new ReporteSemanal();
              report.setIdReportesemnal(va1.getInt(1));
              report.setCantidadinspeccionada(va1.getString(11));
              report.setCantidadrecibida(va1.getString(10));              
              report.setDescripcionmaterial(va1.getString(6));  
              report.setFacturafolio(va1.getString(9));
              report.setFecharecepcion(va1.getString(4));              
              report.setFolioinsp(va1.getString(5));  
              report.setMedidacolortamaño(va1.getString(7));
              report.setObservaciones(va1.getString(16));              
              report.setPeriodo(va1.getString(3));                
              report.setPorcentaje(va1.getString(12));
              report.setProblema(va1.getString(13));              
              report.setProveedor(va1.getString(8));  
              report.setResultadoaprobadorechazado(va1.getString(14));
              report.setResumenauditoria(va1.getString(15));                 
              report.setSemana(va1.getString(2));   
              
              reportes.add(report);
            } 
            base.getConexion().commit();
            base.getConexion().rollback();
              return reportes;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se encuentra ningun reporte de esa semana");
            System.out.println(ex.getMessage());
        } finally {

        }
        return reportes;
    }

         public boolean EliminarReporteSemanal(int id){
        boolean respuesta=false;
    
            try {
             ConfiguracionBD base = new ConfiguracionBD();
             CallableStatement procedimiento = null;
                procedimiento = base.getConexion().prepareCall("delete from reportesemanal where idReportesemnal=?;");
            procedimiento.setInt(1, id);
            int va1 = procedimiento.executeUpdate();
            if (va1 > 0) {
                base.getConexion().commit();
                JOptionPane.showMessageDialog(null, "Se Elimino Correctamente");
                respuesta = true;
            } else {
                base.getConexion().rollback();
            }
            
        }
     catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog(null, "A Ocurrido Un Error");
            System.out.println(ex.getMessage());
            respuesta= false;
        }
        finally
        {
            
        }
         return respuesta;
    }
         
       public boolean ModificarReporteSemanal(ReporteSemanal rs){
        boolean respuesta=false;
    
            try {
             ConfiguracionBD base = new ConfiguracionBD();
             CallableStatement procedimiento = null;
             procedimiento = 
             base.getConexion().prepareCall("update reportesemanal set semana=?, periodo=?, "
                     + "fecharecepcion=?, folioinsp=?, descripcionmaterial=?,"
                     + "medidacolortamaño=?, proveedor=?, facturafolio=?,"
                     + "cantidadrecibida=?, cantidadinspeccionada=?,"
                     + "porcentaje=?, problema=?, "
                     + "resultadoaprobadorechazado=?,"
                     + "resumenauditoria=?, observaciones=? where idReportesemnal=?;");
             procedimiento.setString(1,rs.getSemana());
             procedimiento.setString(2,rs.getPeriodo());
             procedimiento.setString(3,rs.getFecharecepcion());
             procedimiento.setString(4,rs.getFolioinsp());
             procedimiento.setString(5,rs.getDescripcionmaterial());
             procedimiento.setString(6,rs.getMedidacolortamaño());
             procedimiento.setString(7,rs.getProveedor());
             procedimiento.setString(8,rs.getFacturafolio());
             procedimiento.setString(9,rs.getCantidadrecibida());
             procedimiento.setString(10,rs.getCantidadinspeccionada());
             procedimiento.setString(11,rs.getPorcentaje());
             procedimiento.setString(12,rs.getProblema());
             procedimiento.setString(13,rs.getResultadoaprobadorechazado());
             procedimiento.setString(14,rs.getResumenauditoria());
             procedimiento.setString(15,rs.getObservaciones());             
             procedimiento.setInt(16,rs.getIdReportesemnal());
             
             int va1= procedimiento.executeUpdate();
             if(va1>0){
                 base.getConexion().commit();
                 respuesta= true;
                 
             JOptionPane.showMessageDialog(null, "Se modifico correctamente");
             }
             else
            {
                
            JOptionPane.showMessageDialog(null, "A Ocurrido Un Error");
                base.getConexion().rollback();
            }
            
        }
     catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog(null, "A Ocurrido Un Error");
            System.out.println(ex.getMessage());
            respuesta= false;
        }
        finally
        {
            
        }
         return respuesta;
    }
}
