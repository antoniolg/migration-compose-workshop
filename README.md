# Taller de Arquitectura y Jetpack Compose

El objetivo de este taller es montar una App Android con una arquitectura simple. Después, se demuestra cómo con una buena arquitectura podemos migrar a Compose sin modificar el código de la App.

La App es una lista de notas. Cada nota tiene un título y un cuerpo. La App permite crear, editar y borrar notas.

## Arquitectura

Para ello constará de estos componentes principales:

* UI: Se encarga de mostrar las notas e interactuar con el usuario
* ViewModel: Extrae la lógica de negocio de la UI y la expone a través de un modelo de datos
* Repository: Se encarga de obtener los datos de la fuente de datos (en este caso, una base de datos local)
* Database: Se encarga de persistir los datos en la base de datos local. En este caso, se usa Room.

## Migración a Compose

Una vez el código está desarrollado para una App con una arquitectura simple, se demuestra cómo migrar a Compose sin modificar el código de la App.

La única parte que vuelve a desarrollarse es la interfaz, pero la lógica de negocio se mantiene intacta.

## Licencia

Copyright 2022 Antonio Leiva

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.