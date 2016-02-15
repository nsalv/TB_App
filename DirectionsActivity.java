package tb.tester.heregoes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class DirectionsActivity extends Activity implements OnClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);
		
		((Button) findViewById(R.id.button1)).setOnClickListener(this);
	}
	
	public void onClick(View view) {
		Intent intent =
				new Intent(this, Photo.class);
		startActivity(intent);
	}
}
