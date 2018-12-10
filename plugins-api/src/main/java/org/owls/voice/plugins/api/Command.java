package org.owls.voice.plugins.api;

public abstract class Command {

    private Object applicationContext;
    private SpeechSynthesizer speechSynthesizer;

    private boolean loaded;

    public abstract String getName();

    public String getVersion() {
        return "0.0.2-built-in";
    }

    public String getProvider() {
        return "built-in";
    }

    public abstract void start();

    public abstract void execute();

    public abstract void finish();

    public void unload() {
        System.out.println("Unloading " + getName() + " ... ");
    }

    public void disable() {
        System.out.println("Disable " + getName() + " ... ");
    }

    public Object getApplicationContext() {
        return this.applicationContext;
    }

    public void setApplicationContext(Object applicationContext) {
        this.applicationContext = applicationContext;
    }

    public SpeechSynthesizer getSpeechSynth() {
        return this.speechSynthesizer;
    }

    public void setSpeechSynth(SpeechSynthesizer speechSynth) {
        this.speechSynthesizer = speechSynth;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
