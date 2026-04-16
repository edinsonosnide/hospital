package com.solvd.hospital.model;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.countMatches;

public class WordCounter {
    public String getWordCounter(String completeText, String[] wordsToCount) {
        StringBuilder finalText = new StringBuilder();
        for (String word : wordsToCount) {
            finalText.append(word).append(": ").append(countMatches(completeText, word)).append("\n");
        }
        return finalText.toString();
    }
}
