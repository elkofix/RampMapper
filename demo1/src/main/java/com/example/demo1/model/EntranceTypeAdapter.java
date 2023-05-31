package com.example.demo1.model;

import com.example.demo1.model.Entrance;
import com.google.gson.*;

import java.lang.reflect.Type;

public class EntranceTypeAdapter implements JsonSerializer<Entrance>, JsonDeserializer<Entrance> {

    @Override
    public Entrance deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String name = jsonObject.get("name").getAsString();
        Double posX = jsonObject.get("posX").getAsDouble();
        Double posY = jsonObject.get("posY").getAsDouble();
        int floor = jsonObject.get("floor").getAsInt();

        Entrance entrance = new Entrance(name, posX, posY, String.valueOf(floor));
        return entrance;
    }

    @Override
    public JsonElement serialize(Entrance src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("posX", src.getPosX());
        jsonObject.addProperty("posY", src.getPosY());
        jsonObject.addProperty("floor", src.getFloor());

        return jsonObject;
    }
}