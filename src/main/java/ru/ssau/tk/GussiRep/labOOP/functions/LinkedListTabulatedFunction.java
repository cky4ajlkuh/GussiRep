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

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        this.count = count;
        double step = (xTo - xFrom) / (count - 1);
        if (xFrom < xTo) {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
                xFrom += step;
            }
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    private Node getNode(int index) {
        Node argument;
        if (index > (count / 2.)) {
            argument = last;
            for (int i = count; i > 0; i--) {
                if (i == index) {
                    return argument;
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
            return 0;
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
        if (head.x == last.x) {
            return head.y;
        }
        return head.y + (head.next.y - head.y) / (x - head.x) * (head.next.x - head.x);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (head.x == last.x) {
            return head.y;
        }
        return last.prev.y + (x - last.prev.x) * (last.y - last.prev.y) / (last.x - last.prev.x);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (head.x == last.x) {
            return head.y;
        }
        Node left = getNode(floorIndex);
        Node right = left.next;
        return left.y + (right.y - left.y) / (right.x - left.x) * (x - left.x);
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
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
        return last.x;
    }
}

