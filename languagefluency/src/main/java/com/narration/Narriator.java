/**
 * Provides text-to-speech narration functionality using Amazon Polly.
 * Supports narrating text, feedback messages, and questions with a Spanish accent.
 */
package com.narration;

import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackListener;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.DescribeVoicesRequest;
import software.amazon.awssdk.services.polly.model.DescribeVoicesResponse;
import software.amazon.awssdk.services.polly.model.OutputFormat;
import software.amazon.awssdk.services.polly.model.PollyException;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechRequest;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechResponse;
import software.amazon.awssdk.services.polly.model.Voice;

public class Narriator {
    
    private Narriator() {}

    /**
     * Narrates a given text by converting it to speech.
     * @param text the text to be narrated
     */
    public static void playSound(String text) {
        try (PollyClient polly = PollyClient.builder().region(Region.EU_WEST_3).build()) {
            talkPolly(polly, text);
        } catch (PollyException e) {
            System.err.println("PollyException: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Narrates a feedback message based on a given score.
     * @param score the score to narrate in the feedback
     */
    public static void playFeedback(double score) {
        String feedback = score >= 80 ? "Great job! You've scored " + score + "%. Moving to the next module." :
                "You scored " + score + "%. You'll need to review this module.";
        playSound(feedback);
    }

    /**
     * Narrates a given question by converting it to speech.
     * @param question the question text to be narrated
     */
    public static void narrateQuestion(String question) {
        playSound("Question: " + question);
    }

    /**
     * Converts the specified text to speech using Amazon Polly and plays it.
     * @param polly the PollyClient instance used to interact with Amazon Polly
     * @param text  the text to be narrated
     */
    private static void talkPolly(PollyClient polly, String text) {
        try {
            DescribeVoicesRequest describeVoiceRequest = DescribeVoicesRequest.builder()
                    .engine("standard")
                    .build();

            DescribeVoicesResponse describeVoicesResult = polly.describeVoices(describeVoiceRequest);
            Voice voice = describeVoicesResult.voices().stream()
                    .filter(v -> v.name().equals("Miguel"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Voice not found"));

            InputStream stream = synthesize(polly, text, voice, OutputFormat.MP3);
            AdvancedPlayer player = new AdvancedPlayer(stream,
                    javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());

            player.setPlayBackListener(new PlaybackListener() {});
            player.play();

        } catch (PollyException | JavaLayerException | IOException e) {
            System.err.println("Error in Polly narration: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Synthesizes the specified text into speech using a specific voice and output format.
     * @param polly  the PollyClient instance used for synthesis
     * @param text   the text to convert to speech
     * @param voice  the Polly voice to use for the synthesis
     * @param format the output format of the speech
     * @return an InputStream containing the speech audio of the spoken text
     * @throws IOException if an error occurs during synthesis
     */
    private static InputStream synthesize(PollyClient polly, String text, Voice voice, OutputFormat format)
            throws IOException {
        SynthesizeSpeechRequest synthReq = SynthesizeSpeechRequest.builder()
                .text(text)
                .voiceId(voice.id())
                .outputFormat(format)
                .build();

        ResponseInputStream<SynthesizeSpeechResponse> synthRes = polly.synthesizeSpeech(synthReq);
        return synthRes;
    }
}