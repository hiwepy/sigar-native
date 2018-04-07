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

/**
 * Sigar（全称System Information Gatherer And Reporter，即系统信息收集报表器），它提供了一个开源的跨平台的收集计算机硬件和操作系统信息的API（该API底层接口用C语言编写），本文将演示如何借助Sigar API获取网络信息：
 */
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarFactory;  
  
/** 
 * 借助Sigar API获取网络信息 
 *  
 * @author GaoHuanjie 
 */  
public class SigarNetwork_Test {  
  
    public static void main(String[] args) throws SigarException {  
  
    	Sigar sigar = (Sigar) SigarFactory.newSigar();
  
        // 当前机器的正式域名  
        try {  
            System.out.println(sigar.getFQDN());// 即Fully Qualified Domain Name，中文为：全称域名  
        } catch (SigarException e) {  
            e.printStackTrace();  
        }  
  
        String[] netInterfaceList = sigar.getNetInterfaceList();  
  
        // 获取网络流量信息  
        for (int i = 0; i < netInterfaceList.length; i++) {  
            String netInterface = netInterfaceList[i];// 网络接口  
            System.out.println("netInterface：" + netInterface);  
            NetInterfaceConfig netInterfaceConfig = sigar.getNetInterfaceConfig(netInterface);  
            System.out.println("Address = " + netInterfaceConfig.getAddress());// IP地址  
            System.out.println("Netmask = " + netInterfaceConfig.getNetmask());// 子网掩码  
            if ((netInterfaceConfig.getFlags() & 1L) <= 0L) {// 网络装置是否正常启用  
                System.out.println("!IFF_UP...skipping getNetInterfaceStat");  
                continue;  
            }  
            NetInterfaceStat netInterfaceStat = sigar.getNetInterfaceStat(netInterface);  
            System.out.println("netInterfaceStat rxPackets：" + netInterfaceStat.getRxPackets());// 接收的总包裹数  
            System.out.println("netInterfaceStat txPackets：" + netInterfaceStat.getTxPackets());// 发送的总包裹数  
            System.out.println("netInterfaceStat rxBytes：" + netInterfaceStat.getRxBytes());// 接收到的总字节数  
            System.out.println("netInterfaceStat txBytes：" + netInterfaceStat.getTxBytes());// 发送的总字节数  
            System.out.println("netInterfaceStat rxErrors：" + netInterfaceStat.getRxErrors());// 接收到的错误包数  
            System.out.println("netInterfaceStat txErrors：" + netInterfaceStat.getTxErrors());// 发送数据包时的错误数  
            System.out.println("netInterfaceStat rxDropped：" + netInterfaceStat.getRxDropped());// 接收时丢弃的包数  
            System.out.println("netInterfaceStat txDropped：" + netInterfaceStat.getTxDropped());// 发送时丢弃的包数  
            System.out.println("netInterfaceStat rxOverruns：" + netInterfaceStat.getRxOverruns());  
            System.out.println("netInterfaceStat txOverruns：" + netInterfaceStat.getTxOverruns());  
            System.out.println("netInterfaceStat rxFrame：" + netInterfaceStat.getRxFrame());  
            System.out.println("netInterfaceStat txCollisions：" + netInterfaceStat.getTxCollisions());  
            System.out.println("netInterfaceStat txCarrier：" + netInterfaceStat.getTxCarrier());  
            System.out.println("netInterfaceStat speed：" + netInterfaceStat.getSpeed());  
        }  
  
        // 一些其它的信息  
        for (int i = 0; i < netInterfaceList.length; i++) {  
            String netInterface = netInterfaceList[i];// 网络接口  
            NetInterfaceConfig netInterfaceConfig = sigar.getNetInterfaceConfig(netInterface);  
            if (NetFlags.LOOPBACK_ADDRESS.equals(netInterfaceConfig.getAddress())  
                || (netInterfaceConfig.getFlags() & NetFlags.IFF_LOOPBACK) != 0  
                || NetFlags.NULL_HWADDR.equals(netInterfaceConfig.getHwaddr())) {  
                continue;  
            }  
  
            System.out.println("netInterfaceConfig name：" + netInterfaceConfig.getName());  
            System.out.println("netInterfaceConfig hwaddr：" + netInterfaceConfig.getHwaddr());// 网卡MAC地址  
            System.out.println("netInterfaceConfig type:" + netInterfaceConfig.getType());  
            System.out.println("netInterfaceConfig description：" + netInterfaceConfig.getDescription());// 网卡描述信息  
            System.out.println("netInterfaceConfig address：" + netInterfaceConfig.getAddress());// IP地址  
            System.out.println("netInterfaceConfig destination：" + netInterfaceConfig.getDestination());  
            System.out.println("netInterfaceConfig broadcast：" + netInterfaceConfig.getBroadcast());// 网关广播地址  
            System.out.println("netInterfaceConfig netmask：" + netInterfaceConfig.getNetmask());// 子网掩码  
            System.out.println("netInterfaceConfig flags：" + netInterfaceConfig.getFlags());  
            System.out.println("netInterfaceConfig mtu：" + netInterfaceConfig.getMtu());  
            System.out.println("netInterfaceConfig metric：" + netInterfaceConfig.getMetric());  
        }  
        sigar.close();  
    }  
}  