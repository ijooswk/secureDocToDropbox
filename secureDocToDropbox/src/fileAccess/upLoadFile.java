package fileAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.os.DropBoxManager.Entry;
import android.util.Log;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.exception.DropboxUnlinkedException;
import com.dropbox.manager.SecureDocToDropboxActivity;

public class upLoadFile {
	Context mContext;
	public upLoadFile(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
//		upLoad();
		new ProcessTask().execute(null, null, null);
	}
	public void upLoad(){
		FileInputStream inputStream = null;
		try {
		    File file = new File("/sdcard/mpop_devinfo.txt");
		    inputStream = new FileInputStream(file);
		    DropboxAPI.Entry newEntry = SecureDocToDropboxActivity.auth.mDBApi.putFile("testing.txt", inputStream,
		            file.length(), null, null);
		    Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
		} catch (DropboxUnlinkedException e) {
		    // User has unlinked, ask them to link again here.
		    Log.e("DbExampleLog", "User has unlinked.");
		} catch (DropboxException e) {
		    Log.e("DbExampleLog", "Something went wrong while uploading.");
		} catch (FileNotFoundException e) {
		    Log.e("DbExampleLog", "File not found.");
		} finally {
		    if (inputStream != null) {
		        try {
		            inputStream.close();
		        } catch (IOException e) {}
		    }
		}
	}
	
	private class ProcessTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			upLoad();
			return null;
		}
		
	}
}
