package fileAccess;

import java.util.ArrayList;

import android.content.Context;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.manager.SecureDocToDropboxActivity;

public class getDropboxList {
	Context mContext;
	public getDropboxList(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	
	private ArrayList<DropboxAPI.Entry> getFileList(){
		ArrayList<DropboxAPI.Entry> entry = new ArrayList<DropboxAPI.Entry>();
		DropboxAPI.Entry newEntry = null;
		try {
			newEntry = SecureDocToDropboxActivity.auth.mDBApi.metadata("/secureDocToDropbox/", 10000,"" ,true, null);
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entry = (ArrayList<DropboxAPI.Entry>) newEntry.contents;
		
		return entry;
	}
}
