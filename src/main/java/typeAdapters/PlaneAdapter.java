package typeAdapters;

import com.google.gson.*;
import domain.Coords;
import domain.Plane;
import domain.type.PlaneType;

import java.lang.reflect.Type;

public class PlaneAdapter implements JsonSerializer<Plane>, JsonDeserializer<Plane> {

    private static final String CLASSNAME = "CLASSNAME";

    public Plane deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject =  jsonElement.getAsJsonObject();
        Plane plane= new Plane();
        plane.setType(PlaneType.valueOf(jsonObject.get("type").getAsString()));
        plane.setNumber(jsonObject.get("number").getAsInt());

        JsonElement coordsElement = jsonObject.get("coords");
        Coords coords = context.deserialize(coordsElement, Coords.class);
        plane.setCoords(coords);

        return plane;
    }


    public JsonElement serialize(Plane plane, Type type, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();
        String className = plane.getClass().getName();
        jsonObject.add(CLASSNAME, new JsonPrimitive(className));
        jsonObject.addProperty("type", String.valueOf(plane.getType()));
        jsonObject.addProperty("number", plane.getNumber());
        jsonObject.add("coords", context.serialize(plane.getCoords(), plane.getCoords().getClass()));

        return jsonObject;
    }
}
