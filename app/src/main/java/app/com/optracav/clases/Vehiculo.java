package app.com.optracav.clases;

public class Vehiculo {
    private String tipo;
    private String marca;
    private String modelo;
    private String nroMotor;
    private String nroChasis;
    private String nroVin;
    private String color;
    private String combustible;
    private String PBV;
    private String seguroObligatorioVigente;
    private String patente;
    private String agno;

    public boolean isOK(){
        return patente != null && nroChasis != null && nroMotor != null && agno != null;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String replaced = tipo.replace("$a","á");
        replaced = replaced.replace("$e","é");
        replaced = replaced.replace("$i","í");
        replaced = replaced.replace("$o","ó");
        replaced = replaced.replace("$u","ú");
        replaced = replaced.replace("$A","Á");
        replaced = replaced.replace("$E","É");
        replaced = replaced.replace("$I","Í");
        replaced = replaced.replace("$O","Ó");
        replaced = replaced.replace("$U","Ú");
        replaced = replaced.replace("%3","-");
        this.tipo = replaced.trim();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        String replaced = marca.replace("$a","á");
        replaced = replaced.replace("$e","é");
        replaced = replaced.replace("$i","í");
        replaced = replaced.replace("$o","ó");
        replaced = replaced.replace("$u","ú");
        replaced = replaced.replace("$A","Á");
        replaced = replaced.replace("$E","É");
        replaced = replaced.replace("$I","Í");
        replaced = replaced.replace("$O","Ó");
        replaced = replaced.replace("$U","Ú");
        replaced = replaced.replace("%3","-");
        this.marca = replaced.trim();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        String replaced = modelo.replace("$a","á");
        replaced = replaced.replace("$e","é");
        replaced = replaced.replace("$i","í");
        replaced = replaced.replace("$o","ó");
        replaced = replaced.replace("$u","ú");
        replaced = replaced.replace("$A","Á");
        replaced = replaced.replace("$E","É");
        replaced = replaced.replace("$I","Í");
        replaced = replaced.replace("$O","Ó");
        replaced = replaced.replace("$U","Ú");
        replaced = replaced.replace("%3","-");
        this.modelo = replaced.trim();
    }

    public String getNroMotor() {
        return nroMotor;
    }

    public void setNroMotor(String nroMotor) {

        nroMotor = nroMotor.replace("%3","-");
        this.nroMotor = nroMotor.trim();
    }

    public String getNroChasis() {
        return nroChasis;
    }

    public void setNroChasis(String nroChasis) {

        nroChasis = nroChasis.replace("%3","-");
        this.nroChasis = nroChasis.trim();
    }

    public String getNroVin() {
        return nroVin;
    }

    public void setNroVin(String nroVin) {
        nroVin = nroVin.replace("%3","-");
        this.nroVin = nroVin.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        String replaced = color.replace("$a","á");
        replaced = replaced.replace("%3","-");
        replaced = replaced.replace("$e","é");
        replaced = replaced.replace("$i","í");
        replaced = replaced.replace("$o","ó");
        replaced = replaced.replace("$u","ú");
        replaced = replaced.replace("$A","Á");
        replaced = replaced.replace("$E","É");
        replaced = replaced.replace("$I","Í");
        replaced = replaced.replace("$O","Ó");
        replaced = replaced.replace("$U","Ú");
        this.color = replaced.trim();
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        String replaced = combustible.replace("$a","á");
        replaced = replaced.replace("%3","-");
        replaced = replaced.replace("$e","é");
        replaced = replaced.replace("$i","í");
        replaced = replaced.replace("$o","ó");
        replaced = replaced.replace("$u","ú");
        replaced = replaced.replace("$A","Á");
        replaced = replaced.replace("$E","É");
        replaced = replaced.replace("$I","Í");
        replaced = replaced.replace("$O","Ó");
        replaced = replaced.replace("$U","Ú");
        this.combustible = replaced.trim();
    }

    public String getPBV() {
        return PBV;
    }

    public void setPBV(String PBV) {

        PBV = PBV.replace("%3","-");
        this.PBV = PBV.trim();
    }

    public String getSeguroObligatorioVigente() {
        return seguroObligatorioVigente;
    }

    public void setSeguroObligatorioVigente(String seguroObligatorioVigente) {
        seguroObligatorioVigente = seguroObligatorioVigente.replace("%3","-");
        this.seguroObligatorioVigente = seguroObligatorioVigente.trim();
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        patente = patente.replace(".","");
        patente = patente.replace("%3","-");
        this.patente = patente.trim();
    }

    public String getAgno() {
        return agno;
    }

    public void setAgno(String agno) {
        agno = agno.replace("%3","-");
        this.agno = agno.trim();
    }
}
