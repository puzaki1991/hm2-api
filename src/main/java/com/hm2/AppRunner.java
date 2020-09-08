package com.hm2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Value("${timezone}")
    private String timezone;

//    @Autowired
//    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // TODO Time Zone
        logger.info("==**== Spring boot application running in {} timezone : {}", timezone, new Date());

        // TODO  Create Role Admin and Staff
//        Role findAdmin = roleRepository.findByRoleCode(RoleConstant.ADMIN);
//        Role findStaff = roleRepository.findByRoleCode(RoleConstant.STAFF);
//        Role findGeneral = roleRepository.findByRoleCode(RoleConstant.GENERAL);

//        Role role = null;
//        if (findAdmin == null) {
//			role = new Role();
//			role.setRoleCode(RoleConstant.ADMIN);
//			role.setRoleName("Admin");
//			role.setDescription("Admin rights");
//            roleRepository.save(role);
//            logger.info(".... Create Role Admin = {}", RoleConstant.ADMIN);
//        }
//        if (findStaff == null) {
//			role = new Role();
//			role.setRoleCode(RoleConstant.STAFF);
//			role.setRoleName("Staff");
//			role.setDescription("Staff");
//            roleRepository.save(role);
//            logger.info(".... Create Role Staff = {}", RoleConstant.STAFF);
//        }
//        if (findGeneral == null) {
//			role = new Role();
//			role.setRoleCode(RoleConstant.GENERAL);
//			role.setRoleName("Member");
//			role.setDescription("Member");
//			roleRepository.save(role);
//			logger.info(".... Create Role General = {}", RoleConstant.GENERAL);
//        }

        logger.info(".... Fetching lookups");
    }

}