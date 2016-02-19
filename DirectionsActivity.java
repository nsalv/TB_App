package tb.tester.heregoes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class DirectionsActivity extends Activity implements OnClickListener {
	TextView textview;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//reads xml file for layout information
		setContentView(R.layout.activity2);
		//will use this line to write out directions for how to use the app
		StringBuilder str = new StringBuilder("This is where the directions go.");
		textview.setText(str);
		
		//this line set a click listener for the button on the screen
		//if a click is registered it activates onClick function
		((Button) findViewById(R.id.button1)).setOnClickListener(this);
	}
	//sends user to photo activity
	public void onClick(View view) {
		Intent intent =
				new Intent(this, Photo.class);
		startActivity(intent);
	}
}
