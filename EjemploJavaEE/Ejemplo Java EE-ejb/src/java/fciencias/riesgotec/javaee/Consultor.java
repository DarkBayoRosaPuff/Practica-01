package fciencias.riesgotec.javaee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author nachintoch
 */
@ApplicationScoped
public class Consultor {
    
    public ObjetoBD[] consultar() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Impuestos", "postgres", "12345");
        Statement stmt = con.createStatement();
        String query = "SELECT * FROM Ventas;";
        ResultSet resultados = stmt.executeQuery(query);
        LinkedList<ObjetoBD> salida = new LinkedList<>();
        while(resultados.next()) {
            salida.add(new ObjetoBD(resultados.getInt("id"),
                    resultados.getString("value")));
        }
        return salida.toArray(new ObjetoBD[0]);
    }
    
}//Consultor