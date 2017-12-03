# ElevatorApp
Запускал через mvn clean install exec:java

Описание меню:
1. Open config - открывает меню настройки начальной конфигурации
2. Show config - выводит текущую начальную конфигурацию
3. Set default config - добавляется стандартная начальная конфигурация (5 этажей, 12 слотов, 5 пассажиров, 5 лифтов)
4. Run simulation - запуск
5. Exit - выход

Команды в меню настройки начальной конфигурации:
1. add passenger -pos <startPosition> -level <startLevel> -tPos <targetPosition> -tLevel <targetLevel>
2. add elevator -pos <position> -level <startLevel> -range <minLevel>,<maxLevel>
3. set floor count <count>
4. set slot count <count>
5. back - возврат в начальное меню




