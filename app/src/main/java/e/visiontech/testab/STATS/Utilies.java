package e.visiontech.testab.STATS;

import android.content.Context;
import android.provider.Settings;

public class Utilies
{
    Context context;
    public Utilies(Context context)
    {
        this.context=context;
    }
    // get device id for unique identifier
    public String getDeviceId()
    {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
