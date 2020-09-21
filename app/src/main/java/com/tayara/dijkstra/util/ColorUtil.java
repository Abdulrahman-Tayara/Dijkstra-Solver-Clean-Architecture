package com.tayara.dijkstra.util;

public class ColorUtil {
    private static String allowedChars = "0123456789ABCDEF";


    public static String generateRandomColor() {
        String color = "";
        for (int i = 0; i < 6; i++) {
            color += allowedChars.charAt((int) (Math.random() * (allowedChars.length() - 1)));
        }
        return "#" + color;
    }
}
