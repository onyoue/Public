/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kdtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.vecmath.Vector2f;

/**
 *
 * @reference "Realistic Image Synthesis Using Photon Mapping" p.71
 */
public class KDTree2Heap {

    enum Flag {
        LEAF,
        X,
        Y,
        Z,
    }

    class AABB {
        public Vector2f min;
        public Vector2f max;
    }

    class KDTNode {
        public int pointIndex;
        public Flag flag = Flag.LEAF;

    }

    private AABB computeBoundingBox(int start, int end) {

        Vector2f min = new Vector2f();
        Vector2f max = new Vector2f();
        min.x = Float.MAX_VALUE;
        min.y = Float.MAX_VALUE;
        max.x = -Float.MAX_VALUE;
        max.y = -Float.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            Vector2f v = _points[_pointIndices[i]];
            min.x = Math.min(min.x, v.x);
            min.y = Math.min(min.y, v.y);
            max.x = Math.max(max.x, v.x);
            max.y = Math.max(max.y, v.y);
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
        Vector2f range = new Vector2f();
        range.sub(aabb.max, aabb.min);
        Flag axis = (range.x > range.y) ? Flag.X : Flag.Y;

        if (axis == Flag.X) {
            Arrays.sort(_pointIndices, start, end+1, new Comparator<Integer>() {
                public int compare(Integer t, Integer t1) {
                    Vector2f v = _points[t];
                    Vector2f v1 = _points[t1];
                    return (v.x > v1.x) ? 1 : -1;
                }
            });
        } else {
            Arrays.sort(_pointIndices, start, end+1, new Comparator<Integer>() {
                public int compare(Integer t, Integer t1) {
                    Vector2f v = _points[t];
                    Vector2f v1 = _points[t1];
                    return (v.y > v1.y) ? 1 : -1;
                }
            });            
        }

        int mid = start + (end - start + 1) / 2;
        KDTNode node = new KDTNode();
        node.pointIndex = _pointIndices[mid];
        node.flag = axis;
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

    public void generate(Vector2f[] points) {

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
        Vector2f point = _points[node.pointIndex];
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

    public void find(Vector2f target, float distance) {
        _d2 = distance * distance;
        _target = target;
        _result.clear();
        find(1);
    }

    public void dump(int nodeIndex, String indent) {

        KDTNode node = _nodes[nodeIndex];
        Vector2f point = _points[node.pointIndex];
        Flag flag = node.flag;

        System.out.println(indent + Float.toString(point.x) + " " + Float.toString(point.y));
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

    public List<Integer> getResult() {
        return _result;
    }

    private Vector2f[] _points;
    private Integer[] _pointIndices;
    private KDTNode[] _nodes;

    private float _d2 = 0;
    private Vector2f _target;
    private List<Integer> _result = new ArrayList<Integer>();

    private Vector2f tmp = new Vector2f();

    public static void main(String[] args) {
        
        Vector2f[] points = new Vector2f[10];
        points[0] = new Vector2f(3, 5);
        points[1] = new Vector2f(1, 2);
        points[2] = new Vector2f(5, 1);
        points[3] = new Vector2f(2, 3);
        points[4] = new Vector2f(4, 4);
        points[5] = new Vector2f(7, 8);
        points[6] = new Vector2f(1, 9);
        points[7] = new Vector2f(6, 3);
        points[8] = new Vector2f(10, 3);
        points[9] = new Vector2f(10, 5);

        KDTree2Heap kdtree = new KDTree2Heap();
        kdtree.generate(points);
        kdtree.dump(1, "");

        Vector2f x = new Vector2f();
        x.x = 5;
        x.y = 3;
        kdtree.find(x, 1.5f);

        System.out.println("find target = " + Float.toString(x.x) + " " + Float.toString(x.y));
        System.out.println("result::::");
        List<Integer> result = kdtree.getResult();
        for (Integer i : result) {
            Vector2f v = points[i];
            System.out.println(Float.toString(v.x) + " " + Float.toString(v.y));
        }
    }
}
