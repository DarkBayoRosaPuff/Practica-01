package fciencias.riesgotec.javaee;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;

/**
 * Dibuja una gr&aacute;fica y la guarda en un PNG en el directorio <tt>img</tt>
 * del server.
 * @author <a href="mailto:manuel_castillo_cc@ciencias.unam.mx" >Manuel,
 * "Nachintoch", Castillo</a>
 * @version 1.0, february 2016
 * @since Riesgo Tecnol&oacute;gico, february 2016
 */
@ApplicationScoped
public class Pintor {
    
    private String pronostico;

    public String getPronostico() {
        return pronostico;
    }

    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }
    
    /**
     * Pinta una gr&aacute;fica en un PNG llamado <tt>"grafica.png"</tt>.
     * @throws IOException - Si ocurre un problema al crear el archivo PNG.
     * @since Pintor 1.0, february 2016
     */
    public void paintGraph() throws IOException {
        
        // creamos un buffer de imagen con dimenciones y propiedades dadas
        BufferedImage graf =
                new BufferedImage(1000, 500, BufferedImage.TYPE_4BYTE_ABGR);
        
        // Creamos el archivo donde vamos aguardar la imagen
        // (Modificar de acuerdo a la ruta actual)
        Path ruta = Paths.get("C:","Users", "Kikinzco","Desktop","Escuela","Semestre 6","Riesgo",
                "Practica 1", "Practica-01","EjemploJavaEE","EjemploJavaEE-war","build","web","img",
                "grafica.png");                
        File png = ruta.toFile();
        png.mkdirs();
        png.createNewFile();
        
        // recuperamos los gráficos para empezar a dibujar
        Graphics2D g = graf.createGraphics();
        
        // asignamos un color para el dibujado
        g.setColor(Color.black);
        // dibujamos los ejes
        g.drawLine(50, 450, 50, 50);
        g.drawLine(50, 450, 750, 450);
        // asignamos una fuente para el texto
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        // dibujamos una cadena en la posición dada
        g.drawString("Tiempo", 380, 480);
        // respaldamos la configuración original del plano donde trabajamos
        AffineTransform original = g.getTransform();
        // trasladamos el plano
        g.translate(0, 280);
        // rotamos 90 grados en sentido anti-horario.
        g.rotate(-Math.PI /2);
        g.drawString("Ventas", 0, 25);
        // restauramos la configuración original del plano
        g.setTransform(original);
        // dibujamos el perímetro de un rectángulo
        g.drawRect(760, 100, 190, 100);
        
        // cambiamos el color de dibujado
        g.setColor(Color.red);
        // dibujamos el área de un rectángulo
        g.fillRect(810, 110, 20, 20);
        
        g.setColor(Color.green);
        g.fillRect(810, 140, 20, 20);
        
        g.setColor(Color.blue);
        g.fillRect(810, 170, 20, 20);
        
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Brutas", 850, 125);
        g.drawString("Impuestos", 850, 155);
        g.drawString("Netas", 850, 185);
        //Obteniendo registros de la base de datos
        Consultor consultorBase = new Consultor();
        ObjetoBD[] tuplas = new ObjetoBD[1];
        try{tuplas = consultorBase.consultar();
        }catch(SQLException | ClassNotFoundException e){System.err.println(e);}
        int nTuplas = tuplas.length;
        //Parametros para ubicacion de las graficas
        double reglaX = 700/nTuplas;
        double xN, yN;
        //Brutas
        double x1 = 50;
        double y1 = 450;
        g.setColor(Color.red);
        for(int i = 0; i < nTuplas; i++){
            xN = x1+reglaX;
            double altura = Double.parseDouble(tuplas[i].getVentaBruta());
            yN = 450 - altura/60;
            g.draw(new Line2D.Double(x1,y1,xN,yN));
            x1 = xN;
            y1 = yN;
        }
        //Impuestos
        x1 = 50;
        y1 = 450;
        g.setColor(Color.green);
        for(int i = 0; i < nTuplas; i++){
            xN = x1+reglaX;
            double altura = Double.parseDouble(tuplas[i].getIVA());
            yN = 450 - altura/60;
            g.draw(new Line2D.Double(x1,y1,xN,yN));
            x1 = xN;
            y1 = yN;
        }
        //Netas
        x1 = 50;
        y1 = 450;
        g.setColor(Color.blue);
        for(int i = 0; i < nTuplas; i++){
            xN = x1+reglaX;
            double altura = Double.parseDouble(tuplas[i].getVentaNeta());
            yN = 450 - altura/60;
            g.draw(new Line2D.Double(x1,y1,xN,yN));
            x1 = xN;
            y1 = yN;
        }
        
        g.dispose();        
        ImageIO.write(graf, "PNG", png);
        double gananciaAnterior = Double.parseDouble(tuplas[nTuplas-2].getVentaNeta());
        double gananciaActual = Double.parseDouble(tuplas[nTuplas-1].getVentaNeta());
        if(gananciaActual > gananciaAnterior)
            pronostico = "Las ventas han mejorado c:";
        else
            pronostico = "Las ventas han empeorado :c";
    }//paintGraph
    
}//clase Pintor
