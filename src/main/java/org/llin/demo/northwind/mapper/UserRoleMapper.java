	
package org.llin.demo.northwind.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.llin.demo.northwind.data.entity.Role;
import org.llin.demo.northwind.data.entity.UserDetail;
import org.llin.demo.northwind.model.User;

public class UserRoleMapper {

	public static User toModel(UserDetail entity) {
		if (entity == null)
			return null;

		User model = new User();
		model.setId(entity.getId());
		model.setUsername(entity.getUsername());
		model.setPassword(entity.getPassword()); // Optional: remove for security
		model.setEmail(entity.getEmail());
		model.setEnabled(entity.isEnabled());

		if (entity.getRoles() != null) {
			List<org.llin.demo.northwind.model.Role> roles = entity.getRoles().stream().map(UserRoleMapper::toModel)
					.collect(Collectors.toList());
			model.setRoles(roles);
		}

		return model;
	}

	public static UserDetail toEntity(User model) {
		if (model == null)
			return null;

		UserDetail entity = new UserDetail();
		entity.setId(model.getId());
		entity.setUsername(model.getUsername());
		entity.setPassword(model.getPassword());
		entity.setEmail(model.getEmail());
		entity.setEnabled(model.isEnabled());

		if (model.getRoles() != null) {
			Set<Role> roles = model.getRoles().stream()
					.map(UserRoleMapper::toEntity).collect(Collectors.toSet());
			entity.setRoles(new ArrayList<Role>(roles));
		}

		return entity;
	}

	public static org.llin.demo.northwind.model.Role toModel(org.llin.demo.northwind.data.entity.Role entityRole) {
		if (entityRole == null)
			return null;

		org.llin.demo.northwind.model.Role role = new org.llin.demo.northwind.model.Role();
		role.setId(entityRole.getId());
		role.setName(entityRole.getName());
		role.setDescription(entityRole.getDescription());
		return role;
	}

	public static org.llin.demo.northwind.data.entity.Role toEntity(org.llin.demo.northwind.model.Role modelRole) {
		if (modelRole == null)
			return null;

		org.llin.demo.northwind.data.entity.Role role = new org.llin.demo.northwind.data.entity.Role();
		role.setId(modelRole.getId());
		role.setName(modelRole.getName());
		role.setDescription(modelRole.getDescription());
		return role;
	}
}
