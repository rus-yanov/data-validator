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
    public void required() {
        this.isRequired = true;
    }

    public List<Predicate> getValid() {
        return validStuff;
    }

    public void addValid(Predicate predicate) {
        validStuff.add(predicate);
    }
}
