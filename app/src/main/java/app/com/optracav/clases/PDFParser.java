package app.com.optracav.clases;

public class PDFParser {
    public CAVObject parse(String pdf417){

        Propietario propietario = new Propietario();
        Vehiculo vehiculo = new Vehiculo();
        CAVObject cavObject = new CAVObject();

        String[] partes = pdf417.split("\\$C");

        for(int i=0;i<partes.length;i++){
            ///////////////////// DATOS DEL VEHICULO /////////////////////
            if(partes[i].contains("Inscripci$on")){
                String[] vPatenteToda = partes[i].split("%\\.");
                String patente = vPatenteToda[1];
                patente = patente.replace("%3", "-");
                String[] vPatente = patente.split("-");
                vehiculo.setPatente(vPatente[0]);
            }
            // TIPO DE VEHICULO Y AÑO
            if (partes[i].contains("Tipo Veh$iculo")){
                String []vTipoAgnoArr = partes[i].split("%\\.");
                String[] vTipoArr = vTipoAgnoArr[1].split("A%no");
                vehiculo.setTipo(vTipoArr[0]);
                vehiculo.setAgno(vTipoAgnoArr[2]);
            }
            // MARCA
            if (partes[i].contains("Marca")){
                String[] vMarcaArr = partes[i].split("%\\.");
                vehiculo.setMarca(vMarcaArr[1]);
            }
            // Modelo
            if (partes[i].contains("Modelo")){
                String[] vModeloArr = partes[i].split("%\\.");
                vehiculo.setModelo(vModeloArr[1]);
            }
            // Numero Motor
            if (partes[i].contains("Nro. Motor")){
                String []vMotorArr = partes[i].split("%\\.");
                vehiculo.setNroMotor(vMotorArr[1]);
            }
            // Numero Chasis
            if (partes[i].contains("Nro. Chasis")){
                String[] vChasisArr = partes[i].split("%\\.");
                vehiculo.setNroChasis(vChasisArr[1]);
            }
            // Numero Vin
            if (partes[i].contains("Nro. Vin")){
                String[] vVinArr = partes[i].split("%\\.");
                vehiculo.setNroVin(vVinArr[1]);
            }
            // Color
            if (partes[i].contains("Color")){
                String[] vColorArr = partes[i].split("%\\.");
                vehiculo.setColor(vColorArr[1]);
            }
            // Combustible
            if (partes[i].contains("Combustible")){
                String[] vCombustibleArr = partes[i].split("%\\.");
                vehiculo.setCombustible(vCombustibleArr[1]);
            }
            // PBV
            if (partes[i].contains("PBV")){
                String[] vPBVArr = partes[i].split("%\\.");
                vehiculo.setPBV(vPBVArr[1].replace("%", ""));
            }
            // Instit Aseg
            if (partes[i].contains("Instit. aseg.")){
                //String []vArrInstitAseg = partes[i].split("%\\.");
                //jsonVehiculo.put("Instit",vArrInstitAseg[1]);
            }
            // Numero Poliza
            if (partes[i].contains("Numero poliza")){
                //vNroPolizaArr = partes[i].split("%\\.");
                //jsonVehiculo.put("Poliza",vNroPolizaArr[1]);
            }
            // Fecha Vencimiento Poliza
            if (partes[i].contains("Fec. ven. pol.")){
                //vFechaVenPolArr = partes[i].split("%\\.");
                //jsonVehiculo.put("Fecha Vencimiento Poliza",vFechaVenPolArr[1].replace("%3", "-"));
            }
            //////////////////////////////////////////////////////////////

            /////////////////// DATOS DEL PROPIETARIO ////////////////////
            // Nombre
            if (partes[i].contains("Nombre")){
                String[] pNombreArr = partes[i].split("%\\.");
                propietario.setNombreCompleto(pNombreArr[1]);
            }
            // RUN
            if (partes[i].contains("R.U.N") || partes[i].contains("R.U.T")){
                if(partes[i].contains("R.U.T")){
                    String[] pRUNArr = partes[i].split("%\\.");
                    propietario.setRut(pRUNArr[1].replace("%3", "-"));
                    propietario.setNombreCompletoEmpresa();
                    propietario.setPersonaNatural("0");
                }
                else{
                    String[] pRUNArr = partes[i].split("%\\.");
                    propietario.setRut(pRUNArr[1].replace("%3", "-"));
                    propietario.setPersonaNatural("1");
                }

            }
            // Fecha Adquisicion
            if (partes[i].contains("Fec. adquisici$on")){
                String[] pAdquisicionArr = partes[i].split("%\\.");
                propietario.setFechaAdquisicion(pAdquisicionArr[1].replace("%3", "-"));
            }
            // Repertorio
            if (partes[i].contains("Repertorio")){
                String[] pRepertorio = partes[i].split("%\\.");
                propietario.setRepertorio(pRepertorio[1]);
            }
            // Numero y Fecha
            if (partes[i].contains("N$umero")){
                String[] pNombreArr = partes[i].split("%\\.");
                String[] vNumeroArr = pNombreArr[1].split(" ");
                propietario.setNumeroRepertorio(vNumeroArr[1]);
                propietario.setFechaRepertorio(pNombreArr[2].replace("%3", "-"));
            }
            //////////////////////////////////////////////////////////////

            if (partes[i].contains("$V")){

            }
        }

        cavObject.setPropietario(propietario);
        cavObject.setVehiculo(vehiculo);
        return cavObject;
    }

    public CAVObject parse2(String pdf417){

        CAVObject cavObject = new CAVObject();
        String[] partes = pdf417.split("\\$C");

        cavObject.setPrenda(1);
        cavObject.setMultas(0);

        for(int i=0;i<partes.length;i++){
            // FECHA DEL CAV //
            if (partes[i].startsWith("$V")){
                String[] vPatente = partes[i].split("%,");
                String fe = vPatente[0].substring(2).trim();
                cavObject.setFecha(fe);
            }

            // PRENDA //
            if(partes[i].contains("A LA FECHA NO TIENE ANOTACIONES VIGENTES")){
                cavObject.setPrenda(0);
            }

            // MULTAS //
            if(partes[i].contains("** REGISTRA MULTAS DE TRANSITO")){
                cavObject.setMultas(1);
            }
        }

        return cavObject;
    }
}
