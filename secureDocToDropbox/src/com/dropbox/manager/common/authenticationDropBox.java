package com.dropbox.manager.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.manager.SecureDocToDropboxActivity;

public class authenticationDropBox extends Activity{
	Context mContext;
	final static private String APP_KEY = "msyot073g9636kg";
	final static private String APP_SECRET = "paf6i1o6aykhumd";
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
	public final static String ACCOUNT_PREFS_NAME = "ACCOUNT_PREFS_NAME";
	
	static public String ACCESS_KEY_NAME = "ACCESS_KEY_NAME";
	static public String ACCESS_SECRETKEY_NAME = "ACCESS_SECRETKEY_NAME";
	
	
	public DropboxAPI<AndroidAuthSession> mDBApi;
	
	public authenticationDropBox(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	public void Init(){
		AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session = new AndroidAuthSession(appKeys, ACCESS_TYPE);
		mDBApi = new DropboxAPI<AndroidAuthSession>(session);
	}
	
	public void Init2(){
//		clearKeys();
		AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session = new AndroidAuthSession(appKeys, ACCESS_TYPE);
		
		mDBApi = new DropboxAPI<AndroidAuthSession>(session);
		mDBApi.getSession().startAuthentication(mContext);
	}
	 
	public void Finish(){
	    if (mDBApi.getSession().authenticationSuccessful()) {
	        try {
	            // MANDATORY call to complete auth.
	            // Sets the access token on the session
	            mDBApi.getSession().finishAuthentication();

	            AccessTokenPair tokens = mDBApi.getSession().getAccessTokenPair();

	            // Provide your own storeKeys to persist the access token pair
	            // A typical way to store tokens is using SharedPreferences
	            storeKeys(tokens.key, tokens.secret);
	        } catch (IllegalStateException e) {
	            Log.i("DbAuthLog", "Error authenticating", e);
	        }
	    }
	}

	private void storeKeys(String key, String secret) {
        // Save the access key for later
        Editor edit = SecureDocToDropboxActivity.prefs.edit();
        edit.putString(ACCESS_KEY_NAME, key);
        edit.putString(ACCESS_SECRETKEY_NAME, secret);
        edit.commit();
	}
	
    private void clearKeys() {
        Editor edit = SecureDocToDropboxActivity.prefs.edit();
        edit.clear();
        edit.commit();
    }
}
