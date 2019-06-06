package com.thymeleaf.demo.controller;

import com.thymeleaf.demo.DemoApplication;
import com.thymeleaf.demo.entity.Sale;
import com.thymeleaf.demo.entity.SaleItem;
import com.thymeleaf.demo.service.SaleService;
import com.thymeleaf.demo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	private SaleService saleService;

	@GetMapping("/list")
	public String list(Model model,
					   @PageableDefault(size = DemoApplication.PAGE_SIZE) Pageable pageable) {
		model.addAttribute("datas", saleService.findAll(pageable));
		return "sale/list";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute Sale sale) {
		return "sale/content";
	}

	@RequestMapping("/save")
	public String save(@RequestParam String itemJson) {
		// 将JSON转换为SaleItem对象
		List<SaleItem> items = JsonUtil.fromJson(itemJson, SaleItem.class);
		saleService.save(items);
		return "redirect:/sale/list";
	}

	@GetMapping("/view/{saleId}")
	public String view(@PathVariable Integer saleId, Model model) {
		// 查询入库单
		model.addAttribute("sale", saleService.findById(saleId));
		// 查询入库单明细
		model.addAttribute("items", saleService.findBySaleId(saleId));
		model.addAttribute("readonly", true);
		return "sale/content";
	}
}
