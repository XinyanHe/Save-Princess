package com.example.gamecenter.games.math24game.model;

class QuestionBank {

    /**
     * create level 1 question bank
     */
    private int[][] level1;
    /**
     * create level 2 question bank
     */
    private int[][] level2;

    /**
     * Create question bank for two difficulty levels of the game
     */
    QuestionBank() {

        level1 = new int[][]{
                {6, 6, 6, 6},
                {0, 8, 8, 8},
                {4, 0, 10, 10},
                {1,2,8,0},
                {1,8,8,8},
                {4,6,1,1},
                {4,6,2,1},
                {1, 1, 1, 24},
                {2, 3, 4, 1}};

        level2 = new int[][]{
                {3,8,5,5},
                {2, 8, 10, 4},
                {2, 6, 3, 4},
                {6, 6, 8, 9},
                {4, 5, 5, 5},
                {4, 5, 5, 8}};

    }


    /**
     * @param nextLevel True when the game is in level 2, while false when the game is in level 1
     * @return one random question from the corresponding level's question bank
     */
    int[] getRandomQ(boolean nextLevel){
        if (!nextLevel){
            return level1[(int)(Math.random() * (level1.length - 1) + 0.5)];
        }
        else{
            return level2[(int)(Math.random() * (level2.length - 1) + 1)];

        }


    }
}
