package typeAdapters;

import com.google.gson.*;
import domain.Plane;
import domain.type.PlaneType;

import java.lang.reflect.Type;

public class PlaneAdapter implements JsonSerializer<Plane>, JsonDeserializer<Plane> {

    private static final String CLASSNAME = "CLASSNAME";

    public Plane deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject =  jsonElement.getAsJsonObject();
        Plane plane= new Plane();
        plane.setType(PlaneType.valueOf(jsonObject.get("type").getAsString()));
        plane.setNumber(jsonObject.get("number").getAsInt());
        plane.setAltitude(jsonObject.get("altitude").getAsFloat());
        plane.setLongitude(jsonObject.get("longitude").getAsFloat());
        plane.setLatitude(jsonObject.get("latitude").getAsFloat());

        return plane;
    }


    public JsonElement serialize(Plane plane, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();
        String className = plane.getClass().getName();
        jsonObject.add(CLASSNAME, new JsonPrimitive(className));
        jsonObject.addProperty("type", String.valueOf(plane.getType()));
        jsonObject.addProperty("number", plane.getNumber());
        jsonObject.addProperty("latitude", plane.getLatitude());
        jsonObject.addProperty("longitude", plane.getLongitude());
        jsonObject.addProperty("altitude", plane.getAltitude());

        return jsonObject;
    }
}
