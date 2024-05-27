// StudentMarks.java
package com.example.studentmarks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StudentMarks {

    public static void main(String[] args) {
        int[] marks = {85, 75, 95, 60, 70};
        try {
            Map<Integer, Integer> ranks = rankStudents(marks);
            ranks.forEach((key, value) -> System.out.println("Student " + key + " Rank: " + value));
            for (int i = 0; i < marks.length; i++) {
                System.out.println("Student " + i + " Pass: " + isPass(marks, i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Integer> rankStudents(int[] marks) throws IllegalArgumentException {
        if (marks == null || marks.length == 0) {
            throw new IllegalArgumentException("Marks array cannot be null or empty");
        }
        
        int[] sortedMarks = marks.clone();
        Arrays.sort(sortedMarks);
        Map<Integer, Integer> rankMap = new HashMap<>();

        for (int i = 0; i < marks.length; i++) {
            for (int j = 0; j < sortedMarks.length; j++) {
                if (marks[i] == sortedMarks[j]) {
                    rankMap.put(i, marks.length - j);
                    break;
                }
            }
        }

        return rankMap;
    }

    public static boolean isPass(int[] marks, int studentIndex) throws IllegalArgumentException {
        if (marks == null || marks.length == 0) {
            throw new IllegalArgumentException("Marks array cannot be null or empty");
        }
        if (studentIndex < 0 || studentIndex >= marks.length) {
            throw new IllegalArgumentException("Invalid student index");
        }

        int totalMarks = 0;
        int minMarks = Integer.MAX_VALUE;
        int maxMarks = Integer.MIN_VALUE;

        for (int mark : marks) {
            totalMarks += mark;
            if (mark < minMarks) {
                minMarks = mark;
            }
            if (mark > maxMarks) {
                maxMarks = mark;
            }
        }

        double averageMarks = (totalMarks - minMarks - maxMarks) / (double) (marks.length - 2);

        return marks[studentIndex] >= averageMarks;
    }
}
