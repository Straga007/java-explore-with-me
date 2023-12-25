package ru.practicum.ewm.categories.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.categories.service.CategoryService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryControllerAdmin {

    CategoryService categoryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoryDto addCategory(@RequestBody @Valid CategoryDto categoryDto) {
        log.info("Received a request to create a category: categoryDto={}", categoryDto);
        CategoryDto newCategoryDto = categoryService.addCategory(categoryDto);
        log.info("Returning the created category: {}", newCategoryDto);
        return newCategoryDto;
    }

    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@PathVariable Long catId,
                                      @RequestBody @Valid CategoryDto categoryDto) {
        log.info("Received a request to update: categoryId={}, categoryDto={}", catId, categoryDto);
        CategoryDto newCategoryDto = categoryService.updateCategory(catId, categoryDto);
        log.info("Returning the updated category: {}", newCategoryDto);
        return newCategoryDto;
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long catId) {
        log.info("Received a request to delete the category: categoryId={}", catId);
        categoryService.deleteCategory(catId);
        log.info("Category categoryId={} deleted.", catId);
    }
}
