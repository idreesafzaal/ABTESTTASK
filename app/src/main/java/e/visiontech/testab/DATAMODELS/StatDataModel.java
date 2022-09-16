package e.visiontech.testab.DATAMODELS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatDataModel
{
    @SerializedName("TotalDevice")
    @Expose
    String TotalDevice;

    @SerializedName("ExOneLike")
    @Expose
    String ExOneLike;

    @SerializedName("ExTwoLike")
    @Expose
    String ExTwoLike;

    @SerializedName("ExThreeLike")
    @Expose
    String ExThreeLike;

    public String getTotalDevice() {
        return TotalDevice;
    }

    public void setTotalDevice(String totalDevice) {
        TotalDevice = totalDevice;
    }

    public String getExOneLike() {
        return ExOneLike;
    }

    public void setExOneLike(String exOneLike) {
        ExOneLike = exOneLike;
    }

    public String getExTwoLike() {
        return ExTwoLike;
    }

    public void setExTwoLike(String exTwoLike) {
        ExTwoLike = exTwoLike;
    }

    public String getExThreeLike() {
        return ExThreeLike;
    }

    public void setExThreeLike(String exThreeLike) {
        ExThreeLike = exThreeLike;
    }
}
