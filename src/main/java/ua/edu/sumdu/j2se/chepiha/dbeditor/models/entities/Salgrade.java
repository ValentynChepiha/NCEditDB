package ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities;

public class Salgrade {
    private int grade;
    private float minsal;
    private float hisal;

    public Salgrade() {
    }

    public Salgrade(int grade, float minsal, float hisal) {
        this.grade = grade;
        this.minsal = minsal;
        this.hisal = hisal;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(byte grade) {
        this.grade = grade;
    }

    public float getMinsal() {
        return minsal;
    }

    public void setMinsal(float minsal) {
        this.minsal = minsal;
    }

    public float getHisal() {
        return hisal;
    }

    public void setHisal(float hisal) {
        this.hisal = hisal;
    }

    @Override
    public String toString() {
        return "Salgrade{" +
                "grade=" + grade +
                ", minsal=" + minsal +
                ", hisal=" + hisal +
                '}';
    }
}
