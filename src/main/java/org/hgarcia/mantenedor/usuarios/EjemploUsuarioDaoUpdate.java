package org.hgarcia.mantenedor.usuarios;

import org.hgarcia.mantenedor.usuarios.modelo.Usuario;
import org.hgarcia.mantenedor.usuarios.dao.UsuarioDao;
import org.hgarcia.mantenedor.usuarios.dao.UsuarioDaoImpl;

import java.sql.SQLException;

public class EjemploUsuarioDaoUpdate {

    public static void main(String[] args) throws SQLException {

            UsuarioDao<Usuario> repositorio = new UsuarioDaoImpl();
            //Lista todos los productos
            System.out.println();
            System.out.println("============ Listar ============");
            repositorio.listar().forEach(System.out::println);
            System.out.println("================================");
            System.out.println();

            //Busca un producto por su id y lo muestra
            System.out.println("======== Obtener por id ========");
            System.out.println(repositorio.buscarPorId(1L));
            System.out.println("================================");
            System.out.println();

            //Modifica registro de producto
            System.out.println("==== Modificar usuario ====");
            Usuario usuario = new Usuario();
            usuario.setId(1L);
            usuario.setEmail("henry.garcia@gmail.com");
            usuario.setPassword("Leandro2009**");
            usuario.setUsername("hegaros");
            repositorio.guardar(usuario);
            System.out.println("Producto modificado con éxito");
            System.out.println("=================================");
            repositorio.listar().forEach(System.out::println);
    }
}