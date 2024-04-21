# RestApiOfEquipRegistry

## тестовое задание REST-приложение с Open API v3 документацией в виде страницы swagger.
`http://localhost:9090/actuator/swagger-ui/index.html#/`

## Предусловия: 
- На рабочей станции установлены: JavaJDK, Gradle, Docker.


#### Склонировать репозитооий на рабочую станцию и перейти в ветку develop. 
`Git checkout develop`
#### Если нет на машине Postgresql, можно запусить в контейнере. В корне лежит `docker-compose.yaml`,в нем данные по инициализации БД для успешного подключения программы.

### `docker compose up -d`

#### После поднятия БД нужно проинициализировать схему и залить тестовые данные Запустить инициализацию БД из командной строки или через IDE

`cat /scripts/init.sql | psql -h localhost -p5433 -U admin registry`

#### После успешного старта БД можно попробовать запустить программу
- разрешаем запуск файла `chmod ugo+x gradlew`

#### и старт `./gradlew bootRun`
#### (при необходимости можно указать путь до java.home `-Dorg.gradle.java.home=(path)`)

- доп сервисы можно посмотреть тут - `http://localhost:9090/actuator`

## Приятного полета!!!!
## С уважением, Григорьев Димитрий!



