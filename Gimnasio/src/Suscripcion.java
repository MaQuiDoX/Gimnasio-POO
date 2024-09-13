import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que representa la suscripción
 * @author Manuel Matías Quesada Riccieri
 */
public class Suscripcion {
    private int idSuscripcion;
    private int idMiembro;
    private int costoSuscripcion;
    private String tipoSuscripcion;
    private String fechaInicio;
    private String fechaFin;
    public boolean estadoSuscripcion;

    static ArrayList<Integer> idsUsadas;

    /**
     * Constructor de suscripción, recibe parámetros
     * @param idSuscripcion id de la Suscripción
     * @param idMiembro id del Miembro asociado
     * @param costoSuscripcion costo de la Suscripción
     * @param tipoSuscripcion tipo de Suscripción
     * @param fechaInicio fecha en la que inicia la suscripción
     * @param fechaFin fecha en la que vence la suscripción
     * @param estadoSuscripcion estado de la Suscripción
     */
    public Suscripcion(int idSuscripcion, int idMiembro, int costoSuscripcion, String tipoSuscripcion, String fechaInicio, String fechaFin, boolean estadoSuscripcion) {
        this.idSuscripcion = idSuscripcion;
        this.idMiembro = idMiembro;
        this.costoSuscripcion = costoSuscripcion;
        this.tipoSuscripcion = tipoSuscripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoSuscripcion = estadoSuscripcion;
    }

