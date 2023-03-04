package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final List<Predicate> checks = new ArrayList<>();
    private boolean isRequired = false;

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public List<Predicate> getValid() {
        return checks;
    }

    public void addValid(Predicate predicate) {
        checks.add(predicate);
    }

    public boolean isValid(Object obj) {
        if (!isRequired && (obj == null || obj.equals(""))) {
            return true;
        } else if (isRequired && (obj == null || obj.equals(""))) {
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
