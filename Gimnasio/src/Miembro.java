import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Miembro {
    private String nombreMiembro;
    private String apellidoMiembro;
    private int idMiembro;
    private int nroDocumento;
    private String email;
    private String fechaNacimiento;
    private String fechaInscripcion;
    private String tipoMembresia;
    private Suscripcion estadoSuscripcion;
    private String condicion;

    static ArrayList<Integer> idsUsadas = new ArrayList<>();

    public Miembro(String nombreMiembro, String apellidoMiembro, int idMiembro, int nroDocumento, String email, String fechaNacimiento, String fechaInscripcion, String tipoMembresia, Suscripcion estadoSuscripcion, String condicion) {
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

    public static Miembro registrarMiembro(Gimnasio gimnasio1) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre del Miembro: ");
        String nombreMiembro1 = sc.nextLine();

        System.out.print("Apellido del Miembro: ");
        String apellidoMiembro1 = sc.nextLine();

        System.out.print("Email del Miembro: ");
        String email1 = sc.nextLine();

        System.out.print("Tipo de Membresia: ");
        String tipoMembresia1 = sc.nextLine();

        System.out.print("Condicion del Usuario: ");
        String condicion1 = sc.nextLine();

        boolean existe = false;
        System.out.println("ID del Miembro: ");
        int idMiembro1 = sc.nextInt();
        do {
            existe = idsUsadas.contains(idMiembro1);
            if (existe) {
                System.out.println("ID ya utilizada, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) {
                    return null;
                }
                idMiembro1 = sc.nextInt();
            } else {
                break;
            }
        } while (existe);

        System.out.print("Nro de Documento: ");
        int nroDocumento1 = sc.nextInt();

        System.out.print("Fecha Nacimiento: ");
        String fechaNacimiento1 = sc.nextLine();
        do {
            if (fechaNacimiento1.length() != 8) {
                System.out.println("Fecha de Nacimiento invalida, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) {
                    return null;
                }
                fechaNacimiento1 = sc.nextLine();
            } else {
                break;
            }
        } while (fechaNacimiento1.length() != 8);

        System.out.print("Fecha Inscripcion: ");
        String fechaInscripcion1 = sc.nextLine();
        do {
            if (fechaInscripcion1.length() != 8) {
                System.out.println("Fecha de Inscripcion invalida, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) {
                    return null;
                }
                fechaInscripcion1 = sc.nextLine();
            } else {
                break;
            }
        } while (fechaInscripcion1.length() != 8);

        Miembro miembro1 = new Miembro(nombreMiembro1, apellidoMiembro1, idMiembro1, nroDocumento1, email1, fechaNacimiento1, fechaInscripcion1, tipoMembresia1, null, condicion1);
        System.out.println();
        idsUsadas.add(idMiembro1);

        gimnasio1.listaMiembros.add(miembro1);
        return miembro1;
    }

    ;

    public static Miembro actualizarInformacion(Miembro miembro1, Gimnasio gimnasio1) {
        Scanner sc1 = new Scanner(System.in);
        int opcion = 0;
        System.out.println("¿Que dato desea actualizar?: ");
        System.out.println("1.Nombre ");
        System.out.println("2.Apellido ");
        System.out.println("3.Email ");
        System.out.println("4.Tipo de Membresia ");
        System.out.println("5.Condicion del Usuario ");
        System.out.println("6. Nro de Documento ");
        System.out.println("7. Fecha de Nacimiento ");
        System.out.println("8. Fecha de Inscripcion ");
        System.out.println("9. ID del Miembro ");

        for (Miembro miembro : gimnasio1.listaMiembros) {
            if (miembro1 == miembro) {
                gimnasio1.listaMiembros.remove(miembro);
            }
        }

        do {
            try {
                opcion = sc1.nextInt();
                sc1.nextLine();
                if (opcion == 1) {
                    String nombre1 = sc1.nextLine();
                    miembro1.setNombreMiembro(nombre1);
                } else if (opcion == 2) {
                    String apellido1 = sc1.nextLine();
                    miembro1.setApellidoMiembro(apellido1);
                } else if (opcion == 3) {
                    String email1 = sc1.nextLine();
                    miembro1.setEmail(email1);
                } else if (opcion == 4) {
                    String tipo1 = sc1.nextLine();
                    miembro1.setTipoMembresia(tipo1);
                } else if (opcion == 5) {
                    String condicion1 = sc1.nextLine();
                    miembro1.setCondicion(condicion1);
                } else if (opcion == 6) {
                    int dni1 = sc1.nextInt();
                    miembro1.setNroDocumento(dni1);
                    sc1.nextLine();
                } else if (opcion == 7) {
                    String fechaNacimiento1 = sc1.nextLine();
                    miembro1.setFechaNacimiento(fechaNacimiento1);
                } else if (opcion == 8) {
                    String fechaInscripcion1 = sc1.nextLine();
                    miembro1.setFechaInscripcion(fechaInscripcion1);
                } else if (opcion == 9) {
                    int idMiembro1 = sc1.nextInt();
                    miembro1.setIdMiembro(idMiembro1);
                } else {
                    System.out.println("Opcion invalida. Ingrese de nuevo.");
                }
            } catch (InputMismatchException e1) {
                sc1.nextLine();
                System.out.println("Opcion invalida. Ingrese de nuevo.");
            }
        } while ((opcion > 9) || (opcion < 1));
        gimnasio1.listaMiembros.add(miembro1);

        for (Clase clase : gimnasio1.listaClases) {
            for (Miembro miembro : clase.listaMiembros) {
                if (miembro.getIdMiembro() == miembro1.getIdMiembro()) {
                    clase.listaMiembros.remove(miembro);
                    clase.listaMiembros.add(miembro1);
                }
            }
        }

        return miembro1;
    }

    public static void imprimirMiembro(Miembro miembro1) {
        System.out.println("Nombre: " + miembro1.getNombreMiembro());
        System.out.println("Apellido: " + miembro1.getApellidoMiembro());
        System.out.println("Email: " + miembro1.getEmail());
        System.out.println("Tipo de Membresia: " + miembro1.getTipoMembresia());
        if (miembro1.getEstadoSuscripcion() == null){
            System.out.println("El miembro aún no está suscripto.");
        } else if (miembro1.estadoSuscripcion.isEstadoSuscripcion()) {
            System.out.println("Estado de suscripción: Pagado");
        } else {
            System.out.println("Estado de suscripción: Sin pagar");
        }

        System.out.println("Estado de la suscipcion: ");
        System.out.println("Condicion: " + miembro1.getCondicion());
        System.out.println("Nro de Documento: " + miembro1.getNroDocumento());
        System.out.println("Fecha de Nacimiento: " + formatearFecha(miembro1.getFechaNacimiento()));
        System.out.println("Fecha de Inscripcion: " + formatearFecha(miembro1.getFechaInscripcion()));
        System.out.println("ID: " + miembro1.getIdMiembro());
        System.out.println();
    }

    public static Miembro searchMiembroInList(ArrayList<Miembro> gimnasio, int idMiembro) {
        for (Miembro miembro : gimnasio) {
            if (miembro.getIdMiembro() == idMiembro) {
                return miembro;
            }
        }
        return null;
    }

    public static Integer askMiembroId(Gimnasio gimnasio1){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del miembro: ");
        int id;
        boolean existe;
        do {
            id = sc.nextInt();
            if (Miembro.searchMiembroInList(gimnasio1.listaMiembros, id) == null) {
                System.out.println("El ID del miembro no existe");
                existe = false;
                if (Gimnasio.consultaOperacion()) {return 0;}
            } else {
                existe = true;
                return id;
            }
        } while (!existe);
        return 0;
    }

    public static String formatearFecha(String numero) {
        String numeroStr = numero;

        String dia = numeroStr.substring(0, 2);
        String mes = numeroStr.substring(2, 4);
        String anio = numeroStr.substring(4, 8);

        return dia + "/" + mes + "/" + anio;
    }

    public String getNombreMiembro() {
        return nombreMiembro;
    }

    public void setNombreMiembro(String nombreMiembro) {
        this.nombreMiembro = nombreMiembro;
    }

    public String getApellidoMiembro() {
        return apellidoMiembro;
    }

    public void setApellidoMiembro(String apellidoMiembro) {
        this.apellidoMiembro = apellidoMiembro;
    }

    public int getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public Suscripcion getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    public void setEstadoSuscripcion(Suscripcion estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
}

