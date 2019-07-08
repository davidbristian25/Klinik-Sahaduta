package com.example.klinik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<PlayerItem> {

    private List<PlayerItem> playerItemList;

    private Context context;

    public ListViewAdapter(List<PlayerItem> playerItemList, Context context) {
        super(context, R.layout.list_item, playerItemList);
        this.playerItemList = playerItemList;
        this.context = context;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView textViewid_komentar = listViewItem.findViewById(R.id.textViewid_komentar);
        TextView textViewno_rm = listViewItem.findViewById(R.id.textViewno_rm);
        TextView textViewKritik = listViewItem.findViewById(R.id.textViewKritik);
        TextView textViewsaran = listViewItem.findViewById(R.id.textViewsaran);



        PlayerItem playerItem = playerItemList.get(position);

        textViewid_komentar.setText(playerItem.getid_komentar());
        textViewno_rm.setText(playerItem.getno_rm());
        textViewKritik.setText(playerItem.getKritik());
        textViewsaran.setText(playerItem.getsaran());




        return listViewItem;
    }
}