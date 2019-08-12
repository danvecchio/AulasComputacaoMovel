package com.example.jsonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    //Constants
    private static final String APIKEY = "fd45d594";
    private String TAG = MainActivity.class.getSimpleName();

    //Views
    private TextView txtViewTitle = null;
    private TextView txtViewYear = null;
    private TextView txtViewRuntime = null;
    private Button btnLoadMovie = null;
    private ImageView imgViewPoster = null;
    private EditText edtTextInput = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();


    }

    private void findViews() {
        edtTextInput = findViewById(R.id.edtTextInput);
        imgViewPoster = findViewById(R.id.imgViewPoster);
        txtViewTitle = findViewById(R.id.txtViewTitle);
        txtViewYear = findViewById(R.id.txtViewYear);
        txtViewRuntime = findViewById(R.id.txtViewRuntime);
        btnLoadMovie = findViewById(R.id.btnLoadMovie);

        btnLoadMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieSearch = edtTextInput.getText().toString();
                Log.e(TAG, "search: " + movieSearch);
                new GetMovies().execute(movieSearch);
            }
        });


    }


    private class GetMovies extends AsyncTask<String, Void, Movie> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Movie doInBackground(String... search) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://www.omdbapi.com/?i=" + search[0] + "&apikey=" + APIKEY;
            Log.e(TAG, "URL: " + url);
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, jsonStr);
            Movie movie = new Movie();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    movie.setTitle(jsonObj.getString("Title"));
                    movie.setYear(jsonObj.getInt("Year"));
                    movie.setRuntime(jsonObj.getString("Runtime"));
                    movie.setPoster(jsonObj.getString("Poster"));
                    Log.e(TAG, movie.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return movie;
        }

        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);
            if (movie != null) {
                txtViewTitle.setText(movie.getTitle());
                txtViewYear.setText(Integer.toString(movie.getYear()));
                txtViewRuntime.setText(movie.getRuntime());
                new DownloadImageTask(imgViewPoster).execute(movie.getPoster());
            }

        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}