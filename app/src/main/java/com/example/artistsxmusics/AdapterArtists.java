package com.example.artistsxmusics;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

public class AdapterArtists extends CursorAdapter {

    public AdapterArtists(Context context, Cursor cursor){

        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listItem = inflater.inflate(R.layout.list_layout, parent, false);
        return listItem;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView lblArtist = view.findViewById(R.id.lblArtist);
        TextView lblGenre = view.findViewById(R.id.lblGenre);

        String name = cursor.getString(cursor.getColumnIndex("name"));
        String genre = cursor.getString(cursor.getColumnIndex("genre"));

        lblArtist.setText(name);
        lblGenre.setText(genre);
    }
}
