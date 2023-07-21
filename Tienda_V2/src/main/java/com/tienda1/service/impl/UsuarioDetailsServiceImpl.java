package com.tienda1.service.impl;

import com.tienda1.dao.UsuarioDao;
import com.tienda1.domain.Rol;
import com.tienda1.domain.Usuario;
import com.tienda1.service.UsuarioDetailsService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //se obtiene el usuario que tiene el mismo username
        Usuario usuario = usuarioDao.findByUsername(username);

        //se verifica que el usuario exista
        if (usuario == null) {
            //no se encontro el usuario
            throw new UsernameNotFoundException(username);
        }
        
        //se obtiene el rol del usuario
        var roles = new ArrayList<GrantedAuthority>();
        
        //se recorre la lista
        for(Rol rol:usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        //se obtiene el usuario con los roles 
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

}
