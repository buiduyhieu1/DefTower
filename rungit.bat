for /f "delims=" %%D in ('dir /a:d /b') do git add %%D
for %%f in (*.*) do git add %%f
git commit -am "test auto run"
git remote add hsv git@github.com:buiduyhieu1/DefTower.git
git push -u hsv master
pause