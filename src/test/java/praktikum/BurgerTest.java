package praktikum;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Mock
    Bun bun;

    @Mock
    Ingredient mockIngredient;

    private Ingredient ingredientWithName(String name) {
        Ingredient ing = mock(Ingredient.class);
        when(ing.getName()).thenReturn(name);
        return ing;
    }

    private Burger burgerWithThreeIngredients() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientWithName("ketchup"));
        burger.addIngredient(ingredientWithName("mayonnaise"));
        burger.addIngredient(ingredientWithName("cutlet"));
        return burger;
    }

    @Test
    public void setBuns() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient);
        assertTrue(burger.ingredients.contains(mockIngredient));
    }

    @Test
    public void removeIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(mockIngredient));
    }

    @Test
    public void moveIngredientForward() {
        Burger burger = burgerWithThreeIngredients();
        burger.moveIngredient(0, 2);

        softly.assertThat(burger.ingredients.get(0).getName()).isEqualTo("mayonnaise");
        softly.assertThat(burger.ingredients.get(1).getName()).isEqualTo("cutlet");
        softly.assertThat(burger.ingredients.get(2).getName()).isEqualTo("ketchup");
        softly.assertThat(burger.ingredients.size()).isEqualTo(3);
        softly.assertAll();
    }

    @Test
    public void moveIngredientBackward() {
        Burger burger = burgerWithThreeIngredients();
        burger.moveIngredient(2, 0);

        softly.assertThat(burger.ingredients.get(0).getName()).isEqualTo("cutlet");
        softly.assertThat(burger.ingredients.get(1).getName()).isEqualTo("ketchup");
        softly.assertThat(burger.ingredients.get(2).getName()).isEqualTo("mayonnaise");
        softly.assertAll();
    }

    @Test
    public void moveIngredientSameIndex() {
        Burger burger = burgerWithThreeIngredients();
        List<Ingredient> before = new ArrayList<>(burger.ingredients);

        burger.moveIngredient(1, 1);

        assertEquals(before, burger.ingredients);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientNegativeIndex() {
        Burger burger = burgerWithThreeIngredients();
        burger.moveIngredient(-1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientIndexIsEqualToList() {
        Burger burger = burgerWithThreeIngredients();
        burger.moveIngredient(3, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientIndexGreaterThanList() {
        Burger burger = burgerWithThreeIngredients();
        burger.moveIngredient(0, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientEmptyList() {
        Burger emptyBurger = new Burger();
        emptyBurger.moveIngredient(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientSingleElementList() {
        Burger singleBurger = new Burger();
        singleBurger.addIngredient(mock(Ingredient.class));
        singleBurger.moveIngredient(0, 1);
    }

    @Test(expected = NullPointerException.class)
    public void getPriceNullBunThrowsNPE() {
        new Burger().getPrice();
    }

    @Test
    public void getPriceOnlyBunReturnsBunPriceTimesTwo() {
        Burger burger = new Burger();
        when(bun.getPrice()).thenReturn(10f);
        burger.setBuns(bun);
        assertEquals(20f, burger.getPrice(), 0.001f);
    }

    @Test
    public void receiptWithoutIngredientsContainsOnlyBunAndPrice() {
        Burger burger = new Burger();
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        String expected = String.format(
                "(==== black bun ====)%n" +
                        "(==== black bun ====)%n" +
                        "%nPrice: %f%n", 200f);
        assertEquals(expected, burger.getReceipt());
    }

    @Test
    public void receiptContainsIngredientLineInLowercase() {
        Burger burger = new Burger();
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getName()).thenReturn("hot sauce");
        when(mockIngredient.getPrice()).thenReturn(50f);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();
        assertEquals(
                String.format(
                        "(==== black bun ====)%n" +
                                "= sauce hot sauce =%n" +
                                "(==== black bun ====)%n" +
                                "%nPrice: %f%n", 250f),
                receipt);
    }

    @Test
    public void receiptPreservesIngredientsOrder() {
        Burger burger = new Burger();
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("sauce");
        when(sauce.getPrice()).thenReturn(50f);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(150f);

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();
        int sauceIdx = receipt.indexOf("sauce sauce");
        int fillingIdx = receipt.indexOf("filling cutlet");
        assertTrue(sauceIdx < fillingIdx);
    }
}
