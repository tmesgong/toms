package org.example.repository;

import org.example.po.TextbookAdmin;
import org.springframework.data.repository.Repository;

public interface TextbookAdminRepository extends Repository<TextbookAdmin,String> {
    TextbookAdmin findTextbookAdminByAworkNoAndApassword(String aworkNo, String apassword);
}
