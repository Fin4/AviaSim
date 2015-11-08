package typeAdapters;

import com.google.gson.*;
import domain.Coords;
import domain.Helicopter;
import domain.type.HelicopterType;

import java.lang.reflect.Type;

public class HelicopterAdapter implements JsonSerializer<Helicopter>, JsonDeserializer<Helicopter> {

    private static final String CLASSNAME = "CLASSNAME";

    public Helicopter deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {


        JsonObject jsonObject =  jsonElement.getAsJsonObject();
        Helicopter helicopter = new Helicopter();
        helicopter.setType(HelicopterType.valueOf(jsonObject.get("type").getAsString()));
        helicopter.setNumber(jsonObject.get("number").getAsInt());

        JsonElement coordsElement = jsonObject.get("coords");
        Coords coords = context.deserialize(coordsElement, Coords.class);
        helicopter.setCoords(coords);

        return helicopter;
    }


    public JsonElement serialize(Helicopter helicopter, Type type, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();
        String className = helicopter.getClass().getName();
        jsonObject.add(CLASSNAME, new JsonPrimitive(className));
        jsonObject.addProperty("type", String.valueOf(helicopter.getType()));
        jsonObject.addProperty("number", helicopter.getNumber());
        jsonObject.add("coords", context.serialize(helicopter.getCoords(), helicopter.getCoords().getClass()));

        return jsonObject;
    }
}
