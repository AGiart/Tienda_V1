
package com.tienda1.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author isalr
 */
public interface UsuarioDetailsService {
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException;
}