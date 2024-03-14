package ur_os;

import java.util.ArrayList;
import java.util.Arrays;

public class MFQ extends Scheduler {
    ArrayList<Scheduler> schedulers;
    int currentScheduler;

    public MFQ(OS os) {
        super(os);
        schedulers = new ArrayList<>();
        currentScheduler = -1;
    }

    public MFQ(OS os, Scheduler... schedulers) {
        this(os);
        this.schedulers.addAll(Arrays.asList(schedulers));
        if (this.schedulers.size() > 0) {
            currentScheduler = 0;
        }
    }

    @Override
    public void getNext(boolean cpuEmpty) {
        if (cpuEmpty) {
            defineCurrentScheduler();
        }
        schedulers.get(currentScheduler).getNext(cpuEmpty);
        if (os.isCPUEmpty()) {
            defineCurrentScheduler();
            schedulers.get(currentScheduler).getNext(true);
        }
    }

    void defineCurrentScheduler() {
        for (int i = 0; i < schedulers.size(); i++) {
            Scheduler s = schedulers.get(i);
            if (!s.isEmpty()) {
                currentScheduler = i;
                return;
            }
        }
    }

    @Override
    public void addProcess(Process p) {
        ProcessState ps = p.getState();
        int idx = p.getCurrentScheduler();
        if (ps == ProcessState.CPU) {
            idx = Math.min(idx + 1, schedulers.size() - 1);
            p.setCurrentScheduler(idx);
        } 
        else if (ps == ProcessState.IO) {
            idx = 0;
            p.setCurrentScheduler(idx);
        }
        schedulers.get(idx).addProcess(p);
    }

    @Override
    public void newProcess(boolean cpuEmpty) {
    }

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {
    }
}
