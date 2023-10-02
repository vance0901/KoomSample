package com.enjoy.forksamples;

public class NativeLib {

    // Used to load the 'forksamples' library on application startup.
    static {
        System.loadLibrary("forksamples");
    }

    /**
     * A native method that is implemented by the 'forksamples' native library,
     * which is packaged with this application.
     */
    public native int fork();
}