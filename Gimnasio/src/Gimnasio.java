import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gimnasio {
    private String nombreGimnasio;
    private String direccionGimnasio;
    private String horaApertura;
    private String horaCierre;
    private String telefonoGimnasio;
    public ArrayList<Miembro> listaMiembros;
    public ArrayList<Entrenador> listaEntrenadores;
    public ArrayList<Clase> listaClases;
    public ArrayList<Area> listaAreas;
    public ArrayList<Equipo> listaEquipos;
    public ArrayList<Reserva> listaReserva;

    public Gimnasio(String nombreGimnasio, String direccionGimnasio, String horaApertura, String horaCierre, String telefonoGimnasio, ArrayList<Miembro> listaMiembros, ArrayList<Entrenador> listaEntrenadores, ArrayList<Clase> listaClases, ArrayList<Area> listaAreas, ArrayList<Equipo> listaEquipos, ArrayList<Reserva> listaReserva) {
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
        this.listaReserva = listaReserva;
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

    public static void obtenerListaArea(Gimnasio gimnasio1){
        int numentr = 1;

        for (Area area : gimnasio1.listaAreas) {
            System.out.println("AREA N° " + numentr);
            Area.imprimirArea(area);
            numentr++;
            System.out.println();
        }
    }

    public static void obtenerListaReservas(Gimnasio gimnasio1){
        int numentr = 1;

        for (Reserva reserva : gimnasio1.listaReserva) {
            System.out.println("RESERVA N° " + numentr);
            Reserva.imprimirReserva(reserva);
            numentr++;
            System.out.println();
        }
    }

    public static void obtenerListaEquipos(Gimnasio gimnasio1){
        int numentr = 1;

        for (Equipo equipo: gimnasio1.listaEquipos) {
            System.out.println("EQUIPO N° " + numentr);
            Equipo.imprimirEquipo(equipo);
            numentr++;
            System.out.println();
        }
    }

    public static void obtenerListaClases(Gimnasio gimnasio1){
        int numentr = 1;

        for (Clase clase : gimnasio1.listaClases) {
            System.out.println("CLASE N° " + numentr);
            Clase.imprimirClase(clase);
            numentr++;
            System.out.println();
        }
    }

    public static boolean consultaOperacion() {
        Scanner sc = new Scanner(System.in);

        System.out.println("¿Desea seguir con la operación? (y/n)");
        char opcion = ' ';
        do {
            opcion = sc.next().charAt(0);
            sc.nextLine();
            try {
                if (opcion == 'y') {
                    break;
                } else if (opcion == 'n') {
                    System.out.println("OK");
                    return true;
                } else {
                    System.out.println("Opcion invalida. Ingrese de nuevo. ");
                }
            } catch (InputMismatchException e1) {
                sc.nextLine();
                System.out.println("Opcion invalida. Ingrese de nuevo. ");
            }
        } while ((opcion != 'y'));
        return false;
    }

    public static void imprimirGimnasio(Gimnasio gimnasio){
        System.out.println("Nombre del gimnasio:" + gimnasio.getNombreGimnasio());
        System.out.println("Dirección del gimnasio: " + gimnasio.getDireccionGimnasio());
        System.out.println("Hora de apertura: "+ Entrenador.formatearHora(gimnasio.getHoraApertura()));
        System.out.println("Hora de cierre: "+ Entrenador.formatearHora(gimnasio.getHoraCierre()));
        System.out.println("Telefono del gimnasio: "+gimnasio.getTelefonoGimnasio());
        System.out.println();
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

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
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

    public ArrayList<Reserva> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(ArrayList<Reserva> listaReserva) {
        this.listaReserva = listaReserva;
    }
}
