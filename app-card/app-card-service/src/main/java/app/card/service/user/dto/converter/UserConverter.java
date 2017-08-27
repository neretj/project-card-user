package app.card.service.user.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import app.card.model.user.UserVO;
import app.card.service.user.dto.UserDto;

@Component
public class UserConverter {

	public UserDto toDto(UserVO userVO) {
		UserDto userDto = new UserDto();
		userDto.setIdUser(userVO.getIdUser());
		userDto.setUsername(userVO.getUsername());
		userDto.setPassword(userVO.getPassword());
		userDto.setName(userVO.getName());
		userDto.setSurname(userVO.getSurname());
		return userDto;
	}

	public List<UserDto> toDtos(List<UserVO> usersVO) {
		List<UserDto> list = new ArrayList<UserDto>();
		for (UserVO userVO : usersVO) {
			list.add(toDto(userVO));
		}
		return list;
	}
	
	public UserVO toVO(UserDto userDto) {
		UserVO userVO = new UserVO();
		userVO.setIdUser(userDto.getIdUser());
		userVO.setUsername(userDto.getUsername());
		userVO.setPassword(userDto.getPassword());
		userVO.setName(userDto.getName());
		userVO.setSurname(userDto.getSurname());
		return userVO;
	}

	public List<UserVO> toVOs(List<UserDto> usersDto) {
		List<UserVO> list = new ArrayList<UserVO>();
		for (UserDto userDto : usersDto) {
			list.add(toVO(userDto));
		}
		return list;
	}
}
