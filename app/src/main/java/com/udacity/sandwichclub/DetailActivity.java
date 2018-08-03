package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView tv_description_text,tv_place_of_origin,tv_also_know_as,tv_ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d("Executing this", "Executing this");
        ImageView ingredientsIv = findViewById(R.id.image_iv);
        tv_description_text = findViewById(R.id.tv_descripiton_text);
        tv_place_of_origin = findViewById(R.id.tv_place_of_origin_text);
        tv_also_know_as = findViewById(R.id.tv_also_known_as_text);
        tv_ingredients = findViewById(R.id.tv_ingredients_text);


        Intent intent = getIntent();
        if (intent == null) {
            Log.d("null intent", "null intent");
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION,DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            Log.d("bad position", "null intent");
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable

            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        String alsoKnownAsText= "";
        String ingredientsText = "";
        tv_description_text.setText(sandwich.getDescription());
        tv_place_of_origin.setText(sandwich.getPlaceOfOrigin());

        for (int dataCount = 0; dataCount < sandwich.getAlsoKnownAs().size();
             dataCount++) {
            alsoKnownAsText = alsoKnownAsText
                    .concat(sandwich
                            .getAlsoKnownAs()
                            .get(dataCount) + "\n");

        }
        tv_also_know_as.setText(alsoKnownAsText);

        for (int dataCount = 0; dataCount < sandwich.getIngredients().size();
             dataCount++) {
            ingredientsText = alsoKnownAsText
                    .concat(sandwich
                            .getIngredients()
                            .get(dataCount) + "\n");
        }

        tv_ingredients.setText(ingredientsText);
    }
}
