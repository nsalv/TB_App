package tb.tester.heregoes;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Photo extends Activity implements OnClickListener{
	
private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
public static final int MEDIA_TYPE_IMAGE = 1;

private Uri fileUri;

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.photo_layout);
	
	((Button) findViewById(R.id.button1)).setOnClickListener(this);
	//create Intent to take a picture and return control to the calling application
	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	//private static Uri getOutputMediaFileUri(int type) {
		//return Uri.fromFile(getOutputMediaFile(type));
	//}
	//fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); //create a file to save the image
	intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); //set the image file name
	
	//start the image capture Intent
	startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if (resultCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
		if (resultCode == RESULT_OK) {
			//Image captured and saved to the fileUri specified in the Intent
			Toast.makeText(this,  "Image saved to :\n" + data.getData(), Toast.LENGTH_LONG).show();
		}
		else if (resultCode == RESULT_CANCELED) {
			//User Canceled the image capture
		}
		else {
			//Image capture failed, advise user
		}
	}
}

	public void onClick(View view) {
		Intent intent =
				new Intent(this, MyActivity.class);
		startActivity(intent);
	
	}
}
