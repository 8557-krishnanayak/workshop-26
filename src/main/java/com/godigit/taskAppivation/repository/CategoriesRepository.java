package com.godigit.taskAppivation.repository;

import com.godigit.taskAppivation.model.CategoryModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoryModal, Long> {
    CategoryModal findByName(String name);
}
