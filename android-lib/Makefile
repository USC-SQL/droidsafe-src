
-include Makefile.user

export CLASSPATH := .:$(DROIDSAFE_SRC_HOME)/android-lib/src:$(ANDROID_SDK_HOME)/android.jar

build: 
	echo CLASSPATH = $$CLASSPATH
	cd src && javac -target 1.7 -source 1.7 edu/mit/csail/droidsafe/*.java
	# cd src && javac -target 1.6 -source 1.6 edu/mit/csail/droidsafe/*.java
	cd src && jar cvf ../droidcalls.jar  edu/mit/csail/droidsafe/*.class

dump-vars:
	echo CLASSPATH = $$CLASSPATH

