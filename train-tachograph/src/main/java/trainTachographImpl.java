import com.google.common.collect.*;

import java.time.LocalDateTime;

public class trainTachographImpl {

    private Table<LocalDateTime, Integer, Integer> t_table;

    public void TrainTachograph(){
        t_table= HashBasedTable.create();
    }

    public void add(Integer joy_pos, Integer ref_spe){
        t_table.put(LocalDateTime.now(), joy_pos, ref_spe);
    }

    public boolean isEmpty()  {
        return t_table.isEmpty();
    }


}