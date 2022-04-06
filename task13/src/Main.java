import student.Student;
import student.Subject;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Main {
    static final String PATH = "src/resources/student.txt";
    static final String PATH_WRITE = "src/resources/rating.txt";
    public static void main(String[] args) {
        List<String> dataFromFile = new ArrayList<>();
        readData(dataFromFile);
        showSortAndWriteToFile(dataFromFile);
    }
    private static void readData(List<String> dataFromFile) {
        try (
                Stream<String> stream = Files.lines(Paths.get(PATH))) {
            stream.forEach(dataFromFile::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void showSortAndWriteToFile(List<String> dataFromFile) {
        System.out.println("Data from file:");
        dataFromFile.forEach(System.out::println);
        Map<Student, List<Subject>> map = new HashMap<>();
        dataFromFile.forEach(x -> {
            String[] s = x.split(", ");
            Subject subject = new Subject(s[1], Integer.parseInt(s[2]));
            String[] s2 = s[0].split(" ");
            Student student = new Student(s2[0], s2[1]);
            List<Subject> temp = new ArrayList<>();
            map.computeIfAbsent(student, a -> temp).add(subject);
        });
        Comparator<Map.Entry<Student, List<Subject>>> comparator = Comparator
                .comparingDouble(x -> -x.getValue().stream().mapToInt(Subject::getMark).average().orElse(0));
        System.out.println("\nData from map before sort:");
        map.forEach((key, value) -> {
            for (Subject subject : value) {
                System.out.printf("%s %s: %s = %d\n", key.getName(), key.getSecondName(),
                        subject.getSubjectName(), subject.getMark());
            }
        });
        System.out.println("\nData from map after sort:");
        map.entrySet().stream().sorted(comparator).forEach(System.out::println);
        writeToFile(map, comparator);
    }
    public static void writeToFile(Map<Student, List<Subject>> map,
                                   Comparator<Map.Entry<Student, List<Subject>>> comparator) {
        try (OutputStream write = new FileOutputStream(PATH_WRITE)){
            for (Map.Entry<Student, List<Subject>> entry :
                    map.entrySet().stream().sorted(comparator).collect(Collectors.toList())) {
                write.write(String.format("%s %s: average mark = %.2f\n", entry.getKey().getName(),
                        entry.getKey().getSecondName(),
                        entry.getValue().stream().mapToDouble(Subject::getMark).average().orElse(0)).getBytes());
            }
            System.out.println("\nFile created and wrote. Check it.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}