package com.example.frost.notes;

import java.util.Date;

/**
 * Created by frost on 01.10.2017.
 */

public class Note {
    private Date _noteDate;
    private String _noteText;

    public Date get_noteDate() {
        return _noteDate;
    }
    public String get_noteText() {
        return _noteText;
    }

    public void set_noteDate(Date _noteDate) {
        this._noteDate = _noteDate;
    }
    public void set_noteText(String _noteText) {
        this._noteText = _noteText;
    }

    public Note(){
        this._noteDate = new Date();
        this._noteText = "hello";
    }
    public Note(Date date, String text) {
        this._noteDate = date;
        this._noteText = text;
    }

    @Override
    public String toString() {
        return String.valueOf(_noteDate.getDate()+" "+_noteText);
    }
}