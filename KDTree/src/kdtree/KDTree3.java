/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kdtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.vecmath.Vector3f;

/**
 *
 * @author onyoue
 */
public class KDTree3 {
    enum Flag {
        LEAF,
        X,
        Y,
        Z,
    }

    class AABB {
        public Vector3f min;
        public Vector3f max;
    }

    class KDTNode {
        public int pointIndex;
        public Flag flag = Flag.LEAF;

    }

    private class XComparator implements Comparator<Integer> {

        public int compare(Integer t, Integer t1) {
            Vector3f v = _points[t];
            Vector3f v1 = _points[t1];
            return (v.x > v1.x) ? 1 : -1;
        }
    }

    private class YComparator implements Comparator<Integer> {

        public int compare(Integer t, Integer t1) {
            Vector3f v = _points[t];
            Vector3f v1 = _points[t1];
            return (v.y > v1.y) ? 1 : -1;
        }
    }

    private class ZComparator implements Comparator<Integer> {

        public int compare(Integer t, Integer t1) {
            Vector3f v = _points[t];
            Vector3f v1 = _points[t1];
            return (v.z > v1.z) ? 1 : -1;
        }
    }

    public KDTree3() {
        _xComparator = new XComparator();
        _yComparator = new YComparator();
        _zComparator = new ZComparator();
    }
    
