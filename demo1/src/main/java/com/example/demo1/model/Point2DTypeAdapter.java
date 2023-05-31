package com.example.demo1.model;

import com.google.gson.*;
import javafx.geometry.Point2D;

import java.lang.reflect.Type;

public class Point2DTypeAdapter implements JsonSerializer<Point2D>, JsonDeserializer<Point2D> {

    @Override
    public Point2D deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        double x = jsonObject.get("x").getAsDouble();
        double y = jsonObject.get("y").getAsDouble();
        return new Point2D(x, y);
    }

    @Override
    public JsonElement serialize(Point2D src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", src.getX());
        jsonObject.addProperty("y", src.getY());
        return jsonObject;
    }
}