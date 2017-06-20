/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
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
package org.hyperic.sigar;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hyperic.jni.ArchNotSupportedException;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarLoader;
import org.hyperic.sigar.SigarProxy;

public class SigarFactory {
	
    private static final String SIGAR_LIB_DIR = "sigar-1.6.5.132-6";
    
    private static final String SIGAR_X86_WINNT_DLL = "sigar-x86-winnt.dll";
    private static final String SIGAR_X86_WINNT_LIB = "sigar-x86-winnt.lib";

    public static SigarProxy newSigar() {
        return Loader.newSigar();
    }
    
    private static class Loader {
        static {
            LibraryExtractor libExtractor = new LibraryExtractor(SIGAR_LIB_DIR);
            
            try {
                for (String libFile : getSigarLibFiles()) {
                    libExtractor.extractFile(libFile);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
            System.setProperty("org.hyperic.sigar.path", libExtractor.getLibPath());
        }
        
        public static SigarProxy newSigar() {
            return new Sigar();
        }
    }
    
    private static List<String> getSigarLibFiles() {
        try {
            String libName = new SigarLoader(SigarProxy.class).getLibraryName();
            
            if (SIGAR_X86_WINNT_DLL.equals(libName)) {
                return Arrays.asList(SIGAR_X86_WINNT_DLL, SIGAR_X86_WINNT_LIB);
            } else {
                return Collections.singletonList(libName);
            }
        } catch (ArchNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
