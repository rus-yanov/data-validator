### Tests and linter status:
[![Actions Status](https://github.com/rus-yanov/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/rus-yanov/java-project-78/actions)
[![Basic validation](https://github.com/rus-yanov/java-project-78/actions/workflows/validator-check.yml/badge.svg)](https://github.com/rus-yanov/java-project-78/actions/workflows/validator-check.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/09d9341787c30639a6eb/maintainability)](https://codeclimate.com/github/rus-yanov/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/09d9341787c30639a6eb/test_coverage)](https://codeclimate.com/github/rus-yanov/java-project-78/test_coverage)

<h1><b>Data validator</b></h1>
<p>Data Validator is a tool for checking the accuracy and consistency of values during runtime. it allows users to create a schema to define, transform, and verify values, ensuring that the values meet the desired format and requirements. This validator can check the correctnes of ints, strings and maps.</p>
<h2><b>Number validation</b></h2>
<ul>
  <li>required - any number including zero</li>
  <li>positive - positive number</li>
  <li>range - the range in which the numbers should fall, including borders.</li>
</ul>

```ts
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator v = new Validator();
NumberSchema schema = v.number();

// Until the required() method is called, null is considered valid
schema.isValid(null); // true
schema.positive().isValid(null); // true

schema.required();
schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false
schema.isValid(-10); // false
// Zero is not a positive number
schema.isValid(0); // false

schema.range(5, 10);
schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```

<h2><b>String validation</b></h2>
<ul>
  <li>required - any non-empty string</li>
  <li>minLength - the string is equal to or longer than the specified number</li>
  <li>contains - the string contains a specific substring</li>
</ul>

```ts
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator v = new Validator();
StringSchema schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required();
schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(5); // false
schema.isValid(""); // false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// already false, as one more check contains("whatthe") has been added
```

<h2><b>Map validation</b></h2>
<ul>
  <li>required - Map is required</li>
  <li>sizeof - the number of key-value pairs in the Map must be equal to the specified number</li>
</ul>

```ts
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator v = new Validator();
MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();
schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);
schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
```

<h2><b>Nested Validation</b></h2>
<p>When working with complex data, it may be necessary to check not only the Map object itself, but also the data inside</p>
<ul>
  <li>shape - allows to describe validation for Map object values by keys</li>
</ul>

```ts
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("last name", "Korinevskaya");
human3.put("age", 50);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
```
