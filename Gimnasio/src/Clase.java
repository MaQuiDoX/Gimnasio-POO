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

        boolean existe = false;
        System.out.println("Ingrese el ID de la clase: ");
        int idClase = sc.nextInt();
        do {
            existe = idsUsadas.contains(idClase);
            if (existe) {
                System.out.println("ID ya utilizada, ingrese nuevamente: ");
                idClase = sc.nextInt();
            } else {
                idsUsadas.add(idClase);
                break;
            }
        } while (existe);
        sc.nextLine();

        System.out.println("Ingrese el nombre del clase: ");
        String nombreClase = sc.nextLine();

        System.out.println("Ingrese la capacidad de miembros: ");
        int capacidadMiembros = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese la horario de clase: ");
        int horarioClase = sc.nextInt();
        do {
            if (Integer.toString(horarioClase).length() != 4) {
                System.out.println("Valor temporal invalido, ingrese nuevamente: ");
                horarioClase = sc.nextInt();
            } else {
                break;
            }
        } while (Integer.toString(horarioClase).length() != 4);
        sc.nextLine();

        System.out.println("Ingrese el fecha de la clase: ");
        int fechaClase = sc.nextInt();
        do {
            if (Integer.toString(fechaClase).length() != 8) {
                System.out.println("Fecha de Inscripcion invalida, ingrese nuevamente: ");
                fechaClase = sc.nextInt();
            } else {
                break;
            }
        } while (Integer.toString(fechaClase).length() != 8);

        System.out.println("Ingrese el ID del entrenador asignado ");
        int idEntrenador = sc.nextInt();
        Entrenador entrenadorClase = Entrenador.searchEntrenadorInList(gimnasio1.getListaEntrenadores(),idEntrenador);
        do {
            if (entrenadorClase == null){
                System.out.println("El entrenador no existe");
                idEntrenador = sc.nextInt();
                entrenadorClase = Entrenador.searchEntrenadorInList(gimnasio1.getListaEntrenadores(),idEntrenador);
            } else {
                break;
            }
        } while (entrenadorClase == null);

        Clase clase1 = new Clase(idClase,nombreClase,capacidadMiembros,horarioClase,fechaClase,entrenadorClase,new ArrayList<>());
        Entrenador.deleteEntrenador(gimnasio1,idEntrenador);
        entrenadorClase.historialEntrenamientos.add(clase1);
        gimnasio1.listaEntrenadores.add(entrenadorClase);
        gimnasio1.listaClases.add(clase1);

        System.out.println();
        return clase1;
    }

    public static Clase searchClaseInList(ArrayList<Clase> gimnasio, int idClase){
        for (Clase clase : gimnasio){
            if (clase.getIdClase() == idClase){
                return clase;
            }
        } return null;
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
