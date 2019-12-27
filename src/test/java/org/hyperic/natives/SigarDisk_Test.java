/*
 * Copyright (c) 2018 (https://github.com/hiwepy).
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


import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;

import kamon.sigar.SigarProvisioner;  

/** 
 * 
 * Sigar（全称System Information Gatherer And Reporter，即系统信息收集报表器），它提供了一个开源的跨平台的收集计算机硬件和操作系统信息的API（该API底层接口用C语言编写），本文将演示如何借助Sigar API获取磁盘信息：
 * 
* 借助Sigar API获取磁盘信息 
*  
* @author GaoHuanjie  
*/  
public class SigarDisk_Test {  

  public static void main(String[] args) throws Exception {  
	  
	  SigarProvisioner.provision();
	  SigarProxy sigar = new Sigar();

      FileSystem [] fileSystemArray = sigar.getFileSystemList();  
      for ( FileSystem fileSystem:fileSystemArray ) {  
          System.out.println("fileSystem dirName：" + fileSystem.getDirName());//分区的盘符名称   
          System.out.println("fileSystem devName：" + fileSystem.getDevName());//分区的盘符名称   
          System.out.println("fileSystem typeName：" + fileSystem.getTypeName());// 文件系统类型名，比如本地硬盘、光驱、网络文件系统等    
          System.out.println("fileSystem sysTypeName：" + fileSystem.getSysTypeName());//文件系统类型，比如 FAT32、NTFS  
          System.out.println("fileSystem options：" + fileSystem.getOptions());  
          System.out.println("fileSystem flags：" + fileSystem.getFlags());  
          System.out.println("fileSystem type：" + fileSystem.getType());  
            
          FileSystemUsage fileSystemUsage = null;  
            
          try {  
              fileSystemUsage = sigar.getFileSystemUsage(fileSystem.getDirName());  
          } catch (SigarException e) {//当fileSystem.getType()为5时会出现该异常——此时文件系统类型为光驱  
              continue;  
          }  
          System.out.println("fileSystemUsage total：" + fileSystemUsage.getTotal() + "KB");// 文件系统总大小  
          System.out.println("fileSystemUsage free：" + fileSystemUsage.getFree() + "KB");// 文件系统剩余大小   
          System.out.println("fileSystemUsage used：" + fileSystemUsage.getUsed() + "KB");// 文件系统已使用大小  
          System.out.println("fileSystemUsage avail：" + fileSystemUsage.getAvail() + "KB");// 文件系统可用大小  
          System.out.println("fileSystemUsage files：" + fileSystemUsage.getFiles());  
          System.out.println("fileSystemUsage freeFiles：" + fileSystemUsage.getFreeFiles());  
          System.out.println("fileSystemUsage diskReadBytes：" + fileSystemUsage.getDiskReadBytes());  
          System.out.println("fileSystemUsage diskWriteBytes：" + fileSystemUsage.getDiskWriteBytes());  
          System.out.println("fileSystemUsage diskQueue：" + fileSystemUsage.getDiskQueue());  
          System.out.println("fileSystemUsage diskServiceTime：" + fileSystemUsage.getDiskServiceTime());  
          System.out.println("fileSystemUsage usePercent：" + fileSystemUsage.getUsePercent() * 100 + "%");// 文件系统资源的利用率    
          System.out.println("fileSystemUsage diskReads：" + fileSystemUsage.getDiskReads());  
          System.out.println("fileSystemUsage diskWrites：" + fileSystemUsage.getDiskWrites());  
          System.err.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");  
      }  
  }  
}  