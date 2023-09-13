package org.hgarcia.mantenedor.usuarios.dao;

import org.hgarcia.mantenedor.usuarios.modelo.Usuario;
import org.hgarcia.mantenedor.usuarios.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDao<Usuario> {

    private final Connection conn;
    public UsuarioDaoImpl() throws SQLException {
        conn = getConnection();
    }

    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                Usuario u = crearUsuario(rs);
                usuarios.add(u);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        Usuario usuario = null;

        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            stmt.setLong(1, id);
            try {
                ResultSet rs = getStmt(stmt).executeQuery();
                if (rs.next())
                    usuario = crearUsuario(rs);
            } catch (SQLException e){
                e.printStackTrace();
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    private static PreparedStatement getStmt(PreparedStatement stmt) {
        return stmt;
    }
    @Override
    public void guardar(Usuario usuario) {
        String sql;
        if (usuario.getId() != null && usuario.getId() > 0) {
            // Actualización de un registro existente
            sql = "UPDATE usuarios SET username=?, password=?, email=? WHERE id=?";
        } else {
            // Inserción de un nuevo registro
            sql = "INSERT INTO usuarios(username, password, email, id) VALUES(?,?,?,?)";
        }
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            if (usuario.getId() != null && usuario.getId() > 0) {
                stmt.setString(1, usuario.getUsername());
                stmt.setString(2, usuario.getPassword());
                stmt.setString(3, usuario.getEmail());
                stmt.setLong(4, usuario.getId()); // Para la actualización
            } else {
                // Al insertar, asignamos un valor NULL al marcador de posición del ID
                stmt.setString(1, usuario.getUsername());
                stmt.setString(2, usuario.getPassword());
                stmt.setString(3, usuario.getEmail());
                stmt.setNull(4, java.sql.Types.INTEGER); // ID es autoincrementable
            }
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void eliminar(Long id) {
        try {
                PreparedStatement stmt;
                stmt = conn.prepareStatement("DELETE FROM usuarios WHERE id=?");
                stmt.setLong(1, id);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }
    private Usuario crearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setEmail(rs.getString("email"));
        return u;
    }
}