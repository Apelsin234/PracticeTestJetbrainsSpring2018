package ru.kravchenko;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class FirstTaskTest {

    private FirstTask task;

    @BeforeMethod
    public void before() {
        task = new FirstTask();
    }

    @Test
    public void test1() {
        //a < b < q < t -> norm
        List<String> names = Arrays.asList("alex", "brow", "qqq", "tye");
        assertEquals(task.run(names), "zyxwvusrponmlkjihgfedcabqt");
    }

    @Test
    public void test2() {
        // a < i < e < u -> norm
        List<String> names = Arrays.asList("alex", "alexey", "igorek", "egorok", "ulugbek");
        assertEquals(task.run(names), "zyxwvtsrqponmlkjhgfdcbaieu");
    }

    @Test
    public void test3() {
        //alex < alexey -> всегда поэтому невозможно
        List<String> names = Arrays.asList("alexey", "alex", "igorek", "egorok", "ulugbek");
        assertEquals(task.run(names), "Impossible");
    }

    @Test
    public void test4() {
        //a < i < e < u < a -> цикл
        List<String> names = Arrays.asList("alex", "alexey", "igorek", "egorok", "ulugbek", "ulaqqqqqq");
        assertEquals(task.run(names), "Impossible");
    }

    @Test
    public void test5() {
        //g < v < i < a & n < d < l -> norm
        List<String> names = Arrays.asList("gleb", "vitaliy", "ilyuxa", "antoha", "adik", "altynbek");
        assertEquals(task.run(names), "zyxwutsrqponmkjhgvifedlcba");
    }

    @Test
    public void test6() {
        // g < v & a < i & v < i < a & n < d < l -> a < i < a - цикл
        List<String> names = Arrays.asList("gleb", "vadim", "vitaliy", "ilyuxa", "antoha", "adik", "altynbek");
        assertEquals(task.run(names), "Impossible");
    }

    @Test
    public void test7() {
        // g < v < i < a & n < d < l & a < p < q & e < r
        List<String> names = Arrays.asList("gleb", "vitaliy", "ilyuxa", "antoha", "adik", "altynbek", "popl", "qwe", "qwr");
        assertEquals(task.run(names), "zyxwutsonmkjhgviferdlcbapq");
    }

    @Test
    public void test8() {
        // (1)g < v < i < a &(2) n < d < l &(3) a < p < q &(4) e < r < g &(5) w < a < n &(6) w < l < e
        // цикл  (1) -> (5) -> (2) -> (6) -> (4)
        List<String> names = Arrays.asList("gleb", "vitaliy", "ilyuxa", "antoha",
                "adik", "altynbek", "popl", "qwe", "qwr", "qwg", "qaw", "qnw", "qnl", "qne");
        assertEquals(task.run(names), "Impossible");
    }

}