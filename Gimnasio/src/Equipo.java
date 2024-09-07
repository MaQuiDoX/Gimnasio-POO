import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Equipo {
    private int idEquipo;
    private String tipoEquipo;
    private String estadoEquipo;

    static ArrayList<Integer> idsUsadas = new ArrayList<>();

    public Equipo(int idEquipo, String tipoEquipo, String estadoEquipo) {
        this.idEquipo = idEquipo;
        this.tipoEquipo = tipoEquipo;
        this.estadoEquipo = estadoEquipo;
    }

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

    public static Area asignarArea(Equipo equipo1, Area area1, Gimnasio gimnasio1) {

        gimnasio1.listaAreas.removeIf(area -> area.getIdArea() == area1.getIdArea() );


        area1.listaEquipo.add(equipo1);
        gimnasio1.listaAreas.add(area1);

        return area1;
    }

    public static void consultarMantenimiento(Equipo equipo) {
        Scanner sc2 = new Scanner(System.in);
        char opcion = ' ';
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

    public static Equipo searchEquipoInList(ArrayList<Equipo> gimnasio, int idEquipo){
        for (Equipo equipo : gimnasio){
            if (equipo.getIdEquipo() == idEquipo){
                return equipo;
            }
        } return null;
    }

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

    /*
    private int idEquipo;
    private String tipoEquipo;
    private String estadoEquipo;
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