    private AABB computeBoundingBox(int start, int end) {

        Vector3f min = new Vector3f(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
        Vector3f max = new Vector3f(-Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE);
        for (int i = start; i <= end; i++) {
            Vector3f v = _points[_pointIndices[i]];
            min.x = Math.min(min.x, v.x);
            min.y = Math.min(min.y, v.y);
            min.z = Math.min(min.z, v.z);
            max.x = Math.max(max.x, v.x);
            max.y = Math.max(max.y, v.y);
            max.z = Math.max(max.z, v.z);
        }
        AABB aabb = new AABB();
        aabb.min = min;
        aabb.max = max;
        return aabb;
    }

    private static final int leftIndex(int index) {
        return 2 * index;
    }

    private static final int rightIndex(int index) {
        return 2 * index + 1;
    }

    public void generate(int nodeIndex, int start, int end) {

        AABB aabb = computeBoundingBox(start, end);
        Vector3f range = new Vector3f();
        range.sub(aabb.max, aabb.min);
        Flag flag;
        if (range.x > range.y && range.x > range.z) {
            flag = Flag.X;
            Arrays.sort(_pointIndices, start, end+1, _xComparator);
        } else if (range.y > range.z) {
            flag = Flag.Y;
            Arrays.sort(_pointIndices, start, end+1, _yComparator);
        } else {
            flag = Flag.Z;
            Arrays.sort(_pointIndices, start, end+1, _zComparator);
        }

        int mid = start + (end - start + 1) / 2;
        KDTNode node = new KDTNode();
        node.pointIndex = _pointIndices[mid];
        node.flag = flag;
        _nodes[nodeIndex] = node;

        if (mid != start) {
            if (start == mid - 1) {
                KDTNode left = new KDTNode();
                left.pointIndex = _pointIndices[start];
                _nodes[leftIndex(nodeIndex)] = left;
            } else {
                generate(leftIndex(nodeIndex), start, mid-1);
            }
        }

        if (mid != end) {
            if (mid+1 == end) {
                KDTNode right = new KDTNode();
                right.pointIndex = _pointIndices[end];
                _nodes[rightIndex(nodeIndex)] = right;
            } else {
                generate(rightIndex(nodeIndex), mid+1, end);
            }
        }
    }

    public void generate(Vector3f[] points) {

        _nodes = new KDTNode[points.length * 2]; // 適当に大きめ
        _points = points;
        _pointIndices = new Integer[_points.length];
        for (int i = 0; i < _pointIndices.length; i++) {
            _pointIndices[i] = i;
        }

        generate(1, 0, points.length-1);
    }

    private void find(int nodeIndex) {

        KDTNode node = _nodes[nodeIndex];
        if (node == null) {
            return;
        }
        Vector3f point = _points[node.pointIndex];
        if (node.flag != Flag.LEAF) {
            float delta = 0;
            if (node.flag == Flag.X) {
                delta = _target.x - point.x;
            } else {
                delta = _target.y - point.y;
            }

            if (delta < 0) {
                find(leftIndex(nodeIndex));
                if (delta*delta < _d2) {
                    find(rightIndex(nodeIndex));
                }
            } else {
                find(rightIndex(nodeIndex));
                if (delta*delta < _d2) {
                    find(leftIndex(nodeIndex));
                }
            }
        }

        tmp.sub(_target, point);
        float delta2 = tmp.lengthSquared();
        if (delta2 < _d2) {
            _result.add(node.pointIndex);
        }
    }

    public List<Integer> find(Vector3f target, float distance) {
        _d2 = distance * distance;
        _target = target;
        _result.clear();
        find(1);

        return _result;
    }

    public void dump(int nodeIndex, String indent) {

        KDTNode node = _nodes[nodeIndex];
        Vector3f point = _points[node.pointIndex];
        Flag flag = node.flag;

        System.out.println(indent + vector3fToString(point));
        System.out.println(indent + "flag : " + flag.toString());
        int left = leftIndex(nodeIndex);
        if (flag == Flag.LEAF) {
            return;
        }
        if (_nodes[left] != null) {
            System.out.println(indent + "left:");
            dump(left, indent + "  ");
        }
        int right = rightIndex(nodeIndex);
        if (_nodes[right] != null) {
            System.out.println(indent + "right:");
            dump(right, indent + "  ");
        }
    }

    public static String vector3fToString(Vector3f v) {
        return Float.toString(v.x) + " " + Float.toString(v.y) + " " + Float.toString(v.z);
    }

    private Vector3f[] _points;
    private Integer[] _pointIndices;
    private KDTNode[] _nodes;

    private float _d2 = 0;
    private Vector3f _target;
    private List<Integer> _result = new ArrayList<Integer>();

    private Vector3f tmp = new Vector3f();
    private Comparator<Integer> _xComparator;
    private Comparator<Integer> _yComparator;
    private Comparator<Integer> _zComparator;

    public static void main(String[] args) {

        Vector3f[] points = new Vector3f[10];
        points[0] = new Vector3f(3, 5, 8);
        points[1] = new Vector3f(1, 2, 9);
        points[2] = new Vector3f(5, 1, 3);
        points[3] = new Vector3f(2, 3, 4);
        points[4] = new Vector3f(4, 4, 5);
        points[5] = new Vector3f(7, 8, 1);
        points[6] = new Vector3f(1, 9, 3);
        points[7] = new Vector3f(6, 3, 3);
        points[8] = new Vector3f(10, 3, 5);
        points[9] = new Vector3f(10, 5, 6);

        KDTree3 kdtree = new KDTree3();
        kdtree.generate(points);
        kdtree.dump(1, "");

        Vector3f p = new Vector3f();
        p.x = 5;
        p.y = 3;
        p.z = 5;
        float dd = 4.0f;
        List<Integer> result = kdtree.find(p, dd);

        System.out.println("find target = " + vector3fToString(p));
        System.out.println("result::::");
        Vector3f a = new Vector3f();
        for (Integer i : result) {
            Vector3f v = points[i];
            a.sub(v, p);
            float d = a.length();
            System.out.println(vector3fToString(v) + " | dist : " + Float.toString(d));
        }

        // brute force test
        System.out.println("brute force result::::");
        for (Vector3f v : points) {
            a.sub(v, p);
            float d = a.length();
            if (d < dd) {
                System.out.println(vector3fToString(v) + " | dist : " + Float.toString(d));
            }
        }
    }
}
