package com.javanauta.usuario.business;

import com.javanauta.usuario.business.converter.UsuarioConverter;
import com.javanauta.usuario.business.dto.UsuarioDTO;
import com.javanauta.usuario.infrastructure.entity.Usuario;
import com.javanauta.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        // converte o DTO para usuario
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        // acessa o metodo save do Controller pelo usuario convertido
        // realiza a transação no banco e retorna como DTO
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

}
