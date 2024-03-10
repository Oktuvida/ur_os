/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

/**
 *
 * @author prestamour
 */
public class SJF_NP extends Scheduler {

    SJF_NP(OS os) {
        super(os);
    }

    @Override
    public void getNext(boolean cpuEmpty) {
        if (!processes.isEmpty() && cpuEmpty) {

            Process p = processes.get(0);
            for (int i = 1; i < processes.size(); i++) {
                if (processes.get(i).getRemainingTimeInCurrentBurst() < p.getRemainingTimeInCurrentBurst()) {
                    p = processes.get(i);
                }
            }
            processes.remove(p);
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, p);
        }
    }

    @Override
    public void newProcess(boolean cpuEmpty) {
    } // It's empty because it is Non-preemptive

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {
    } // It's empty because it is Non-preemptive

}
