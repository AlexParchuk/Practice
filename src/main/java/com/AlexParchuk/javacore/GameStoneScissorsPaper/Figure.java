package com.AlexParchuk.javacore.GameStoneScissorsPaper;

public enum Figure {
    STONE(1), SCISSOR(2),PAPER(3);

    private Integer choise;

    Figure(Integer choise) {
        this.choise = choise;
    }

    public int getChoice(){
        return this.choise;
    }

    public static Figure getByValue(Integer value){
        for (Figure figure : Figure.values()) {
            if (figure.choise.equals(value))
                return figure;
        }
        return null;
    }

}
