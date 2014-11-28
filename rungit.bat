@echo off
set /p msg= message commit:
for /f "delims=" %%D in ('dir /a:d /b') do git add %%D
for %%f in (*.*) do git add %%f
git commit -am "%msg%"
echo done!
pause