package com.juc.jmm;

import com.juc.jmm.entities.Customer;
import org.openjdk.jol.info.ClassLayout;

/**
 * Classname: JOLDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/4-13:30
 * Version: v1.0
 */
public class JOLDemo {
    public static void main(String[] args) {
        // JVM 详细细节
//        System.out.println(VM.current().details());
        // 所有对象分配空间都是 8 字节的整数倍
//        System.out.println(VM.current().objectAlignment());

        Object object = new Object();
        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
              /*   -XX:ConcGCThreads = 3
                - XX:G1ConcRefinementThreads = 13
                - XX:GCDrainStackTargetSize = 64
                - XX:InitialHeapSize = 267583488
                - XX:MarkStackSize = 4194304
                - XX:MaxHeapSize = 4281335808
                - XX:MinHeapSize = 6815736
                - XX:+PrintCommandLineFlags
                - XX:ReservedCodeCacheSize = 251658240
                - XX:+SegmentedCodeCache
                - XX:+UseCompressedClassPointers
                - XX:+UseCompressedOops
                - XX:+UseG1GC
                - XX:-UseLargePagesIndividualAllocation*/
    }
}
