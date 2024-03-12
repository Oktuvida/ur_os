/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

/**
 *
 * @author prestamour
 */
public class RoundRobin extends Scheduler{

    int q;
    int cont;
    
    RoundRobin(OS os){
        super(os);
        q = 5;
        cont=0;
    }
    
    RoundRobin(OS os, int q){
        this(os);
        this.q = q;
    }
    
   
    @Override
    public void getNext(boolean cpuEmpty) {
        if (!cpuEmpty) {
            cont += 1;
            if (cont > q) {
                Process p = null;
                if (!processes.isEmpty()) {
                    p = processes.remove();
                }
                os.interrupt(InterruptType.SCHEDULER_CPU_TO_RQ, p);
                cont = 1;
            }
        } else if (!processes.isEmpty()) {
            Process p = processes.remove();
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, p);
            cont = 1;
        }
    }
    
    @Override
    public void newProcess(boolean cpuEmpty) {} //It's empty because it is Non-preemptive

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //It's empty because it is Non-preemptive
    
}
