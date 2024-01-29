cls
set server_directory="C:\Program Files\Apache Software Foundation\Tomcat 10.1"

xcopy "bin\production\Poketra" "build\WEB-INF\classes" /Y /E /I
@REM manao war
jar -cvf Poketra.war -C build .
@REM deployer
copy Poketra.war %server_directory%\webapps