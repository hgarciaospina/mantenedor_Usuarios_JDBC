package org.hgarcia.mantenedor.usuarios;

import org.hgarcia.mantenedor.usuarios.modelo.Usuario;
import org.hgarcia.mantenedor.usuarios.dao.UsuarioDao;
import org.hgarcia.mantenedor.usuarios.dao.UsuarioDaoImpl;
import org.hgarcia.mantenedor.usuarios.util.ConexionBaseDatos;

import java.sql.SQLException;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws SQLException {

        UsuarioDao<Usuario> dao = new UsuarioDaoImpl();
        Scanner leerDato = new Scanner(System.in);
        Integer opcion = 0;

        // Menu
        do {
            System.out.println("\n\nMANTENEDOR DE USUARIOS");
            System.out.println("======================");
            System.out.println("1. Buscar usuario por Id");
            System.out.println("2. Agregar usuario");
            System.out.println("3. Actualizar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Listar Usuarios");
            System.out.println("6. Salir");
            System.out.println();
            System.out.print("Introduzca una opción: ");
            opcion = leerDato.nextInt();

            switch (opcion) {
                case 1:
                    Usuario usuario = null;
                    System.out.println("1. Buscar usuario por Id");
                    System.out.println("=======================");
                    System.out.print("Introduzca un número entero correspondiente al Id del usuario: ");
                    Long usuarioId = leerDato.nextLong();
                    usuario = dao.buscarPorId(usuarioId);
                    if(usuario == null){
                        System.out.print("El usuario ingresado con Id: " + usuarioId + " no existe");
                        System.out.println("Oprima <Enter> para continuar");
                        leerDato.nextLine();
                    }
                    System.out.println(usuario);
                    break;
                case 2:
                    Usuario usuarioNuevo = new Usuario();
                    System.out.println("2. Agregar usuario");
                    System.out.println("=======================");
                    System.out.print("Ingrese un correo electrónico válido: ");
                    String email = leerDato.next();
                    System.out.print("Ingrese un nombre de usuario: ");
                    String userName = leerDato.next();
                    System.out.print("Ingrese una clave de usuario: ");
                    String password = leerDato.next();
                    usuarioNuevo.setEmail(email);
                    usuarioNuevo.setUsername(userName);
                    usuarioNuevo.setPassword(password);
                    dao.guardar(usuarioNuevo);
                    System.out.println("*** Usuario creado correctamente");
                    break;
                case 3:
                    System.out.println("3. Actualizar usuario");
                    System.out.println("=======================");
                    Usuario usuarioModificar = null;
                    System.out.print("Introduzca un número entero correspondiente al Id del usuario que desea modificar: ");
                    Long buscarUsuarioId  = leerDato.nextLong();
                    usuarioModificar = dao.buscarPorId(buscarUsuarioId);
                    if(usuarioModificar == null){
                        System.out.print("El usuario ingresado con Id: " + buscarUsuarioId + " no existe");
                        System.out.println("Oprima <Enter> para continuar");
                        leerDato.nextLine();
                    }
                    else {
                        System.out.println("Datos actuales del usuario");
                        System.out.println("==========================");
                        System.out.println(usuarioModificar);
                        System.out.println("==========================");
                        System.out.print("Ingrese un correo electrónico válido: ");
                        String emailModificado = leerDato.next();
                        System.out.print("Ingrese un nombre de usuario: ");
                        String userNameModificado = leerDato.next();
                        System.out.print("Ingrese una clave de usuario: ");
                        String passwordModificado = leerDato.next();
                        usuarioModificar.setEmail(emailModificado);
                        usuarioModificar.setUsername(userNameModificado);
                        usuarioModificar.setPassword(passwordModificado);
                        dao.guardar(usuarioModificar);
                        System.out.println("Nuevos datos del usuario");
                        System.out.println("==========================");
                        dao.buscarPorId(buscarUsuarioId);
                        System.out.println("==========================");
                    }
                    break;
                case 4:
                    Usuario usuarioEliminar = null;
                    System.out.println("4. Eliminar usuario");
                    System.out.println("=======================");
                    System.out.print("Introduzca un número entero correspondiente al Id del usuario a eliminar: ");
                    Long eliminarUsuarioId = leerDato.nextLong();
                    usuarioEliminar = dao.buscarPorId(eliminarUsuarioId);
                    if(usuarioEliminar == null){
                        System.out.print("El usuario ingresado con Id: " + eliminarUsuarioId + " no existe");
                        System.out.println("Oprima <Enter> para continuar");
                        leerDato.nextLine();
                    }else{
                        dao.eliminar(eliminarUsuarioId);
                        System.out.println("Se eliminó el usuario: " + usuarioEliminar);
                    }
                    break;
                case 5:
                    System.out.println("5. Listar usuarios");
                    System.out.println("=======================");
                    dao.listar().forEach(System.out::println);
                    break;
                case 6:
                    ConexionBaseDatos.cerrarConexion();
                    break;
            }
        } while (opcion != 6);
    }
}