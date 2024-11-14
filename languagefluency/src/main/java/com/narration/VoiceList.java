/**
 * Provides functionality to list available voices from the Amazon Polly service for a specified AWS region
 */
package com.narration;

import java.util.Iterator;
import java.util.stream.Stream;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.DescribeVoicesRequest;
import software.amazon.awssdk.services.polly.model.DescribeVoicesResponse;
import software.amazon.awssdk.services.polly.model.PollyException;
import software.amazon.awssdk.services.polly.model.Voice;

public class VoiceList {

    /**
     * Private constructor to prevent instantiation of the VoiceList class.
     */
    private VoiceList() {}

    /**
     * Displays available voices in Amazon Polly for a specified AWS region.
     * Initializes and closes the PollyClient connection.
     *
     * @param region the AWS region to use for Polly service
     */
    public static void showVoices(Region region) {
        try (PollyClient polly = PollyClient.builder().region(region).build()) {
            displayVoices(polly);
        } catch (PollyException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Retrieves and displays a list of available voices from Amazon Polly using
     * the specified PollyClient.
     *
     * @param polly the PollyClient instance used to fetch voice data
     */
    private static void displayVoices(PollyClient polly) {
        try {
            DescribeVoicesRequest describeVoiceRequest = DescribeVoicesRequest.builder()
                    .engine("standard")
                    .build();

            DescribeVoicesResponse describeVoicesResult = polly.describeVoices(describeVoiceRequest);
            Stream<Voice> voiceStream = describeVoicesResult.voices().stream();

            Iterator<Voice> voices = voiceStream.iterator();

            while (voices.hasNext()) {
                Voice voice = voices.next();
                System.out.println(voice.name() + ": " + voice.genderAsString() + " " + voice.languageName());
            }

        } catch (PollyException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Main method to run the VoiceList program, listing voices for a specified region.
     * The default region used here is EU_WEST_3.
     * @param args command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        VoiceList.showVoices(Region.EU_WEST_3);
    }
}