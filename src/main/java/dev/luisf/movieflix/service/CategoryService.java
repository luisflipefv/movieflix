package dev.luisf.movieflix.service;

import dev.luisf.movieflix.entity.Category;
import dev.luisf.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category save(Category category){
        return repository.save(category);
    }

    public Optional<Category> getCategoryById(Long id){
        return repository.findById(id);
    }

    public void deleteCategory(Long id){
        repository.deleteById(id);
    }

}
