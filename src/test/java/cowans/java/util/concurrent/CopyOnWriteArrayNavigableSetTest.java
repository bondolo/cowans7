/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cowans.java.util.concurrent;

import java.util.concurrent.CopyOnWriteArrayNavigableSet;
import org.junit.Test;

/**
 *
 * @author mikeduigou
 */
public class CopyOnWriteArrayNavigableSetTest {


    @Test
    public void basic() {
        System.out.println("CopyOnWriteArrayNavigableSet");
        CopyOnWriteArrayNavigableSet<String> cowans = new CopyOnWriteArrayNavigableSet<>();

        cowans.add("foo");
        System.out.println(cowans);
    }
}
