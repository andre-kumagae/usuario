package com.javanauta.usuario.business.converter;

import com.javanauta.usuario.business.dto.EnderecoDTO;
import com.javanauta.usuario.business.dto.TelefoneDTO;
import com.javanauta.usuario.business.dto.UsuarioDTO;
import com.javanauta.usuario.infrastructure.entity.Endereco;
import com.javanauta.usuario.infrastructure.entity.Telefone;
import com.javanauta.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// esta anotacao marca a classe como um bean Spring. É necessária para injetar a dependência em outras classes
@Component
public class UsuarioConverter {
    // uma classe para converter de DTO para Entity e de Entity para DTO
    // a ideia das classes DTO é de transitar no banco de dados, pesquisar e então converter de volta para a classe Entity
    // perceba que para Endereco e Telefone precisamos utilizar os metodos abaixo, pois elas são da classe List
    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder().nome(usuarioDTO.getNome()).email(usuarioDTO.getEmail()).senha(usuarioDTO.getSenha()).enderecos(paraListaEndereco(usuarioDTO.getEndereco())).telefones(paraListaTelefone(usuarioDTO.getTelefone())).build();
    }

    // perceba que precisamos de 2 métodos para as classes Endereco e Telefone;
    // esse recebe uma List DTO e transforma em List da classe da Entity
    // perceba que o metodo map usa o metodo paraEndereco já iterando cada item da lista
    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecosDTOS) {
        return enderecosDTOS.stream().map(this::paraEndereco).toList();
    }

    // recebe todos os atributos de EnderecoDTO e transforma em um Endereco da Entity
    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder().rua(enderecoDTO.getRua()).numero(enderecoDTO.getNumero()).complemento(enderecoDTO.getComplemento()).bairro(enderecoDTO.getBairro()).cidade(enderecoDTO.getCidade()).estado(enderecoDTO.getEstado()).cep(enderecoDTO.getCep()).build();
    }

    // aqui seria uma versão sem a stream
    // perceba que temos que criar uma List, iterar para adicionar e só então retorná-la
    // em uma stream, o metodo forEach é terminal e retorna void, enquanto o map é uma opderação de transformação e retorna os valores no novo tipo desejado
    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS) {
        List<Telefone> telefones = new ArrayList<>();
        for (TelefoneDTO telefoneDTO : telefoneDTOS) {
            telefones.add(paraTelefone(telefoneDTO));
        }
        return telefones;
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder().telefone(telefoneDTO.getTelefone()).ddd(telefoneDTO.getDdd()).build();
    }

    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder().nome(usuario.getNome()).email(usuario.getEmail()).senha(usuario.getSenha()).endereco(paraListaEnderecoDTO(usuario.getEnderecos())).telefone(paraListaTelefoneDTO(usuario.getTelefones())).build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecosDTOS) {
        return enderecosDTOS.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder().id(endereco.getId()).rua(endereco.getRua()).numero(endereco.getNumero()).complemento(endereco.getComplemento()).bairro(endereco.getBairro()).cidade(endereco.getCidade()).estado(endereco.getEstado()).cep(endereco.getCep()).build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> listaTelefonesDTOS) {
        return listaTelefonesDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder().id(telefone.getId()).telefone(telefone.getTelefone()).ddd(telefone.getDdd()).build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario entity) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

    public Endereco updateEndereco(EnderecoDTO enderecoDTO, Endereco enderecoEntity) {
        return Endereco.builder()
                .id(enderecoEntity.getId())
                .rua(enderecoDTO.getRua() != null ? enderecoDTO.getRua() : enderecoEntity.getRua())
                .numero(enderecoDTO.getNumero() != null ? enderecoDTO.getNumero() : enderecoEntity.getNumero())
                .complemento(enderecoDTO.getComplemento() != null ? enderecoDTO.getComplemento() : enderecoEntity.getComplemento())
                .bairro(enderecoDTO.getBairro() != null ? enderecoDTO.getBairro() : enderecoEntity.getBairro())
                .cidade(enderecoDTO.getCidade() != null ? enderecoDTO.getCidade() : enderecoEntity.getCidade())
                .estado(enderecoDTO.getEstado() != null ? enderecoDTO.getEstado() : enderecoEntity.getEstado())
                .cep(enderecoDTO.getCep() != null ? enderecoDTO.getCep() : enderecoEntity.getCep())
                .build();
    }

    public Telefone updateTelefone(TelefoneDTO telefoneDTO, Telefone telefoneEntity) {
        return Telefone.builder()
                .id(telefoneEntity.getId())
                .telefone(telefoneDTO.getTelefone() != null ? telefoneDTO.getTelefone() : telefoneEntity.getTelefone())
                .ddd(telefoneDTO.getTelefone() != null ? telefoneDTO.getDdd() : telefoneEntity.getDdd())
                .build();
    }
}
