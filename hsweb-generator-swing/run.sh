#!/usr/bin/env bash
if [ ! -f "bin/hsweb-generator.run.jar" ]; then
     echo "请先运行build.sh"
     else
     cd bin
     java -cp hsweb-generator.run.jar org.hsweb.generator.swing.SwingGeneratorApplication
fi