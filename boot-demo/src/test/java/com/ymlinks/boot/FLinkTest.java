package com.ymlinks.boot;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;

import java.util.List;

public class FLinkTest {


    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Integer> amounts = env.fromElements(1, 29, 40, 50);
        int threshold = 30;
        try {
            List<Integer> collect = amounts
                    .filter(a -> a > threshold)
                    .reduce((integer, t1) -> integer + t1)
                    .collect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
