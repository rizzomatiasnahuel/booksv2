package tp.booksv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mEdtName, mEdtAge, mEdtPhone;
    Button mBtnAdd, mBtnList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mEdtName = findViewById(R.id.edtName);
        mEdtAge = findViewById(R.id.edtAge);
        mEdtPhone = findViewById(R.id.edtPhone);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnList = findViewById(R.id.btnList);

        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start recordlist activity
                startActivity(new Intent(MainActivity.this, RecordListActivity.class));
            }
        });



    }
}
