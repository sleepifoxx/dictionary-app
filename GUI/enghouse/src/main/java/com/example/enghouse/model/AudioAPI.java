package com.example.enghouse.model;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AudioAPI {
    public static final String GOOGLE_TRANSLATE_AUDIO = "http://translate.google.com/translate_tts?";
    private static final SecureRandom secureRandom = new SecureRandom(); // threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); // threadsafe
    private final static String userAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";
    private static AudioAPI audio;

    private AudioAPI() {
    }

    public synchronized static AudioAPI getInstance() {

        if (audio == null) {
            audio = new AudioAPI();
        }
        return audio;
    }

    private static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    private static String generateSpeakURL(String language, String text) {
        return GOOGLE_TRANSLATE_AUDIO + "?ie=UTF-8" +
                "&q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                "&tl=" + language +
                "&tk=" + generateNewToken() +
                "&client=tw-ob";
    }

    public InputStream getAudio(String text, String languageOutput)
            throws IOException {

        String urlString = generateSpeakURL(languageOutput, text);
        URL url = new URL(urlString);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setRequestMethod("GET");
        urlConn.setRequestProperty("User-Agent", userAgent);
        InputStream audioSrc = urlConn.getInputStream();
        return new BufferedInputStream(audioSrc);
    }

    public void play(InputStream sound) throws JavaLayerException {
        new Player(sound).play();
    }

    public static void main(String[] args) throws IOException, JavaLayerException {
        getInstance().play(getInstance().getAudio("Incorrect! Try it again.", "en-US"));
    }

    public static void AudioPlay(String text, String language) {
        Thread au = new Thread(() -> {
            AudioAPI audio = AudioAPI.getInstance();
            InputStream sound = null;
            try {
                sound = audio.getAudio(text, language);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                audio.play(sound);
            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }
        });
        au.start();
    }
}