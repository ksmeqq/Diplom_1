package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class IngredientTypeTest {
    @Test
    public void valuesContainsSauceAndFilling() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(IngredientType.values()).hasSize(2);
        softly.assertThat(IngredientType.values()).containsExactly(IngredientType.SAUCE, IngredientType.FILLING);

        softly.assertAll();
    }
}
