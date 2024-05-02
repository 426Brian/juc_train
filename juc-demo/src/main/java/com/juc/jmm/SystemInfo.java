package com.juc.jmm;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Classname: SystemInfo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-12:22
 * Version: v1.0
 */
public class SystemInfo {
    public static void main(String[] args) {
        // 获取操作系统MXBean
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

        // 获取CPU的核心数
        int cpuCores = osBean.getAvailableProcessors();
        System.out.println("CPU cores: " + cpuCores);

        // 获取线程MXBean
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        // 获取线程的详细信息（如果需要）
        ThreadInfo[] threadInfos = threadBean.dumpAllThreads(false, false);
        int count = 0;
        // 打印所有线程的信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(++count + ". Thread ID: " + threadInfo.getThreadId() + " - Thread Name: " + threadInfo.getThreadName());
        }
    }
}
