package fciencias.riesgotec.javaee;

/**
 *
 * @author nachintoch
 */
public class ObjetoBD {

    private String fecha;
    private String capturista;
    private String ventaBruta;
    private String IVA;
    private String ventaNeta;

    public String getVentaNeta() {
        return ventaNeta;
    }

    public void setVentaNeta(String ventaNeta) {
        this.ventaNeta = ventaNeta;
    }

    public String getIVA() {
        return IVA;
    }

    public void setIVA(String IVA) {
        this.IVA = IVA;
    }

    public String getVentaBruta() {
        return ventaBruta;
    }

    public void setVentaBruta(String ventaBruta) {
        this.ventaBruta = ventaBruta;
    }

    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getCapturista() {
        return capturista;
    }

    public void setCapturista(String capturista) {
        this.capturista = capturista;
    }
    
    public ObjetoBD(String fec, String cap, String bru, String iv, String net) {
        this.fecha = fec;
        this.capturista = cap;
        this.ventaBruta = bru;
        this.IVA = iv;
        this.ventaNeta = net;
    }

}
