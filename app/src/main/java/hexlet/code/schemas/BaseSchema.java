package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    protected final List<Predicate> predicates = new ArrayList<>();
    protected boolean isRequired = false;

    public final void setRequired(boolean required) {
        isRequired = required;
    }

    public final void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    public final boolean isValid(Object obj) {
        if (!isRequired && (obj == null || obj.equals(""))) {
            return true;
        } else if (isRequired && (obj == null || obj.equals(""))) {
            return false;
        } else {
            for (Predicate pred : predicates) {
                if (!pred.test(obj)) {
                    return false;
                }
            }
            return true;
        }
    }
}
