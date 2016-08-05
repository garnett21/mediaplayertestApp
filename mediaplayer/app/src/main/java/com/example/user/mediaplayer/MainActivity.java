package com.example.user.mediaplayer;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

class Mp3Filter implements FilenameFilter{
    public boolean accept(File dir,String name){
        return (name.endsWith(".mp3"));
    }


}
public class MainActivity extends ListActivity {
    private static final String SD_PATH = new String(Environment.getExternalStorageDirectory().getPath() + "/music/");
    private List<String> songs=new ArrayList<String>();
    private MediaPlayer mp=new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updatePlayList();

        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button stop = (Button) findViewById(R.id.stop);
        Button ff = (Button) findViewById(R.id.ff);
        Button bb = (Button) findViewById(R.id.bb);

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View View) {
                mp.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {

            public void onClick(View View) {
                mp.pause();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View View) {
                mp.stop();
                MainActivity.this.finish();
            }
        });
        ff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View View) {
                mp.seekTo(mp.getCurrentPosition() + 5000);

            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View View) {
                mp.seekTo(mp.getCurrentPosition() - 5000);

            }
        });
    }

    private void updatePlayList() {
    File home= new File(SD_PATH);
        if(home.listFiles( new Mp3Filter()).length>0){
            for (File file : home.listFiles(new Mp3Filter()))
            songs.add(file.getName());
      }
     ArrayAdapter<String>songList=new ArrayAdapter<String>(this,R.layout.song_item,songs);
        setListAdapter(songList);
    }
}