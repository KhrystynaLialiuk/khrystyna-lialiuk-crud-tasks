call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo Calling runcrud.bat has errors - breaking work
goto fail

:openbrowser
start chrome --new-window "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Unable to start Chrome browser or open the link
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.