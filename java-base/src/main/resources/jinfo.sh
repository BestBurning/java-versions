WORKPATH=$(dirname $0)
CLASS=com.diyishuai.gc.HelloJVM

## 进入类目录
##
cd ${WORKPATH}  # 当前位置跳到脚本位置
cd ../../..
mvn clean compile
cd target/classes

## 执行class文件
##
echo "使用-XX:+PrintClassHistogram运行Java类"
java -XX:+PrintClassHistogram ${CLASS} &
CODE=$(jps -l | grep ${CLASS} | awk '{print $1}')

echo "1、使用jinfo -flag <name> <pid>来查看某个参数"
jinfo -flag PrintClassHistogram ${CODE}
echo
echo
echo "2、使用jinfo -flag [+|-]<name> <pid>来动态修改某个boolean类型参数"
jinfo -flag -PrintClassHistogram ${CODE}
jinfo -flag PrintClassHistogram ${CODE}
echo
echo
echo "3、使用jinfo -flag <name>=<value> <pid>来动态修改某个kv类型参数"
jinfo -flag CMSWaitDuration ${CODE}
jinfo -flag CMSWaitDuration=1999 ${CODE}
jinfo -flag CMSWaitDuration ${CODE}
echo
echo
echo "4、使用jinfo -flags <pid>输出全部参数"
jinfo -flags ${CODE}
echo
echo
echo "5、使用jinfo -sysprops <pid>输出系统参数"
jinfo -sysprops ${CODE}
echo
echo
echo "6、使用jinfo <pid>输出全部参数和系统参数"
jinfo ${CODE}
echo
kill -9 ${CODE}
echo "kill CLASS ${CLASS}"
