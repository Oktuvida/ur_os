/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.paging;

import ur_os.memory.MemoryAddress;
import ur_os.process.ProcessMemoryManager;
import ur_os.process.ProcessMemoryManagerType;

/**
 *
 * @author super
 */
public class PMM_Paging extends ProcessMemoryManager{
    
    PageTable pt;

    public PMM_Paging(int processSize) {
        super(ProcessMemoryManagerType.PAGING,processSize);
        pt = new PageTable(processSize);
    }

    public PMM_Paging(PageTable pt) {
        this.pt = pt;
    }
    
    public PMM_Paging(PMM_Paging pmm) {
        super(pmm);
        if(pmm.getType() == this.getType()){
            this.pt = new PageTable(pmm.getPt());
        }else{
            System.out.println("Error - Wrong PMM parameter");
        }
    }

    public PageTable getPt() {
        return pt;
    }
    
    public void addFrameID(int frame){
        pt.addFrameID(frame);
    }
    
    public MemoryAddress getPageMemoryAddressFromLocalAddress(int locAdd){
        int page = locAdd / PageTable.getPageSize();
        int offset = locAdd % PageTable.getPageSize();

        MemoryAddress ma = new MemoryAddress(page, offset);
        return ma;
    }
    
    public MemoryAddress getFrameMemoryAddressFromLogicalMemoryAddress(MemoryAddress m){
        int frame = pt.getFrameIdFromPage(m.getDivision());
        MemoryAddress ma = new MemoryAddress(frame, m.getOffset());
        return ma;
    }
    
   @Override
    public int getPhysicalAddress(int logicalAddress){
        MemoryAddress ma1 = getPageMemoryAddressFromLocalAddress(logicalAddress);
        MemoryAddress ma2 = getFrameMemoryAddressFromLogicalMemoryAddress(ma1);
        MemoryAddress ma3 = new MemoryAddress(ma2.getDivision() * PageTable.getPageSize(), ma2.getOffset());
        System.out.println("Accessing Page " + ma1.getDivision() + " and offset " + ma1.getOffset());
        return ma3.getAddress();
    }
    
     @Override
    public String toString(){
        return pt.toString();
    }
    
}
