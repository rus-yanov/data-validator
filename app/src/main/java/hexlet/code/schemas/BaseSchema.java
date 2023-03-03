package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    private final List<Predicate> validStuff = new ArrayList<>();
    private boolean isRequired = false;

    public boolean isRequired() {
        return isRequired;
    }
    public BaseSchema required() {
        this.isRequired = true;
        return this;
    }

    public List<Predicate> getValid() {
        return validStuff;
    }

    public void addValid(Predicate predicate) {
        validStuff.add(predicate);
    }

    public boolean isValid(Object obj) {
        if (!isRequired() && (obj == null || obj.equals(""))) {
            return true;
        } else if (isRequired() && (obj == null || obj.equals(""))) {
            return false;
        } else {
            for (Predicate pred : getValid()) {
                if (!pred.test(obj)) {
                    return false;
                }
            }
            return true;
        }
    }
}
