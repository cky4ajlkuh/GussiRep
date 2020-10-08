package ru.ssau.tk.GussiRep.labOOP.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    private Node last;
    private Node head;
    protected int count;

    private static class Node {
        public double x;
        public double y;
        public Node next;
        public Node prev;
    }


    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {

    }

    private void addNode(double x, double y) {

        Node knot = new Node();
        if (head == null) {
            head = knot;
            knot.next = knot;
            knot.prev = knot;
            last = knot;
            knot.x = x;
            knot.y = y;
        } else {
            knot.next = head;
            knot.prev = last;
            head.prev = knot;
            last.next = knot;
            knot.x = x;
            knot.y = y;
            last = knot;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i != xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    // private Node getNode(double index) {
    //
    // }

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
        Node firstX;
        firstX = head;
        for (int i = 0; i < count; i++) {
            if (firstX.x == x) {
                return i;
            } else {
                firstX = firstX.next;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node firstY;
        firstY = head;
        for (int i = 0; i < count; i++) {
            if (firstY.y == y) {
                return i;
            } else {
                firstY = firstY.next;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return last.x;
    }
}
