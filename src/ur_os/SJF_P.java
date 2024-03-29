/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

/**
 *
 * @author prestamour
 */
public class SJF_P extends Scheduler {

    SJF_P(OS os) {
        super(os);
    }

    @Override
    public void newProcess(boolean cpuEmpty) {// When a NEW process enters the queue, process in CPU, if any, is
                                              // extracted to compete with the rest
        if (!cpuEmpty) {
            os.interrupt(InterruptType.SCHEDULER_CPU_TO_RQ, null);
        }
    }

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {// When a process return from IO and enters the queue, process in
                                                      // CPU, if any, is extracted to compete with the rest
        if (!cpuEmpty) {
            os.interrupt(InterruptType.SCHEDULER_CPU_TO_RQ, null);
        }
    }

    @Override
    public void getNext(boolean cpuEmpty) {
        if (!processes.isEmpty() && cpuEmpty) {

            Process p = processes.get(0);
            Process tmp_p;
            for (int i = 1; i < processes.size(); i++) {
                tmp_p = processes.get(i);
                if (tmp_p.getRemainingTimeInCurrentBurst() < p.getRemainingTimeInCurrentBurst()) {
                    p = tmp_p;
                } else if (tmp_p.getRemainingTimeInCurrentBurst() == p.getRemainingTimeInCurrentBurst() && tmp_p.getPid() < p.getPid()) {
                    p = tmp_p;
                }
            }
            processes.remove(p);
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, p);
        }
    }
}
