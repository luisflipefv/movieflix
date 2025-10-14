package dev.luisf.movieflix.mapper;

import dev.luisf.movieflix.Controller.request.CategoryRequest;
import dev.luisf.movieflix.Controller.response.CategoryResponse;
import dev.luisf.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
