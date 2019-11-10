package tp.booksv2;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

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


        mlistView = findViewById(R.id.listView);

        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter(this, R.layout.row, mList);
        mlistView.setAdapter(mAdapter);



        //get all data from sqlite
        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT * FROM RECORD");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String phone = cursor.getString(3);
            //add to list
            mList.add(new Model(id, name, age, phone));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size()==0){
            //if there is no record in table of database which means listview is empty
            Toast.makeText(this, "No record found...", Toast.LENGTH_SHORT).show();
        }







    }


}