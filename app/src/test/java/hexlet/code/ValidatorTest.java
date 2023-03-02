package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.schemas.StringSchema;


class ValidatorTest {

    @Test
    public void testString() {

        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("java")).isTrue();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(777)).isFalse();

        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("the fox").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        assertThat(schema.isValid("what does the fox say")).isFalse();
    }

    @Test
    public void testNumber() {

    }

}
