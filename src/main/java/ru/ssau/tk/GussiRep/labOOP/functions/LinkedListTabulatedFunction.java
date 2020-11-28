package ru.ssau.tk.GussiRep.labOOP.functions;

import ru.ssau.tk.GussiRep.labOOP.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable, Removable, Iterable<Point> {

    private static final long serialVersionUID = -461948185547873972L;
    private Node head;
    protected int count;

    private static class Node implements Serializable {
        private static final long serialVersionUID = -8848583414268867499L;
        public double x;
        public double y;
        public Node next;
        public Node prev;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2 & yValues.length < 2) {
            throw new IllegalArgumentException("Count of points less then 2");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
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
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private Node node = head;

            public boolean hasNext() {
                return (node != null);
            }

            @Override
            public Point next() {
                if (hasNext()) {
                    Point point = new Point(node.x, node.y);
                    node = (node != head.prev) ? node.next : null;
                    return point;
                } else {
                    throw new NoSuchElementException();
                }
            }

        };
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

    @Override
    public void remove(int index) {
        if (count == 2) {
            throw new IllegalArgumentException("small number of tabulated values");
        }
        checkBorders(index);
        Node executed = getNode(index);
        if (executed == head) {
            head.next = head;
        }
        count--;
        executed.prev.next = executed.next;
        executed.next.prev = executed.prev;
    }

    @Override
    public int getCount() {
        return count;
    }

    private Node getNode(int index) {
        checkBorders(index);
        Node argument = head;
        for (int i = 0; i < index; i++) {
            argument = argument.next;
            if (argument == head) {
                throw new IllegalArgumentException();
            }
        }
        return argument;
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

        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {

        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < head.x || x > head.prev.x) {
            throw new InterpolationException("X is out of bounds of interpolation");
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
        checkBorders(index);
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

    private void checkBorders(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Invalid index");
        }
    }
}

