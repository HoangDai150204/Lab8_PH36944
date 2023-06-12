package com.hoangdai.lab5_ph36944;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Lab8.DeleteItem;
import Lab8.UpdateItem;

public class StudentListViewAdapter extends BaseAdapter implements Filterable {
    private final Context context;
    private List<Student> list;
    private final List<Student> listOld;

    DeleteItem deleteItem;
    UpdateItem updateItem;

    public StudentListViewAdapter(Context context, ArrayList<Student> list){
        this.context = context;
        this.list = list;
        this.listOld = list;
    }

    public StudentListViewAdapter(Context context, ArrayList<Student> list, DeleteItem deleteItem, UpdateItem updateItem){
        this.context = context;
        this.list = list;
        this.listOld = list;
        this.deleteItem=deleteItem;
        this.updateItem=updateItem;
    }


    @Override
    public int getCount() {return list.size();}
    @Override
    public Object getItem(int i) {return list.get(i);}
    @Override
    public long getItemId(int i) {return i;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroud){
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        view = inflater.inflate(R.layout.item_listview, viewGroud, false);

        TextView txt_coso = view.findViewById(R.id.txtcoso);
        TextView txt_name = view.findViewById(R.id.txtname);
        TextView txt_address = view.findViewById(R.id.txtaddress);
        Button btndelete=view.findViewById(R.id.btndelete);
        Button btnUpdate = view.findViewById(R.id.btnupdate);

        txt_coso.setText("FPoly "+ list.get(i).getBranch());
        txt_name.setText("Họ tên: "+ list.get(i).getName());
        txt_address.setText("Địa chỉ: "+ list.get(i).getAddress());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater1= ((Activity)context).getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.addstudent, null);
                builder.setView(view1);
                final EditText txtTen=view1.findViewById(R.id.edtname);
                final EditText txtDiaChi=view1.findViewById(R.id.edtaddress);
                builder.setTitle("Update form");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ten = txt_name.getText().toString();
                        String diaChi = txt_address.getText().toString();
                        updateItem.onClickForUpadte(i,"Hà Nội", ten, diaChi);
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                notifyDataSetChanged();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem.onClickForDelete(i);
            }
        });


        return view;

    }
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String s = charSequence.toString();
                if (s.isEmpty()){
                    list = listOld;
                } else {
                    List<Student> listS = new ArrayList<>();
                    for(Student st:listOld){
                        if (st.getName().toLowerCase().contains(s.toLowerCase())){
                            listS.add(st);
                        }
                    }
                    list = listS;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values=list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (List<Student>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
