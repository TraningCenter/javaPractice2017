package com.alegerd.json;

import com.alegerd.model.Person;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PersonConverter implements JsonSerializer<Person>{

    @Override
    public JsonElement serialize(Person person, Type type,
           JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("id", person.getId());
        return object;
    }
}
