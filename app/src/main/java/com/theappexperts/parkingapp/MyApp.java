package com.theappexperts.parkingapp;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Base64;

import com.theappexperts.parkingapp.injection.components.ApplicationComponent;
import com.theappexperts.parkingapp.injection.components.DaggerApplicationComponent;
import com.theappexperts.parkingapp.injection.modules.ApplicationModule;

import java.security.SecureRandom;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by TheAppExperts on 03/12/2017.
 */

public class MyApp extends Application {
    ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {return applicationComponent; }

    public void setApplicationComponent(ApplicationComponent applicationComponent){
        this.applicationComponent = applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        setupRealm();
    }

    void setupRealm()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("storedKey", MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        byte[] usedKey = new byte[64];

        if(pref.getString("key_name", null) == null)
        {
            byte[] key = new byte[64];
            new SecureRandom().nextBytes(key);
            editor.putString("key_name", Base64.encodeToString(key, Base64.DEFAULT)); // Storing string
            editor.commit();
            usedKey = key;
            //Toast.makeText(getApplicationContext(), "new key", Toast.LENGTH_LONG).show();
        }
        else
        {
            byte[] key = Base64.decode(pref.getString("key_name", null), Base64.DEFAULT);
            usedKey = key;
            //Toast.makeText(getApplicationContext(), " old key len = " + key.length, Toast.LENGTH_LONG).show();
        }

        Realm.init(getApplicationContext());

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .encryptionKey(usedKey)
                .build();


        Realm.setDefaultConfiguration(configuration);
    }

}
