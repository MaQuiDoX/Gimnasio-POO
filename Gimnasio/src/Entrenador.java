import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que representa a los Entrenadores del gimnasio
 * @author Manuel Matías Quesada Riccieri
 */
public class Entrenador {
    private String nombreEntrenador;
    private String apellidoEntrenador;
    private int idEntrenador;
    private String especialidadEntrenador;
    private String horarioInicio;
    private String horarioFin;
    private ArrayList<String> diasDisponibles;
    public ArrayList<Clase> historialEntrenamientos;

    static ArrayList<Integer> idsUsadas = new ArrayList<>();

    /**
     * Constructor del objeto Entrenador, recibe parametros
     * @param nombreEntrenador Nombre del entrenador
     * @param apellidoEntrenador Apellido del entrenador
     * @param idEntrenador ID del entrenador
     * @param especialidadEntrenador especialidad del Entrenador
     * @param horarioInicio Horario de inicio del entrenador
     * @param horarioFin Horario de fin del entrenador
     * @param diasDisponibles Lista de dias en los que trabaja
     * @param historialEntrenamientos Lista historial de entrenamientos del entrenador
     */
    public Entrenador(String nombreEntrenador, String apellidoEntrenador, int idEntrenador, String especialidadEntrenador, String horarioInicio, String horarioFin, ArrayList<String> diasDisponibles, ArrayList<Clase> historialEntrenamientos) {
        this.nombreEntrenador = nombreEntrenador;
        this.apellidoEntrenador = apellidoEntrenador;
        this.idEntrenador = idEntrenador;
        this.especialidadEntrenador = especialidadEntrenador;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.diasDisponibles = diasDisponibles;
        this.historialEntrenamientos = historialEntrenamientos;
    }

    /**
     * Función para registrar un nuevo entrenador, pide al usuario todos los parametros individuales y llama al constructor para generar una suscripción
     * @param gimnasio1 Entra a la función para trabajar con sus listas asociadas
     * @return Objeto entrenador inicializado
     */
    public static Entrenador registrarEntrenador(Gimnasio gimnasio1) {
        Scanner sc1 = new Scanner(System.in);
        ArrayList<String> semana = new ArrayList<>();
        semana.add("Lunes");
        semana.add("Martes");
        semana.add("Miercoles");
        semana.add("Jueves");
        semana.add("Viernes");
        semana.add("Sabado");
        semana.add("Domingo");
        char opcion = ' ';
        ArrayList<String> diasDisp = new ArrayList<>();

        System.out.println("Nombre del Entrenador: ");
        String nombreEntrenador1 = sc1.nextLine();

        System.out.println("Apellido del Entrenador: ");
        String apellidoEntrenador1 = sc1.nextLine();

        System.out.println("Especialidad del Entrenador: ");
        String especialidadEntrenador1 = sc1.nextLine();

        // Consulta si la ID del entrenador ya esta registrada
        boolean existe = false;
        System.out.println("ID del Entrenador: ");
        int idEntrenador = sc1.nextInt();
        do {
            existe = idsUsadas.contains(idEntrenador);
            if (existe) {
                System.out.println("ID ya utilizada, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) {
                    return null;
                }
                idEntrenador = sc1.nextInt();
            } else {
                break;
            }
        } while (existe);

        // Chequea que el horario de entrada este en el formato correcto
        System.out.println("Horario de entrada: ");
        String horarioEntrada1 = sc1.nextLine();
        do {
            if (horarioEntrada1.length() != 4) {
                System.out.println("Valor temporal invalido, ingrese nuevamente: ");
                if (Gimnasio.consultaOperacion()) {
                    return null;
                }
                horarioEntrada1 = sc1.nextLine();
            } else {
                break;
            }
        } while (horarioEntrada1.length() != 4);

        // Chequea que el horario de salida este en el formato correcto
        System.out.println("Horario de salida: ");
        String horarioSalida1 = sc1.nextLine();
        do {
            if (horarioSalida1.length() != 4) {
                System.out.println("Valor temporal invalido, ingrese nuevamente: ");
                horarioSalida1 = sc1.nextLine();
            } else {
                break;
            }
        } while (horarioSalida1.length() != 4);

        // Recorre una lista con todos los dias de la semana. Al tipear 'y' se añade a la lista de dias del entrenador, al tipear 'n' no se añade
        System.out.println("Por dia de la semana, indique 'y' para los dias en donde el entrenador si participa:");
        for (String dia : semana) {
            System.out.println(nombreEntrenador1 + " trabaja el " + dia + "?");
            do {
                opcion = sc1.next().charAt(0);
                sc1.nextLine();
                try {
                    if (opcion == 'y') {
                        diasDisp.add(dia);
                        break;
                    } else if (opcion == 'n') {
                        break;
                    } else {
                        System.out.println("Opcion invalida. Ingrese de nuevo. ");
                    }
                } catch (InputMismatchException e1) {
                    sc1.nextLine();
                    System.out.println("Opcion invalida. Ingrese de nuevo. ");
                }
            } while ((opcion != 'y'));
        }

        // Constructor de entrenador, añade ID a lista de IDs usadas
        Entrenador entrendador1 = new Entrenador(nombreEntrenador1, apellidoEntrenador1, idEntrenador, especialidadEntrenador1, horarioEntrada1, horarioSalida1, diasDisp, new ArrayList<>());
        idsUsadas.add(idEntrenador);

        gimnasio1.listaEntrenadores.add(entrendador1);
        return entrendador1;
    };

