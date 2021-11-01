package com.example.artistsxmusics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SongsActivity extends AppCompatActivity {

    private TextView lblSongsArtist;
    private EditText txtTitle;
    private Button btnAddSong;
    private ListView listReleases;

    private Cursor cursorSongs;

    private AdapterSongs adapterSongs;

    private SQLiteDatabase database;

    private int idArtist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        lblSongsArtist = findViewById(R.id.lblSongsArtist);
        txtTitle = findViewById(R.id.txtTitle);
        btnAddSong = findViewById(R.id.btnAddSong);
        listReleases = findViewById(R.id.listReleases);

        btnAddSong.setOnClickListener(new ListenerAdder());

        database = openOrCreateDatabase("musicartists", MODE_PRIVATE, null);

        Intent i = getIntent();

        idArtist = i.getIntExtra("id", 0);

        lblSongsArtist.setText(i.getStringExtra("name"))    ;

        String cmd = "SELECT __rowid__ _id, title FROM songs WHERE idArtist = " + idArtist;
        cursorSongs = database.rawQuery(cmd, null);

        adapterSongs = new AdapterSongs(this, cursorSongs);

        listReleases.setAdapter(adapterSongs);
    }

    private class ListenerAdder implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String title;

            title = txtTitle.getText().toString();

            String cmd = "INSERT INTO songs(idArtist, title) VALUES(";
            cmd += idArtist;
            cmd += ", '";
            cmd += title;
            cmd += "')";

            database.execSQL(cmd);

            txtTitle.setText("");

            cursorSongs = database.rawQuery("SELECT __rowid__ _id, title FROM songs WHERE idArtist = " + idArtist, null);
            listReleases.setAdapter(adapterSongs);
        }
    }
}