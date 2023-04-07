package com.koushik.imageprocessing;

public class Red implements Color {
    @Override
    public int getColorExtract(int rgbColor) {
        return (rgbColor & 0x00FF0000) >> 16;
    }
}