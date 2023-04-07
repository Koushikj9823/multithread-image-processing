package com.koushik.imageprocessing;

public class Blue implements Color {
    @Override
    public int getColorExtract(int rgbColor) {
        return rgbColor & 0x000000FF;
    }
}
