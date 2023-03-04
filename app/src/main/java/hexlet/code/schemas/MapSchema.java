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

    public void shape(Map<?, BaseSchema> schemas) {
        Predicate<Map<?, ?>> shape = x -> validateValue(x, schemas);
        addValid(shape);
    }

    public boolean validateValue(Map<?, ?> data, Map<?, BaseSchema> schemas) {
        for (Map.Entry<?, BaseSchema> schema : schemas.entrySet()) {
            Object key = schema.getKey();
            if (!data.containsKey(key)) {
                return false;
            } else if (!schema.getValue().isValid(data.get(key))) {
                return false;
            }
        }
        return true;
    }
}
