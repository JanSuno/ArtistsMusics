package com.example.artistsxmusics;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EditText txtArtist;
    private EditText txtGenre;
    private Button btnAdd;
    private ListView listSongs;

    Cursor cursorArtists;

    AdapterArtists adapterArtists;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtArtist = findViewById(R.id.txtArtist);
        txtGenre = findViewById(R.id.txtGenre);
        btnAdd = findViewById(R.id.btnAdd);
        listSongs = findViewById(R.id.listSongs);

        btnAdd.setOnClickListener(new ListenerAdd());

//        listSongs.setOnItemClickListener(new ListClickListener());

        database = openOrCreateDatabase("musicartists", MODE_PRIVATE, null);

        String cmd;
        cmd = "CREATE TABLE IF NOT EXISTS artists(";
        cmd += "id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, genre VARCHAR)";
        database.execSQL(cmd);

        cmd = "CREATE TABLE IF NOT EXISTS songs(";
        cmd += "id INTEGER PRIMARY KEY AUTOINCREMENT, idArtist INTEGER, title VARCHAR)";
        database.execSQL(cmd);

        cursorArtists = database.rawQuery("SELECT _rowid_ _id, id, name, genre FROM artists", null);

        adapterArtists = new AdapterArtists(this, cursorArtists);

        listSongs.setAdapter(adapterArtists);
    }

    private class ListenerAdd implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String artist, genre;

            artist = txtArtist.getText().toString();
            genre = txtGenre.getText().toString();

            String cmd = "INSERT INTO artists(name, genre) VALUES ('";
            cmd = cmd + artist;
            cmd = cmd + "',''";
            cmd = cmd + genre;
            cmd = cmd + "')";

            database.execSQL(cmd);

            txtArtist.setText("");
            txtGenre.setText("");

            cursorArtists = database.rawQuery("SELECT _rowid_ _id, id, name, genre FROM artists", null);
            adapterArtists.changeCursor(cursorArtists);
        }
    }
}