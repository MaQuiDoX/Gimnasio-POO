import java.util.ArrayList;
import java.util.Scanner;

public class Reserva {
    private int idReserva;
    private Miembro miembroReserva;
    private Clase claseReserva;


    static ArrayList<Integer> idsUsadas = new ArrayList();

    public Reserva(int idReserva, Miembro miembroReserva, Clase claseReserva) {
        this.idReserva = idReserva;
        this.miembroReserva = miembroReserva;
        this.claseReserva = claseReserva;
    }

    public static Reserva hacerReserva(Gimnasio gimnasio1) {
        Scanner sc = new Scanner(System.in);

        // Este sistema chequea si la ID ya ha sido utilizada para otra reserva
        boolean existe = false;
        System.out.println("Ingrese el ID de la reserva: ");
        int idReserva = sc.nextInt();
        do {
            existe = idsUsadas.contains(idReserva);
            if (existe) {
                System.out.println("ID ya utilizada, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return null; }
                idReserva = sc.nextInt();
            } else {
                break;
            }
        } while (existe);

        // Este sistema chequea si el miembro pertenece al gimnasio
        int idMiembro;
        Miembro miembroReserva;
        boolean miembroExistente;
        System.out.println("Ingrese el ID del miembro que desea reservar: ");
        do {
            idMiembro = sc.nextInt();
            miembroReserva = Miembro.searchMiembroInList(gimnasio1.getListaMiembros(),idMiembro);
            if (miembroReserva == null){
                System.out.println("El miembro no está registrado");
                if (Gimnasio.consultaOperacion()) { return null; }
                miembroExistente = false;
            } else {
                miembroExistente = true;
            }
        }while (!miembroExistente);

        // Este sistema chequea si la clase seleccionada ya esta al tope de miembros, y si el miembro ya se encuentra en inscripto en la clase
        System.out.println("Ingrese el ID de la clase que desea reservar: ");
        int idClase;
        Clase claseReserva;

        boolean existe1;
        boolean llena1 = false;
        boolean anotado1 = false;
        do {
            idClase = sc.nextInt();
            claseReserva = Clase.searchClaseInList(gimnasio1.getListaClases(),idClase);

            // Este parrafo chequea si la clase existe
            if (claseReserva == null){
                System.out.println("La clase no existe");
                existe1 = false;
            } else {
                existe1 = true;
            }

            // Esto parrafo chequea si la clase acepta mas miembros
            if ((existe1)&&(claseReserva.getCapacidadMiembros() == claseReserva.getListaMiembros().size())) {
                System.out.println("La clase no acepta mas miembros");
                llena1 = true;
            } else if ((existe1)&&(claseReserva.getCapacidadMiembros() != claseReserva.getListaMiembros().size())){
                llena1 = false;
            }

            // Este parrafo chequea si el miembro ya esta anotado en la lista de la clase
            if ((existe1)&&(Miembro.searchMiembroInList(claseReserva.getListaMiembros(),idMiembro)!=null)){
                System.out.println("El miembro ya está anotado en esta clase");
                anotado1 = true;
            } else if ((existe1)&&(Miembro.searchMiembroInList(claseReserva.getListaMiembros(),idMiembro)==null)){
                anotado1 = false;
            }

            if ((!existe1) || (llena1) || anotado1){
                if (Gimnasio.consultaOperacion()) { return null; }
            }

        }while ((!existe1)||(llena1)||(anotado1));


        deleteClase(gimnasio1,idClase);
        claseReserva.listaMiembros.add(miembroReserva);
        gimnasio1.listaClases.add(claseReserva);
        idsUsadas.add(idReserva);

        System.out.println();
        return new Reserva(idReserva, miembroReserva, claseReserva);
    }

    public static void cancelarReserva(Gimnasio gimnasio) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID de la reserva que desea cancelar:");
        int idReserva = sc.nextInt();

        // Comprueba que la reserva dada por ID exista en el gimnasio.
        Reserva reservaBorrar = searchReservaInList(gimnasio.getListaReserva(), idReserva);
        do {
            if (reservaBorrar == null) {
                System.out.println("Esta reserva no existe");
                if (Gimnasio.consultaOperacion()) { return; }
                idReserva = sc.nextInt();
                reservaBorrar = searchReservaInList(gimnasio.getListaReserva(), idReserva);
            } else {
                break;
            }
        } while (reservaBorrar == null);

        int idMiembro = reservaBorrar.getMiembroReserva().getIdMiembro();

        ArrayList<Clase> listaClase = new ArrayList<>();
        listaClase = gimnasio.getListaClases();
        for (Clase clase : listaClase) {
            clase.listaMiembros.removeIf(miembro -> miembro.getIdMiembro() == idMiembro);
        }

        int finalIdReserva = idReserva;
        idsUsadas.removeIf(e -> e == finalIdReserva);
        gimnasio.setListaClases(listaClase);

        // Elimina la reserva de la lista del gimnasio
        deleteReserva(gimnasio, idReserva);

    }

    public static Reserva searchReservaInList(ArrayList<Reserva> gimnasio, int idReserva){
        // A partir de una ID dada, recorre el ArrayList donde se encuentran las reservas registradas en el gimnasio, la devuelve. En caso contrario retorna un null
        for (Reserva reserva : gimnasio){
            if (reserva.getIdReserva() == idReserva){
                return reserva;
            }
        } return null;
    }

    public static void deleteClase(Gimnasio gimnasio1, int idClase) {
        gimnasio1.listaClases.removeIf(clase -> clase.getIdClase() == idClase);
    }

    public static void deleteReserva(Gimnasio gimnasio1, int idReserva) {
        gimnasio1.listaReserva.removeIf(reserva -> reserva.getIdReserva() == idReserva);
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Miembro getMiembroReserva() {
        return miembroReserva;
    }

    public void setMiembroReserva(Miembro miembroReserva) {
        this.miembroReserva = miembroReserva;
    }

    public Clase getClaseReserva() {
        return claseReserva;
    }

    public void setClaseReserva(Clase claseReserva) {
        this.claseReserva = claseReserva;
    }
}
