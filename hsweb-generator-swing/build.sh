#!/usr/bin/env bash
if [ ! -d "bin" ]; then
    mkdir -p bin/config
fi
if [ ! -f "bin/config/header.cfg.json" ]; then
     cp -R src/main/java/resources/* bin
fi
cd ..
touch install.log
mvn install -Dmaven.test.skip > install.log
install_result=$(cat install.log | tail -10)
if [[ $install_result =~ "BUILD SUCCESS" ]];then
      cd hsweb-generator-swing
      mvn assembly:assembly -Dmaven.test.skip > install.log
      install_result=$(cat install.log | tail -10)
      if [[ $install_result =~ "BUILD SUCCESS" ]];then
         cp target/hsweb-generator-jar-with-dependencies.jar bin/hsweb-generator.run.jar
         echo "构建成功"
         else
       cat install.log
      fi
   else
       cat install.log
fi