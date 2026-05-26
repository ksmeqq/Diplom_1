package praktikum;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class DatabaseTest {
    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAvailableBunsReturnsThreeBuns() {
        List<Bun> buns = database.availableBuns();

        softly.assertThat(buns).isNotNull();
        softly.assertThat(buns).hasSize(3);
        softly.assertThat(buns.get(0).getName()).isEqualTo("black bun");
        softly.assertThat(buns.get(1).getName()).isEqualTo("white bun");
        softly.assertThat(buns.get(2).getName()).isEqualTo("red bun");
        softly.assertThat(buns.get(0).getPrice()).isEqualTo(100);
        softly.assertThat(buns.get(1).getPrice()).isEqualTo(200);
        softly.assertThat(buns.get(2).getPrice()).isEqualTo(300);
        softly.assertAll();
    }

    @Test
    public void testAvailableIngredientsReturnsSixIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();

        softly.assertThat(ingredients).isNotNull();
        softly.assertThat(ingredients).hasSize(6);
        softly.assertAll();
    }

    @Test
    public void testAvailableIngredientsContainsCorrectTypes() {
        List<Ingredient> ingredients = database.availableIngredients();

        softly.assertThat(ingredients.get(0).getType()).isEqualTo(IngredientType.SAUCE);
        softly.assertThat(ingredients.get(1).getType()).isEqualTo(IngredientType.SAUCE);
        softly.assertThat(ingredients.get(2).getType()).isEqualTo(IngredientType.SAUCE);

        softly.assertThat(ingredients.get(3).getType()).isEqualTo(IngredientType.FILLING);
        softly.assertThat(ingredients.get(4).getType()).isEqualTo(IngredientType.FILLING);
        softly.assertThat(ingredients.get(5).getType()).isEqualTo(IngredientType.FILLING);

        softly.assertAll();
    }

    @Test
    public void testAvailableIngredientsContainsCorrectNamesAndPrices() {
        List<Ingredient> ingredients = database.availableIngredients();

        softly.assertThat(ingredients.get(0).getName()).isEqualTo("hot sauce");
        softly.assertThat(ingredients.get(1).getName()).isEqualTo("sour cream");
        softly.assertThat(ingredients.get(2).getName()).isEqualTo("chili sauce");
        softly.assertThat(ingredients.get(3).getName()).isEqualTo("cutlet");
        softly.assertThat(ingredients.get(4).getName()).isEqualTo("dinosaur");
        softly.assertThat(ingredients.get(5).getName()).isEqualTo("sausage");
        softly.assertThat(ingredients.get(0).getPrice()).isEqualTo(100f);
        softly.assertThat(ingredients.get(1).getPrice()).isEqualTo(200f);
        softly.assertThat(ingredients.get(2).getPrice()).isEqualTo(300f);
        softly.assertThat(ingredients.get(3).getPrice()).isEqualTo(100f);
        softly.assertThat(ingredients.get(4).getPrice()).isEqualTo(200f);
        softly.assertThat(ingredients.get(5).getPrice()).isEqualTo(300f);

        softly.assertAll();
    }
}
