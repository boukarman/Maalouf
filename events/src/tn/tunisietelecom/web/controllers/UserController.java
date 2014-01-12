package tn.tunisietelecom.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.tunisietelecom.event.entities.Role;
import tn.tunisietelecom.event.entities.SubcontractorType;
import tn.tunisietelecom.event.entities.User;
import tn.tunisietelecom.event.entities.UserRoleSubcontractorType;
import tn.tunisietelecom.event.service.RoleService;
import tn.tunisietelecom.event.service.SubcontractorTypeService;
import tn.tunisietelecom.event.service.UserService;
import tn.tunisietelecom.event.utils.RoleType;
import tn.tunisietelecom.web.dto.UserConsultDto;
import tn.tunisietelecom.web.dto.UserCreateDto;

@Controller
@RequestMapping("/telecom/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SubcontractorTypeService subcontractorTypeService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String telecomUserCreateForm(Model model) {
		model.addAttribute("roles", userService.findAllRoles());
		model.addAttribute("subcontractorTypes",
				userService.findAllSubcontractorType());
		model.addAttribute("command", new UserCreateDto());
		return "telecom.user.create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String telecomUserCreateForm(
			@ModelAttribute("command") UserCreateDto userForm) {
		User newUser = new User();
		String[] ignoreProperties = { "idRole", "subcontractorTypeId",
				"telNumber" };
		BeanUtils.copyProperties(userForm, newUser, ignoreProperties);
		if (StringUtils.isNotBlank(userForm.getTelNumber()))
			newUser.setTelNumber(Integer.parseInt(userForm.getTelNumber()));
		Role role = roleService.findById(userForm.getIdRole());
		newUser.setRole(role);
		newUser = userService.addUser(newUser);
		if (StringUtils.equals(Long.toString(userForm.getIdRole()),
				RoleType.SUBCONTRACTOR.getType())) {
			SubcontractorType subcontractorType = subcontractorTypeService
					.findById(userForm.getSubcontractorTypeId());
			UserRoleSubcontractorType userRoleSubcontractorType = new UserRoleSubcontractorType(
					newUser, role);
			userRoleSubcontractorType.setType(subcontractorType);
			newUser.setSubcontractorType(userRoleSubcontractorType);
			userService.update(newUser);
		}

		return "telecom.save.success";
	}

	@RequestMapping(value = "/consult", method = RequestMethod.GET)
	public String telecomUserConsult(Model model) {
		return "telecom.user.consult";
	}

	@RequestMapping(value = "/consult/elements")
	@ResponseBody
	public Map<String, Object> telecomPieceConsultElements(
			@RequestParam int limit, @RequestParam int page,
			@RequestParam int start, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<UserConsultDto> users = new ArrayList<UserConsultDto>();
		String[] ignoreProperties = { "fullName", "role", "subcontractorType",
				"privileges", "eventUserStatus" };
		for (User user : userService.findUsersByPage(start, limit)) {
			UserConsultDto dto = new UserConsultDto();
			BeanUtils.copyProperties(user, dto, ignoreProperties);
			String role = user.getRole().getLabel();
			if (StringUtils.equals(Long.toString(user.getRole().getIdRole()),
					RoleType.SUBCONTRACTOR.getType())) {
				role = role + " - "
						+ user.getSubcontractorType().getType().getType();
			}
			dto.setRole(role);
			users.add(dto);
		}
		result.put("users", users);
		result.put("totalCount", userService.countUsers());
		return result;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public boolean telecomUserDelete(@RequestParam("userId") Long userId) {
		User user = userService.findById(userId);
		userService.deleteUser(user);
		return true;
	}
}
