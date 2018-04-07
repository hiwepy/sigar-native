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

import java.net.InetAddress;

import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarFactory;
import org.hyperic.sigar.Who;

import junit.framework.TestCase;  
  
/** 
 * 借助Sigar API获取操作系统信息 
 * @author GaoHuanjie  
 */  
public class SigarOS_Test extends TestCase {  
  
    public void testOS() throws SigarException {  
    	
    	Sigar sigar = (Sigar) SigarFactory.newSigar();
  
        // 取到当前操作系统的名称    
        String hostname = "";  
        try {  
            hostname = InetAddress.getLocalHost().getHostName();  
        } catch (Exception e) {  
            hostname = sigar.getNetInfo().getHostName();  
        }  
        System.out.println(hostname);  
  
        // 获取当前操作系统的信息    
        OperatingSystem operatingSystem = OperatingSystem.getInstance();  
        System.out.println("operatingSystem arch：" + operatingSystem.getArch());// 操作系统内核类型如： 386、486、586等x86  
        System.out.println("operatingSystem cpuEndian：" + operatingSystem.getCpuEndian());//  
        System.out.println("operatingSystem dataModel：" + operatingSystem.getDataModel());//  
        System.out.println("operatingSystem description：" + operatingSystem.getDescription());// 系统描述  
        System.out.println("operatingSystem machine：" + operatingSystem.getMachine());//    
        System.out.println("operatingSystem name：" + operatingSystem.getName());// 操作系统类型  
        System.out.println("operatingSystem patchLevel：" + operatingSystem.getPatchLevel());// 操作系统补丁级别  
        System.out.println("operatingSystem vendor：" + operatingSystem.getVendor());// 操作系统供应商   
        System.out.println("operatingSystem vendorCodeName：" + operatingSystem.getVendorCodeName());// 供应商编码名  
        System.out.println("operatingSystem vendorName：" + operatingSystem.getVendorName());// 操作系统供应商名称   
        System.out.println("operatingSystem vendorVersion：" + operatingSystem.getVendorVersion());// 操作系统供应商版本    
        System.out.println("operatingSystem version：" + operatingSystem.getVersion());// 操作系统的版本号  
  
        // 获取当前系统进程表中的用户信息    
        Who whoArray [] = sigar.getWhoList();  
        if (whoArray != null) {  
            for (int i = 0; i < whoArray.length; i++) {  
                Who who = whoArray[i];  
                System.out.println("\n~~~~~~~~~" + i + "~~~~~~~~~~~~");  
                System.out.println("who device：" + who.getDevice());  
                System.out.println("who host：" + who.getHost());  
                System.out.println("who time：" + who.getTime());  
                System.out.println("who user：" + who.getUser());// 当前系统进程表中的用户名  
            }  
        }  
        sigar.close();  
    }  
}  