package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {

    private List<Predicate> validStuff = new ArrayList<>();
    private boolean isRequired = false;

    public StringSchema() {
        Predicate<Object> isString = x -> x instanceof String;
        validStuff.add(isString);
    }

    public void required() {
        this.isRequired = true;
    }

    public StringSchema minLength(int num) {
        Predicate<String> minLength = x -> x.length() >= num;
        validStuff.add(minLength);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> contains = x -> x.contains(str);
        validStuff.add(contains);
        return this;
    }

    public boolean isValid(Object obj) {

        if (!isRequired && (obj == null || obj.equals(""))) {
            return true;
        } else if (isRequired && (obj == null || obj.equals(""))) {
            return false;
        } else {
            for (Predicate pred : validStuff) {
                if (!pred.test(obj)) {
                    return false;
                }
            }
            return true;
        }
    }
}
