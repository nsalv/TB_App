package tb.tester.heregoes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MyActivity extends Activity implements OnClickListener {
	//generates two buttons; one for the photo activity and one for the directions
	Button button1, button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//sets the layout of the activity
		setContentView(R.layout.activity_my);
		
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
	}
	//function that if 'get started' button is clicked, go to photo activity
	public void onClick(View view){
		
		if(view.getId() == R.id.button1){
		Intent intent =
				new Intent(MyActivity.this, Photo.class);
		startActivity(intent);
		}
		else if(view.getId() == R.id.button2){
			Intent intent1 = 
					new Intent(MyActivity.this, DirectionsActivity.class);
			startActivity(intent1);
		}
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
