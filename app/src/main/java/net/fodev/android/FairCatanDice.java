package net.fodev.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FairCatanDice {
    private List<Pair<Integer, Integer>> rolls;
    private List<Pair<Integer, Integer>> history;

    public FairCatanDice() {
        rolls = new ArrayList<>();
        history = new ArrayList<>();
        reset();
    }

    public void reset() {
        history =new ArrayList<>();
        rolls = new ArrayList<>();
        Pair<Integer, Integer> p = new Pair<>(1, 1);
        for (int i = 0; i < 6; i = i + 2) {
            for (int j = 0; j < 6; j++) {
                rolls.add(new Pair<>(i + 1, j + 1));
            }
        }
        for (int i = 1; i < 6; i = i + 2) {
            for (int j = 0; j < 6; j++) {
                rolls.add(new Pair<>(i + 1, j + 1));
            }
        }
    }

    public void shuffle(int times) {
        for (int i = 0; i < times; i++) {
            cutRandom(rolls.size() / 6);
            overhand();
            riffle();
            overhand();
        }
    }

    public void cut(int index) {
        if (index <= 0 || index >= (rolls.size() - 1)) {
            index = rolls.size() / 2;
        }
        List<Pair<Integer, Integer>> firstHalf = rolls.stream().skip(index).collect(Collectors.toList());
        List<Pair<Integer, Integer>> secondHalf = rolls.stream().limit(index).collect(Collectors.toList());
        rolls = firstHalf;
        rolls.addAll(secondHalf);
    }

    public void cutRandom(int min) {
        Random random = new Random();
        int index = random.nextInt(rolls.size() - min) + min;
        cut(index);
    }

    public void overhand() {
        Random random = new Random();
        int min = 3;
        List<Pair<Integer, Integer>> other = new ArrayList<>();
        int max = rolls.size() / 4;
        do {
            int index = random.nextInt(max - min) + min;
            if (index >= rolls.size()) {
                other.addAll(rolls);
                rolls.clear();
            } else {
                List<Pair<Integer, Integer>> sublist = rolls.subList(0, index);
                other.addAll(0, sublist);
                sublist.clear();
            }
        } while (!rolls.isEmpty());
        rolls = other;
    }

    public void riffle() {
        List<Pair<Integer, Integer>> first = new ArrayList<>();
        first.addAll(rolls.subList(0, rolls.size() / 2));
        List<Pair<Integer, Integer>> second  = new ArrayList<>();
        second.addAll(rolls.subList(rolls.size() / 2, rolls.size()));
        rolls.clear();
        for (int i = 0; i < first.size(); i++) {
            rolls.add(first.get(i));
            rolls.add(second.get(i));
        }
        if (second.size() > first.size()) {
            rolls.add(second.get(second.size() - 1));
        }
    }

    public Pair<Integer, Integer> getNext() {
        if (!rolls.isEmpty()) {
            history.add(rolls.get(0));
            return rolls.remove(0);
        } else {
            return null;
        }
    }

    public void print() {
        String sb = "";
        for (int i = 0; i < rolls.size(); i++) {
            sb += "(" + rolls.get(i).getFirst() + ", " + rolls.get(i).getSecond() + ") ";
            if ((i + 1) % 6 == 0) {
                System.out.println(sb);
                sb = "";
            }
        }
        System.out.println(sb);
    }

    public void printVerbose() {
        String sb = "";
        for (int i = 0; i < rolls.size(); i++) {
            sb += String.format("(%d, %d) = %2d;   ", rolls.get(i).getFirst(), rolls.get(i).getSecond(), rolls.get(i).getFirst() + rolls.get(i).getSecond());
            if ((i + 1) % 6 == 0) {
                System.out.println(sb);
                sb = "";
            }
        }
        System.out.println(sb);
    }

    public void printValueOnly() {
        String sb = "";
        for (int i = 0; i < rolls.size(); i++) {
            sb += String.format("%2d  ", rolls.get(i).getFirst() + rolls.get(i).getSecond());
        }
        System.out.println(sb);
    }

    public void printValueOnly(List<Pair<Integer, Integer>> other) {
        String sb = "";
        for (int i = 0; i < other.size(); i++) {
            sb += String.format("%2d  ", other.get(i).getFirst() + other.get(i).getSecond());
        }
        System.out.println(sb);
    }

    public String getHistory() {
        String sb = "";
        for (int i = 0; i < history.size(); i++) {
            sb += String.format("%d + %d = %d   ", history.get(i).getFirst(), history.get(i).getSecond(),
                    (history.get(i).getFirst() + history.get(i).getSecond()));
        }
        return sb;
    }

    public String getHistoryValueOnly() {
        String sb = "";
        for (int i = 0; i < history.size(); i++) {
            sb += String.format("%d   ", history.get(i).getFirst() + history.get(i).getSecond());
        }
        return sb;
    }
    public int getSize() {
        return rolls.size();
    }
}
