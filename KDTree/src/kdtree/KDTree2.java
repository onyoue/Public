/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kdtree;

import java.util.Arrays;
import java.util.Comparator;
import javax.vecmath.Vector2f;

/**
 *
 * @author onyoue
 */
public class KDTree2 {

    enum Axis {
        NONE,
        X,
        Y,
        Z,
    }

    static class AABB {
        public Vector2f min;
        public Vector2f max;
    }

    static class KDTNode {
        public Vector2f point;
        public Axis axis = Axis.NONE;

        public KDTNode left;
        public KDTNode right;

        public void dump(String indent) {
            System.out.println(indent + Float.toString(point.x) + " " + Float.toString(point.y));
            System.out.println(indent + "axis : " + axis.toString());
            if (left != null) {
                System.out.println(indent + "left:");
                left.dump(indent + "  ");
            }
            if (right != null) {
                System.out.println(indent + "right:");
                right.dump(indent + "  ");
            }
        }
    }

    private static AABB computeBoundingBox(Vector2f[] points, int start, int end) {

        Vector2f min = new Vector2f();
        Vector2f max = new Vector2f();
        min.x = Float.MAX_VALUE;
        min.y = Float.MAX_VALUE;
        max.x = -Float.MAX_VALUE;
        max.y = -Float.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            Vector2f v = points[i];
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

    // algorithm
    // kdtree* balance( points ) {
    //  Find the cube surrounding the points
    //  select dimension dim in which the cube is largest
    //  find median of the points in dim
    //  s1 = all points below median
    //  s2 = all points above median
    //  node = median
    //  node.left = balance(s1)
    //  node.right = balance(s2)
    //  return node;

    public static KDTNode generate(Vector2f[] points, int start, int end) {

        AABB aabb = computeBoundingBox(points, start, end);
        Vector2f range = new Vector2f();
        range.sub(aabb.max, aabb.min);
        Axis axis = (range.x > range.y) ? Axis.X : Axis.Y;

        if (axis == Axis.X) {
            Arrays.sort(points, start, end+1, new Comparator<Vector2f>() {
                public int compare(Vector2f t, Vector2f t1) {
                    return (t.x > t1.x) ? 1 : -1;
                }
            });
        } else {
            Arrays.sort(points, start, end+1, new Comparator<Vector2f>() {
                public int compare(Vector2f t, Vector2f t1) {
                    return (t.y > t1.y) ? 1 : -1;
                }
            });            
        }

        int mid = start + (end - start + 1) / 2;
        KDTNode node = new KDTNode();
        node.point = points[mid];
        node.axis = axis;

        if (mid != start) {
            if (start == mid - 1) {
                KDTNode left = new KDTNode();
                left.point = points[start];
                node.left = left;
            } else {
                node.left = generate(points, start, mid-1);
            }
        }

        if (mid != end) {
            if (mid+1 == end) {
                KDTNode right = new KDTNode();
                right.point = points[end];
                node.right = right;
            } else {
                node.right = generate(points, mid+1, end);
            }
        }

        return node;
    }

    public static KDTNode generate(Vector2f[] points) {
        return generate(points, 0, points.length-1);
    }

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

        KDTNode root = generate(points);
        root.dump("");

//        Arrays.sort(points, new Comparator<Vector2f>() {
//
//            public int compare(Vector2f t, Vector2f t1) {
//                return (t.x > t1.x) ? 1 : -1;
//            }
//        });
//
//        for (Vector2f v : points) {
//            System.out.println(Float.toString(v.x) + " " + Float.toString(v.y));
//        }
//
//        Arrays.sort(points, new Comparator<Vector2f>() {
//
//            public int compare(Vector2f t, Vector2f t1) {
//                return (t.y > t1.y) ? 1 : -1;
//            }
//        });
//
//        for (Vector2f v : points) {
//            System.out.println(Float.toString(v.x) + " " + Float.toString(v.y));
//        }
        
    }

}
