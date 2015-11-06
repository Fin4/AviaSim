/*
package typeAdapters;

import com.google.gson.*;
import domain.Aircraft;
import domain.type.AircraftType;
import domain.type.PlaneType;

import java.lang.reflect.Type;

public class FlyableAdapter implements JsonSerializer<Aircraft>, JsonDeserializer<Aircraft> {
    private static final String CLASSNAME = "CLASSNAME";

    @Override
    public JsonElement serialize(Aircraft src, Type typeOfSrc,
                                 JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", String.valueOf(src.getType()));
        jsonObject.addProperty("number", src.getNumber());
        jsonObject.addProperty("latitude", src.getLatitude());
        jsonObject.addProperty("longitude", src.getLongitude());
        jsonObject.addProperty("altitude", src.getAltitude());

        return jsonObject;
    }

    @Override
    public Aircraft deserialize(JsonElement json, Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException  {

        JsonObject jsonObject =  json.getAsJsonObject();

*/
/*        String className = jsonObject.get(CLASSNAME).getAsString();

        Class cls;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }*//*



        Aircraft aircraft = new Aircraft();

        aircraft.setType(AircraftType.valueOf(jsonObject.get("type").getAsString()));
        aircraft.setNumber(jsonObject.get("number").getAsInt());
        aircraft.setAltitude(jsonObject.get("altitude").getAsFloat());
        aircraft.setLongitude(jsonObject.get("longitude").getAsFloat());
        aircraft.setLatitude(jsonObject.get("latitude").getAsFloat());

        return aircraft;
    }
}
*/

