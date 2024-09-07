import javax.naming.spi.ResolveResult;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Clase {
    private int idClase;
    private String nombreClase;
    private int capacidadMiembros;
    private String horarioClase;
    private String fechaClase;
    private Entrenador entrenadorClase;
    public ArrayList<Miembro> listaMiembros;

    static ArrayList<Integer> idsUsadas = new ArrayList<>();

    public Clase(int idClase, String nombreClase, int capacidadMiembros, String horarioClase, String fechaClase, Entrenador entrenadorClase, ArrayList<Miembro> listaMiembros) {
        this.idClase = idClase;
        this.nombreClase = nombreClase;
        this.capacidadMiembros = capacidadMiembros;
        this.horarioClase = horarioClase;
        this.fechaClase = fechaClase;
        this.entrenadorClase = entrenadorClase;
        this.listaMiembros = listaMiembros;
    }

    public static Clase programarClase(Gimnasio gimnasio1){
        Scanner sc = new Scanner(System.in);

        // Tipeo de la ID, y chequea si la ID ya existe
        boolean existe = false;
        System.out.println("Ingrese la ID de la clase: ");
        int idClase = sc.nextInt();
        do {
            existe = idsUsadas.contains(idClase);
            if (existe) {
                System.out.println("ID ya utilizada: ");
                if (Gimnasio.consultaOperacion()) { return null; }
                idClase = sc.nextInt();
            } else {
                break;
            }
        } while (existe);
        sc.nextLine();

        // Tipeo del nombre de la clase
        System.out.println("Ingrese el nombre de la clase: ");
        String nombreClase = sc.nextLine();

        // Tipeo de la capacidad de miembros de la clase
        System.out.println("Ingrese la capacidad de miembros: ");
        int capacidadMiembros = sc.nextInt();
        sc.nextLine();

        // Tipeo del horario de la clase, chequea que el formato sea el adecuado
        System.out.println("Ingrese el horario de clase (HHMM): ");
        String horarioClase = sc.nextLine();
        do {
            if (horarioClase.length() != 4) {
                System.out.println("Valor temporal invalido, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return null; }
                horarioClase = sc.nextLine();
            } else {
                break;
            }
        } while (horarioClase.length() != 4);
        sc.nextLine();

        // Tipeo de la fecha de la clase, chequea que el formato sea el adecuado
        System.out.println("Ingrese la fecha de la clase: (DDMMAAAA)");
        String fechaClase = sc.nextLine();
        do {
            if (fechaClase.length() != 8) {
                System.out.println("Fecha de Inscripcion invalida, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return null; }
                fechaClase = sc.nextLine();
            } else {
                break;
            }
        } while (fechaClase.length() != 8);

        System.out.println("Ingrese el ID del area a la cual desea asignar la clase: ");
        int idArea = sc.nextInt();

        Area areaClase = Area.searchAreaInList(gimnasio1.listaAreas, idArea);
        do{
            if (areaClase == null) {
                System.out.println("El area no existe");
                if (Gimnasio.consultaOperacion()) { return null; }
                idArea = sc.nextInt();
                areaClase = Area.searchAreaInList(gimnasio1.listaAreas, idArea);
            }

            if ((areaClase != null) && (areaClase.claseArea != null)){
                System.out.println("Esta area ya tiene asignada otra clase");
                if (Gimnasio.consultaOperacion()) { return null; }
                idArea = sc.nextInt();
                areaClase = Area.searchAreaInList(gimnasio1.listaAreas, idArea);
            }
        } while ((areaClase == null)||(areaClase.claseArea != null));

        // Tipeo de la ID del entrenador a asignar, chequea si el entrenador elegido existe en el gimnasio
        System.out.println("Ingrese el ID del entrenador asignado ");
        int idEntrenador = sc.nextInt();
        Entrenador entrenadorClase = Entrenador.searchEntrenadorInList(gimnasio1.getListaEntrenadores(),idEntrenador);
        do {
            if (entrenadorClase == null){
                System.out.println("El entrenador no existe");
                if (Gimnasio.consultaOperacion()) { return null; }
                idEntrenador = sc.nextInt();
                entrenadorClase = Entrenador.searchEntrenadorInList(gimnasio1.getListaEntrenadores(),idEntrenador);
            } else {
                break;
            }
        } while (entrenadorClase == null);

        // Crea el objeto clase, actualiza la información del entrenador, añadiendo la clase a su historial de clases y reañadiendo el entrenador a la lista del gimnasio.
        Clase clase1 = new Clase(idClase,nombreClase,capacidadMiembros,horarioClase,fechaClase,entrenadorClase,new ArrayList<>());
        Entrenador.deleteEntrenador(gimnasio1,idEntrenador);
        entrenadorClase.historialEntrenamientos.add(clase1);
        gimnasio1.listaEntrenadores.add(entrenadorClase);

        // Actualiza la clase con el area asignada
        Area.deleteArea(gimnasio1,idArea);
        areaClase.claseArea = clase1;
        gimnasio1.listaAreas.add(areaClase);


        gimnasio1.listaClases.add(clase1);
        idsUsadas.add(idClase);
        // Retorna el objeto Clase
        System.out.println();
        return clase1;
    }

    public static Clase searchClaseInList(ArrayList<Clase> gimnasio, int idClase){
        // A partir de una ID dada, recorre el ArrayList donde se encuentran las clases registradas en el gimnasio, la devuelve. En caso contrario retorna un null
        for (Clase clase : gimnasio){
            if (clase.getIdClase() == idClase){
                return clase;
            }
        } return null;
    }

    public static void cumplirClase(Gimnasio gimnasio) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el ID de la clase ya realizada: ");
        int idClase = sc.nextInt();

        Clase claseCumplir = searchClaseInList(gimnasio.getListaClases(), idClase);
        do {
            if (claseCumplir == null) {
                System.out.println("Esta clase no existe");
                if (Gimnasio.consultaOperacion()) { return; }
                idClase = sc.nextInt();
                claseCumplir = searchClaseInList(gimnasio.getListaClases(), idClase);
            } else {
                break;
            }
        } while (claseCumplir == null);

        // Elimina todas las reservas hechas para la clase
        ArrayList<Reserva> listaReservas = gimnasio.getListaReserva();
        int finalIdClase = idClase;
        listaReservas.removeIf(reserva -> reserva.getClaseReserva().getIdClase() == finalIdClase);
        gimnasio.setListaReserva(listaReservas);

        // Elimina el area la cual la clase fue asignada
        Area areaClase = Area.searchAreaInList(gimnasio.getListaAreas(), finalIdClase);
        Area.deleteArea(gimnasio,finalIdClase);
        areaClase.setClaseArea(null);
        gimnasio.listaAreas.add(areaClase);

        // Elimina la clase del gimnasio
        deleteClase(gimnasio,idClase);

    }

    public static void cancelarClase(Gimnasio gimnasio) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID de la clase que desea cancelar:");
        int idClase = sc.nextInt();

        // Comprueba que la clase dada por ID exista en el gimnasio.
        Clase claseBorrar = searchClaseInList(gimnasio.getListaClases(), idClase);
        do {
            if (claseBorrar == null) {
                System.out.println("Esta clase no existe");
                if (Gimnasio.consultaOperacion()) { return; }
                idClase = sc.nextInt();
                claseBorrar = searchClaseInList(gimnasio.getListaClases(), idClase);
            } else {
                break;
            }
        } while (claseBorrar == null);

        // Elimina la clase de la lista del gimnasio
        deleteClase(gimnasio, idClase);

        // Elimina la clase correspondiente del historial de los entrenadores del gimnasio
        ArrayList<Entrenador> listaEntrenadores;
        listaEntrenadores = gimnasio.getListaEntrenadores();
        for (Entrenador entrenador : listaEntrenadores) {
            int finalIdClase = idClase;
            entrenador.historialEntrenamientos.removeIf(clase -> clase.getIdClase() == finalIdClase);
        }
        gimnasio.setListaEntrenadores(listaEntrenadores);

        // Elimina el area la cual la clase fue asignada
        int finalIdClase = idClase;
        Area areaClase = Area.searchAreaInList(gimnasio.getListaAreas(), finalIdClase);
        Area.deleteArea(gimnasio,finalIdClase);
        areaClase.setClaseArea(null);
        gimnasio.listaAreas.add(areaClase);

        idsUsadas.removeIf(e -> e == finalIdClase);
    }

    public static void imprimirClase(Clase clase){
        int numMiembro = 1;
        System.out.println("Nombre de la clase: "+clase.getNombreClase());
        System.out.println("ID de la clase: " + clase.getIdClase());
        System.out.println("Capacidad de miembros: "+clase.getCapacidadMiembros());
        System.out.println("Fecha de la clase: "+ Miembro.formatearFecha(clase.getFechaClase()));
        System.out.println("Horario de la clase: "+ Entrenador.formatearHora(clase.getHorarioClase()));
        if (clase.getEntrenadorClase() != null){
            System.out.println("Nombre del Entrenador asignado: "+clase.getEntrenadorClase().getNombreEntrenador()+" | ID: "+clase.getEntrenadorClase().getIdEntrenador());
        } else {
            System.out.println("Esta clase no tiene entrenador asignado");
        }
        if (clase.getListaMiembros()!=null) {
            for (Miembro miembro : clase.getListaMiembros()){
                System.out.println("Miembro N°"+numMiembro+" | Nombre del miembro: "+miembro.getNombreMiembro()+" | ID: "+miembro.getIdMiembro());
                numMiembro++;
            }
        } else {
            System.out.println("Esta clase no tiene miembros inscriptos");
        }
    }

    public static void deleteClase(Gimnasio gimnasio1, int idClase){
        gimnasio1.listaClases.removeIf(clase -> clase.getIdClase() == idClase);
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public int getCapacidadMiembros() {
        return capacidadMiembros;
    }

    public void setCapacidadMiembros(int capacidadMiembros) {
        this.capacidadMiembros = capacidadMiembros;
    }

    public String getHorarioClase() {
        return horarioClase;
    }

    public void setHorarioClase(String horarioClase) {
        this.horarioClase = horarioClase;
    }

    public Entrenador getEntrenadorClase() {
        return entrenadorClase;
    }

    public void setEntrenadorClase(Entrenador entrenadorClase) {
        this.entrenadorClase = entrenadorClase;
    }

    public ArrayList<Miembro> getListaMiembros() {
        return listaMiembros;
    }

    public void setListaMiembros(ArrayList<Miembro> listaMiembros) {
        this.listaMiembros = listaMiembros;
    }

    public String getFechaClase() {
        return fechaClase;
    }

    public void setFechaClase(String fechaClase) {
        this.fechaClase = fechaClase;
    }
}
