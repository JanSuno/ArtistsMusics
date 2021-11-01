package com.example.artistsxmusics;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AdapterSongs extends CursorAdapter {
    public AdapterSongs(Context context, Cursor cursor){

        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listItem = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return listItem;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitle = view.findViewById(android.R.id.text1);

        String title = cursor.getString(cursor.getColumnIndex("title"));

        txtTitle.setText(title);
    }
}
