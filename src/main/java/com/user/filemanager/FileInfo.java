package com.user.filemanager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileInfo {

    private File file;

    private final Boolean _isDirectory;
    private final String _name;
    private final String _absolutePath;

    public FileInfo (File file) {
        this.file = file;

        this._isDirectory = file.isDirectory();
        this._name = file.getName();
        this._absolutePath = file.getAbsolutePath();
    }


    public Boolean isDirectory() {
        return _isDirectory;
    }

    public String getName() {
        return _name;
    }

    public String getAbsolutePath() {
        return _absolutePath;
    }

    public FileInfo[] listFiles () {
        File[] files = this.file.listFiles();

        if (files != null) {
            FileInfo[] result = new FileInfo[files.length];

            for (int i = 0; i < files.length; i++) {
                result[i] = new FileInfo(files[i]);
            }

            return result;
        }

        return null;
    }
}
