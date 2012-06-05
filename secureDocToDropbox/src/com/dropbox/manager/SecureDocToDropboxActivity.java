package com.dropbox.manager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.manager.common.authenticationDropBox;

import fileAccess.upLoadFile;

public class SecureDocToDropboxActivity extends Activity {
    public static authenticationDropBox auth;
    public upLoadFile upload;
    public String key_name;
    public String key_sec_name;
    public boolean isFirstTime = false;
    public static SharedPreferences prefs;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	// auth 성공의 경우 Key를 저장하여 더이상 부르지 않는다.
    	prefs = getSharedPreferences(authenticationDropBox.ACCOUNT_PREFS_NAME, 0);
    	key_name = prefs.getString(authenticationDropBox.ACCESS_KEY_NAME, "");
    	key_sec_name = prefs.getString(authenticationDropBox.ACCESS_SECRETKEY_NAME, "");
    	
    	if(key_name.equalsIgnoreCase("")||key_sec_name.equalsIgnoreCase(""))
    		isFirstTime = true;
    	else
    		isFirstTime = false;
    	auth = new authenticationDropBox(this);
    	
    	if(isFirstTime)
    		auth.Init2();
    	else{
    		auth.Init();
    		AccessTokenPair tokens = new AccessTokenPair(key_name,key_sec_name);
    		auth.mDBApi.getSession().setAccessTokenPair(tokens);
    	}
    	
        upload = new upLoadFile(this);
        
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	if(isFirstTime)
    		auth.Finish();
    }
    
}