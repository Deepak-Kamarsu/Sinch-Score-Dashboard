package sinch.scoredashboard.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import sinch.scoredashboard.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
    Category findByName(String categoryName);
    default Iterable<Category> getAll(){
        var categories = findAll();
        List<Category> allCategories = new ArrayList<Category>();
        categories.forEach(allCategories::add);
        allCategories.add(0, new Category("Overall"));
        return allCategories;
    }
}
