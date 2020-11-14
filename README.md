### Примечания
- Перед сборкой приложения, в файле src/main/resources/application.yml переменные kafka.bootstrap-servers  
и cassandra.connection-host меняем на свои.  
- Скрипт создания таблицы cassandra:  
CREATE KEYSPACE linux_logs WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor' : 1};  
USE linux_logs;  
CREATE TABLE logs (id int PRIMARY KEY, datetime timestamp, hostname text, process text, message text, priority int);  
- Сборка приложения: mvn clean install  
- Запуск приложения: java -jar target/sbloghandler-0.0.1-SNAPSHOT.jar
- Отправка логов: POST -x http://localhost:8082/app/send?filePath=<путь до файла с логами>  
- Получение статистики: GET -x http://localhost:8082/app/compute  