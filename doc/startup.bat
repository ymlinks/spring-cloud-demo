@echo on

setlocal
consul agent -dev
goto end

:end
