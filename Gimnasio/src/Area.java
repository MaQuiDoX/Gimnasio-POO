import java.util.ArrayList;
import java.util.Scanner;

public class Area {
    private int idArea;
    private String nombreArea;
    public ArrayList<Equipo> listaEquipo;
    public Clase claseArea;

    public Area(int idArea, String nombreArea, ArrayList<Equipo> listaEquipo, Clase claseArea) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.listaEquipo = listaEquipo;
        this.claseArea = claseArea;
    }

    static ArrayList<Integer> idsUsadas = new ArrayList<>();

    public static Area registrarArea(Gimnasio gimnasio){
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.print("Ingrese el nombre del area: ");
        String nombreArea = sc.nextLine();

        // Tipeo de la ID, y chequea si la ID ya existe
        boolean existe = false;
        System.out.println("Ingrese la ID del area: ");
        int idArea;
        idArea= sc.nextInt();
        do {
            existe = idsUsadas.contains(idArea);
            if (existe) {
                System.out.println("ID ya utilizada: ");
                if (Gimnasio.consultaOperacion()) { return null; }
                idArea = sc.nextInt();
            } else {
                break;
            }
        } while (existe);
        sc.nextLine();

        Area area = new Area(idArea,nombreArea,new ArrayList<>(),null);
        gimnasio.listaAreas.add(area);
        return area;
    }

    public static void deleteArea(Gimnasio gimnasio1, int idArea){
        gimnasio1.listaAreas.removeIf(area -> area.getIdArea() == idArea);
    }

    public static Area searchAreaInList(ArrayList<Area> gimnasio, int idArea){
        for (Area area : gimnasio){
            if (area.getIdArea() == idArea){
                return area;
            }
        } return null;
    }



    public int getIdArea() {
        return idArea;
    }

    public String getNombreArea() {
        return nombreArea;
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

    public void setListaEquipo(ArrayList<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }

    public Clase getClaseArea() {
        return claseArea;
    }

    public void setClaseArea(Clase claseArea) {
        this.claseArea = claseArea;
    }
}
