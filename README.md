# NetologyTestApp
Тестовое задание для Нетологии
# Что нужно было сделать?
Написать приложение, которое загружает и отображает список курсов/тем курсов по url (https://raw.githubusercontent.com/netology-code/rn-task/master/netology.json)
# Что было сделано?
* Загрузка списка курсов с помощью Retrofit и RxJava 2
* Реализация Clean Architecture
* Реализация DI с помощью Dagger 2
* Реализация ui-паттерна MVVM, с помощью Android Jetpack
* Покрытие функционала unit-тестами
* Настройка CI с помощью Github Actions (Добавлены стилистическая проверка кода Detekt и прогон юнит тестов при пуше)
# Миграция на Jetpack Compose
В ветках :
1) feature/jetpack-compose-migration-1 - Переводим на композ отдельные фрагменты
2) feature/jetpack-compose-migration-2 - Переводим на композ флоу фрагменты + добавляем Jetpack Compose Navigation
3) feature/jetpack-compose-migration-3 - Переводим на композ главную активити и всё приложение


Последовательно осуществляется поэтапная миграция на Jetpack Compose.
# Сборка
[Ссылка на сборку](https://github.com/LukianBat/NetologyTestApp/blob/master/app-release.apk)
