import java.util.ArrayList;

public class Gimnasio {
    private String nombreGimnasio;
    private String direccionGimnasio;
    private int horaApertura;
    private int horaCierre;
    private String telefonoGimnasio;
    public ArrayList<Miembro> listaMiembros;
    public ArrayList<Entrenador> listaEntrenadores;
    public ArrayList<Clase> listaClases;
    public ArrayList<Area> listaAreas;
    public ArrayList<Equipo> listaEquipos;

    public Gimnasio(String nombreGimnasio, String direccionGimnasio, int horaApertura, int horaCierre, String telefonoGimnasio, ArrayList<Miembro> listaMiembros, ArrayList<Entrenador> listaEntrenadores, ArrayList<Clase> listaClases, ArrayList<Area> listaAreas, ArrayList<Equipo> listaEquipos) {
        this.nombreGimnasio = nombreGimnasio;
        this.direccionGimnasio = direccionGimnasio;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.telefonoGimnasio = telefonoGimnasio;
        this.listaMiembros = listaMiembros;
        this.listaEntrenadores = listaEntrenadores;
        this.listaClases = listaClases;
        this.listaAreas = listaAreas;
        this.listaEquipos = listaEquipos;
    }


    public static void obtenerListaMiembros(Gimnasio gimnasio1){
        int nummiembro = 1;

        for (Miembro memb1 : gimnasio1.listaMiembros) {
            System.out.println("MIEMBRO N° " + nummiembro);
            Miembro.imprimirMiembro(memb1);
            nummiembro++;
            System.out.println();
        }
    }

    public static void obtenerListaEmpleados(Gimnasio gimnasio1){
        int numentr = 1;

        for (Entrenador entrenador : gimnasio1.listaEntrenadores) {
            System.out.println("ENTRENADOR N° " + numentr);
            Entrenador.imprimirEntrenador(entrenador);
            numentr++;
            System.out.println();
        }
    }

    public String getNombreGimnasio() {
        return nombreGimnasio;
    }

    public void setNombreGimnasio(String nombreGimnasio) {
        this.nombreGimnasio = nombreGimnasio;
    }

    public String getDireccionGimnasio() {
        return direccionGimnasio;
    }

    public void setDireccionGimnasio(String direccionGimnasio) {
        this.direccionGimnasio = direccionGimnasio;
    }

    public int getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(int horaApertura) {
        this.horaApertura = horaApertura;
    }

    public int getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(int horaCierre) {
        this.horaCierre = horaCierre;
    }

    public String getTelefonoGimnasio() {
        return telefonoGimnasio;
    }

    public void setTelefonoGimnasio(String telefonoGimnasio) {
        this.telefonoGimnasio = telefonoGimnasio;
    }

    public ArrayList<Miembro> getListaMiembros() {
        return listaMiembros;
    }

    public void setListaMiembros(ArrayList<Miembro> listaMiembros) {
        this.listaMiembros = listaMiembros;
    }

    public ArrayList<Entrenador> getListaEntrenadores() {
        return listaEntrenadores;
    }

    public void setListaEntrenadores(ArrayList<Entrenador> listaEntrenadores) {
        this.listaEntrenadores = listaEntrenadores;
    }

    public ArrayList<Clase> getListaClases() {
        return listaClases;
    }

    public void setListaClases(ArrayList<Clase> listaClases) {
        this.listaClases = listaClases;
    }

    public ArrayList<Area> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(ArrayList<Area> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public ArrayList<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(ArrayList<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }
}
