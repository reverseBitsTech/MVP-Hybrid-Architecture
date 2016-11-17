package com.reversebits.projects.app.hybridmvp.common;

/**
 * Created by TapanHP on 11/10/2016.
 */

public interface IBaseView {

    void savePref(String key, String value);

    String loadPref(String key);

    void doToast(String message);

    boolean validateMail(String email);

   // void savePrefSet(Activity cntx, String key, Set value);

    //void savePrefSet(String key, Set value);

    //Set loadPrefSet(Activity cntx, String key);



//    String loadPref(Context cntx, String key);
}
