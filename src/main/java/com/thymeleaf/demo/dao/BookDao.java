package com.thymeleaf.demo.dao;

import com.thymeleaf.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Integer> {

	// 查询前3条 isDelete 的数据，并且会根据 id 排序
	List<Book> findTop3ByIsDeleteOrderByIdDesc(boolean isDelete);
}