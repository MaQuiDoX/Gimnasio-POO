import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Gimnasio gimnasio1 = new Gimnasio("Coca Gaston","Rodriguez Pena",1123,1134,"1231231123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());

        //PRUEBA DE REGISTRO DE MIEMBRO
        //Miembro memb1 = Miembro.registrarMiembro(gimnasio1);
        //Miembro memb1 = new Miembro("Matias","Quesada", 1,123123,"matuquesada999@gmail.com",23022004,12122024,"VIP","Por pagar","Ninguna");
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
        ArrayList<String> Dias = new ArrayList();
        Dias.add("Lunes");
        Dias.add("Martes");
        Dias.add("Jueves");
        Entrenador ent1 = new Entrenador("Samuel", "Ponce", 1,"Yoga",1212,2323,Dias,new ArrayList<>());
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
        gimnasio1.listaEntrenadores.add(ent1);

        Gimnasio.obtenerListaEmpleados(gimnasio1);
        Clase clase = Clase.programarClase(gimnasio1);
        Gimnasio.obtenerListaEmpleados(gimnasio1);
        //Reserva reserva1 = Reserva.hacerReserva(gimnasio1);
        //Reserva reserva2 = Reserva.hacerReserva(gimnasio1);

        //Gimnasio.obtenerListaEmpleados(gimnasio1);
    }
}