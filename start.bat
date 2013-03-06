@ECHO OFF
SET BINDIR=%~dp0
CD /D "%BINDIR%"
:start
"%ProgramFiles%\Java\jre6\bin\java.exe" -Xmx1024M -Xms1024M -jar craftbukkit.jar
goto start