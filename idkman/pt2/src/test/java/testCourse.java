import org.example.Course;
import org.example.Student;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class testCourse {

    static Course course;
    Student student;
    Student student1;
    Student student2;
    ArrayList<Student> students = new ArrayList<>();

    @BeforeAll
    public static void initClass(){
        course = new Course("pppl", "Praktikum Pengujian Perangkat Lunak");
    }

    @AfterAll
    public static void cleanClass(){
        course.clearAllStudents();
    }

    @BeforeEach
    public void initMethod(){
        student = new Student("1", "Afif");
        student1 = new Student("2", "Afif1");
        student2 = new Student("3", "Afif2");
        students.add(student);
        students.add(student1);
        students.add(student2);
    }

    @AfterEach
    public void cleanMethod(){
        System.out.println("Jumlah students: " + course.getStudentCount());
        course.printAllStudents();
        students.clear();
    }

    @Test
    public void method1(){
        ArrayList<Student> studentsToEnroll = students;
        course.enrollStudent(studentsToEnroll.get(0));
        course.enrollStudent(studentsToEnroll.get(1));
        course.enrollStudent(studentsToEnroll.get(2));
        assertEquals(3, course.getStudentCount());
    }

    @Test
    public void method2(){
        List<Student> studentsToUnenroll = course.getAllStudents();
        course.unenrollStudent(studentsToUnenroll.get(0));
        assertEquals(2, course.getStudentCount());
    }
}
