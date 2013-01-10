@echo off

set programdir="C:\Programming"
set packagedir="%programdir%\Packages"
set repodir="%programdir%\Repositories"
set forgedir="%repodir%\MinecraftForge"
set fmldir="%repodir%\MinecraftForge\fml"
set mcpdir="%programdir%\mcp"
set euryscore="%repodir%\EurysCore-FML"
set mtsigns="%repodir%\MultiTexturedSigns"
cd %mcpdir%

if not exist %euryscore% GOTO :ECFAIL
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
xcopy "%euryscore%\SV-common\*.*" "%mcpdir%\src\minecraft" /S
xcopy "%mtsigns%\MTS-source\*.*" "%mcpdir%\src\minecraft" /S
pause
call %mcpdir%\recompile.bat
call %mcpdir%\reobfuscate.bat
echo Recompile and Reobf Completed Successfully
pause

:REPACKAGE
if not exist "%mcpdir%\reobf" GOTO :ECFAIL
if exist "%packagedir%\MultiTexturedSigns" (
del "%packagedir%\MultiTexturedSigns\*.*" /S /Q
rmdir "%packagedir%\MultiTexturedSigns" /S /Q
)
mkdir "%packagedir%\MultiTexturedSigns\eurymachus\mts"
xcopy "%mcpdir%\reobf\minecraft\eurymachus\mts\*.*" "%packagedir%\MultiTexturedSigns\eurymachus\mts\" /S
xcopy "%mtsigns%\MTS-resources\*.*" "%packagedir%\MultiTexturedSigns\" /S
echo "Multi-Textured Signs Packaged Successfully
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
echo Could not compile Multi-Textured Signs
pause
GOTO :EOF

:ECCOMPLETE
echo Multi-Textured Signs completed compile successfully
pause
GOTO :EOF

:EOF