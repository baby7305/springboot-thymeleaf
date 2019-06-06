package com.thymeleaf.demo.dao;

import com.thymeleaf.demo.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleItemDao extends JpaRepository<SaleItem, Integer> {

	List<SaleItem> findBySaleId(Integer saleId);
}
