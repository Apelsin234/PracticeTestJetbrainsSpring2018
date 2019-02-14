package ru.kravchenko;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FirstTask {

    public static void main(String[] args) {
        new FirstTask().run();
    }

    public String run(List<String> names) {
        return buildAlphabet(names);
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        List<String> names = new ArrayList<>(100);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            names.add(name);
        }
        System.out.println(buildAlphabet(names));
    }

    private int[] colors = new int[26];
    private List<Integer> ans = new ArrayList<>();

    private String buildAlphabet(List<String> names) {

        List<Set<Integer>> compareRelations = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            compareRelations.add(new HashSet<>(26));
        }

        for (int i = 0; i < names.size() - 1; i++) {
            String first = names.get(i);
            String second = names.get(i + 1);
            int size = Math.min(second.length(), first.length());
            for (int j = 0; j < size + 1; j++) {
                if (size == j) {
                    if (first.length() > second.length()) {
                        return "Impossible";

                    }
                    break;
                }
                int a = first.charAt(j) - 'a';
                int b = second.charAt(j) - 'a';
                if (a != b) {
                    compareRelations.get(a).add(b);
                    break;
                }
            }
        }
        if (!top_sort(compareRelations)) {
            return "Impossible";
        }
        StringBuilder s = new StringBuilder();
        for (int i = ans.size() - 1; i >= 0; i--) {
            s.append((char) (ans.get(i) + 'a'));
        }
        return s.toString();

    }

    private boolean dfs(int v, final List<Set<Integer>> g) {
        colors[v] = 1;
        for (Integer to : g.get(v)) {
            if (colors[to] == 0) {

                if (dfs(to, g)) {
                    return true;
                }
            }
            else if (colors[to] == 1) {
                return true;
            }
        }
        colors[v] = 2;
        ans.add(v);
        return false;
    }

    private boolean top_sort(final List<Set<Integer>> g) {
        for (int i = 0; i < 26; i++) {
            if (colors[i] == 0) {
                if (dfs(i, g)) {
                    return false;
                }
            }
        }
        return true;
    }
}

