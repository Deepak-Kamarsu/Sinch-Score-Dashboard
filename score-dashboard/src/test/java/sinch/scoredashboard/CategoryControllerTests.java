package sinch.scoredashboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.tomcat.util.bcel.Const;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import sinch.scoredashboard.controller.CategoryController;
import sinch.scoredashboard.model.Category;
import sinch.scoredashboard.repository.CategoryRepository;
import sinch.scoredashboard.repository.UserRankRepository;

@SpringBootTest
public class CategoryControllerTests {

    @Test
	void shouldGetCategory() {
        var categoryName = "Overall";
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        UserRankRepository userRankRepository = Mockito.mock(UserRankRepository.class);
        when(categoryRepository.findByName(categoryName)).thenReturn(new Category(categoryName));
		when(userRankRepository.findCategoriesByName(categoryName, 10)).thenReturn(null);
        
        CategoryController categoryController = new CategoryController(categoryRepository, userRankRepository);
        var expecteResult = categoryController.getCategory("Overall");
        assertEquals(categoryName, expecteResult.getName(),"getCategory failed!!!");

	}
    
}
