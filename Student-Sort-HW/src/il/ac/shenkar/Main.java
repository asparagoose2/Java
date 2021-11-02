package il.ac.shenkar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;

/*
    Work of Koral Tsaba & Ofir Duchovne
 */

public class Main {

    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<Student>(10);

        students.add(new Student(307,"koral",98.9));
        students.add(new Student(106,"Danny",50));
        students.add(new Student(109,"Rachel",100));
        students.add(new Student(55,"Avraham",12.8));
        students.add(new Student(66,"Ytzhak",77.3));
        students.add(new Student(77,"Yaakov",41));
        students.add(new Student(88,"Sarra",72.6));
        students.add(new Student(99,"Rivka",68.7));
        students.add(new Student(1010,"Moshe",98.4));
        students.add(new Student(1111,"Aharon",88.2));

        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student, Student t1) {
                return Double.compare(student.average, t1.getAverage());
            }
        });

        students.forEach(new Consumer<Student>() {
            @Override
            public void accept(Student student) {
                System.out.println(student);
            }
        });

    }
}
