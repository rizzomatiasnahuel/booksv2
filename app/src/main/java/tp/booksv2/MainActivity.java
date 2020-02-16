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

    EditText mEdtTitulo , mEdtAutor , mEdtSinopsis ,mEdtFecha_de_Publicacion  , mEdtEditorial ,mEdtCant_de_pag , mEdtIdioma, mEdtCategoria;
    Button mBtnAdd, mBtnList;

    public static SQLiteHelper mSQLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Record");



        mEdtTitulo = findViewById(R.id.EdtTitulo);
        mEdtAutor = findViewById(R.id.EdtAutor);
        mEdtSinopsis = findViewById(R.id.EdtSinopsis);
        mEdtFecha_de_Publicacion = findViewById(R.id.EdtFecha_de_Publicacion);
        mEdtEditorial = findViewById(R.id.EdtEditorial);
        mEdtCant_de_pag = findViewById(R.id.EdtCant_de_pag);
        mEdtIdioma = findViewById(R.id.EdtIdioma);
        mEdtCategoria = findViewById(R.id.EdtCategoria);











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
                            mEdtTitulo.getText().toString().trim(),
                            mEdtAutor.getText().toString().trim(),
                            mEdtSinopsis.getText().toString().trim(),
                            mEdtFecha_de_Publicacion.getText().toString().trim(),
                            mEdtEditorial.getText().toString().trim(),
                            mEdtCant_de_pag.getText().toString().trim(),
                            mEdtIdioma.getText().toString().trim(),
                            mEdtCategoria.getText().toString().trim()


                    );
                    Toast.makeText(MainActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    //reset views
                    mEdtTitulo.setText("");
                    mEdtAutor.setText("");
                    mEdtSinopsis.setText("");
                    mEdtFecha_de_Publicacion.setText("");
                    mEdtEditorial.setText("");
                    mEdtCant_de_pag.setText("");
                    mEdtIdioma.setText("");
                    mEdtCategoria.setText("");

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });




    }
}
