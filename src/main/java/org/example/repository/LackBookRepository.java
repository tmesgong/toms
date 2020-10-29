package org.example.repository;

import org.example.bo.LackBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LackBookRepository extends Repository<LackBook,Integer> {
    @Query(nativeQuery = true,value = "select r.bno,sum(application_num-ISNULL(stock_quantity, 0))num \n" +
            "from (select * from requisition_item where rno in(select rno from buying_requisition where audit_status=1 and get_status!=2)) r left outer join inventory i \n" +
            "on r.bno=i.ibook_no \n" +
            "where application_num > ISNULL(stock_quantity, 0)  \n" +
            "group by r.bno")
    List<LackBook> findAllBookLack();

}
