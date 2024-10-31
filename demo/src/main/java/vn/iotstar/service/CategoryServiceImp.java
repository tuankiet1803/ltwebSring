package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Category;
import vn.iotstar.repository.*;

@Service
public class CategoryServiceImp implements ICategoryService {

	@Autowired
	CategoryRespositary category;

	@Override
	public <S extends Category> S save(S entity) {
		return category.save(entity);
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return category.findAll(sort);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return category.findAll(pageable);
	}

	@Override
	public List<Category> findAll() {
		return category.findAll();
	}

	@Override
	public Optional<Category> findById(Long id) {
		return category.findById(id);
	}

	@Override
	public long count() {
		return category.count();
	}

	@Override
	public void deleteById(Long id) {
		category.deleteById(id);
	}

	@Override
	public Page<Category> findByNameContaining(String keyword, Pageable page) {
		return category.findByNameContaining(keyword, page);
	}
}
