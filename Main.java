package il.ac.shenkar;

public class Main {

    public static void main(String[] args) {

        Student student[];
        student = new Student[5];


        student[0] = new Student(307,"koral",98.9);
        student[1] = new Student(106,"Danny",90.5);
        student[2] = new Student(109,"Rachel",60.5);

        Student newStudent[];
        newStudent = new Student[5];
        System.arraycopy(student,0,newStudent,0,3);

        for (Student tempStudent : student) {
            System.out.println(tempStudent);
        }
    }
}
