package org.example.repository;

import org.example.po.BuyingRequisition;
import org.example.po.Teacher;
import org.springframework.data.repository.Repository;


public interface TeacherRepository extends Repository<Teacher, String> {
    Teacher findTeacherByTworkNoAndTpassword(String tworkNo, String tpassword);



}
