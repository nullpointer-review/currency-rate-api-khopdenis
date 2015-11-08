# rest-example
Решение тестового задания. REST сервис на Spring, интеграция с http://www.cbr.ru/

Требования к среде сборки:
- JDK 1.8
- Maven 3+

Для сборки и тестирования решения тестового задания необходимо проделать следующие шаги:

1. Выкачать CurrencyRestApi

2. Открыть директорию CurrencyRestApi.

3. Запустить команду
	mvn clean package

4. Запустить команду 
	java -jar target\rate-cbr-example-0.1.0.jar --server.port=8181

5. Отправить запросы через браузер, SoapUI или любое другое средство
	GET http://localhost:8181/currency/api/rate/USD
	GET http://localhost:8181/currency/api/rate/USD/2015-11-09
