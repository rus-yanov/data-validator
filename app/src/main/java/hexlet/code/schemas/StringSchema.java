package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        Predicate<Object> isString = x -> x instanceof String;
        addValid(isString);
    }

    public StringSchema required() {
        setRequired(true);
        return this;
    }

    public void minLength(int num) {
        Predicate<String> minLength = x -> x.length() >= num;
        addValid(minLength);
    }

    public StringSchema contains(String str) {
        Predicate<String> contains = x -> x.contains(str);
        addValid(contains);
        return this;
    }
}
