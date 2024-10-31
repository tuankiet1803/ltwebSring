package vn.iotstar.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Category;

@Repository
public interface CategoryRespositary extends JpaRepository<Category, Long>{
	Page<Category> findByNameContaining(String keyword, Pageable page);
}
