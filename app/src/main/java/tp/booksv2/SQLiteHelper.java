package tp.booksv2;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper{

    //constructor
    SQLiteHelper(Context context,
                 String name,
                 SQLiteDatabase.CursorFactory factory,
                 int version){
        super(context, name, factory, version);



    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //insertData
    public void insertData(String TITULO,
                           String AUTOR,
                           String SINOPSIS,
                           String FECHA_DE_PUBLICACION,
                           String EDITORIAL,
                           String CANTIDAD_DE_PAGINAS,
                           String IDIOMA,
                           String CATEGORIA){
        SQLiteDatabase database = getWritableDatabase();
        //query to insert record in database table
        String sql = "INSERT INTO LIBROS VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?)"; //where "RECORD" is table name in database we will create in mainActivity

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, TITULO);
        statement.bindString(2, AUTOR);
        statement.bindString(3, SINOPSIS);
        statement.bindString(4, FECHA_DE_PUBLICACION);
        statement.bindString(5, EDITORIAL);
        statement.bindString(6, CANTIDAD_DE_PAGINAS);
        statement.bindString(7, IDIOMA);
        statement.bindString(8, CATEGORIA);
        statement.executeInsert();
    }

    //updateData
    public void updateData(String TITULO,
                           String AUTOR,
                           String SINOPSIS,
                           String FECHA_DE_PUBLICACION,
                           String EDITORIAL,
                           String CANTIDAD_DE_PAGINAS,
                           String IDIOMA,
                           String CATEGORIA,
                           int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to update record
        String sql = "UPDATE LIBROS SET TITULO=?, AUTOR=?, SINOPSIS=?, FECHA_DE_PUBLICACION=?, EDITORIAL=?, CANTIDAD_DE_PAGINAS=?, IDIOMA=?, CATEGORIA=?  WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, TITULO);
        statement.bindString(2, AUTOR);
        statement.bindString(3, SINOPSIS);
        statement.bindString(4, FECHA_DE_PUBLICACION);
        statement.bindString(5, EDITORIAL);
        statement.bindString(6, CANTIDAD_DE_PAGINAS);
        statement.bindString(7, IDIOMA);
        statement.bindString(8, CATEGORIA);

        statement.bindDouble(9, (double)id);

        statement.execute();
        database.close();
    }

    //deleteData
    public void deleteData(int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to delete record using id
        String sql = "DELETE FROM LIBROS WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
