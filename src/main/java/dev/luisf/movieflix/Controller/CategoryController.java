package dev.luisf.movieflix.Controller;

import dev.luisf.movieflix.Controller.request.CategoryRequest;
import dev.luisf.movieflix.Controller.response.CategoryResponse;
import dev.luisf.movieflix.entity.Category;
import dev.luisf.movieflix.mapper.CategoryMapper;
import dev.luisf.movieflix.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(categoryService.findAll()
            .stream()
            .map(CategoryMapper::toCategoryResponse)
            .toList());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request){
        Category newCategory = CategoryMapper.toCategory(request);
        Category saved = categoryService.save(newCategory);
        CategoryResponse response = CategoryMapper.toCategoryResponse(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getByCategoryId(@PathVariable Long id){
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByCategoryId(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
