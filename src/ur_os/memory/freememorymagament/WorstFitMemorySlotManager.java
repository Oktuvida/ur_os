/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

/**
 *
 * @author super
 */
public class WorstFitMemorySlotManager extends FreeMemorySlotManager{
    
    @Override
    public MemorySlot getSlot(int size) {
        MemorySlot m = null;
        for (MemorySlot ms : list) {
            if (ms.getSize() >= size && (m == null || m.getSize() < ms.getSize())) {
                m = ms;
            }
        }
        //if (m != null) {
        //    m.setSize(m.getSize() - size);
        //}
        return m;
    }
    
}
