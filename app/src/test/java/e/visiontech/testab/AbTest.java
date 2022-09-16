package e.visiontech.testab;

import android.app.Instrumentation;
import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import e.visiontech.testab.STATS.Utilies;
import e.visiontech.testab.VIEWS.MainActivity;

import static org.junit.Assert.*;
public class AbTest
{


   @Test
   public void CheckDeviceIdIsnotNull()
   {
       Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
      assertNotNull(new Utilies(appContext).getDeviceId());
   }
}
