package ru.ssau.tk.GussiRep.labOOP.functions;

import java.util.Iterator;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Removable, Insertable, Iterable<Point> {

    private Node head;
    protected int count;

    @Override
    public void insert(int x, int y) {

    }

    @Override
    public Iterator<Point> iterator() {
        throw new UnsupportedOperationException();
    }


    private static class Node {
        public double x;
        public double y;
        public Node next;
        public Node prev;
    }

    private void addNode(double x, double y) {
        Node node = new Node();
        if (head == null) {
            head = node;
            node.next = node;
            node.prev = node;
            node.x = x;
            node.y = y;
        } else {
            node.next = head;
            node.prev = head.prev;
            head.prev.next = node;
            node.x = x;
            node.y = y;
            head.prev = node;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2 & yValues.length < 2) {
            throw new IllegalArgumentException("Count of points less then 2");
        }
        for (int i = 0; i != xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Count of points less then 2");
        }
        double step = (xTo - xFrom) / (count - 1);
        if (xFrom < xTo) {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
                xFrom += step;
            }
        }
    }

    @Override
    public void remove(int index) {
        if (count == 2) {
            throw new IllegalArgumentException("small number of tabulated values");
        }
        checkBorders(index);
        Node executed = getNode(index);
        count--;
        executed.prev.next = executed.next;
        executed.next.prev = executed.prev;
    }

    @Override
    public int getCount() {
        return count;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Invalid index");
        }
        Node argument;
        if (index > (count / 2.)) {
            argument = head.prev;
            for (int i = count; i > 0; i--) {
                if (i == index) {
                    return argument.next;
                } else {
                    argument = argument.prev;
                }
            }
        } else {
            argument = head;
            for (int i = 0; i < count - index; i++) {
                if (i == index) {
                    return argument;
                } else {
                    argument = argument.next;
                }
            }
        }
        return null;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("X less than the left border");
        }
        Node node = head;
        for (int i = 0; i <= count; i++) {
            if (node.x < x) {
                node = node.next;
            } else {
                return i - 1;
            }
        }
        return getCount();
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (head.x == head.prev.x) {
            return x;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (head.x == head.prev.x) {
            return x;
        }
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (head.x == head.prev.x) {
            return x;
        }

        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    @Override
    public double getX(int index) {
        checkBorders(index);
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        checkBorders(index);
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
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
        return head.prev.x;
    }

    public void checkBorders(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Invalid index");
        }
    }
}

