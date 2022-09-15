package com.pe.ttk.admision.util.mapper;

import com.pe.ttk.admision.security.dto.UsuarioDto;
import com.pe.ttk.admision.security.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDto toUsuarioDto(Usuario usuario);

    List<UsuarioDto> toUsuarioDtoList(List<Usuario> usuarios);

}
