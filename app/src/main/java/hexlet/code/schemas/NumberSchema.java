package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        Predicate<Object> isNumber = x -> x instanceof Integer;
        addPredicate(isNumber);
    }

    public NumberSchema required() {
        setRequired(true);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> isPositive = x -> x > 0;
        addPredicate(isPositive);
        return this;
    }

    public void range(int min, int max) {
        Predicate<Integer> range = x -> x <= max && x >= min;
        addPredicate(range);
    }
}
