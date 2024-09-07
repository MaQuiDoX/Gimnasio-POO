import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Suscripcion {
    private int idSuscripcion;
    private int idMiembro;
    private int costoSuscripcion;
    private String tipoSuscripcion;
    private String fechaInicio;
    private String fechaFin;
    public boolean estadoSuscripcion;

    static ArrayList<Integer> idsUsadas;

    public Suscripcion(int idSuscripcion, int idMiembro, int costoSuscripcion, String tipoSuscripcion, String fechaInicio, String fechaFin, boolean estadoSuscripcion) {
        this.idSuscripcion = idSuscripcion;
        this.idMiembro = idMiembro;
        this.costoSuscripcion = costoSuscripcion;
        this.tipoSuscripcion = tipoSuscripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoSuscripcion = estadoSuscripcion;
    }


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
        String fechaInicio = sc.nextLine();
        do {
            if (fechaInicio.length() != 8) {
                System.out.println("Fecha de inicio invalida, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return; }
                fechaInicio = sc.nextLine();
            } else {
                break;
            }
        } while (fechaInicio.length() != 8);

        System.out.print("Fecha de vencimiento de suscripción: ");
        String fechaVencimiento = sc.nextLine();
        do {
            if (fechaVencimiento.length() != 8) {
                System.out.println("Fecha de vencimiento invalida, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return; }
                fechaVencimiento = sc.nextLine();
            } else {
                break;
            }
        } while (fechaVencimiento.length() != 8);

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

    public static void pagarSuscripcion(Gimnasio gimnasio1){
        Scanner sc = new Scanner(System.in);

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

        Suscripcion auxSuscripcion = miembroSub.getEstadoSuscripcion();
        auxSuscripcion.setEstadoSuscripcion(true);
        miembroSub.setEstadoSuscripcion(auxSuscripcion);

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

    public static void cancelarSuscripcion(Gimnasio gimnasio1){
        Scanner sc = new Scanner(System.in);

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

        miembroSub.setEstadoSuscripcion(null);


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

        Miembro finalMiembroSub = miembroSub;
        gimnasio1.listaReserva.removeIf(reserva -> reserva.getMiembroReserva().getIdMiembro() == finalMiembroSub.getIdMiembro());
    }


    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public int getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    public int getCostoSuscripcion() {
        return costoSuscripcion;
    }

    public void setCostoSuscripcion(int costoSuscripcion) {
        this.costoSuscripcion = costoSuscripcion;
    }

    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(String tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    public void setEstadoSuscripcion(boolean estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }
}
