package hai.com.myapp.autocomplete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import hai.com.myapp.R;

/**
 * Created by Administrator on 2017/6/10.
 */

public class AutoCompleteActivity extends AppCompatActivity {
    private String[] names = {"laowang", "laoli", "laozhang", "xiaowang", "xiaoli", "xiaozhang"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.act_autocomplete_text);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_autocomplete, names);
        autoCompleteTextView.setAdapter(adapter);
    }
}
