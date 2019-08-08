package app.com.optracav.clases;

public class Propietario {
    private String nombreCompleto;
    private String rut;
    private String fechaAdquisicion;
    private String repertorio;
    private String numeroRepertorio;
    private String fechaRepertorio;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String personaNatural;


    public boolean isOK(){
        return nombres != null && rut != null && personaNatural != null;
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        String replaced = nombreCompleto.replace("$a","á");
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
        this.nombreCompleto = replaced;
        this.nombreCompleto = this.nombreCompleto.replace("  "," ");
        this.nombreCompleto = this.nombreCompleto.trim();

        String[] nombresApellidos = this.nombreCompleto.split(" ");
        if(nombresApellidos.length == 4){
            this.nombres = nombresApellidos[0]+" "+nombresApellidos[1];
            this.apellidoPaterno = nombresApellidos[2];
            this.apellidoMaterno = nombresApellidos[3];
        }
        else if(nombresApellidos.length == 3){
            this.nombres = nombresApellidos[0]+" "+nombresApellidos[1];
            this.apellidoPaterno = nombresApellidos[2];
            this.apellidoMaterno = "";
        }
        else if(nombresApellidos.length == 2){
            this.nombres = nombresApellidos[0];
            this.apellidoPaterno = nombresApellidos[1];
            this.apellidoMaterno = "";
        }
        else if(nombresApellidos.length == 1){
            this.nombres = nombresApellidos[0];
            this.apellidoPaterno = "";
            this.apellidoMaterno = "";
        }
        else{
            if(nombresApellidos.length > 4){
                this.nombres = nombresApellidos[0] + " " + nombresApellidos[1];
                this.apellidoPaterno = nombresApellidos[2] +  " " + nombresApellidos[3];
                this.apellidoMaterno = "";
                for(int i = 4; i < nombresApellidos.length; i++){
                    this.apellidoMaterno += " " + nombresApellidos[i];
                }
            }
        }
    }

    public void setNombreCompletoEmpresa() {
        String replaced = this.nombreCompleto.replace("$a","á");
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
        this.nombres = replaced.trim();
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut.trim();
    }

    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion.trim();
    }

    public String getRepertorio() {
        return repertorio;
    }

    public void setRepertorio(String repertorio) {
        String replaced = repertorio.replace("$a","á");
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
        this.repertorio = replaced.trim();
    }

    public String getNumeroRepertorio() {
        return numeroRepertorio;
    }

    public void setNumeroRepertorio(String numeroRepertorio) {
        numeroRepertorio = numeroRepertorio.replace("%3","-");
        this.numeroRepertorio = numeroRepertorio.trim();
    }

    public String getFechaRepertorio() {
        return fechaRepertorio;
    }

    public void setFechaRepertorio(String fechaRepertorio) {
        this.fechaRepertorio = fechaRepertorio.trim();
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {

        nombres = nombres.replace("%3","-");
        this.nombres = nombres.trim();
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        apellidoPaterno = apellidoPaterno.replace("%3","-");
        this.apellidoPaterno = apellidoPaterno.trim();
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        apellidoMaterno = apellidoMaterno.replace("%3","-");
        this.apellidoMaterno = apellidoMaterno.trim();
    }

    public String getPersonaNatural() {
        return personaNatural;
    }

    public void setPersonaNatural(String personaNatural) {
        this.personaNatural = personaNatural;
    }
}
