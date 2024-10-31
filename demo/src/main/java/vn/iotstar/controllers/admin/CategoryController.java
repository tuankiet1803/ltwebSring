package vn.iotstar.controllers.admin;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.CategoryModel;
import vn.iotstar.service.ICategoryService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

	@Autowired
	ICategoryService categoryService;

	@RequestMapping("")
	public String all(Model model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("listcategory", list);
		return "admin/category-list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		return "admin/category-add";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") Long cateid) {
		Optional<Category> optCategory = categoryService.findById(cateid);
		CategoryModel cateModel = new CategoryModel();
		if (optCategory.isPresent()) {
			Category entity = optCategory.get();
			BeanUtils.copyProperties(entity, cateModel);
			cateModel.setIsEdit(true);
			model.addAttribute("category", cateModel);
			return new ModelAndView("admin/category-add", model);
		}
		return new ModelAndView("forward:/admin/categories", model);
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(ModelMap model, @PathVariable("id") Long cateid) {
		categoryService.deleteById(cateid);
		return new ModelAndView("redirect:/admin/categories");
	}

	@PostMapping("/save")
	public ModelAndView postMethodName(ModelMap model, @Valid @ModelAttribute("category") CategoryModel cateModel,
			BindingResult result) {
		// TODO: process POST request
		if (result.hasErrors()) {
			return new ModelAndView("admin/category-add");
		}
		Category entity = new Category();
		BeanUtils.copyProperties(cateModel, entity);
		categoryService.save(entity);
		return new ModelAndView("forward:/admin/categories", model);
	}

	@RequestMapping("/searchpaginated")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int count = (int) categoryService.count();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Category> resultPage = null;
		if (StringUtils.hasText(name)) {
			resultPage = categoryService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = categoryService.findAll(pageable);
		}
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > count) {
				if (end == totalPages)
					start = end - count;
				else if (start == 1)
					end = start + count;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("categoryPage", resultPage);
		return "admin/category-list";
	}
}
