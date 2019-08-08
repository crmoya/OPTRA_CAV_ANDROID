package app.com.optracav.clases;

public class CAVObject {

    private Vehiculo vehiculo;
    private Propietario propietario;
    private String fecha;
    private int prenda;
    private int multas;


    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public String getFecha() {
        return fecha.trim();
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPrenda() {
        return prenda;
    }

    public void setPrenda(int prenda) {
        this.prenda = prenda;
    }

    public int getMultas() {
        return multas;
    }

    public void setMultas(int multas) {
        this.multas = multas;
    }

}
