Важно получить сертифекат разработчика. В папке debug лежит debug.keystore. В build.gradle.android есть конфиги, в которых указан пароль от debug.keystore. Там же надо указать путь к этому файлу. Дальше можно регистрировать приложение в консоле гугла под AndroidApp. keytool.exe лежит в Java\..., запускается как .\keytool.exe в Windows Power shell.

https://habr.com/ru/post/229039/

Регистрировать можно здесь кнопкой configure project:

https://developers.google.com/identity/sign-in/android/start-integrating