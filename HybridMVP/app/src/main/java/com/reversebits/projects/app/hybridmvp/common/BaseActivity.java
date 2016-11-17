package com.reversebits.projects.app.hybridmvp.common;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by TapanHP on 11/10/2016.
 */

public class BaseActivity extends AppCompatActivity implements IBaseView {

    Activity cntx;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        cntx = this;
    }

    @Override
    public boolean validateMail(String email) {
        if (!TextUtils.isEmpty(email)) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return false;
        }
    }

    @Override
    public void doToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private static SharedPreferences sharedPreferences;

    @Override
    public void savePref(String key, String value) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cntx);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

    }

    @Override
    public String loadPref(String key) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cntx);
        return sharedPreferences.getString(key, null);
    }

}
