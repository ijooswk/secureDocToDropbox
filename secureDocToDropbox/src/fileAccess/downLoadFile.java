package fileAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.util.Log;

import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.manager.SecureDocToDropboxActivity;

public class downLoadFile {
	
	public downLoadFile() {
		// TODO Auto-generated constructor stub
	}
	public void downLoad(){
		// Get file.
		FileOutputStream outputStream = null;
		try {
		    File file = new File("/path/to/new/file.txt");
		    outputStream = new FileOutputStream(file);
		    DropboxFileInfo info = SecureDocToDropboxActivity.auth.mDBApi.getFile("/testing.txt", null, outputStream, null);
		    Log.i("DbExampleLog", "The file's rev is: " + info.getMetadata().rev);
		    // /path/to/new/file.txt now has stuff in it.
		} catch (DropboxException e) {
		    Log.e("DbExampleLog", "Something went wrong while downloading.");
		} catch (FileNotFoundException e) {
		    Log.e("DbExampleLog", "File not found.");
		} finally {
		    if (outputStream != null) {
		        try {
		            outputStream.close();
		        } catch (IOException e) {}
		    }
		}
	}
}
