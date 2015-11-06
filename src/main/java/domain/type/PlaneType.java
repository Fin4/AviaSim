package domain.type;

import com.google.gson.annotations.SerializedName;

public enum PlaneType implements AircraftType {

    @SerializedName("A300")
    A300,
    @SerializedName("L39")
    L39,
    @SerializedName("B777")
    B777
}
