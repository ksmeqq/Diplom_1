package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{0}, {1}, {2}")
    public static Object[][] data() {
        return new Object[][] {
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.FILLING, "cutlet", 200f},
                {IngredientType.SAUCE, "", 0f},
        };
    }

    @Test
    public void getNameReturnsConstructorValue() {
        assertEquals(name, new Ingredient(type, name, price).getName());
    }

    @Test
    public void getPriceReturnsConstructorValue() {
        assertEquals(price, new Ingredient(type, name, price).getPrice(), 0.001f);
    }

    @Test
    public void getTypeReturnsConstructorValue() {
        assertEquals(type, new Ingredient(type, name, price).getType());
    }
}
