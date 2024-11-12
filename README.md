# Graphs
App on Java for charting<br>
# Compiling
Firstly, you should install last version of JDK on official site.<br>
For compiling, you need to follow this steps:<br>
```console
javac -classpath .;stdlib.jar Main.java
jar -cf Main.jar Main.class
```
For compile jar to exe, you can use Jar2Exe Wizard, also you need to mark additional jar file(stdlib.jar)<br>
# Usage
Args to launch: x scale, y scale, w of canvas, h of canvas. Then type < "file with coordinates where every row looks like: 1 100 50 *(1 column - y value, 2 and next column - your graphs)"<br>

COLORS:<br>
1 graph - green<br>
2 graph - blue<br>
3 graph - cyan<br>
4 graph - purple<br>
5 graph - yellow<br>
6 graph - light pink<br>
7 graph - red<br><br>
**Example of usage:**<br>
```console
gr 10000 10000 800 800 < values.txt
```
