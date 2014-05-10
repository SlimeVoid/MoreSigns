@echo off

set programdir=%CD%\..\..
set packagedir=%programdir%\Packages
set repodir=%programdir%\Git
set forgedir=%programdir%\Forge
set mcpdir=%forgedir%\mcp
set slimevoidlib=%repodir%\SlimevoidLibrary\src\main\java
set moresigns=%repodir%\MoreSigns\src\main
cd %mcpdir%

if not exist %slimevoidlib% GOTO :ECFAIL
GOTO :EC

:EC
if exist %mcpdir%\src GOTO :COPYSRC
GOTO :ECFAIL

:COPYSRC
if not exist "%mcpdir%\src-work" GOTO :CREATESRC
GOTO :ECFAIL

:CREATESRC
mkdir "%mcpdir%\src-work"
xcopy "%mcpdir%\src\*.*" "%mcpdir%\src-work\" /S
if exist "%mcpdir%\src-work" GOTO :COPYEC
GOTO :ECFAIL

:COPYEC
xcopy "%slimevoidlib%\*.*" "%mcpdir%\src\minecraft" /S
xcopy "%moresigns%\java\*.*" "%mcpdir%\src\minecraft" /S
pause
call %mcpdir%\recompile.bat
call %mcpdir%\reobfuscate.bat
echo Recompile and Reobf Completed Successfully
pause

:REPACKAGE
if not exist "%mcpdir%\reobf" GOTO :ECFAIL
if exist "%packagedir%\MoreSigns" (
del "%packagedir%\MoreSigns\*.*" /S /Q
rmdir "%packagedir%\MoreSigns" /S /Q
)
mkdir "%packagedir%\MoreSigns\com\slimevoid\moresigns"
xcopy "%mcpdir%\reobf\minecraft\com\slimevoid\moresigns\*.*" "%packagedir%\MoreSigns\com\slimevoid\moresigns\" /S
xcopy "%moresigns%\resources\*.*" "%packagedir%\MoreSigns\" /S
echo "More Signs Packaged Successfully
pause
ren "%mcpdir%\src" src-old
echo Recompiled Source folder renamed
pause
ren "%mcpdir%\src-work" src
echo Original Source folder restored
pause
del "%mcpdir%\src-old" /S /Q
del "%mcpdir%\reobf" /S /Q
if exist "%mcpdir%\src-old" rmdir "%mcpdir%\src-old" /S /Q
if exist "%mcpdir%\reobf" rmdir "%mcpdir%\reobf" /S /Q
echo Folder structure reset
GOTO :ECCOMPLETE

:ECFAIL
echo Could not compile More Signs
pause
GOTO :EOF

:ECCOMPLETE
echo More Signs completed compile successfully
pause
GOTO :EOF

:EOF