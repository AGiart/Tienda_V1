
package com.tienda1.dao;

import com.tienda1.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    Usuario findByUsername(String Username);
}
