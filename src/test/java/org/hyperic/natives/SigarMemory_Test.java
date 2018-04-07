/*
 * Copyright (c) 2018 (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.hyperic.natives;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarFactory;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.Swap;  
  
/** 
 * 借助Sigar API获取获取内存信息 
 *  
 * @author GaoHuanjie  
 */  
public class SigarMemory_Test {  
  
    public static void main(String[] args) throws SigarException {  
    	SigarProxy sigar = SigarFactory.newSigar();  
  
        // 物理内存信息    
        Mem mem = sigar.getMem();  
        System.out.println("mem total：" + mem.getTotal() + " B");  
        System.out.println("mem ram：" + mem.getRam() + " B");  
        System.out.println("mem used：" + mem.getUsed() + " B");  
        System.out.println("mem free：" + mem.getFree() + " B");  
        System.out.println("mem actualUsed：" + mem.getActualUsed() + " B");    
        System.out.println("mem actualFree：" + mem.getActualFree() + " B");  
        System.out.println("mem usedPercent：" + mem.getUsedPercent() + "%");  
        System.out.println("mem freePercent：" + mem.getFreePercent() + "%");  
          
        // 交换区信息    
        Swap swap = sigar.getSwap();  
        System.err.println("swap total：" + swap.getTotal() + " B");  
        System.err.println("swap used：" + swap.getUsed() + " B");  
        System.err.println("swap free：" + swap.getFree() + " B");  
        System.err.println("swap pageIn：" + swap.getPageIn());  
        System.err.println("swap pageOut：" + swap.getPageOut());  
    }  
}  