import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Miembro {
    private String nombreMiembro;
    private String apellidoMiembro;
    private int idMiembro;
    private int nroDocumento;
    private String email;
    private int fechaNacimiento;
    private int fechaInscripcion;
    private String tipoMembresia;
    private String estadoSuscripcion;
    private String condicion;

    static ArrayList<Integer> idsUsadas = new ArrayList<>();

    public Miembro(String nombreMiembro, String apellidoMiembro, int idMiembro, int nroDocumento, String email, int fechaNacimiento, int fechaInscripcion, String tipoMembresia, String estadoSuscripcion, String condicion) {
        this.nombreMiembro = nombreMiembro;
        this.apellidoMiembro = apellidoMiembro;
        this.idMiembro = idMiembro;
        this.nroDocumento = nroDocumento;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaInscripcion = fechaInscripcion;
        this.tipoMembresia = tipoMembresia;
        this.estadoSuscripcion = estadoSuscripcion;
        this.condicion = condicion;
    }

    public static Miembro registrarMiembro(Gimnasio gimnasio1){
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre del Miembro: ");
        String nombreMiembro1 = sc.nextLine();

        System.out.print("Apellido del Miembro: ");
        String apellidoMiembro1 = sc.nextLine();

        System.out.print("Email del Miembro: ");
        String email1 = sc.nextLine();

        System.out.print("Tipo de Membresia: ");
        String tipoMembresia1 = sc.nextLine();

        System.out.print("Estado de Suscripcion: ");
        String estadoSuscripcion1 = sc.nextLine();

        System.out.print("Condicion del Usuario: ");
        String condicion1 = sc.nextLine();

        boolean existe = false;
        System.out.println("ID del Miembro: ");
        int idMiembro1 = sc.nextInt();
        do {
            existe = idsUsadas.contains(idMiembro1);
            if (existe) {
                System.out.println("ID ya utilizada, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return null; }
                idMiembro1 = sc.nextInt();
            } else {
                idsUsadas.add(idMiembro1);
                break;
            }
        } while (existe);

        System.out.print("Nro de Documento: ");
        int nroDocumento1 = sc.nextInt();

        System.out.print("Fecha Nacimiento: ");
        int fechaNacimiento1 = sc.nextInt();
        do {
            if (Integer.toString(fechaNacimiento1).length() != 8) {
                System.out.println("Fecha de Nacimiento invalida, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return null; }
                fechaNacimiento1 = sc.nextInt();
            } else {
                break;
            }
        } while (Integer.toString(fechaNacimiento1).length() != 8);

        System.out.print("Fecha Inscripcion: ");
        int fechaInscripcion1 = sc.nextInt();
        do {
            if (Integer.toString(fechaInscripcion1).length() != 8) {
                System.out.println("Fecha de Inscripcion invalida, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) { return null; }
                fechaInscripcion1 = sc.nextInt();
            } else {
                break;
            }
        } while (Integer.toString(fechaInscripcion1).length() != 8);

        Miembro miembro1 = new Miembro(nombreMiembro1,apellidoMiembro1,idMiembro1,nroDocumento1,email1,fechaNacimiento1,fechaInscripcion1,tipoMembresia1,estadoSuscripcion1,condicion1);
        System.out.println();

        gimnasio1.listaMiembros.add(miembro1);
        return miembro1;
    };

    public static Miembro actualizarInformacion(Miembro miembro1, Gimnasio gimnasio1){
        Scanner sc1 = new Scanner(System.in);
        int opcion = 0;
        System.out.println("¿Que dato desea actualizar?: ");
        System.out.println("1.Nombre ");
        System.out.println("2.Apellido ");
        System.out.println("3.Email ");
        System.out.println("4.Tipo de Membresia ");
        System.out.println("5.Estado de Suscripcion ");
        System.out.println("6.Condicion del Usuario ");
        System.out.println("7. Nro de Documento ");
        System.out.println("8. Fecha de Nacimiento ");
        System.out.println("9. Fecha de Inscripcion ");
        System.out.println("10. ID del Miembro ");

        for (Miembro miembro : gimnasio1.listaMiembros) {
            if (miembro1 == miembro){
                gimnasio1.listaMiembros.remove(miembro);
            }
        }

        do{
            try{
                opcion = sc1.nextInt();
                sc1.nextLine();
                if (opcion == 1){
                    String nombre1 = sc1.nextLine();
                    miembro1.setNombreMiembro(nombre1);
                } else if (opcion == 2){
                    String apellido1 = sc1.nextLine();
                    miembro1.setApellidoMiembro(apellido1);
                } else if (opcion == 3) {
                    String email1 = sc1.nextLine();
                    miembro1.setEmail(email1);
                } else if (opcion == 4){
                    String tipo1 = sc1.nextLine();
                    miembro1.setTipoMembresia(tipo1);
                } else if (opcion == 5){
                    String estado1 = sc1.nextLine();
                    miembro1.setEstadoSuscripcion(estado1);
                } else if (opcion == 6){
                    String condicion1 = sc1.nextLine();
                    miembro1.setCondicion(condicion1);
                } else if (opcion == 7){
                    int dni1 = sc1.nextInt();
                    miembro1.setNroDocumento(dni1);
                } else if (opcion == 8){
                    int fechaNacimiento1 = sc1.nextInt();
                    miembro1.setFechaNacimiento(fechaNacimiento1);
                } else if (opcion == 9){
                    int fechaInscripcion1 = sc1.nextInt();
                    miembro1.setFechaInscripcion(fechaInscripcion1);
                } else if (opcion == 10){
                    int idMiembro1 = sc1.nextInt();
                    miembro1.setIdMiembro(idMiembro1);
                } else {
                    System.out.println("Opcion invalida. Ingrese de nuevo.");
                }
            } catch (InputMismatchException e1){
                sc1.nextLine();
                System.out.println("Opcion invalida. Ingrese de nuevo.");
            }
        } while ((opcion > 10) || (opcion < 1));
        gimnasio1.listaMiembros.add(miembro1);
        return miembro1;
    }

    public static void imprimirMiembro(Miembro miembro1){
        System.out.println("Nombre: " + miembro1.getNombreMiembro());
        System.out.println("Apellido: " + miembro1.getApellidoMiembro());
        System.out.println("Email: " + miembro1.getEmail());
        System.out.println("Tipo de Membresia: " + miembro1.getTipoMembresia());
        System.out.println("Estado: " + miembro1.getEstadoSuscripcion());
        System.out.println("Condicion: " + miembro1.getCondicion());
        System.out.println("Nro de Documento: " + miembro1.getNroDocumento());
        System.out.println("Fecha de Nacimiento: " + formatearFecha(miembro1.getFechaNacimiento()));
        System.out.println("Fecha de Inscripcion: " + formatearFecha(miembro1.getFechaInscripcion()));
        System.out.println("ID: " + miembro1.getIdMiembro());
        System.out.println();
    }

    public static Miembro searchMiembroInList(ArrayList<Miembro> gimnasio, int idMiembro){
        for (Miembro miembro : gimnasio){
            if (miembro.getIdMiembro() == idMiembro){
                return miembro;
            }
        } return null;
    }

    public static String formatearFecha(int numero) {
        String numeroStr = String.format("%06d", numero);

        String dia = numeroStr.substring(0, 2);
        String mes = numeroStr.substring(2, 4);
        String anio = numeroStr.substring(4, 8);

        return dia + "/" + mes + "/" + anio;
    }

    public String getNombreMiembro() {
        return nombreMiembro;
    }

    public String getApellidoMiembro() {
        return apellidoMiembro;
    }

    public int getIdMiembro() {
        return idMiembro;
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getFechaInscripcion() {
        return fechaInscripcion;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public String getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setNombreMiembro(String nombreMiembro) {
        this.nombreMiembro = nombreMiembro;
    }

    public void setApellidoMiembro(String apellidoMiembro) {
        this.apellidoMiembro = apellidoMiembro;
    }

    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaInscripcion(int fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public void setEstadoSuscripcion(String estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
}
