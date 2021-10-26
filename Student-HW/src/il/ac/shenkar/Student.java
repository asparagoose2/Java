package il.ac.shenkar;

public class Student implements Comparable<Student>  {
    public int id;
    public String name;
    public double average;

    public Student(int _id,String _name, double _average) {
        this.id = _id;
        this.name = _name;
        this.average = _average;
    }

    public double getAverage() {
        return average;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", average=" + average +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(this.average, o.getAverage());
    }
}
