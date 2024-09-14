package com.cg.casestudy.service;

import com.cg.casestudy.entity.Admin;

import java.util.List;

public interface AdminService {

    boolean saveAdmin(Admin admin);

    Admin getAdminByEmailAndPassword(String email, String password);

    List<Admin> getAllAdmins();

    boolean deleteAdmin(int id);

    // to be updated...
}
