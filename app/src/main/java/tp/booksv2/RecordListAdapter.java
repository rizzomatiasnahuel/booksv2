package tp.booksv2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Model> recordList;

    public RecordListAdapter(Context context, int layout, ArrayList<Model> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView eTITULO, eAutor, eSinopsis, eFecha_de_publicacion, eEditorial, eCantidad_de_Paginas, eIdioma,  eCategoria ;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.eTITULO = row.findViewById(R.id.eTITULO);
            holder.eAutor = row.findViewById(R.id.eAutor);
            holder.eSinopsis = row.findViewById(R.id.eSinopsis);
            holder.eFecha_de_publicacion = row.findViewById(R.id.eFecha_de_publicacion);
            holder.eEditorial = row.findViewById(R.id.eEditorial);
            holder.eCantidad_de_Paginas = row.findViewById(R.id.eCantidad_de_Paginas);
            holder.eIdioma = row.findViewById(R.id.eIdioma);
            holder.eCategoria = row.findViewById(R.id.eCategoria);


            row.setTag(holder);
        }
        else {
            holder = (ViewHolder)row.getTag();
        }

        Model model = recordList.get(i);

        holder.eTITULO.setText(model.getTITULO());
        holder.eAutor.setText(model.getAUTOR());
        holder.eSinopsis.setText(model.getSINOPSIS());
        holder.eFecha_de_publicacion.setText(model.getFECHA_DE_PUBLICACION());
        holder.eEditorial.setText(model.getEDITORIAL());
        holder.eCantidad_de_Paginas.setText(model.getCANTIDAD_DE_PAGINAS());
        holder.eIdioma.setText(model.getIDIOMA());
        holder.eCategoria.setText(model.getCATEGORIA());


        return row;
    }
}