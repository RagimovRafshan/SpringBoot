package com.example.springboot311;

import com.example.springboot311.model.Role;
import com.example.springboot311.model.User;
import com.example.springboot311.service.RoleService;
import com.example.springboot311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestConfig {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TestConfig(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void insertData() {

//        Role roleAdmin = new Role("ROLE_ADMIN");
//        Role roleUser = new Role("ROLE_USER");
        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_USER"));

        Set<Role> roles1 = new HashSet<>();
        roles1.add(roleService.getRoleByName("ROLE_USER"));

        User bob = new User();
        bob.setUsername("bob");
        bob.setPassword("bob");
        bob.setName("Bob");
        bob.setAge(23);
        bob.setEmail("bob23@test.com");
        bob.setRoles(roles1);
        userService.addUser(bob);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(roleService.getRoleByName("ROLE_USER"));
        roles2.add(roleService.getRoleByName("ROLE_ADMIN"));

        User tom = new User();
        tom.setUsername("tom");
        tom.setPassword("tom");
        tom.setName("Tom");
        tom.setAge(41);
        tom.setEmail("tom41@test.com");
        tom.setRoles(roles2);
        userService.addUser(tom);
    }
}
