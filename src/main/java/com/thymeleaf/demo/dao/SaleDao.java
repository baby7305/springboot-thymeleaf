package com.thymeleaf.demo.dao;

import com.thymeleaf.demo.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleDao extends JpaRepository<Sale, Integer> {

}
