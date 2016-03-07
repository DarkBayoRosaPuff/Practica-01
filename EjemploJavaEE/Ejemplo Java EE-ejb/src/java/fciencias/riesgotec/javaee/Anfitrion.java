package fciencias.riesgotec.javaee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
/**
 * Ejemplo de c&oacute;mo crear un EJB mediante POJO para crear aplicaciones web
 * con Java.
 * @version 1.0, february 2016
 * @since Riesgo Tecnol&oacute;gico 2016-2
 */
@ApplicationScoped
public class Anfitrion {
        
    private String fecha;
    private String AManipular;
    private double bruto;
    private double IVA;
    private double cantidad;
    
     public String getFecha() {
        return fecha;
    }   
     
    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public String getAManipular() {
        return AManipular;
    }

    public void setAManipular(String aManipular) {
        this.AManipular = aManipular;
    }    
        
    public double getBruto() {
        return bruto;
    }
    
    public void setBruto(int bruto){
        this.bruto = cantidad;
    }
    
    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }
    
    public double getCantidad() {
       return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    /**
     * M&eacute;todo que ejemplifica el atender la petici&oacute;n de un
     * cliente.
     * @param nombre
     * @param bru
     * @param fec
     * @throws IOException - Si hay un problema al abrir el archivo     
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     * @since Anfitrion 1.0, february 2016.
     * <tt>index.xhtml</tt> en el proyecto WAR.
     */
    public void atiende(String nombre, double bru, String fec) throws IOException, SQLException, ClassNotFoundException {
        
        this.fecha = fec;
        this.AManipular = nombre;
        this.bruto = bru;
        this.IVA = bruto*.16;
        this.cantidad = bruto-IVA;
        
        //Insertar en la base de datos los datos obtenidos
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Impuestos", "postgres", "12345");
        Statement stmt = con.createStatement();        
        String query = "INSERT INTO ventas VALUES"
                + "('" +fecha+ "', "
                + "'" +AManipular+ "', "
                + bruto +", "
                + IVA + ", "
                + cantidad +");";
        stmt.executeUpdate(query); //Insertando en la base de datos
                
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("index.xhtml");
    }//atiende
    
}//Anfitrión Bean
