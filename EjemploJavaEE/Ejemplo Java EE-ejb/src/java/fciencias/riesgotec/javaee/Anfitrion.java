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
    
    /**
     * Cadena a manipular al atender la petici&oacute;n.
     * @since Anfitrion 1.0, february 2016.
     */
    private int contador;
    private String aManipular;
    private int cantidad;
    private int bruto;
    private String fecha;
    // métodos de acceso
        
    public String getAManipular() {
        return aManipular;
    }//getaManipular

     public String getFecha() {
        return fecha;
    }   
    
    public int getCantidad() {
       return cantidad;
    }
    
    public int getBruto() {
        return bruto;
    }

    public int getContador() {
        return contador;
    }
    
    public void setAManipular(String aManipular) {
        this.aManipular = aManipular;
    }//setaManipular
    
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    public void setBruto(int bruto){
        this.bruto = cantidad;
    }
    
    // métodos de implementación
    
    
    /**
     * M&eacute;todo que ejemplifica el atender la petici&oacute;n de un
     * cliente.
     * @param nombre
     * @param bruto
     * @param cantidad
     * @param fecha
     * @throws IOException - Si hay un problema al abrir el archivo     
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     * @since Anfitrion 1.0, february 2016.
     * <tt>index.xhtml</tt> en el proyecto WAR.
     */
    public void atiende(String nombre,int bruto,int cantidad,String fecha) throws IOException, SQLException, ClassNotFoundException {
        
        contador++;
        this.aManipular = nombre;
        this.fecha = fecha;
        this.bruto = cantidad;
        this.cantidad = (int) ((int) cantidad - (cantidad*0.16));
        
        //Insertar en la base de datos los datos obtenidos
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Impuestos", "postgres", "12345");
        Statement stmt = con.createStatement();        
        String query = "INSERT INTO ventas VALUES"
                + "('2015-10-10','" +aManipular+ "', 666, 16, 600);";
        stmt.executeQuery(query); //Insertando en la base de datos
                
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("index.xhtml");
    }//atiende
    
}//Anfitrión Bean
