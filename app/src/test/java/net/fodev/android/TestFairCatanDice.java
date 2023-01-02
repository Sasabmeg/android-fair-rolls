package net.fodev.android;

import org.junit.Test;

public class TestFairCatanDice {
    FairCatanDice dice = new FairCatanDice();


    @Test
    public void print_single_valid() {
        dice.reset();
        dice.printValueOnly();
        dice.shuffle(1);
        dice.printValueOnly();
    }

    @Test
    public void getNext_simple_valid() {
        dice.reset();
        dice.printValueOnly();
        Pair<Integer, Integer> d = dice.getNext();
        System.out.println(String.format("(%d, %d) = %2d", d.getFirst(), d.getSecond(), d.getFirst() + d.getSecond()));
        dice.printValueOnly();
    }

    @Test
    public void overhand_valid() {
        dice.reset();
        dice.printValueOnly();
        dice.overhand();
        dice.printValueOnly();
    }

    @Test
    public void riffle_valid() {
        dice.reset();
        dice.printValueOnly();
        dice.riffle();
        dice.printValueOnly();
    }

    @Test
    public void shuffle_valid() {
        dice.reset();
        dice.printValueOnly();
        dice.shuffle(5);
        dice.printValueOnly();
    }

    @Test
    public void getHistory_valid() {
        dice.reset();
        dice.getNext();
        System.out.println(dice.getHistory());
    }

    @Test
    public void getNext_nullTest_Valid() {
        dice.reset();
        dice.printValueOnly();
        int max = dice.getSize();
        for (int i = 0; i < max; i++) {
            dice.getNext();
        }
        dice.printValueOnly();
        dice.getNext();
    }

}
