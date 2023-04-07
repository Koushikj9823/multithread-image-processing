package com.koushik.imageprocessing;

public class Green implements Color {
    @Override
    public int getColorExtract(int rgbColor) {
        return (rgbColor & 0x0000FF00) >> 8;
    }
}