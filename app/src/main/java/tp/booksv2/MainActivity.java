package tp.booksv2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mEdtName, mEdtAge, mEdtPhone;
    Button mBtnAdd, mBtnList;

    public static SQLiteHelper mSQLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Record");



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

        //creating database
        mSQLiteHelper = new SQLiteHelper(this, "LIBROSBD.sqlite", null, 2);

        //creating table in database
        mSQLiteHelper.queryData( " CREATE TABLE IF NOT EXISTS  LIBROS ( id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "TITULO TEXT NOT NULL, " +
                "AUTOR TEXT, " +
                "SINOPSIS TEXT, " +
                "FECHA_DE_PUBLICACION TEXT, " +
                "EDITORIAL TEXT, " +
                "CANTIDAD_DE_PAGINAS TEXT, " +
                "IDIOMA TEXT, " +
                "CATEGORIA TEXT)");


        //add record to sqlite
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mSQLiteHelper.insertData(
                            mEdtName.getText().toString().trim(),
                            mEdtAge.getText().toString().trim(),
                            mEdtPhone.getText().toString().trim()

                    );
                    Toast.makeText(MainActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    //reset views
                    mEdtName.setText("");
                    mEdtAge.setText("");
                    mEdtPhone.setText("");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });




    }
}
