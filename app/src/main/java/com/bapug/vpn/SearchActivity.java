package com.bapug.vpn;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.*;
import com.blogspot.atifsoftwares.animatoolib.*;
import de.blinkt.openvpn.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class SearchActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ListView listview1;
	private LinearLayout linear1;
	private ImageView imageview7;
	private SearchView searchView1;
	private ImageView imageView;
	private TextView textview2;
	private ImageView imageview2;
	
	private SpeechRecognizer taste;
	private SharedPreferences s;
	private Intent i = new Intent();
	private TimerTask tt;
	private SharedPreferences c;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.search);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		listview1 = findViewById(R.id.listview1);
		linear1 = findViewById(R.id.linear1);
		imageview7 = findViewById(R.id.imageview7);
		searchView1 = findViewById(R.id.searchView1);
		imageView = findViewById(R.id.imageView);
		textview2 = findViewById(R.id.textview2);
		imageview2 = findViewById(R.id.imageview2);
		taste = SpeechRecognizer.createSpeechRecognizer(this);
		s = getSharedPreferences("s", Activity.MODE_PRIVATE);
		c = getSharedPreferences("c", Activity.MODE_PRIVATE);
		
		searchView1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String _charSeq) {
				if (!searchView1.getQuery().toString().equals("")) {
					s.edit().putString("search", searchView1.getQuery().toString()).commit();
					i.setClass(getApplicationContext(), PageActivity.class);
					startActivity(i);
				}
				return true;
			}
			
			@Override
			public boolean onQueryTextChange(String _charSeq) {
				
				return true;
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!searchView1.getQuery().toString().equals("")) {
					s.edit().putString("search", searchView1.getQuery().toString()).commit();
					i.setClass(getApplicationContext(), PageActivity.class);
					startActivity(i);
				}
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent _intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				_intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
				_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
				taste.startListening(_intent);
			}
		});
		
		taste.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle _param1) {
			}
			
			@Override
			public void onBeginningOfSpeech() {
			}
			
			@Override
			public void onRmsChanged(float _param1) {
			}
			
			@Override
			public void onBufferReceived(byte[] _param1) {
			}
			
			@Override
			public void onEndOfSpeech() {
			}
			
			@Override
			public void onPartialResults(Bundle _param1) {
			}
			
			@Override
			public void onEvent(int _param1, Bundle _param2) {
			}
			
			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);
				SearchView searchView1 = findViewById(R.id.searchView1); // Replace with your SearchView ID
				
				// Assuming you have recognized speech and stored it in a variable called speechText
				String speechText = "Your speech recognition result";
				
				// Set the recognized speech text to the SearchView
				searchView1.setQuery(speechText, false); // The second argument (false) means don't submit the query
				
				if (!searchView1.getQuery().toString().equals("")) {
					s.edit().putString("search", searchView1.getQuery().toString()).commit();
					i.setClass(getApplicationContext(), PageActivity.class);
					startActivity(i);
				}
			}
			
			@Override
			public void onError(int _param1) {
				final String _errorMessage;
				switch (_param1) {
					case SpeechRecognizer.ERROR_AUDIO:
					_errorMessage = "audio error";
					break;
					
					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
					_errorMessage = "speech timeout";
					break;
					
					case SpeechRecognizer.ERROR_NO_MATCH:
					_errorMessage = "speech no match";
					break;
					
					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
					_errorMessage = "recognizer busy";
					break;
					
					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
					_errorMessage = "recognizer insufficient permissions";
					break;
					
					default:
					_errorMessage = "recognizer other error";
					break;
				}
				SketchwareUtil.showMessage(getApplicationContext(), "try again");
			}
		});
	}
	
	private void initializeLogic() {
		searchView1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)1, 0xFFE0E0E0, Color.TRANSPARENT));
		linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)1, 0xFFE0E0E0, Color.TRANSPARENT));
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)1, 0xFFE0E0E0, Color.TRANSPARENT));
		searchView1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFFE0E0E0));
		imageView.setVisibility(View.GONE);
		// Define a Handler and Runnable
		Handler handler = new Handler();
		Runnable stopListeningRunnable = new Runnable() {
			    @Override
			    public void run() {
				        taste.stopListening();
				    }
		};
		
		SearchView searchView1 = findViewById(R.id.searchView1); // Replace with your SearchView ID
		
		// Add a TextWatcher to monitor SearchView changes
		searchView1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			    @Override
			    public boolean onQueryTextSubmit(String query) {
				        // Handle query submission if needed
				        return true;
				    }
			
			    @Override
			    public boolean onQueryTextChange(String newText) {
				        if (newText.isEmpty()) {
					            // Start a timer to stop listening when SearchView is empty
					            handler.removeCallbacks(stopListeningRunnable); // Remove any previous callbacks
					            handler.postDelayed(stopListeningRunnable, 2000); // Adjust the delay as needed (in milliseconds)
					        } else {
					            // If SearchView has text, remove pending stopListening Runnable
					            handler.removeCallbacks(stopListeningRunnable);
					        }
				        return true;
				    }
		});
		
		textview2.setVisibility(View.VISIBLE);
		imageView.setVisibility(View.GONE);
		searchView1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (true) {
					imageView.setVisibility(View.GONE);
					imageview2.setVisibility(View.GONE);
					imageview7.setVisibility(View.GONE);
				}
				else {
					imageView.setVisibility(View.GONE);
					imageview2.setVisibility(View.VISIBLE);
					imageview7.setVisibility(View.VISIBLE);
				}
				ImageView imageView = findViewById(R.id.imageView); // Replace with your ImageView ID
				SearchView searchView1 = findViewById(R.id.searchView1); // Replace with your SearchView ID
				
				imageView.setOnClickListener(new View.OnClickListener() {
					    @Override
					    public void onClick(View _view) {
						        // Clear the text in the SearchView
						        searchView1.setQuery("", false); // The second argument (false) means don't submit the query
						
						        // Collapse the SearchView if it's expanded
						        searchView1.setIconified(false);
						    }
				});
				
			}
		});
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}