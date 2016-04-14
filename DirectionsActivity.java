package tb.tester.heregoes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class DirectionsActivity extends Activity implements OnClickListener {

	Button button3;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//reads xml file for layout information
		setContentView(R.layout.activity2);
		//will use this line to write out directions for how to use the app

		
		//this line set a click listener for the button on the screen
		//if a click is registered it activates onClick function
		button3 = (Button)findViewById(R.id.button3);
	}
	//sends user to photo activity
	public void onClick(View view) {
		if(view.getId() == R.id.button3){
		Intent intent2 =
				new Intent(this, Photo.class);
		startActivity(intent2);
		}
	}
}
