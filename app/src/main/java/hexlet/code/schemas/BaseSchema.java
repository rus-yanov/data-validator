package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final List<Predicate> predicates = new ArrayList<>();
    private boolean isRequired = false;

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public List<Predicate> getPredicate() {
        return predicates;
    }

    public void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    public boolean isValid(Object obj) {
        if (!isRequired && (obj == null || obj.equals(""))) {
            return true;
        } else if (isRequired && (obj == null || obj.equals(""))) {
            return false;
        } else {
            for (Predicate pred : getPredicate()) {
                if (!pred.test(obj)) {
                    return false;
                }
            }
            return true;
        }
    }
}
