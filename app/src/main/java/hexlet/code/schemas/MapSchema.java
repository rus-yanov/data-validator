package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> isMap = x -> x instanceof Map<?, ?>;
        addValid(isMap);
    }

    public void sizeof(int size) {
        Predicate<Map<?, ?>> requiredSize = x -> x.size() == size;
        addValid(requiredSize);
    }
}
