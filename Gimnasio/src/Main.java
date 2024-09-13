import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gimnasio gimnasio1 = new Gimnasio("Coca Gaston", "Rodriguez Pena", "1030", "2230", "1231231123", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        int opcion1 = 0;
        int opcion2 = 0;
        int opcion3 = 0;
        int opcion4 = 0;
        int opcion5 = 0;
        int opcion6 = 0;
        int opcion7 = 0;
        int opcion8 = 0;
        int finalizador = 1;


        do{
            System.out.println("BIENVENIDO AL GIMNASIO");
            System.out.println("Ingrese un numero del 1 al 9 para acceder a las distintas funcionalidades del gimnasio");
            System.out.println("1. Miembro");
            System.out.println("2. Entrenador");
            System.out.println("3. Suscripción");
            System.out.println("4. Area");
            System.out.println("5. Clase");
            System.out.println("6. Reserva");
            System.out.println("7. Equipo");
            System.out.println("8. Gimnasio");
            System.out.println("9. Salir");
            do{
                try{
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion == 1){
                        System.out.println("Indique la opción de 'MIEMBRO' a la que desea acceder (1 a)");
                        System.out.println("1. Registrar Miembro");
                        System.out.println("2. Actualizar información de Miembro");
                        System.out.println("3. Imprimir Miembro");
                        System.out.println("4. Atrás");
                        do{
                            try{
                                opcion1 = sc.nextInt();
                                sc.nextLine();
                                if (opcion1 == 1){
                                    Miembro.registrarMiembro(gimnasio1);
                                    break;
                                } else if (opcion1 == 2){
                                    int idMiembro = Miembro.askMiembroId(gimnasio1);
                                    if (idMiembro != 0){
                                        Miembro.actualizarInformacion(Miembro.searchMiembroInList(gimnasio1.listaMiembros,idMiembro), gimnasio1);
                                        break;
                                    } else {
                                        break;
                                    }
                                } else if (opcion1 == 3){
                                    int idMiembro = Miembro.askMiembroId(gimnasio1);
                                    if (idMiembro != 0){
                                        Miembro.imprimirMiembro(Miembro.searchMiembroInList(gimnasio1.getListaMiembros(),idMiembro));
                                        break;
                                    } else {
                                        break;
                                    }
                                } else if (opcion1 == 4){
                                    break;
                                }
                            } catch (InputMismatchException e1) {
                                sc.nextLine();
                                System.out.println("Opcion invalida. Ingrese de nuevo.");
                            }
                        } while ((opcion1 > 4) || (opcion1 < 1));

                    } else if (opcion == 2){
                        System.out.println("Indique la opción de 'ENTRENADOR' a la que desea acceder (1 a)");
                        System.out.println("1. Registrar Entrenador");
                        System.out.println("2. Imprimir Entrenador");
                        System.out.println("3. Atrás");
                        do{
                            try{
                                opcion2 = sc.nextInt();
                                sc.nextLine();
                                if (opcion2 == 1){
                                    Entrenador.registrarEntrenador(gimnasio1);
                                    break;
                                } else if (opcion2 == 2){
                                    int idEntrenador = Entrenador.askEntrenadorId(gimnasio1);
                                    if (idEntrenador != 0){
                                        Entrenador.imprimirEntrenador(Entrenador.searchEntrenadorInList(gimnasio1.getListaEntrenadores(),idEntrenador));
                                        break;
                                    } else {
                                        break;
                                    }
                                } else if (opcion2 == 3){
                                    break;
                                }
                            } catch (InputMismatchException e1) {
                                sc.nextLine();
                                System.out.println("Opcion invalida. Ingrese de nuevo.");
                            }
                        } while ((opcion2 > 3) || (opcion2 < 1));

                    } else if (opcion == 3){
                        System.out.println("Indique la opción de 'SUSCRIPCIÓN' a la que desea acceder (1 a)");
                        System.out.println("1. Registrar Suscripción");
                        System.out.println("2. Pagar Suscripción");
                        System.out.println("3. Cancelar Suscripición");
                        System.out.println("4. Atrás");
                        do{
                            try{
                                opcion3 = sc.nextInt();
                                sc.nextLine();
                                if (opcion3 == 1){
                                    Suscripcion.registrarSuscripcion(gimnasio1);
                                    break;
                                } else if (opcion3 == 2){
                                    Suscripcion.pagarSuscripcion(gimnasio1);
                                } else if (opcion3 == 3){
                                    Suscripcion.cancelarSuscripcion(gimnasio1);
                                } else if (opcion3 == 4) {
                                    break;
                                }
                            } catch (InputMismatchException e1) {
                                sc.nextLine();
                                System.out.println("Opcion invalida. Ingrese de nuevo.");
                            }
                        } while ((opcion3 > 4) || (opcion3 < 1));

                    } else if (opcion == 4){
                        System.out.println("Indique la opción de 'ÁREA' a la que desea acceder (1 a)");
                        System.out.println("1. Registrar Área");
                        System.out.println("2. Atrás");
                        do{
                            try{
                                opcion4 = sc.nextInt();
                                sc.nextLine();
                                if (opcion4 == 1){
                                    Area.registrarArea(gimnasio1);
                                } else if (opcion4 == 2) {
                                    break;
                                }
                            } catch (InputMismatchException e1) {
                                sc.nextLine();
                                System.out.println("Opcion invalida. Ingrese de nuevo.");
                            }
                        } while ((opcion4 > 2) || (opcion4 < 1));
                    } else if (opcion == 5){
                        System.out.println("Indique la opción de 'CLASE' a la que desea acceder (1 a)");
                        System.out.println("1. Programar Clase");
                        System.out.println("2. Cumplir Clase");
                        System.out.println("3. Cancelar Clase");
                        System.out.println("4. Atrás");
                        do{
                            try{
                                opcion5 = sc.nextInt();
                                sc.nextLine();
                                if (opcion5 == 1){
                                    Clase.programarClase(gimnasio1);
                                } else if (opcion5 == 2){
                                    Clase.cumplirClase(gimnasio1);
                                } else if (opcion5 == 3){
                                    Clase.cancelarClase(gimnasio1);
                                } else if (opcion5 == 4) {
                                    break;
                                }
                            } catch (InputMismatchException e1) {
                                sc.nextLine();
                                System.out.println("Opcion invalida. Ingrese de nuevo.");
                            }
                        } while ((opcion5 > 4) || (opcion5 < 1));
                    } else if (opcion == 6){
                        System.out.println("Indique la opción de 'RESERVA' a la que desea acceder (1 a)");
                        System.out.println("1. Hacer Reserva");
                        System.out.println("2. Cancelar Reserva");
                        System.out.println("3. Atrás");
                        do{
                            try{
                                opcion6 = sc.nextInt();
                                sc.nextLine();
                                if (opcion6 == 1){
                                    Reserva.hacerReserva(gimnasio1);
                                } else if (opcion6 == 2){
                                    Reserva.cancelarReserva(gimnasio1);
                                } else if (opcion6 == 3){
                                    break;
                                }
                            } catch (InputMismatchException e1) {
                                sc.nextLine();
                                System.out.println("Opcion invalida. Ingrese de nuevo.");
                            }
                        } while ((opcion6 > 3) || (opcion6 < 1));
                    } else if (opcion == 7){
                        System.out.println("Indique la opción de 'EQUIPO' a la que desea acceder (1 a)");
                        System.out.println("1. Registrar Equipo");
                        System.out.println("2. Asignar Equipo a Área");
                        System.out.println("3. Consultar Mantenimiento del Equipo");
                        System.out.println("4. Atrás");
                        do{
                            try{
                                opcion7 = sc.nextInt();
                                sc.nextLine();
                                if (opcion7 == 1){
                                    Equipo.registrarEquipo(gimnasio1);
                                } else if (opcion7 == 2){
                                    int idEquipo = Equipo.askEquipoId(gimnasio1);
                                    int idArea = Area.askAreaId(gimnasio1);
                                    if ((idEquipo != 0)&&(idArea != 0)){
                                        Equipo.asignarArea((Equipo.searchEquipoInList((gimnasio1.listaEquipos),idEquipo)),Area.searchAreaInList(gimnasio1.listaAreas,idArea), gimnasio1);
                                        break;
                                    } else {
                                        break;
                                    }
                                } else if (opcion7 == 3){
                                    int idEquipo = Equipo.askEquipoId(gimnasio1);
                                    if (idEquipo != 0){
                                        Equipo.consultarMantenimiento(Equipo.searchEquipoInList(gimnasio1.listaEquipos,idEquipo));
                                        break;
                                    } else {
                                        break;
                                    }
                                } else if (opcion7 == 4) {
                                    break;
                                }
                            } catch (InputMismatchException e1) {
                                sc.nextLine();
                                System.out.println("Opcion invalida. Ingrese de nuevo.");
                            }
                        } while ((opcion7 > 4) || (opcion7 < 1));
                    } else if (opcion == 8){
                        System.out.println("Indique la opción de 'GIMNASIO' a la que desea acceder (1 a)");
                        System.out.println("1. Imprimir información del Gimnasio");
                        System.out.println("2. Imprimir lista de Miembros");
                        System.out.println("3. Imprimir lista de Entrenadores");
                        System.out.println("4. Imprimir lista de Áreas");
                        System.out.println("5. Imprimir lista de Clases");
                        System.out.println("6. Imprimir lista de Reservas");
                        System.out.println("7. Imprimir lista de Equipos");
                        System.out.println("8. Atrás");
                        do{
                            try{
                                opcion8 = sc.nextInt();
                                sc.nextLine();
                                if (opcion8 == 1){
                                    Gimnasio.imprimirGimnasio(gimnasio1);
                                    break;
                                } else if (opcion8 == 2){
                                    Gimnasio.obtenerListaMiembros(gimnasio1);
                                    break;
                                } else if (opcion8 == 3){
                                    Gimnasio.obtenerListaEmpleados(gimnasio1);
                                    break;
                                } else if (opcion8 == 4){
                                    Gimnasio.obtenerListaArea(gimnasio1);
                                    break;
                                } else if (opcion8 == 5){
                                    Gimnasio.obtenerListaClases(gimnasio1);
                                    break;
                                } else if (opcion8 == 6){
                                    Gimnasio.obtenerListaReservas(gimnasio1);
                                    break;
                                } else if (opcion8 == 7){
                                    Gimnasio.obtenerListaEquipos(gimnasio1);
                                    break;
                                } else if (opcion8 == 8){
                                    break;
                                }
                            } catch (InputMismatchException e1) {
                                sc.nextLine();
                                System.out.println("Opcion invalida. Ingrese de nuevo.");
                            }
                        } while ((opcion8 > 8) || (opcion8 < 1));

                    } else if (opcion == 9){
                        System.out.println("Cerrando...");
                        System.exit(0);
                    }
                } catch (InputMismatchException e1) {
                    sc.nextLine();
                    System.out.println("Opcion invalida. Ingrese de nuevo.");
                }
            } while (((opcion > 9) || (opcion < 1)) && (finalizador == 0));
        } while (finalizador == 1);
    }
}