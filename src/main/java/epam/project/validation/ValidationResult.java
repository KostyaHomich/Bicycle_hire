package epam.project.validation;

import lombok.Data;

import java.util.*;

@Data
public class ValidationResult {
    private Map<String, List<String>> errors = new HashMap<>();

    public ValidationResult() {
    }

    public void add(String key, List<String> values) {
        errors.put(key, values);
    }

    public void remove(String key) {
        errors.remove(key);
    }

    public boolean containsKey(String key) {
        return errors.containsKey(key);
    }
    public boolean containsValue(List<String> values) {
        return errors.containsValue(values);
    }
}
