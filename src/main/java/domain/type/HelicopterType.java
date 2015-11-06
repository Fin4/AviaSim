package domain.type;

import com.google.gson.annotations.SerializedName;

public enum HelicopterType implements AircraftType {

    @SerializedName("MI_17")
    MI_17,
    @SerializedName("MI_171")
    MI_171,
    @SerializedName("MI_2")
    MI_2
}
