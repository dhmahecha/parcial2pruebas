package me.kuehle.carreport;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class CarReportTest {
    private static final String PACKAGE_NAME = "me.kuehle.carreport";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() throws UiObjectNotFoundException, InterruptedException{
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(PACKAGE_NAME);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)),
                LAUNCH_TIMEOUT);

        //Crear carro
        if(mDevice.findObject(By.res(PACKAGE_NAME, "btn_create_car")) != null) {
            mDevice.findObject(By.res(PACKAGE_NAME, "btn_create_car")).click();
            Thread.sleep(1500);//wait 1.5 seconds for screen to load
            mDevice.findObject(By.res(PACKAGE_NAME, "edt_name")).setText("Volkswagen Escarabajo");
            mDevice.findObject(By.res(PACKAGE_NAME, "edt_initial_mileage")).setText("15000");
            Thread.sleep(1500);//wait 1.5 seconds for screen to load
            mDevice.findObject(By.res(PACKAGE_NAME, "menu_save")).click();
            Thread.sleep(1500);//wait 1.5 seconds for screen to load
        }
    }

    @Test
    public void checkPreconditions() {
        assertThat(mDevice, notNullValue());


    }

    /**
     * Test for CarReport
     * @author - diegohernando@gmail.com
     * Generated using Barista - http://moquality.com/barista
     */
    @Test
    public void testRefuelingTest() throws UiObjectNotFoundException, InterruptedException{

        //Botón para agregar una nueva entrada (+)
        mDevice.click(951, 1659);
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        //mDevice.findObject(By.)
        mDevice.findObject(By.text("Refueling")).click();
        Thread.sleep(2000);//wait 1.5 seconds for screen to load
        if(mDevice.findObject(By.text("Volkswagen Escarabajo")) != null) {
            mDevice.findObject(By.text("Volkswagen Escarabajo")).click();
            Thread.sleep(1500);//wait 1.5 seconds for screen to load
        }
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_mileage")).setText("5000");
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_volume")).setText("6");
        mDevice.findObject(By.res(PACKAGE_NAME, "chk_partial")).click();
        //mDevice.findObject(By.res(PACKAGE_NAME, "edt_price")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_price")).setText("50");

        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        if(mDevice.findObject(By.res(PACKAGE_NAME, "edt_note")) != null){
            mDevice.findObject(By.res(PACKAGE_NAME, "edt_note")).setText("Carga de combustible semanal");
        }
        //mDevice.findObject(By.res(PACKAGE_NAME, "edt_note")).click();
        //
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.res(PACKAGE_NAME, "menu_save")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
    }

    @Test
    public void testOtherExpenditureTest() throws UiObjectNotFoundException, InterruptedException {
        //Botón para agregar una nueva entrada (+)
        Log.i("log", "Inicio del test Expenditure");
        mDevice.click(951, 1659);
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        UiObject2 botonOtherExpenditure = mDevice.findObject(By.text("Other expenditure"));
        botonOtherExpenditure.clickAndWait(Until.newWindow(),LAUNCH_TIMEOUT);
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        if(mDevice.findObject(By.text("Volkswagen Escarabajo")) != null) {
            mDevice.findObject(By.text("Volkswagen Escarabajo")).click();
            Thread.sleep(1500);//wait 1.5 seconds for screen to load
        }

        mDevice.findObject(By.res(PACKAGE_NAME, "edt_title")).setText("Cambio de Aceite");
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_mileage")).setText("15000");
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_price")).setText("200");
        //mDevice.findObject(By.res(PACKAGE_NAME, "edt_note")).clickAndWait(Until.newWindow(),LAUNCH_TIMEOUT);
        //mDevice.findObject(By.res(PACKAGE_NAME, "edt_note")).setText("Cambio de aceite semestral");
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.res(PACKAGE_NAME, "menu_save")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
    }

    @Test
    public void testOtherIncomeTest() throws UiObjectNotFoundException, InterruptedException {
        //Botón para agregar una nueva entrada (+)
        Log.i("log", "Inicio del test Income");
        mDevice.click(951, 1659);
        Thread.sleep(2000);//wait 1.5 seconds for screen to load
        UiObject2 botonIncome = mDevice.findObject(By.text("Other income"));
        botonIncome.clickAndWait(Until.newWindow(),LAUNCH_TIMEOUT);
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        if(mDevice.findObject(By.text("Volkswagen Escarabajo")) != null) {
            mDevice.findObject(By.text("Volkswagen Escarabajo")).click();
            Thread.sleep(1500);//wait 1.5 seconds for screen to load
        }
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_title")).setText("Ingresos mensuales");
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_mileage")).setText("15000");
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_price")).setText("5000");
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        if(mDevice.findObject(By.res(PACKAGE_NAME, "edt_note")) != null) {
            mDevice.findObject(By.res(PACKAGE_NAME, "edt_note")).setText("Ingresos mensuales");
        }
        mDevice.findObject(By.res(PACKAGE_NAME, "menu_save")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load

    }

    @Test
    public void testReportsTest() throws UiObjectNotFoundException, InterruptedException {
        mDevice.findObject(By.desc("Open navigation")).click();
    }

    @Test
    public void testGeneralSettingsTest() throws UiObjectNotFoundException, InterruptedException {
        mDevice.findObject(By.desc("Open navigation")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.text("Settings")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.text("General settings")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load


        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        appViews.scrollIntoView(new UiSelector().resourceId("edit"));
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        //mDevice.findObject(new UiSelector().text("edit")).clickAndWaitForNewWindow();
        //mDevice.findObject(new UiSelector().resourceId("edit")).clickAndWaitForNewWindow();
        //mDevice.findObject(By.res(PACKAGE_NAME, "edit")).click();
        //mDevice.findObject(By.res(PACKAGE_NAME, "edit")).setText("CO");
    }

    @Test
    public void testCarTest() throws UiObjectNotFoundException, InterruptedException {
        mDevice.findObject(By.desc("Open navigation")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.text("Settings")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.text("Cars")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load

        mDevice.findObject(By.res(PACKAGE_NAME, "menu_add_car")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_name")).setText("Chevrolet Sail");
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_initial_mileage")).setText("0");
        mDevice.findObject(By.res(PACKAGE_NAME, "menu_save")).click();
    }


    @Test
    public void testFuelTest() throws UiObjectNotFoundException, InterruptedException {
        mDevice.findObject(By.desc("Open navigation")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.text("Settings")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.text("Fuel Types")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.res(PACKAGE_NAME, "menu_add_fuel_type")).click();
        Thread.sleep(1500);//wait 1.5 seconds for screen to load
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_name")).setText("Diesel");
        mDevice.findObject(By.res(PACKAGE_NAME, "edt_category")).setText("Diesel");
        //mDevice.findObject(By.res(PACKAGE_NAME, "menu_save")).click();
    }

}