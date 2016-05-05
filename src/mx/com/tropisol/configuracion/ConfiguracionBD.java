/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.tropisol.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rodrigo
 */
public class ConfiguracionBD {
    private String DRIVER =null;
    private String URL = null;
    private String usuario = null;
    private String contrasenia = null;
    private Connection conexion = null;

    public ConfiguracionBD() {
        this.configurar();
    }
    private void configurar(){
        this.DRIVER = "com.mysql.jdbc.Driver";
        this.URL = "jdbc:mysql://localhost:3306/sistemaestadiatropisol";
        this.usuario = "root";
        this.contrasenia = "1234";
    }
   
    public Connection getConexion(){
        try{
            if(this.conexion == null){
                Class.forName(this.DRIVER);
                this.conexion = DriverManager.getConnection(URL,usuario,contrasenia);
                this.conexion.setAutoCommit(false);
            }
            return this.conexion;
        }
        catch(Exception ex){
            System.out.println(ex);
            return null;
        }
        finally{
            System.gc();
        }
    }
    
    public void cerrarConexion(){
        try{
            if(this.conexion != null){
                this.conexion.close();
            }
            else{
                this.conexion=null;
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        finally{
            System.gc();
        }
    }
}
