import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Clase que representa a los miembros
 * @author Manuel Matías Quesada Riccieri
 */
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

    /**
     * Constructor de miembro, recibe parametros
     * @param nombreMiembro Nombre del miembro
     * @param apellidoMiembro Apellido del miembro
     * @param idMiembro ID del miembro
     * @param nroDocumento Número de documento del miembro
     * @param email Email del miembro
     * @param fechaNacimiento Fecha de nacimiento del miembro
     * @param fechaInscripcion Fecha de inscripcion del miembro
     * @param tipoMembresia Tipo de membresia del miembro
     * @param estadoSuscripcion Estado de suscripción del miembro, objeto Suscripción
     * @param condicion Condicion del miembro
     */
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

    /**
     * Función para registrar a un nuevo miembro, pide al usuario todos los parametros individuales y llama al constructor para generar un nuevo miembro
     * @param gimnasio1 Entra a la función para trabajar con sus listas asociadas
     * @return Objeto miembro inicializado
     */
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
        String fechaNacimiento1 = getterFecha();

        System.out.print("Fecha Inscripcion: ");
        String fechaInscripcion1 = getterFecha();

        Miembro miembro1 = new Miembro(nombreMiembro1, apellidoMiembro1, idMiembro1, nroDocumento1, email1, fechaNacimiento1, fechaInscripcion1, tipoMembresia1, null, condicion1);
        System.out.println();
        idsUsadas.add(idMiembro1);

        gimnasio1.listaMiembros.add(miembro1);
        return miembro1;
    };

    /**
     * Función para actualizar determinado dato seleccionado por el usuario, actualiza al miembro en sus respectivas listas
     * @param miembro1 Miembro al que se le editará algun elemento
     * @param gimnasio1 Entra a la función para trabajar con sus listas asociadas
     * @return Objeto miembro actualizado
     */
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
                    String fechaNacimiento1 = getterFecha();
                    miembro1.setFechaNacimiento(fechaNacimiento1);
                } else if (opcion == 8) {
                    String fechaInscripcion1 = getterFecha();
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

    /**
     * Función que imprime todos los parametros del objeto Miembro
     * @param miembro1 Objeto miembro
     */
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
        System.out.println("Condicion: " + miembro1.getCondicion());
        System.out.println("Nro de Documento: " + miembro1.getNroDocumento());
        System.out.println("Fecha de Nacimiento: " + formatearFecha(miembro1.getFechaNacimiento()));
        System.out.println("Fecha de Inscripcion: " + formatearFecha(miembro1.getFechaInscripcion()));
        System.out.println("ID: " + miembro1.getIdMiembro());
        System.out.println();
    }

    /**
     * Función para buscar un Objeto Miembro en una lista de Miembros
     * @param gimnasio Lista de Miembros
     * @param idMiembro ID del miembro a buscar
     * @return Objeto Miembro si se encuentra en la lista, null en caso contrario
     */
    public static Miembro searchMiembroInList(ArrayList<Miembro> gimnasio, int idMiembro) {
        for (Miembro miembro : gimnasio) {
            if (miembro.getIdMiembro() == idMiembro) {
                return miembro;
            }
        }
        return null;
    }

    /**
     * Consulta si el miembro ya existe en el gimnasio a partir de su ID
     * @param gimnasio1 Entra a la función para consultar la lista asociada a miembros
     * @return Retorna la ID en caso de encontrarla, 0 en caso de no encontrar la ID y no querer continuar con la operación
     */
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

    /**
     * Entra un String en formato DDMMAAAA para devolver un String en formato DD/MM/AAAA para la fecha
     * @param numeroStr String de fecha
     * @return String de fecha corregido
     */
    public static String formatearFecha(String numeroStr) {

        String dia = numeroStr.substring(0, 2);
        String mes = numeroStr.substring(2, 4);
        String anio = numeroStr.substring(4, 8);

        return dia + "/" + mes + "/" + anio;
    }

    /**
     * Función que consulta al usuario el dia, mes y año individualmente para construir una fecha correctamente, y devolver un String en el formato asignado
     * @return Fecha en formato DDMMAAAA
     */
    public static String getterFecha(){
        Scanner sc = new Scanner(System.in);

        System.out.print("(AAAA) Año: ");
        int anio = sc.nextInt();
        do {
            if (String.valueOf(anio).length() != 4) {
                System.out.print("Año incorrecto, ingrese nuevamente: ");
                anio = sc.nextInt();
            }
        } while (String.valueOf(anio).length() != 4);

        System.out.print("(MM) Mes: ");
        int mes = sc.nextInt();
        do {
            if ((String.valueOf(mes).length() > 2)||((mes < 1)||(mes > 12))) {
                System.out.print("Mes incorrecto, ingrese nuevamente: ");
                mes = sc.nextInt();
            }
        } while ((String.valueOf(mes).length() > 2)||((mes < 1)||(mes > 12)));

        System.out.print("(DD) Dia: ");
        int dia = sc.nextInt();
        do {
            if ((String.valueOf(dia).length() > 2)||((dia < 1)||(dia > obtenerDiasEnMes(anio,mes)))){
                System.out.print("Dia incorrecto, ingrese nuevamente: ");
                dia = sc.nextInt();
            }
        } while ((String.valueOf(dia).length() > 2)||((dia < 1)||(dia > obtenerDiasEnMes(anio,mes))));

        String diaStr = String.valueOf(dia);
        if (diaStr.length() == 1){ diaStr = "0" + diaStr;}

        String mesStr = String.valueOf(mes);
        if (mesStr.length() == 1){ mesStr = "0" + mesStr;}

        String anioStr = String.valueOf(anio);

        String fechaStr = diaStr + mesStr + anioStr;
        sc.nextLine();
        return fechaStr;

    }

    /**
     * Función auxiliar que dado un mes y un año, devuelve la cantidad de dias que posee dicho mes.
     * @param year Parametro de año
     * @param month Parametro de mes
     * @return Dias del mes
     */
    public static int obtenerDiasEnMes(int year, int month) {
        switch (month) {
            case 1: // Enero
            case 3: // Marzo
            case 5: // Mayo
            case 7: // Julio
            case 8: // Agosto
            case 10: // Octubre
            case 12: // Diciembre
                return 31;
            case 4: // Abril
            case 6: // Junio
            case 9: // Septiembre
            case 11: // Noviembre
                return 30;
            case 2: // Febrero
                return esBisiesto(year) ? 29 : 28;
            default:
                return -1;
        }
    }

    /**
     * Función que comprueba si un año es bisiesto
     * @param year Parametro de año
     * @return True si el año es bisiesto, False en caso contrario
     */
    public static boolean esBisiesto(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter del nombre del miembro
     * @return Nombre del miembro
     */
    public String getNombreMiembro() {
        return nombreMiembro;
    }

    /**
     * Setter del nombre del miembro
     * @param nombreMiembro Ingresa el nombre del miembro para settearlo al objeto miembro
     */
    public void setNombreMiembro(String nombreMiembro) {
        this.nombreMiembro = nombreMiembro;
    }

    /**
     * Getter del apellido del miembro
     * @return Apellido del miembro
     */
    public String getApellidoMiembro() {
        return apellidoMiembro;
    }

    /**
     * Setter del apellido del miembro
     * @param apellidoMiembro Ingresa el apellio del miembro para settearlo al objeto miembro
     */
    public void setApellidoMiembro(String apellidoMiembro) {
        this.apellidoMiembro = apellidoMiembro;
    }

    /**
     * Getter de la ID del miembro
     * @return ID del miembro
     */
    public int getIdMiembro() {
        return idMiembro;
    }

    /**
     * Setter de la ID del miembro
     * @param idMiembro Ingresa la ID del miembro para settearla al objeto miembro
     */
    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    /**
     * Getter del número de documento del miembro
     * @return Número de documento
     */
    public int getNroDocumento() {
        return nroDocumento;
    }

    /**
     * Setter del número de documento del miembro
     * @param nroDocumento Ingresa el número de documento para settearlo al objeto miembro
     */
    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    /**
     * Getter del email del miembro
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter del email del miembro
     * @param email Ingresa el email del miembro para settearlo al objeto miembro
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter de la fecha de nacimiento del miembro
     * @return Fecha de nacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Setter de la fecha de nacimiento del miembro
     * @param fechaNacimiento Ingresa la fecha de nacimiento para settearla al objeto miembro
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Getter de la fecha de inscripción del miembro
     * @return Fecha de inscripción
     */
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Setter de la fecha de inscripción del miembro
     * @param fechaInscripcion Ingresa la fecha de inscripción para settearla al objeto miembro
     */
    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    /**
     * Getter del tipo de membresia del miembro
     * @return Tipo de membresía
     */
    public String getTipoMembresia() {
        return tipoMembresia;
    }

    /**
     * Setter del tipo de membresia del miembro
     * @param tipoMembresia Ingresa el tipo de membresia para settearlo al objeto miembro
     */
    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    /**
     * Getter del estado de suscripción del miembro, Objeto Suscripción
     * @return Objeto Suscripción
     */
    public Suscripcion getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    /**
     * Setter del estado de Suscripción
     * @param estadoSuscripcion Ingresa el objeto Suscripción para settearlo al objeto miembro
     */
    public void setEstadoSuscripcion(Suscripcion estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }

    /**
     * Getter de la condición del miembro
     * @return Condición del miembro
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * Setter de la condición del miembro
     * @param condicion Ingresa la condición para settearla al objeto miembro
     */
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
}

