package tp.booksv2;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class RecordListActivity extends AppCompatActivity {


    ListView mlistView;
    ArrayList<Model> mList;
    RecordListAdapter mAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Record List");




        mlistView = findViewById(R.id.listView);

        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter(this, R.layout.row, mList);
        mlistView.setAdapter(mAdapter);



        //get all data from sqlite
        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT * FROM LIBROS");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String TITULO = cursor.getString(1);
            String AUTOR = cursor.getString(2);
            String SINOPSIS = cursor.getString(3);
            String FECHA_DE_PUBLICACION = cursor.getString(4);
            String EDITORIAL = cursor.getString(5);
            String CANTIDAD_DE_PAGINAS = cursor.getString(6);
            String IDIOMA = cursor.getString(7);
            String CATEGORIA = cursor.getString(8);

            //add to list
            mList.add(new Model(id, TITULO, AUTOR, SINOPSIS, FECHA_DE_PUBLICACION, EDITORIAL , CANTIDAD_DE_PAGINAS, IDIOMA, CATEGORIA));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size()==0){
            //if there is no record in table of database which means listview is empty
            Toast.makeText(this, "No record found...", Toast.LENGTH_SHORT).show();
        }

        mlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //alert dialog to display options of update and delete
                final CharSequence[] items = {"Update", "Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(RecordListActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            //update
                            Cursor c = MainActivity.mSQLiteHelper.getData("SELECT id FROM LIBROS");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            //show update dialog
                            showDialogUpdate(RecordListActivity.this, arrID.get(position));
                        }
                        if (i==1){
                            //delete
                            Cursor c = MainActivity.mSQLiteHelper.getData("SELECT id FROM LIBROS");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });


    }

    private void showDialogDelete(final int idRecord) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(RecordListActivity.this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure to delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    MainActivity.mSQLiteHelper.deleteData(idRecord);
                    Toast.makeText(RecordListActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateRecordList();
            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void showDialogUpdate(Activity activity, final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Update");

        final EditText EdtTitulo = dialog.findViewById(R.id.EdtTitulo);
        final EditText EdtAutor = dialog.findViewById(R.id.EdtAutor);
        final EditText EdtSinopsis = dialog.findViewById(R.id.EdtSinopsis);
        final EditText EdtFecha_de_Publicacion = dialog.findViewById(R.id.EdtFecha_de_Publicacion);
        final EditText EdtEditorial = dialog.findViewById(R.id.EdtEditorial);
        final EditText EdtCant_de_pag = dialog.findViewById(R.id.EdtCant_de_pag);
        final EditText EdtIdioma = dialog.findViewById(R.id.EdtIdioma);
        final EditText EdtCategoria = dialog.findViewById(R.id.EdtCategoria);



        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

        //set width of dialog
        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        //set hieght of dialog
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MainActivity.mSQLiteHelper.updateData(
                            EdtTitulo.getText().toString().trim(),
                            EdtAutor.getText().toString().trim(),
                            EdtSinopsis.getText().toString().trim(),
                            EdtFecha_de_Publicacion.getText().toString().trim(),
                            EdtEditorial.getText().toString().trim(),
                            EdtCant_de_pag.getText().toString().trim(),
                            EdtIdioma.getText().toString().trim(),
                            EdtCategoria.getText().toString().trim(),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update Successfull", Toast.LENGTH_SHORT).show();
                }
                catch (Exception error){
                    Log.e("Update error", error.getMessage());
                }
                updateRecordList();
            }
        });

    }

    private void updateRecordList() {
        //get all data from sqlite
        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT * FROM LIBROS");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String TITULO = cursor.getString(1);
            String AUTOR = cursor.getString(2);
            String SINOPSIS = cursor.getString(3);
            String FECHA_DE_PUBLICACION = cursor.getString(4);
            String EDITORIAL = cursor.getString(5);
            String CANTIDAD_DE_PAGINAS = cursor.getString(6);
            String IDIOMA = cursor.getString(7);
            String CATEGORIA = cursor.getString(8);

            mList.add(new Model(id,TITULO, AUTOR, SINOPSIS, FECHA_DE_PUBLICACION, EDITORIAL , CANTIDAD_DE_PAGINAS, IDIOMA, CATEGORIA));
        }
        mAdapter.notifyDataSetChanged();
    }













































}


