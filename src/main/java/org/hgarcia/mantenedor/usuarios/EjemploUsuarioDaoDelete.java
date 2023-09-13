package org.hgarcia.mantenedor.usuarios;


import org.hgarcia.mantenedor.usuarios.modelo.Usuario;
import org.hgarcia.mantenedor.usuarios.dao.UsuarioDao;
import org.hgarcia.mantenedor.usuarios.dao.UsuarioDaoImpl;

import java.sql.SQLException;

public class EjemploUsuarioDaoDelete {

    public static void main(String[] args) throws SQLException {
            UsuarioDao<Usuario> repositorio = new UsuarioDaoImpl();
            //Lista todos los usuarios
            System.out.println();
            System.out.println("============ Listar ============");
            repositorio.listar().forEach(System.out::println);
            System.out.println("================================");
            System.out.println();

            //Busca un producto por su id y lo muestra
            System.out.println("======== Obtener por id ========");
            System.out.println(repositorio.buscarPorId(2L));
            System.out.println("================================");
            System.out.println();

            //Elimina  un registro de producto
            System.out.println("==== Eliminar usuario ====");
            repositorio.eliminar(3L);
            System.out.println("Usuario eliminado con éxito");
            System.out.println("=================================");
            repositorio.listar().forEach(System.out::println);
    }
}