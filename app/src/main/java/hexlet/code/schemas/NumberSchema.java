package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        Predicate<Object> isNumber = x -> x instanceof Integer;
        addValid(isNumber);
    }

    public NumberSchema positive() {
        Predicate<Integer> isPositive = x -> x > 0;
        addValid(isPositive);
        return this;
    }

    public void range(int min, int max) {
        Predicate<Integer> range = x -> x <= max && x >= min;
        addValid(range);
    }
}
