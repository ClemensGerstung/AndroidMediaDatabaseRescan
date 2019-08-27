package com.clemensgerstung.rebuildmediadatabase;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

import java.io.File;

public class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {
    private MediaScannerConnection _scanner;
    private File _file;
    private Context _context;

    public SingleMediaScanner(Context context, File file) {
        _file = file;
        _context = context;
        _scanner = new MediaScannerConnection(context, this);
        _scanner.connect();
    }

    @Override
    public void onMediaScannerConnected() {
        if (_file.exists() && _file.isDirectory()) {
            String[] files = _file.list();
            if (files == null) {
                return;
            }

            for (String f : files) {
                File fa = new File(_file, f);
                if (fa.isFile())
                    _scanner.scanFile(fa.getAbsolutePath(), null);
            }
        }
    }

    @Override
    public void onScanCompleted(String s, Uri uri) {
        Log.d("SingleMediaScanner", "Scanned " + s);
    }
}
