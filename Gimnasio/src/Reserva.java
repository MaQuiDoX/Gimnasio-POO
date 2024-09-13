import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que representa las reservas
 * @author Manuel Matías Quesada Riccieri
 */
public class Reserva {
    private int idReserva;
    private Miembro miembroReserva;
    private Clase claseReserva;

    static ArrayList<Integer> idsUsadas = new ArrayList();

    /**
     * Constructor de reservas, recibe parametros
     * @param idReserva ID de la reserva
     * @param miembroReserva Objeto Miembro asociado a la reserva
     * @param claseReserva Objeto Clase asociado a la reserva
     */
    public Reserva(int idReserva, Miembro miembroReserva, Clase claseReserva) {
        this.idReserva = idReserva;
        this.miembroReserva = miembroReserva;
        this.claseReserva = claseReserva;
    }

    /**
     * Función para registrar una nueva reserva, pide al usuario todos los parametros individuales y llama al constructor para generar una suscripción
     * @param gimnasio1 Entra a la función para trabajar con sus listas asociadas
     */
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
        boolean miembroSuscripto = false;
        boolean miembroSubPagada = false;
        System.out.println("Ingrese el ID del miembro que desea reservar: ");
        do {
            // Chequea si el miembro ya ha sido registrado
            idMiembro = sc.nextInt();
            miembroReserva = Miembro.searchMiembroInList(gimnasio1.getListaMiembros(),idMiembro);
            if (miembroReserva == null){
                System.out.println("El miembro no está registrado");
                if (Gimnasio.consultaOperacion()) { return null; }
                miembroExistente = false;
            } else {
                miembroExistente = true;
            }

            // Chequea si el miembro está habilitado para hacer una reserva a partir de si posee una suscripción
            if ((miembroExistente) && (miembroReserva.getEstadoSuscripcion() == null)) {
                System.out.println("El miembro no está suscripto");
                if (Gimnasio.consultaOperacion()) { return null; }
                miembroSuscripto = true;
            } else if ((miembroExistente) && (miembroReserva.getEstadoSuscripcion() != null)) {
                miembroSuscripto = false;
            }

            // Chequea si el miembro está habilitado para hacer una reserva a partir de si ha pagado la suscripción
            if ((miembroExistente) && (miembroSuscripto) && (!miembroReserva.getEstadoSuscripcion().isEstadoSuscripcion())) {
                System.out.println("El miembro no ha pagado su suscripción");
                if (Gimnasio.consultaOperacion()) { return null; }
                miembroSubPagada = true;
            } else if ((miembroExistente) && (miembroSuscripto) && (miembroReserva.getEstadoSuscripcion().isEstadoSuscripcion())) {
                miembroSubPagada = false;
            }

            if ((!miembroExistente) || (miembroSuscripto) || (miembroSubPagada)) {
                if (Gimnasio.consultaOperacion()) { return null; }
            }

        }while ((!miembroExistente) || (miembroSuscripto) || (miembroSubPagada));

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

        // Elimina la clase, añade un nuevo miembro a su lista de miembros y la vuelve a añadir a la lista de clases del gimnasio
        deleteClase(gimnasio1,idClase);
        claseReserva.listaMiembros.add(miembroReserva);
        gimnasio1.listaClases.add(claseReserva);
        idsUsadas.add(idReserva);

        // Retorna nuevo objeto reserva
        System.out.println();
        return new Reserva(idReserva, miembroReserva, claseReserva);
    }

    /**
     * Accede a la lista de Reservas del gimnasio y, a partir de un parametro pedido, elimina la reserva y actualiza las lista de clases.
     * @param gimnasio Entra a la función para trabajar con sus listas asociadas
     */
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

        // Elimina al miembro de la reserva su clase correspondiente y actualiza la información
        ArrayList<Clase> listaClase = new ArrayList<>();
        listaClase = gimnasio.getListaClases();
        for (Clase clase : listaClase) {
            clase.listaMiembros.removeIf(miembro -> miembro.getIdMiembro() == idMiembro);
        }

        // Elimina el ID de la reserva y vuelve a añadirla clase a la lista de clases del gimnasio
        int finalIdReserva = idReserva;
        idsUsadas.removeIf(e -> e == finalIdReserva);
        gimnasio.setListaClases(listaClase);

        // Elimina la reserva de la lista del gimnasio
        deleteReserva(gimnasio, idReserva);

    }

    /**
     * Función para buscar una Reserva en una lista de Reservas
     * @param gimnasio ArrayList de reservas del gimnasio
     * @param idReserva ID de la reserva
     * @return Objeto Reserva pedido por ID, null en caso de no encontrarlo en la lista
     */
    public static Reserva searchReservaInList(ArrayList<Reserva> gimnasio, int idReserva){
        // A partir de una ID dada, recorre el ArrayList donde se encuentran las reservas registradas en el gimnasio, la devuelve. En caso contrario retorna un null
        for (Reserva reserva : gimnasio){
            if (reserva.getIdReserva() == idReserva){
                return reserva;
            }
        } return null;
    }

    /**
     * Función que imprime todos los parametros del objeto Reserva
     * @param reserva Objeto Reserva
     */
    public static void imprimirReserva(Reserva reserva){
        System.out.println("ID de la reserva: "+ reserva.getIdReserva());
        System.out.println("Nombre del miembro que realiza la reserva: "+reserva.getMiembroReserva().getNombreMiembro()+" | ID: "+reserva.getMiembroReserva().getIdMiembro());
        System.out.println("Nombre de la clase a la que se asigna: "+reserva.getClaseReserva().getNombreClase()+" | ID: "+reserva.getClaseReserva().getIdClase());
    }

    /**
     * Función que elimina el Objeto Clase de la lista de Clases del Gimnasio ingresado por parametro
     * @param gimnasio1 Objeto Gimnasio
     * @param idClase ID de la Clase
     */
    public static void deleteClase(Gimnasio gimnasio1, int idClase) {
        gimnasio1.listaClases.removeIf(clase -> clase.getIdClase() == idClase);
    }

    /**
     * Función que elimina el Objeto Reserva de la lista de Reservas del Gimnasio ingresado por parametro
     * @param gimnasio1 Objeto Gimnasio
     * @param idReserva ID de la reserva
     */
    public static void deleteReserva(Gimnasio gimnasio1, int idReserva) {
        gimnasio1.listaReserva.removeIf(reserva -> reserva.getIdReserva() == idReserva);
    }

    /**
     * Getter ID de la reserva
     * @return ID de la reserva
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * Setter ID de la reserva
     * @param idReserva Ingresa al ID de la reserva para settearla en el objeto Reserva
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Getter del objeto Miembro
     * @return Objeto Miembro
     */
    public Miembro getMiembroReserva() {
        return miembroReserva;
    }

    /**
     * Setter del objeto Miembro
     * @param miembroReserva Ingresa objeto Miembro para settearlo en el objeto Reserva
     */
    public void setMiembroReserva(Miembro miembroReserva) {
        this.miembroReserva = miembroReserva;
    }

    /**
     * Getter del objeto Clase
     * @return Objeto Clase
     */
    public Clase getClaseReserva() {
        return claseReserva;
    }

    /**
     * Setter del objeto Clase
     * @param claseReserva Ingresa objeto Clase para settearlo en el objeto Reserva
     */
    public void setClaseReserva(Clase claseReserva) {
        this.claseReserva = claseReserva;
    }
}
