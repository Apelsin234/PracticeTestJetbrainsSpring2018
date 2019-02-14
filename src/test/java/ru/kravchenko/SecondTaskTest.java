package ru.kravchenko;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class SecondTaskTest {

    private SecondTask task;
    @BeforeMethod
    public void before() {
        task = new SecondTask();
    }

    @Test
    public void testOneName() {
        List<String> names = Collections.singletonList("alex");
        assertEquals(task.run(names), names);
    }

    @Test
    public void testUniqNames() {
        List<String> names = Arrays.asList("alex", "john", "jack");
        assertEquals(task.run(names), names);
    }

    @Test
    public void testRepeatedName() {
        List<String> names = new ArrayList<>(Collections.nCopies(20, "alex"));
        List<String> answer = new ArrayList<>();
        answer.add("alex");
        for (int i = 1; i < names.size(); i++) {
            answer.add("alex" + i);
        }
        assertEquals(task.run(names), answer);
    }

    @Test
    public void testRepeatedNames() {
        List<String> names = Arrays.asList("alex", "john", "jack", "alex", "alex", "john", "key", "alex", "key");
        List<String> answer = Arrays.asList("alex", "john", "jack", "alex1", "alex2", "john1", "key", "alex3", "key1");

        assertEquals(task.run(names), answer);
    }


}