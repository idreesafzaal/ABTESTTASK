package e.visiontech.testab.DATAMODELS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpDataModel
{
    @SerializedName("ExpId")
    @Expose
    String ExpId;

    @SerializedName("ExpNum")
    @Expose
    String ExpNum;

    @SerializedName("ExpPercentage")
    @Expose
    String ExpPercentage;


    public String getExpId() {
        return ExpId;
    }

    public void setExpId(String expId) {
        ExpId = expId;
    }

    public String getExpNum() {
        return ExpNum;
    }

    public void setExpNum(String expNum) {
        ExpNum = expNum;
    }

    public String getExpPercentage() {
        return ExpPercentage;
    }

    public void setExpPercentage(String expPercentage) {
        ExpPercentage = expPercentage;
    }
}
