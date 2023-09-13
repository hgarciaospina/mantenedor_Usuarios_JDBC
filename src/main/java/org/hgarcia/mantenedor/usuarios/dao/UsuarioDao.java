package org.hgarcia.mantenedor.usuarios.dao;

import java.util.List;
public interface UsuarioDao<T> {
    List<T> listar();
    T buscarPorId(Long id);
    void guardar(T t);
    void eliminar(Long id);
}
