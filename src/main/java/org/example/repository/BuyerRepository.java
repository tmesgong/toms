package org.example.repository;


import org.example.po.Buyer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BuyerRepository extends Repository<Buyer, String> {
    Buyer findBuyerByBworkNoAndBpassword(String bworkNo, String bpassword);










}
