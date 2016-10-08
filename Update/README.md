[material-dialogs](https://github.com/afollestad/material-dialogs)

## 获取apk证书MD5值的方法（JDK的keytool命令）
APK承袭JAVA，证书RSA文件生成方式与JAVA同源，获取APK证书MD5思路：

- 得到APK的RSA证书文件
- 使用Java工具的keyytool命令获取

使用该工具准备条件：
安装JDK（Java Development Kit）环境即可
为确保运行，可添加到环境变量或者在cmd窗口中中cd到对应路径执行。keytool命令对应文件keytool.exe的目录一般为C:\Program Files\Java\jre7\bin\keytool.exe

具体步骤
1、解压得到RSA文件
APK以zip文件方式打开，在\META-INF\目录中存在一个.RSA后缀的文件，一般名为CERT.RSA

2、使用keytool命令获取证书信息（包括MD5）
运行如下keytool命令即可：

keytool -printcert -file CERT.RSA

