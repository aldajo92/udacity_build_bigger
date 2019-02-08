package com.projects.aldajo92.jokeslib;

import java.util.Random;

public class Joker {
    public static String[] jokes = {
            "Q: What is the difference between Android 2.1 and 2.2?\n" + "A: 6 months",
            "Google’s definition of an upgrade?\n" + "Take old bugs out, put new ones in.",
            "The box said ‘Requires Windows Vista or better’. So I installed LINUX.",
            "Computers are like air conditioners: they stop working when you open Windows.",
            "If Ruby is not and Perl is the answer, you don’t understand the question.",
            "Michael Sinz: “Programming is like sex, one mistake and you have to support it for the rest of your life.”"
    };

    public String getJoke() {
        int index = new Random().nextInt(jokes.length);
        return jokes[index];
    }
}