    /**
     * Función que imprime todos los parametros del objeto Entrenador
     * @param entrenador Objeto Entrenador
     */
    public static void imprimirEntrenador(Entrenador entrenador) {
        System.out.println("Nombre: " + entrenador.getNombreEntrenador());
        System.out.println("Apellido: " + entrenador.getApellidoEntrenador());
        System.out.println("Id: " + entrenador.getIdEntrenador());
        System.out.println("Especialidad: " + entrenador.getEspecialidadEntrenador());
        System.out.println("Horario de entrada: " + formatearHora(entrenador.getHorarioInicio()));
        System.out.println("Horario de salida: " + formatearHora(entrenador.getHorarioFin()));
        System.out.print("Dias: ");
        for (String dia : entrenador.getDiasDisponibles()) {
            System.out.print(dia + " ");
        }
        int contador = 1;
        System.out.println("Historial de entrenamientos: ");
        for (Clase clase1 : entrenador.historialEntrenamientos) {
            System.out.println("Clase N° " + contador);
            System.out.println("Nombre de la clase: " + clase1.getNombreClase());
            System.out.println("ID de la clase: " + clase1.getIdClase());
            contador++;
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Función para buscar un Objeto Entrenador en una lista de Entrenadores
     * @param gimnasio Lista de entrenadores
     * @param idEntrenador ID del entrenador a buscar
     * @return Objeto Entrenador si encuentra en equipo, null en caso contrario
     */
    public static Entrenador searchEntrenadorInList(ArrayList<Entrenador> gimnasio, int idEntrenador) {
        for (Entrenador entrenador : gimnasio) {
            if (entrenador.getIdEntrenador() == idEntrenador) {
                return entrenador;
            }
        }
        return null;
    }

    /**
     * Función que busca al objeto Entrenador a partir de ID en la lista de Entrenadores del gimnasio para eliminarlo
     * @param gimnasio1 Entra a la función para trabajar con la lista asociada a entrenadores
     * @param idEntrenador ID del entrenador
     */
    public static void deleteEntrenador(Gimnasio gimnasio1, int idEntrenador) {
        gimnasio1.listaEntrenadores.removeIf(entrenador -> entrenador.getIdEntrenador() == idEntrenador);
    }

    /**
     * Consulta si el entrenador ya existe en el gimnasio a partir de su ID
     * @param gimnasio1 Entra a la función para consultar la lista asociada a entrenadores
     * @return Retorna la ID en caso de encontrarla, 0 en caso de no encontrar la ID y no querer continuar con la operación
     */
    public static Integer askEntrenadorId(Gimnasio gimnasio1) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del entrenador: ");
        int id;
        boolean existe;
        do {
            id = sc.nextInt();
            if (Entrenador.searchEntrenadorInList(gimnasio1.listaEntrenadores, id) == null) {
                System.out.println("El ID del entrenador no existe");
                existe = false;
                if (Gimnasio.consultaOperacion()) {
                    return 0;
                }
            } else {
                existe = true;
                return id;
            }
        } while (!existe);
        return 0;
    }

    /**
     * Función a la que dado un String en formato HHMM, devuelve un nuevo String en formato HH:MM para representar horario
     * @param numero String de la hora en formato HHMM
     * @return String de la hora en formato HH:MM
     */
    public static String formatearHora(String numero) {
        String numeroStr = numero;

        String hora = numeroStr.substring(0, 2);
        String minuto = numeroStr.substring(2, 4);

        return hora + ":" + minuto;
    }

    public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    public void setNombreEntrenador(String nombreEntrenador) {
        this.nombreEntrenador = nombreEntrenador;
    }

    public String getApellidoEntrenador() {
        return apellidoEntrenador;
    }

    public void setApellidoEntrenador(String apellidoEntrenador) {
        this.apellidoEntrenador = apellidoEntrenador;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getEspecialidadEntrenador() {
        return especialidadEntrenador;
    }

    public void setEspecialidadEntrenador(String especialidadEntrenador) {
        this.especialidadEntrenador = especialidadEntrenador;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(String horarioFin) {
        this.horarioFin = horarioFin;
    }

    public ArrayList<String> getDiasDisponibles() {
        return diasDisponibles;
    }

    public void setDiasDisponibles(ArrayList<String> diasDisponibles) {
        this.diasDisponibles = diasDisponibles;
    }

    public ArrayList<Clase> getHistorialEntrenamientos() {
        return historialEntrenamientos;
    }

    public void setHistorialEntrenamientos(ArrayList<Clase> historialEntrenamientos) {
        this.historialEntrenamientos = historialEntrenamientos;
    }
}
