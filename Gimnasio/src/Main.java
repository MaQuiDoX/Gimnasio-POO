import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gimnasio gimnasio1 = new Gimnasio("Coca Gaston", "Rodriguez Pena", 1030, 2230, "1231231123", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
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

                                } else if (opcion2 == 2){

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

                                } else if (opcion3 == 2){

                                } else if (opcion3 == 3){

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

                                } else if (opcion5 == 2){

                                } else if (opcion5 == 3){

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

                                } else if (opcion6 == 2){

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

                                } else if (opcion7 == 2){

                                } else if (opcion7 == 3){

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
                        System.out.println("1. Imprimir lista de Miembros");
                        System.out.println("2. Imprimir lista de Entrenadores");
                        System.out.println("3. Imprimir lista de Áreas");
                        System.out.println("4. Imprimir lista de Clases");
                        System.out.println("5. Imprimir lista de Reservas");
                        System.out.println("6. Imprimir lista de Equipos");
                        System.out.println("7. Atrás");
                        do{
                            try{
                                opcion8 = sc.nextInt();
                                sc.nextLine();
                                if (opcion8 == 1){

                                } else if (opcion8 == 2){

                                } else if (opcion8 == 3){

                                } else if (opcion8 == 4){

                                } else if (opcion8 == 5){

                                } else if (opcion8 == 6){

                                } else if (opcion8 == 7){
                                    break;
                                }
                            } catch (InputMismatchException e1) {
                                sc.nextLine();
                                System.out.println("Opcion invalida. Ingrese de nuevo.");
                            }
                        } while ((opcion8 > 7) || (opcion8 < 1));

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


        //PRUEBA DE REGISTRO DE MIEMBRO
        //Miembro memb1 = Miembro.registrarMiembro(gimnasio1);
        //Miembro memb1 = new Miembro("Matias","Quesada", 1,123123,"matuquesada999@gmail.com",23022004,12122024,"VIP",null,"Ninguna");
        //Miembro memb2 = new Miembro("Aldo","Taha", 2,232323,"aldotaha@gmail.com",22233332,23232323,"Comun","Pagado","Le falta una pierna");

        //System.out.println(gimnasio1.listaMiembros);

        //PRUEBA DE ACTUALIZAR INFORMACION PARA MIEMBRO
        //Miembro memb1 = Miembro.registrarMiembro(gimnasio1);
        //Miembro memb2 = Miembro.registrarMiembro(gimnasio1);
        //Gimnasio.obtenerListaMiembros(gimnasio1);

        //Miembro.actualizarInformacion(memb1, gimnasio1);
        //Gimnasio.obtenerListaMiembros(gimnasio1);

        //PRUEBA DE CONSTRUCTOR DE EQUIPO, CONSULTA Y ACTUALIZACION
        //Equipo eqip1 = new Equipo(12,"Pesa","Para arreglar");
        //Equipo.consultarMantenimiento(eqip1);
        //Equipo.consultarMantenimiento(eqip1);

        //PRUEBA DE CONSULTA DE LISTA DE MIEMBROS
        //Miembro memb1 = Miembro.registrarMiembro(gimnasio1);
        //Miembro memb2 = Miembro.registrarMiembro(gimnasio1);
        //Gimnasio.obtenerListaMiembros(gimnasio1);

        //PRUEBA PARA IMPRIMIR ENTRENADOR
        //ArrayList<String> Dias = new ArrayList();
        //Dias.add("Lunes");
        //Dias.add("Martes");
        //Dias.add("Jueves");
        //Entrenador ent1 = new Entrenador("Samuel", "Ponce", 1,"Yoga",1212,2323,Dias,new ArrayList<>());
        //Entrenador.imprimirEntrenador(ent1);

        //PRUEBA REGISTRO ENTRENADOR
        //Entrenador entr2 = Entrenador.registrarEntrenador(gimnasio1);
        //Miembro memb1 = Miembro.registrarMiembro(gimnasio1);

        //Entrenador entr3 = Entrenador.registrarEntrenador(gimnasio1);
        //Gimnasio.obtenerListaEmpleados(gimnasio1);

        //PRUEBA RESERVA
        //Reserva reserva1 = Reserva.hacerReserva(gimnasio1);

        //gimnasio1.listaMiembros.add(memb1);
        //gimnasio1.listaMiembros.add(memb2);
        //gimnasio1.listaEntrenadores.add(ent1);

        //Area area1 = Area.registrarArea(gimnasio1);

        //Gimnasio.obtenerListaEmpleados(gimnasio1);
        //Clase clase = Clase.programarClase(gimnasio1);
        //Clase clase2 = Clase.programarClase(gimnasio1);

        //Gimnasio.obtenerListaEmpleados(gimnasio1);

        //Clase.cancelarClase(gimnasio1);
        //Gimnasio.obtenerListaEmpleados(gimnasio1);

        //Reserva reserva1 = Reserva.hacerReserva(gimnasio1);
        //Reserva reserva2 = Reserva.hacerReserva(gimnasio1);

        //Gimnasio.obtenerListaEmpleados(gimnasio1);
    }
}