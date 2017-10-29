package com.example.frost.notes;

import android.content.Context;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by frost on 01.10.2017.
 */

public class JSONHelper {
    private static final String FILE_NAME="data.json";

    static boolean ExportToJSON(Context context,ArrayList<Note> dataList){
        Gson gson=new Gson();
        DataItems dataItems=new DataItems();
        dataItems.setNotes(dataList);
        String jsonString = gson.toJson(dataItems);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream =context.openFileOutput(FILE_NAME,Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(fileOutputStream!=null){
                try{
                    fileOutputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    static ArrayList<Note> ImportFromJSON(Context context){
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = context.openFileInput(FILE_NAME);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader,DataItems.class);
            return dataItems.getNotes();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(streamReader!=null){
                try{
                    streamReader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(fileInputStream!=null){
                try{
                    fileInputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static class DataItems{
        private ArrayList<Note> _notes=new ArrayList<>();

        ArrayList<Note> getNotes(){
            return _notes;
        }
        void setNotes(ArrayList<Note> notes){
            this._notes=notes;
        }
    }
}
