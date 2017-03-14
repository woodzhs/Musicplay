package com.zhs.mymusicplayerdemo;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MusicFragment extends Fragment {
    private ListView listView;
    private static String path ="/sdcard/Musicdemo";
    private Button playbtn;
    private Button stopbtn;

    private AudioService audioService;

    //使用ServiceConnection来监听Service状态的变化
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            audioService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            //这里我们实例化audioService,通过binder来实现
            audioService = ((AudioService.AudioBinder)binder).getService();

        }
    };
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){  
        super.onCreateView(inflater, container, savedInstanceState);  
        View content = inflater.inflate(R.layout.my_music, container,false);
        listView = (ListView)content.findViewById(R.id.listView);
       /* playbtn = (Button)content.findViewById(R.id.playBtn);
        stopbtn=(Button)content.findViewById(R.id.stopMusic);
        intent = new Intent();
        intent.setClass(getActivity(), AudioService.class);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMusic();
            }
        });

        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
            }
        });*/

        List<MusicInfo> ret=getAllMusicFiles(path);



        return content;
    }

    public void stopMusic(){
        getActivity().unbindService(conn);
        getActivity().stopService(intent);

    }

    public void startMusic(){

        getActivity().startService(intent);
        getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override  
    public void onActivityCreated(Bundle savedInstanceState){  
        super.onActivityCreated(savedInstanceState);  
    }


    class MusicInfo {
        String singerName;
        String songName;
        String filePath;
    }

    private List<MusicInfo> getAllMusicFiles(String path)
    {

        ArrayList<MusicInfo> result =new ArrayList<>();
        File[] allFiles = new File(path).listFiles();
        for (int i = 0; i < allFiles.length; i++) {
            File file = allFiles[i];
            String filepath =file.getAbsolutePath();
            Log.e("TAG","filepath  is "+filepath );
//            提取音乐歌名歌手名
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(filepath);



            String songName =   ISO2GBK(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));
            String singerName = ISO2GBK(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));

            MusicInfo musicInfo = new MusicInfo();
            musicInfo.filePath=filepath;
            musicInfo.songName =songName;
            musicInfo.singerName =singerName;
            result.add(musicInfo);

        }

        return result;
    }

    private String ISO2GBK(String rawString)
    {
        try {
            return new String(rawString.getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}