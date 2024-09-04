import java.util.Scanner;

public class Suscripcion {
    private int idSuscripcion;
    private int idMiembro;
    private int costoSuscripcion;
    private String tipoSuscripcion;
    private int fechaInicio;
    private int fechaFin;

    public Suscripcion(int idSuscripcion, int idMiembro, int costoSuscripcion, String tipoSuscripcion, int fechaInicio, int fechaFin) {
        this.idSuscripcion = idSuscripcion;
        this.idMiembro = idMiembro;
        this.costoSuscripcion = costoSuscripcion;
        this.tipoSuscripcion = tipoSuscripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    /*
    public static Suscripcion registrarSuscripcion(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el ID del suscripcion: ");
        int idSuscripcion = sc.nextInt();
        
    }
    */
    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public int getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    public int getCostoSuscripcion() {
        return costoSuscripcion;
    }

    public void setCostoSuscripcion(int costoSuscripcion) {
        this.costoSuscripcion = costoSuscripcion;
    }

    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(String tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }

    public int getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(int fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(int fechaFin) {
        this.fechaFin = fechaFin;
    }
}
