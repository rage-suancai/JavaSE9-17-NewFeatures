package Java12_16NewFeatures3;

public class Student {

    private final String name;

    public Student(String name) {
        this.name = name;
    }

    /*@Override
    public boolean equals(Object o) {

        if (o instanceof Student) {
            Student student = (Student) o;
            return student.name.equals(this.name);
        }
        return false;

    }*/

    @Override
    public boolean equals(Object o) {
        if (o instanceof Student student) {
            return student.equals(this.name);
        }
        return false;
    }

}
