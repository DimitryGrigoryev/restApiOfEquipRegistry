# RestApiOfEquipRegistry

## REST-приложение с Open API v3 документацией в виде страницы swagger.
`http://localhost:9090/actuator/swagger-ui/index.html#/`

## Предусловия: 
- На рабочей станции установлены: JavaJDK, Gradle, Postgresql.

#### Склонировать репозитооий на рабочую станцию
`Git clone git@github.com:DimitryGrigoryev/restApiOfEquipRegistry.git`
#### Запустить на машине Postgresql, настройки для подключения можно посмотреть [тут](src/main/resources/application.yml)

#### После успешного старта БД можно попробовать запустить программу
- разрешаем запуск файла `chmod ugo+x gradlew`

#### и старт `./gradlew bootRun`
#### (при необходимости можно указать путь до java.home `-Dorg.gradle.java.home=(path)`)

- доп сервисы можно посмотреть тут - `http://localhost:9090/actuator`

## Приятного полета!!!!
