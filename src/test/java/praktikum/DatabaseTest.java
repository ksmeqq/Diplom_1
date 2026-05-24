package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAvailableBunsReturnsThreeBuns() {
        List<Bun> buns = database.availableBuns();
        assertNotNull(buns);
        assertEquals(3, buns.size());
        assertEquals("black bun", buns.get(0).getName());
        assertEquals(100, buns.get(0).getPrice(), 0.001);
        assertEquals("white bun", buns.get(1).getName());
        assertEquals(200, buns.get(1).getPrice(), 0.001);
        assertEquals("red bun", buns.get(2).getName());
        assertEquals(300, buns.get(2).getPrice(), 0.001);
    }

    @Test
    public void testAvailableIngredientsReturnsSixIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertNotNull(ingredients);
        assertEquals(6, ingredients.size());
    }

    @Test
    public void testAvailableIngredientsContainsCorrectTypes() {
        List<Ingredient> ingredients = database.availableIngredients();

        // Первые 3 - соусы
        assertEquals(IngredientType.SAUCE, ingredients.get(0).getType());
        assertEquals(IngredientType.SAUCE, ingredients.get(1).getType());
        assertEquals(IngredientType.SAUCE, ingredients.get(2).getType());

        // Следующие 3 - начинки
        assertEquals(IngredientType.FILLING, ingredients.get(3).getType());
        assertEquals(IngredientType.FILLING, ingredients.get(4).getType());
        assertEquals(IngredientType.FILLING, ingredients.get(5).getType());
    }

    @Test
    public void testAvailableIngredientsContainsCorrectNamesAndPrices() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals("hot sauce", ingredients.get(0).getName());
        assertEquals(100f, ingredients.get(0).getPrice(), 0.001f);
        assertEquals("sour cream", ingredients.get(1).getName());
        assertEquals(200f, ingredients.get(1).getPrice(), 0.001f);
        assertEquals("chili sauce", ingredients.get(2).getName());
        assertEquals(300f, ingredients.get(2).getPrice(), 0.001f);
        assertEquals("cutlet", ingredients.get(3).getName());
        assertEquals(100f, ingredients.get(3).getPrice(), 0.001f);
        assertEquals("dinosaur", ingredients.get(4).getName());
        assertEquals(200f, ingredients.get(4).getPrice(), 0.001f);
        assertEquals("sausage", ingredients.get(5).getName());
        assertEquals(300f, ingredients.get(5).getPrice(), 0.001f);
    }
}
