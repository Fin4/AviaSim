package typeAdapters;

import com.google.gson.*;
import domain.*;

import java.lang.reflect.Type;

public class AircraftAdapter implements /*JsonSerializer<Aircraft>,*/ JsonDeserializer<Aircraft> {

    private static final String CLASSNAME = "CLASSNAME";

/*    public JsonElement serialize(Aircraft src, Type typeOfSrc,
                                 JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();
        String className = src.getClass().getName();

        jsonObject.add(CLASSNAME, new JsonPrimitive(className));
        jsonObject.add("aircraft", context.serialize(src, src.getClass()));

        return jsonObject;
    }*/


    public Aircraft deserialize(JsonElement json, Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException  {

        JsonObject jsonObject =  json.getAsJsonObject();
        String className = jsonObject.get(CLASSNAME).getAsString();
        JsonElement element = jsonObject.get("aircraft");

        try {
            return context.deserialize(element, Class.forName(className));
        }
        catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown type:" + className, e);
        }
    }
}