    /**
     * Función para registrar una nueva suscripción, pide al usuario todos los parametros individuales y llama al constructor para generar una suscripción
     * @param gimnasio1 Entra a la función para trabajar con sus listas asociadas
     */
    public static void registrarSuscripcion(Gimnasio gimnasio1){
        Scanner sc = new Scanner(System.in);

        boolean existe = false;
        System.out.println("Ingrese el ID de la suscripción: ");
        int idSuscripcion = sc.nextInt();
        do {
            existe = idsUsadas.contains(idSuscripcion);
            if (existe) {
                System.out.println("ID ya utilizada, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return; }
                idSuscripcion = sc.nextInt();
            } else {
                break;
            }
        } while (existe);

        System.out.println("Ingrese el ID del miembro que desea suscribir: ");
        int idMiembro;
        Miembro miembroSub;

        boolean existe1;
        boolean suscripto1 = false;
        do {
            idMiembro = sc.nextInt();
            miembroSub = Miembro.searchMiembroInList(gimnasio1.getListaMiembros(),idMiembro);

            // Este parrafo chequea si el miembro existe
            if (miembroSub == null){
                System.out.println("El miembro no existe");
                existe1 = false;
            } else {
                existe1 = true;
            }

            // Esto parrafo chequea si el miembro ya está suscripto
            if ((existe1)&&(miembroSub.getEstadoSuscripcion() != null)) {
                System.out.println("Este miembro ya cuenta con una suscripción");
                suscripto1 = true;
            } else if ((existe1)&&(miembroSub.getEstadoSuscripcion() == null)) {
                suscripto1 = false;
            }

            if ((!existe1) || (suscripto1)) {
                if (Gimnasio.consultaOperacion()) { return; }
            }

        }while ((!existe1)||(suscripto1));

        sc.nextLine();
        System.out.println("Ingrese el tipo de suscripción: ");
        String tipoSuscripcion = sc.nextLine();

        System.out.println("Ingrese el costo de la suscripción: ");
        int costoSuscripcion = sc.nextInt();
        sc.nextLine();

        System.out.print("Fecha de inicio de suscripción: ");
        String fechaInicio = Miembro.getterFecha();

        System.out.print("Fecha de vencimiento de suscripción: ");
        String fechaVencimiento = Miembro.getterFecha();

        char opcion = ' ';
        boolean estadoSuscripcion = false;
        System.out.println("¿La suscripción ya ha sido pagada?: (y/n)");
        do{
            opcion = sc.next().charAt(0);
            sc.nextLine();
            try{
                if (opcion == 'y'){
                    estadoSuscripcion = true;
                    break;
                } else if (opcion == 'n'){
                    break;
                } else {
                    System.out.println("Opcion invalida. Ingrese de nuevo. ");
                }
            } catch (InputMismatchException e1) {
                sc.nextLine();
                System.out.println("Opcion invalida. Ingrese de nuevo. ");
            }
        }while ((opcion != 'y'));



        Suscripcion suscripcionFinal = new Suscripcion(idSuscripcion,idMiembro,costoSuscripcion,tipoSuscripcion,fechaInicio,fechaVencimiento,estadoSuscripcion);
        miembroSub.setEstadoSuscripcion(suscripcionFinal);

        // ACTUALIZADOR DE MIEMBRO
        int finalIdMiembro = idMiembro;
        gimnasio1.listaMiembros.removeIf(miembro -> miembro.getIdMiembro() == finalIdMiembro);
        gimnasio1.listaMiembros.add(miembroSub);

        for (Clase clase : gimnasio1.listaClases){
            for (Miembro miembro : clase.listaMiembros){
                if (miembro.getIdMiembro() == miembroSub.getIdMiembro()){
                    clase.listaMiembros.remove(miembro);
                    clase.listaMiembros.add(miembroSub);
                }
            }
        }
    }

    /**
     * Función que actualiza el objeto suscripción dependiendo del Miembro y del estado de pago de la Suscripción. Actualiza al miembro que cuente con la suscripción de sus respectivas listas.
     * @param gimnasio1 Entra a la función para trabajar con sus listas asociadas
     */
    public static void pagarSuscripcion(Gimnasio gimnasio1){
        Scanner sc = new Scanner(System.in);

        // Ingresa la ID del miembro
        System.out.println("Ingrese el ID del miembro el cual desea pagar su suscripción: ");
        int idMiembro;
        Miembro miembroSub;

        boolean existe1;
        boolean suscripto1 = false;
        boolean pagado1 = false;
        do {
            idMiembro = sc.nextInt();
            miembroSub = Miembro.searchMiembroInList(gimnasio1.getListaMiembros(),idMiembro);

            // Este parrafo chequea si el miembro existe
            if (miembroSub == null){
                System.out.println("El miembro no existe");
                existe1 = false;
            } else {
                existe1 = true;
            }

            // Esto parrafo chequea si el miembro ya está suscripto
            if ((existe1)&&(miembroSub.getEstadoSuscripcion() == null)) {
                System.out.println("Este miembro no cuenta con una suscripción");
                suscripto1 = true;
            } else if ((existe1)&&(miembroSub.getEstadoSuscripcion() != null)) {
                suscripto1 = false;
            }

            // Este parrafo chequea si la suscripción del miembro ya está pagada
            if ((existe1) && (miembroSub.getEstadoSuscripcion().isEstadoSuscripcion())){
                System.out.println("Este miembro ya ha pagado su suscripción");
                pagado1 = true;
            } else if ((existe1) && (!miembroSub.getEstadoSuscripcion().isEstadoSuscripcion())) {
                pagado1 = false;
            }

            if ((!existe1) || (suscripto1) || (pagado1)) {
                if (Gimnasio.consultaOperacion()) { return; }
            }
        }while ((!existe1)||(suscripto1) || (pagado1));

        // Creo un objeto auxiliar para settear cambios y luego acoplarlos al miembro
        Suscripcion auxSuscripcion = miembroSub.getEstadoSuscripcion();

        // Determino la suscripción como pagada
        auxSuscripcion.setEstadoSuscripcion(true);

        // Extiendo la fecha de caducidad 1 mes
        String fechaFin1 = miembroSub.getEstadoSuscripcion().getFechaFin();
        String fechaFinSuma = sumar30Dias(fechaFin1);
        auxSuscripcion.setFechaFin(fechaFinSuma);

        // Setteo el nuevo objeto
        miembroSub.setEstadoSuscripcion(auxSuscripcion);

        // Actualizo al miembro en la lista de Miembros del gimnasio
        int finalIdMiembro = idMiembro;
        gimnasio1.listaMiembros.removeIf(miembro -> miembro.getIdMiembro() == finalIdMiembro);
        gimnasio1.listaMiembros.add(miembroSub);

        // Actualizo al miembro en la lista de Clases del gimnasio
        for (Clase clase : gimnasio1.listaClases){
            for (Miembro miembro : clase.listaMiembros){
                if (miembro.getIdMiembro() == miembroSub.getIdMiembro()){
                    clase.listaMiembros.remove(miembro);
                    clase.listaMiembros.add(miembroSub);
                }
            }
        }
    }

    /**
     * Accede a las listas del gimnasio para eliminar por completo la existencia del objeto Miembro que cuenten con suscripción y actualiza las listas
     * @param gimnasio1
     */
    public static void cancelarSuscripcion(Gimnasio gimnasio1){
        Scanner sc = new Scanner(System.in);

        // Ingreso de ID del miembro
        System.out.println("Ingrese el ID del miembro el cual desea cancelar la suscripción: ");
        int idMiembro;
        Miembro miembroSub;

        boolean existe1;
        boolean suscripto1 = false;
        do {
            idMiembro = sc.nextInt();
            miembroSub = Miembro.searchMiembroInList(gimnasio1.getListaMiembros(),idMiembro);

            // Este parrafo chequea si el miembro existe
            if (miembroSub == null){
                System.out.println("El miembro no existe");
                existe1 = false;
            } else {
                existe1 = true;
            }

            // Esto parrafo chequea si el miembro ya está suscripto
            if ((existe1)&&(miembroSub.getEstadoSuscripcion() == null)) {
                System.out.println("Este miembro no cuenta con una suscripción");
                suscripto1 = true;
            } else if ((existe1)&&(miembroSub.getEstadoSuscripcion() != null)) {
                suscripto1 = false;
            }

            if ((!existe1) || (suscripto1)) {
                if (Gimnasio.consultaOperacion()) { return; }
            }
        }while ((!existe1)||(suscripto1));

        // Elimina la suscripción del miembro
        miembroSub.setEstadoSuscripcion(null);

        // Actualiza al miembro en la lista del gimnasio
        int finalIdMiembro = idMiembro;
        gimnasio1.listaMiembros.removeIf(miembro -> miembro.getIdMiembro() == finalIdMiembro);
        gimnasio1.listaMiembros.add(miembroSub);

        // Actualiza al miembro que se encuentra en las listas de miembros de las clases, dentro de la lista de clases del gimnasio
        for (Clase clase : gimnasio1.listaClases){
            for (Miembro miembro : clase.listaMiembros){
                if (miembro.getIdMiembro() == miembroSub.getIdMiembro()){
                    clase.listaMiembros.remove(miembro);
                    clase.listaMiembros.add(miembroSub);
                }
            }
        }

        // Elimina las reservas de la listas de reservas del gimnasio que cuenten con el miembro
        Miembro finalMiembroSub = miembroSub;
        gimnasio1.listaReserva.removeIf(reserva -> reserva.getMiembroReserva().getIdMiembro() == finalMiembroSub.getIdMiembro());
    }

    /**
     * Función que añade 30 días a una fecha
     * @param fecha Fecha en formato DDMMAAAA
     * @return Fecha en formato String con 30 dias añadidos
     */
    public static String sumar30Dias(String fecha) {
        int dia = Integer.parseInt(fecha.substring(0, 2));
        int mes = Integer.parseInt(fecha.substring(2, 4));
        int anio = Integer.parseInt(fecha.substring(4, 8));

        // Array con el número de días por mes
        int[] diasPorMes = {31, Miembro.esBisiesto(anio) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        dia += 30;

        // Ajustar días y meses si es necesario
        while (dia > diasPorMes[mes - 1]) {
            // Restar los días del mes actual
            dia -= diasPorMes[mes - 1];

            // Pasar al siguiente mes
            mes++;

            // Si el mes es mayor que 12, se cambia de año
            if (mes > 12) {
                mes = 1;
                anio++;
                diasPorMes[1] = Miembro.esBisiesto(anio) ? 29 : 28;
            }
        }

        // Formatear el día y mes para que tengan siempre dos dígitos
        String diaFormateado = String.format("%02d", dia);
        String mesFormateado = String.format("%02d", mes);

        // Retornar la nueva fecha en formato DDMMAAAA
        return diaFormateado + mesFormateado + anio;
    }

    /**
     * Getter de ID de suscripción
     * @return ID de suscripción
     */
    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    /**
     * Setter de ID de suscripción
     * @param idSuscripcion Ingresa ID de suscripción para settearla en el objeto Suscripción
     */
    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    /**
     * Getter de ID de miembro
     * @return ID de miembro
     */
    public int getIdMiembro() {
        return idMiembro;
    }

    /**
     * Setter de ID de miembro
     * @param idMiembro Ingresa ID de miembro para settearla en el objeto Suscripción
     */
    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    /**
     * Getter de costo de suscripción
     * @return Costo de suscripción
     */
    public int getCostoSuscripcion() {
        return costoSuscripcion;
    }

    /**
     * Setter de costo de suscripción
     * @param costoSuscripcion Ingresa el costo de la suscripción para settearla en el objeto Suscripción
     */
    public void setCostoSuscripcion(int costoSuscripcion) {
        this.costoSuscripcion = costoSuscripcion;
    }

    /**
     * Getter de Tipo de suscripción
     * @return Tipo de suscripción
     */
    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    /**
     * Setter de tipo de suscripción
     * @param tipoSuscripcion Ingresa el tipo de la suscripción para settearla en el objeto Suscripción
     */
    public void setTipoSuscripcion(String tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }

    /**
     * Getter de fecha de Inicio
     * @return Fecha de inicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Setter de fecha de Inicio
     * @param fechaInicio Ingresa la fecha de inicio de la suscripción para settearla en el objeto Suscripción
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Getter de fecha de caducidad
     * @return fecha de caducidad
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Setter de fecha de caducidad
     * @param fechaFin Ingrea la fecha de caducidad de la suscripción para settearla en el objeto Suscripción
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Getter del estado de la suscripción
     * @return Estado de la suscripción
     */
    public boolean isEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    /**
     * Setter del estado de la suscripción
     * @param estadoSuscripcion Ingresa el estado de la suscripción para settearla en el objeto Suscripción
     */
    public void setEstadoSuscripcion(boolean estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }
}
