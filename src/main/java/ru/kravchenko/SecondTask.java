package ru.kravchenko;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SecondTask {

    public static void main(String[] args) {
        new SecondTask().run();
    }

    private Map<String, Integer> lastNameSuf = new HashMap<>();

    public List<String> run(List<String> names) {
        return names.stream().map(this::retName).collect(Collectors.toList());
    }

    public void run() {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            System.out.println(retName(name));
        }
    }

    private String retName(String name) {
        Integer integer = lastNameSuf.putIfAbsent(name, 0);
        //don't use, but for safe
        if (integer == null) {
            integer = 0;
        }
        lastNameSuf.put(name, integer + 1);
        return name + (integer == 0 ? "" : integer);
    }
}

