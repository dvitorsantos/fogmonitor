package lsdi.Threads;

import com.sun.management.OperatingSystemMXBean;
import lsdi.Models.ContextData;
import lsdi.Models.Performace;
import lsdi.Publishers.ContextDataPublisher;

public class ContextMonitorThread extends Thread {
    private static final long MEGABYTE = 1024L * 1024L;
    private final OperatingSystemMXBean osBean;
    private ContextDataPublisher publisher;
    private String hostUuid = System.getenv("HOST_UUID");
    public ContextMonitorThread() {
        osBean = (OperatingSystemMXBean) java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        publisher = new ContextDataPublisher();
    }

    @Override
    public void run() {
        while (true) {
            Performace performace = getPerformace();
            ContextData contextData = new ContextData();
            contextData.setHostUuid(hostUuid == null ? "UNKNOWN" : hostUuid);
            contextData.setHostLevel("FOG");
            contextData.setPerformace(performace);
            publisher.publishContextData(contextData);
        }
    }

    public Performace getPerformace() {
        long memory = osBean.getFreeMemorySize() / MEGABYTE;
        double cpu = osBean.getCpuLoad() * 100;

        return new Performace(cpu, memory, 0);
    }
}
