import java.util.ArrayList;

public class Area {
    private int idArea;
    private String nombreArea;
    private String estadoArea;
    public ArrayList<Equipo> listaEquipo;

    public Area(int idArea, String nombreArea, String estadoArea, ArrayList<Equipo> listaEquipo) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.estadoArea = estadoArea;
        this.listaEquipo = listaEquipo;
    }

    public int getIdArea() {
        return idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public String getEstadoArea() {
        return estadoArea;
    }

    public ArrayList<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public void setEstadoArea(String estadoArea) {
        this.estadoArea = estadoArea;
    }

    public void setListaEquipo(ArrayList<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }
}
