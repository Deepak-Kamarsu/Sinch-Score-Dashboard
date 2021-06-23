package sinch.scoredashboard.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sinch.scoredashboard.model.Category;
import sinch.scoredashboard.repository.CategoryRepository;
import sinch.scoredashboard.repository.UserRankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
public class CategoryController {

    private CategoryRepository categoryRepository;
    private UserRankRepository userRankRepository;
    Logger logger= LoggerFactory.getLogger(CategoryController.class);

    public CategoryController(CategoryRepository categoryRepository
    ,UserRankRepository userRankRepository) {
        this.categoryRepository = categoryRepository;
        this.userRankRepository = userRankRepository;
    }

    @GetMapping("/category")
    public Iterable<Category> getCategories()
    {
        try{
            return categoryRepository.getAll();
        }    
        catch(Exception e)
        {
            logger.error("Something went wrong in retreiving the getCategories values", e);
            throw e;
        }
    }

    @GetMapping("/category/{categoryName}")
    public Category getCategory(@PathVariable String categoryName)
    {
        try
        {
            Category category;
            if(categoryName.equals("Overall"))
            {
                category = new Category("Overall"); 
            } 
            else{
                category = categoryRepository.findByName(categoryName);
            }
            category.setUserRankDetails(userRankRepository.findCategoriesByName(categoryName, 10));
            return category;
        }
        catch(Exception e)
        {
            logger.error("Something went wrong in retreiving the getCategory values", e);
            throw e;
        }
    }

    

    
}
