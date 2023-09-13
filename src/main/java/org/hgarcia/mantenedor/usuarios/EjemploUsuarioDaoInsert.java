package org.hgarcia.mantenedor.usuarios;



import org.hgarcia.mantenedor.usuarios.modelo.Usuario;
import org.hgarcia.mantenedor.usuarios.dao.UsuarioDao;
import org.hgarcia.mantenedor.usuarios.dao.UsuarioDaoImpl;

import java.sql.SQLException;

public class EjemploUsuarioDaoInsert {

    public static void main(String[] args) throws SQLException {

            UsuarioDao<Usuario> repositorio = new UsuarioDaoImpl();

            //Lista todos los usuarios
            System.out.println();
            System.out.println("============ Listar ============");
            repositorio.listar().forEach(System.out::println);
            System.out.println("================================");
            System.out.println();

            //Busca un usuario por su id y lo muestra
            System.out.println("======== Obtener por id ========");
            System.out.println(repositorio.buscarPorId(1L));
            System.out.println("================================");
            System.out.println();

            //Inserta un nuevo registro de producto
            System.out.println("==== Insertar nuevo usuario ====");
            Usuario usuario = new Usuario();
            usuario.setUsername("eliminar");
            usuario.setPassword("eliminar");
            usuario.setEmail("eliminar@goaling.com");
            repositorio.guardar(usuario);
            System.out.println("Usuario guardado con éxito");
            System.out.println("=================================");
            repositorio.listar().forEach(System.out::println);
    }
}