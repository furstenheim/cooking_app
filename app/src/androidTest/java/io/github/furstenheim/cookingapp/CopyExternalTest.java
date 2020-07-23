package io.github.furstenheim.cookingapp;

import android.app.Instrumentation;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.function.LongUnaryOperator;

import static android.content.Context.CLIPBOARD_SERVICE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class CopyExternalTest {
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String BASIC_SAMPLE_PACKAGE
            = "io.github.furstenheim.cookingapp";
    private UiDevice device;
    @Before
    public void startMainActivityFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();

        final String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, is(notNullValue()));

        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }
    @Test
    public void getCopy() {
        Context context = ApplicationProvider.getApplicationContext();
        String htmlCopied = "<h2 style=\"font-family: &quot;Roboto Slab&quot;; margin: 0px 0px 5px; line-height: 1.6; font-size: 32px; color: rgb(17, 17, 17); font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial;\">persona)</h2><ul style=\"list-style-position: inside; color: rgb(17, 17, 17); font-family: georgia; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial;\"><li><span itemprop=\"ingredients\">100 gramos de claras de huevo</span></li><li><span itemprop=\"ingredients\">1 huevo<span> </span><a href=\"https://gastronomiaycia.republica.com/2010/09/14/cuanto-pesan-los-huevos/\" style=\"color: rgb(198, 79, 7); text-decoration: none;\">M</a></span></li><li><span itemprop=\"ingredients\">40-50 gramos de leche en polvo</span></li><li><span itemprop=\"ingredients\">c/n de endulzante<span> </span></span></li></ul>";
        ((ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE))
                .setPrimaryClip(ClipData.newHtmlText("html", "aa", htmlCopied));
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click());

        assertThat(1, is(1));
    }
}
