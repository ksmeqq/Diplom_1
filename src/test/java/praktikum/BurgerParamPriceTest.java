package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParamPriceTest {
    private final float bunPrice;
    private final List<Float> ingredientsPrices;
    private final float expected;

    public BurgerParamPriceTest(float bunPrice, List<Float> ingredientsPrices, float expected) {
        this.bunPrice = bunPrice;
        this.ingredientsPrices = ingredientsPrices;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "bun={0}, ingredients={1}, expected={2}")
    public static Object[][] priceData() {
        return new Object[][] {
                // 1. Пустой список ингредиентов / булка умножается на 2
                {100f, Arrays.asList(), 200f},
                // 2. Один ингредиент
                {200f, Arrays.asList(150f), 550f},
                // 3. Несколько ингредиентов (проверка цикла)
                {300f, Arrays.asList(50f, 100f, 150f), 900f},
                // 4. Нулевые цены (граничное значение)
                {0f, Arrays.asList(0f, 0f), 0f},
                // 5. Нецелые значения
                {0.1f, Arrays.asList(0.2f, 0.3f), 0.7f},
                // 6. Реальные данные из Database
                {100f, Arrays.asList(100f), 300f},  // black bun + hot sauce
                {200f, Arrays.asList(200f, 200f), 800f}, // white bun + sour cream + dinosaur,
                // 7. отрицательные цены
                {-10f, Arrays.asList(-20f, -10f), -50f},
        };
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("test-bun", bunPrice));

        for (Float price : ingredientsPrices) {
            burger.addIngredient(new Ingredient(IngredientType.SAUCE, "test-ing", price));
        }
        assertEquals("Неверная итоговая цена", expected, burger.getPrice(), 0.001f);
    }
}
