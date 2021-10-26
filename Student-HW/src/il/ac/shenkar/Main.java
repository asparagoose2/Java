package il.ac.shenkar;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Student student[];
        student = new Student[10];


        student[0] = new Student(307,"koral",98.9);
        student[1] = new Student(106,"Danny",50);
        student[2] = new Student(109,"Rachel",100);
        student[3] = new Student(55,"Avraham",12.8);
        student[4] = new Student(66,"Ytzhak",77.3);
        student[5] = new Student(77,"Yaakov",41);
        student[6] = new Student(88,"Sarra",72.6);
        student[7] = new Student(99,"Rivka",68.7);
        student[8] = new Student(1010,"Moshe",98.4);
        student[9] = new Student(1111,"Aharon",88.2);

        Arrays.sort(student);

        for (Student tempStudent : student) {
            System.out.println(tempStudent);
        }
    }
}
