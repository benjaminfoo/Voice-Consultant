package org.owls.voice.backend.plugins;

import java.io.File;

interface FileEvent {
    public void onFileCreated(File file);

    public void onFileRemoved(File file);
}