package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunTest {
    private final String bunName;
    private final float bunPrice;

    public BunTest(String bunName, float bunPrice) {
        this.bunPrice = bunPrice;
        this.bunName = bunName;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"black bun", 100f},
                {"", 0f},
                {"x", -50f},
        };
    }

    @Test
    public void createBunTest() {
        Bun bun = new Bun(bunName, bunPrice);
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(bun.getName()).isEqualTo(bunName);
        softly.assertThat(bun.getPrice()).isEqualTo(bunPrice);
        softly.assertAll();
    }
}
