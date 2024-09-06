import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Clase {
    private int idClase;
    private String nombreClase;
    private int capacidadMiembros;
    private int horarioClase;
    private int fechaClase;
    private Entrenador entrenadorClase;
    public ArrayList<Miembro> listaMiembros;

    static ArrayList<Integer> idsUsadas = new ArrayList<>();

    public Clase(int idClase, String nombreClase, int capacidadMiembros, int horarioClase, int fechaClase, Entrenador entrenadorClase, ArrayList<Miembro> listaMiembros) {
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
        int horarioClase = sc.nextInt();
        do {
            if (Integer.toString(horarioClase).length() != 4) {
                System.out.println("Valor temporal invalido, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return null; }
                horarioClase = sc.nextInt();
            } else {
                break;
            }
        } while (Integer.toString(horarioClase).length() != 4);
        sc.nextLine();

        // Tipeo de la fecha de la clase, chequea que el formato sea el adecuado
        System.out.println("Ingrese la fecha de la clase: (DDMMAAAA)");
        int fechaClase = sc.nextInt();
        do {
            if (Integer.toString(fechaClase).length() != 8) {
                System.out.println("Fecha de Inscripcion invalida, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return null; }
                fechaClase = sc.nextInt();
            } else {
                break;
            }
        } while (Integer.toString(fechaClase).length() != 8);

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

        int finalIdClase = idClase;
        idsUsadas.removeIf(e -> e == finalIdClase);
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

    public int getHorarioClase() {
        return horarioClase;
    }

    public void setHorarioClase(int horarioClase) {
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
}
