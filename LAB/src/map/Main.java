package map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(1, "Student", "msk@dsm", 2332943);
        Student student2 = new Student(2, "Stsdudent", "msk@dsm", 2332943);
        Student student3 = new Student(3, "Stusdsddent", "msk@dsm", 2332943);

        //init map

        Map<Integer, Student>  map = new HashMap<Integer, Student>();

        map.put(student1.getId(), student1);
        map.put(student2.getId(), student2);
        map.put(student3.getId(), student3);

        for(Integer key : map.keySet()) {
            Student value = map.get(key);

            System.out.println(key + "=" + value);
        }

        for(Map.Entry<Integer, Student> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Student value = entry.getValue();

            System.out.println( key + "=" + value.getName());
        }

        TreeMap<String, Integer> subjects = new TreeMap<String, Integer>();
        subjects.put("Java", 120);
        subjects.put("C#", 120);
        subjects.put("Php", 120);
        System.out.println(subjects);




    }
}
