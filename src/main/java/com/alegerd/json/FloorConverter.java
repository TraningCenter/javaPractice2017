package com.alegerd.json;

import com.alegerd.model.Floor;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class FloorConverter implements JsonSerializer<Floor>{
    @Override
    public JsonElement serialize(Floor floor, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("number", floor.getNumber());
        return null;
    }
}
