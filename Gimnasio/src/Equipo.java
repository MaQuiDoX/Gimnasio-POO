import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que representa a los equipos del gimnasio
 */
public class Equipo {
    private int idEquipo;
    private String tipoEquipo;
    private String estadoEquipo;

    static ArrayList<Integer> idsUsadas = new ArrayList<>();

    /**
     * Constructor de equipo, recibe parametros
     * @param idEquipo ID del equipo
     * @param tipoEquipo Tipo de equipo
     * @param estadoEquipo Estado del equipo
     */
    public Equipo(int idEquipo, String tipoEquipo, String estadoEquipo) {
        this.idEquipo = idEquipo;
        this.tipoEquipo = tipoEquipo;
        this.estadoEquipo = estadoEquipo;
    }

    /**
     * Función para registrar un nuevo equipo, pide al usuario todos los parametros individuales y llama al constructor para generar una suscripción
     * @param gimnasio1 Entra a la función para trabajar con sus listas asociadas
     * @return Objeto equipo inicializado
     */
    public static Equipo registrarEquipo(Gimnasio gimnasio1){
        Scanner sc = new Scanner(System.in);

        boolean existe = false;
        System.out.println("Ingrese el ID del equipo: ");
        int idEquipo = sc.nextInt();
        do {
            existe = idsUsadas.contains(idEquipo);
            if (existe) {
                System.out.println("ID ya utilizada, ingrese nuevamente: ");
                idEquipo = sc.nextInt();
            } else {
                break;
            }
        } while (existe);

        System.out.println("Ingrese el tipo del equipo: ");
        String tipoEquipo = sc.nextLine();

        System.out.println("Ingrese el estado del equipo: ");
        String estadoEquipo = sc.nextLine();

        System.out.println();
        idsUsadas.add(idEquipo);

        return new Equipo(idEquipo, tipoEquipo, estadoEquipo);
    }

    /**
     * Función que asigna un equipo a un área y actualiza las listas involucradas a área y equipos
     * @param equipo1 Objeto equipo
     * @param area1 Objeto área
     * @param gimnasio1 Entra a la función para trabajar con sus listas asociadas
     * @return
     */
    public static Area asignarArea(Equipo equipo1, Area area1, Gimnasio gimnasio1) {

        // Elimina el objeto área de la lista de áreas del gimnasio para actualizar la información
        gimnasio1.listaAreas.removeIf(area -> area.getIdArea() == area1.getIdArea() );

        // Asigna el equipo a un área y devuelve el área actualizada a la lista de áreas
        area1.listaEquipo.add(equipo1);
        gimnasio1.listaAreas.add(area1);

        return area1;
    }

    /**
     * Función para consultar y actualizar el estado de un equipo
     * @param equipo Objeto equipo
     */
    public static void consultarMantenimiento(Equipo equipo) {
        Scanner sc2 = new Scanner(System.in);
        char opcion = ' ';

        // Consulta si desea actualizar la información del estado del equipo
        System.out.println("El estado del equipo es el siguiente: " + equipo.getEstadoEquipo());
        System.out.println("¿Desea actualizar la informacion? (y/n)");

        do{
            opcion = sc2.next().charAt(0);
            sc2.nextLine();
            try{
                if (opcion == 'y'){
                    System.out.println("Ingrese el estado del equipo: ");
                    String estado = sc2.nextLine();
                    equipo.setEstadoEquipo(estado);
                    break;
                } else if (opcion == 'n'){
                    break;
                } else {
                    System.out.println("Opcion invalida. Ingrese de nuevo. ");
                }
            } catch (InputMismatchException e1) {
                sc2.nextLine();
                System.out.println("Opcion invalida. Ingrese de nuevo. ");
            }
        }while ((opcion != 'y'));
    }

    /**
     * Función para buscar un Objeto Equipo en una lista de Equipos
     * @param gimnasio Lista de equipos
     * @param idEquipo ID del equipo a buscar
     * @return Objeto Equipo si encuentra en equipo, null en caso contrario
     */
    public static Equipo searchEquipoInList(ArrayList<Equipo> gimnasio, int idEquipo){
        for (Equipo equipo : gimnasio){
            if (equipo.getIdEquipo() == idEquipo){
                return equipo;
            }
        } return null;
    }

    /**
     * Consulta si el equipo ya existe en el gimnasio a partir de su ID
     * @param gimnasio1 Entra a la función para consultar la lista asociada a equipos
     * @return ID del equipo si la encuentra, 0  en caso contrario si la decide que la operación debe detenerse
     */
    public static Integer askEquipoId(Gimnasio gimnasio1){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del equipo: ");
        int id;
        boolean existe;
        do {
            id = sc.nextInt();
            if (Equipo.searchEquipoInList(gimnasio1.listaEquipos, id) == null) {
                System.out.println("El ID del equipo no existe");
                existe = false;
                if (Gimnasio.consultaOperacion()) {return 0;}
            } else {
                existe = true;
                return id;
            }
        } while (!existe);
        return 0;
    }

    /**
     * Función que imprime todos los parametros del objeto Equipo
     * @param equipo Objeto equipo
     */
    public static void imprimirEquipo(Equipo equipo){
        System.out.println("ID del equipo: " + equipo.getIdEquipo());
        System.out.println("Tipo de equipo: " + equipo.getTipoEquipo());
        System.out.println("Estado del equipo: " + equipo.getEstadoEquipo());
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public String getEstadoEquipo() {
        return estadoEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public void setEstadoEquipo(String estadoEquipo) {
        this.estadoEquipo = estadoEquipo;
    }
}
