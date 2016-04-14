package tb.tester.heregoes;

import java.io.File;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


//Have a working camera code that displays the picture taken with the app. Need to transfer code over to photo activity and then analyze.
public class Photo extends Activity implements OnClickListener{

	
	private static String logtag = "TBTest";
	private static int TAKE_PICTURE = 1;
	private Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_layout);
		
		Button cameraButton = (Button)findViewById(R.id.button_camera);
		cameraButton.setOnClickListener(cameraListener);
		
	}

	private OnClickListener cameraListener = new OnClickListener () {
		public void onClick(View v){
			takePhoto(v);
		}
	};
	
	private void takePhoto(View v) {
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "picture.jpg");
		imageUri = Uri.fromFile(photo);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, TAKE_PICTURE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		super.onActivityResult(requestCode, resultCode, intent);
		
		if(resultCode == Activity.RESULT_OK){
			Uri selectedImage = imageUri;
			getContentResolver().notifyChange(selectedImage, null);
			
			ImageView imageview = (ImageView)findViewById(R.id.image_camera);
			ContentResolver cr = getContentResolver();
			Bitmap bitmap;
			TextView results = (TextView)findViewById(R.id.result);
			
			try {
				bitmap = MediaStore.Images.Media.getBitmap(cr, selectedImage);
				imageview.setImageBitmap(bitmap);
				Toast.makeText(Photo.this, selectedImage.toString(), Toast.LENGTH_LONG).show();
				int pixel;
				int maxScore = 0;
				int tempScore = 0;
				
				//loop through every pixel in image 
				//also need to localize nested loop such that it only loops through values around bar
                //this means I need to standardize the picture taking procedure more.
				
				//grab pixel locations from illustrator, zoom in for loop using the found values.
				//Be able to justify why the picture itself doesn't change in size as well.
				
				for(int x=(bitmap.getHeight() / 2)-100; x<(bitmap.getHeight() / 2)+100; x++){
					for (int y=(bitmap.getWidth()*(2/3)); y<bitmap.getWidth(); y++){
						
						//stores color value grabbed from indexed pixel
						pixel = bitmap.getPixel(x, y);
						//divide color value into individual colors
						//alpha pertains to the transparency of the color
						int alpha = Color.alpha(pixel);
		                int red = Color.red(pixel);
		                int blue = Color.blue(pixel);
		                int green = Color.green(pixel);
		                
		                //Scoring system follows. If indexed pixel falls within a certain range
		                //it will register as a specific score.
		                //Will eventually fine tune the scoring system to include blue as well as red.
		                //This is because result bar from LFA is a purple-ish color.
		                
		                //scoring needs to be amended such that it registers as either 0 or 1
		                //waiting on reference strip from Sharon in order to grab pixel values
		                
						if (red < 115 && red > 95){
							if (green < 75 && green > 55){
								if (blue < 35 && blue > 25){
									tempScore = 5;
									if(tempScore > maxScore){
					                	maxScore = tempScore;
					                	results.setText("Result: 5");
					                }
								}
							}
						} else if (red < 125 && red >= 115){
							if (green < 100 && green >= 75){
								if (blue < 43 && blue >= 35){
									tempScore = 4;
									if(tempScore > maxScore){
					                	maxScore = tempScore;
					                	results.setText("Result: 4");
					                }
								}
							}
						} else if (red < 141 && red >= 125){
							if (green < 118 && green >= 100){
								if (blue < 56 && blue >= 43){
									tempScore = 3;
									if(tempScore > maxScore){
					                	maxScore = tempScore;
					                	results.setText("Result: 3");
					                }
								}
							}
						} else if (red < 152 && red >= 141){
							if (green < 131 && green >= 118){
								if (blue < 70 && blue >= 56){
									tempScore = 2;
									if(tempScore > maxScore){
					                	maxScore = tempScore;
					                	results.setText("Result: 2");
					                }
								}
							}
						} else if (red < 175 && red >= 152){
							if (green < 155 && green >= 131){
								if (blue < 80 && blue >= 70){
									tempScore = 1;
									if(tempScore > maxScore){
					                	maxScore = tempScore;
					                	results.setText("Result: 1");
					                }
								}
							}
						} else {
							tempScore = 0;
							if (tempScore == maxScore){
								results.setText("Result: 0");
							}
							
						}
					}
				}
			
			//this switch statement sets the results value as the maximum score that was
			//triggered during the loop.
				
//			switch (maxScore) {
//			case 1: results.setText("Result: 1");
//					break;
//			case 2: results.setText("Result: 2");
//					break;
//			case 3: results.setText("Result: 3");
//					break;
//			case 4: results.setText("Result: 4");
//					break;
//			case 5: results.setText("Result: 5");
//					break;
//			default: results.setText("Result: 0");
//					break;
//			}
				
				
			} catch(Exception e) {
				Log.e(logtag, e.toString());
			}
		}
		
	}
	//code for analyzing photo should go here or in another activity
	//if in another activity I need to send the file name to that activity so it can load the picture


	public void onClick(View view) {
		Intent intent =
				new Intent(Photo.this, MyActivity.class);
		startActivity(intent);
	
	}
}
