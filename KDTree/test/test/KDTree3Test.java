/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.List;
import javax.vecmath.Vector3f;
import kdtree.KDTree3;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author onyoue
 */
public class KDTree3Test {

    public KDTree3Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {

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

        System.out.println("find target = " + KDTree3.vector3fToString(p));
        System.out.println("result::::");
        Vector3f a = new Vector3f();
        for (Integer i : result) {
            Vector3f v = points[i];
            a.sub(v, p);
            float d = a.length();
            System.out.println(KDTree3.vector3fToString(v) + " | dist : " + Float.toString(d));
        }

        // brute force test
        System.out.println("brute force result::::");
        for (Vector3f v : points) {
            a.sub(v, p);
            float d = a.length();
            if (d < dd) {
                System.out.println(KDTree3.vector3fToString(v) + " | dist : " + Float.toString(d));
            }
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
