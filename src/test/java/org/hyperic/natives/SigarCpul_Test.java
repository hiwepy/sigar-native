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


import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarFactory;
import org.hyperic.sigar.SigarProxy;  

/** 
* 借助Sigar API获取CPU相关信息 
*  
* @author GaoHuanjie  
*/  
public class SigarCpul_Test {  

  public static void main(String[] args) throws SigarException {  
	  
	  SigarProxy sigar = SigarFactory.newSigar();

      CpuInfo info = sigar.getCpuInfoList()[0];  
      System.out.println("CpuInfo mhz：" + info.getMhz());  
      System.out.println("CpuInfo vendor：" + info.getVendor());  
      System.out.println("CpuInfo Model：" + info.getModel());  
      System.out.println("CpuInfo cacheSize：" + info.getCacheSize());  
      System.out.println("CpuInfo totalCores：" + info.getTotalCores());  
      System.out.println("CpuInfo totalSockets：" + info.getTotalSockets());  
      System.out.println("CpuInfo coresPerSocket：" + info.getCoresPerSocket());  

      Cpu cpu = sigar.getCpu();  
      System.err.println("CPU user：" + cpu.getUser());  
      System.err.println("CPU sys：" + cpu.getSys());  
      System.err.println("CPU nice：" + cpu.getNice());  
      System.err.println("CPU idle：" + cpu.getIdle());  
      System.err.println("CPU wait：" + cpu.getWait());  
      System.err.println("CPU irq：" + cpu.getIrq());  
      System.err.println("CPU total：" + cpu.getTotal());  
      System.err.println("CPU stolen：" + cpu.getStolen());  
      System.err.println("CPU softIrq：" + cpu.getSoftIrq());  

      CpuPerc cpuPerc = sigar.getCpuPerc();  
      System.out.println("CpuPerc user：" + cpuPerc.getUser());  
      System.out.println("CpuPerc sys：" + cpuPerc.getSys());  
      System.out.println("CpuPerc nice：" + cpuPerc.getNice());  
      System.out.println("CpuPerc idle：" + cpuPerc.getIdle());  
      System.out.println("CpuPerc wait：" + cpuPerc.getWait());  
      System.out.println("CpuPerc stolen：" + cpuPerc.getStolen());  
      System.out.println("CpuPerc softIrq：" + cpuPerc.getSoftIrq());  
      System.out.println("CpuPerc combined：" + cpuPerc.getCombined());  
  }  
}  