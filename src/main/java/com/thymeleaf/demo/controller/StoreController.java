package com.thymeleaf.demo.controller;

import com.thymeleaf.demo.DemoApplication;
import com.thymeleaf.demo.entity.Store;
import com.thymeleaf.demo.entity.StoreItem;
import com.thymeleaf.demo.service.StoreService;
import com.thymeleaf.demo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private StoreService storeService;

	@GetMapping("/list")
	public String list(Model model,
					   @PageableDefault(size = DemoApplication.PAGE_SIZE) Pageable pageable) {
		model.addAttribute("datas", storeService.list(pageable));
		return "store/list";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute Store store) {
		return "store/content";
	}

	@GetMapping("/view/{storeId}")
	public String view(@PathVariable Integer storeId, Model model) {
		// 查询入库单
		model.addAttribute("store", storeService.findById(storeId));
		// 查询入库单明细
		model.addAttribute("items", storeService.findByStoreId(storeId));
		model.addAttribute("readonly", true);
		return "store/content";
	}

	@RequestMapping("/save")
	public String save(@RequestParam String itemJson) {
		// 将JSON转换为SaleItem对象
		List<StoreItem> items = JsonUtil.fromJson(itemJson, StoreItem.class);
		storeService.save(items);
		return "redirect:/store/list";
	}

}
