package com.example.demo1.model;

import com.google.gson.*;
import javafx.geometry.Point2D;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ConnectionTypeAdapter implements JsonSerializer<Connection>, JsonDeserializer<Connection> {

    @Override
    public Connection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Entrance oval1 = context.deserialize(jsonObject.get("oval1"), Entrance.class);
        Entrance oval2 = context.deserialize(jsonObject.get("oval2"), Entrance.class);
        Point[] path = context.deserialize(jsonObject.get("path"), Point[].class);
        String id = context.deserialize(jsonObject.get("id"), String.class);

        Connection connection = new Connection(oval1, oval2, path, id);
        return connection;
    }

    @Override
    public JsonElement serialize(Connection src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.add("oval1", context.serialize(src.getOval1()));
        jsonObject.add("oval2", context.serialize(src.getOval2()));
        jsonObject.add("path", context.serialize(src.getPath()));

        return jsonObject;
    }
}
