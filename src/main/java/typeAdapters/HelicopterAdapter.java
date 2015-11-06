package typeAdapters;

import com.google.gson.*;
import domain.Aircraft;
import domain.Helicopter;
import domain.type.HelicopterType;

import java.lang.reflect.Type;

public class HelicopterAdapter implements JsonSerializer<Helicopter>, JsonDeserializer<Helicopter> {
    @Override
    public Helicopter deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {


        JsonObject jsonObject =  jsonElement.getAsJsonObject();
        Helicopter helicopter = new Helicopter();
        helicopter.setType(HelicopterType.valueOf(jsonObject.get("type").getAsString()));
        helicopter.setNumber(jsonObject.get("number").getAsInt());
        helicopter.setAltitude(jsonObject.get("altitude").getAsFloat());
        helicopter.setLongitude(jsonObject.get("longitude").getAsFloat());
        helicopter.setLatitude(jsonObject.get("latitude").getAsFloat());

        return helicopter;
    }

    @Override
    public JsonElement serialize(Helicopter helicopter, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", String.valueOf(helicopter.getType()));
        jsonObject.addProperty("number", helicopter.getNumber());
        jsonObject.addProperty("latitude", helicopter.getLatitude());
        jsonObject.addProperty("longitude", helicopter.getLongitude());
        jsonObject.addProperty("altitude", helicopter.getAltitude());

        return jsonObject;
    }
}
