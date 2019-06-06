package com.thymeleaf.demo.dao;

import com.thymeleaf.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreDao extends JpaRepository<Store, Integer> {


}
