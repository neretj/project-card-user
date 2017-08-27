package app.card.service.user.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import app.card.model.role.RoleVO;
import app.card.service.user.dto.RoleDto;

@Component
public class RoleConverter {

	public RoleDto toDto(RoleVO roleVO) {
		RoleDto roleDto = new RoleDto();
		roleDto.setIdRole(roleVO.getIdRole());
		roleDto.setNameRole(roleVO.getName());
		return roleDto;
	}

	public List<RoleDto> toDtos(List<RoleVO> rolesVO) {
		List<RoleDto> list = new ArrayList<RoleDto>();
		for (RoleVO roleVO : rolesVO) {
			list.add(toDto(roleVO));
		}
		return list;
	}
}
