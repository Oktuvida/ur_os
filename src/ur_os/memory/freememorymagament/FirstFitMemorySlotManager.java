/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

import ur_os.process.Process;

/**
 *
 * @author super
 */
public class FirstFitMemorySlotManager extends FreeMemorySlotManager{

    @Override
    public MemorySlot getSlot(int size) {
        //m is the slot that the method will return to be assigned to the process.
        //The original selected slot needs to be updated with the amount of memory that was taken from it to be assigned to the process.
        //Example: if the selected slot had 190 bytes and the process requested 120 bytes, then m will be a slot of size 120 with a certain base,
        //and the original slot will have the 70 remaining bytes, with either the base or the size modified.
        //Note: Think of what will happen if the process request fits perfectly on a memory slot.

        MemorySlot m = null;
        for (MemorySlot ms : list) {
            if (ms.getSize() >= size) {
                ms.setSize(ms.getSize() - size);
                m = new MemorySlot(ms.getBase(), size);
                ms.setBase(ms.getBase() + size);
                if (ms.size == 0) {
                    list.remove(ms);
                }
                break;
            }
        }
        return m;
    }

    
    
}
