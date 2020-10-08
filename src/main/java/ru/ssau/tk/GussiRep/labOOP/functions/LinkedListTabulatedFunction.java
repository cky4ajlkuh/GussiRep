package ru.ssau.tk.GussiRep.labOOP.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    private Node head;

   private static class Node
    {
        double x;
        double y;
        Node next;
        Node prev;
    }

    private void addNode(double x, double y){

    }

    @Override
    protected int floorIndexOfX(double x) {
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return 0;
    }

    @Override
    protected double extrapolateRight(double x) {
        return 0;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public double getX(int index) {
        return 0;
    }

    @Override
    public double getY(int index) {
        return 0;
    }

    @Override
    public void setY(int index, double value) {

    }

    @Override
    public int indexOfX(double x) {
        return 0;
    }

    @Override
    public int indexOfY(double y) {
        return 0;
    }

    @Override
    public double leftBound() {
        return 0;
    }

    @Override
    public double rightBound() {
        return 0;
    }
}
