# TMDB
Test Android Developer

1. Las capas de la aplicación (por ejemplo capa de persistencia, vistas, red, negocio, etc) y qué
clases pertenecen a cual.
Dentro de la capa de vista se encuentran cuatro clases, las cuales tienen que ver con la forma en que se presenta la información al usuario, en este caso son:
- MainActivity
- ListFragment
- DetailFragment
- MoviesListAdapter

Dentro de la capa de modelo se encuentran cuatro clase, las cuales tienen que ver con los objetos que se usan en la aplicación, estos son:
- Movie
- MovieDatabase
- MovieDao
- ResponseGeneral

Dentro de la capa de modelo de vista se aplica la lógica a los modelos y se conecta con la vista para mostrarse, con ViewModel persisten los datos a pesar de que hayan cambios en la vista, las clases que contiene son:
- BaseViewModel
- ListViewModel
- DetailViewModel

La capa de repositorio accede tanto a la información remota, como a la información almacenada localmente, aquí hay 3 clases:
- MoviesAPI
- MoviesApiService
- MoviesRepository

2. En qué consiste y cuál es el propósito de el principio de responsabilidad única?
Se busca que una clase tenga una sola responsabilidad de acuerdo a su capa evitar la rigidez y fragilidad del código, es decir, que un cambio en el código requiera muchos más cambios para poder repararlo.  

3. Qué características tiene, según su opinión, un código limpio?
Un código limpio debe ser autodescriptivo, seguir convenciones, por ejemplo, para nombrar clases y métodos, contar con un patrón de diseño y ser fácil de leer para un mantenimiento más rápido.

4. Describa la razón del patrón de diseño usado.
A mi consideración, MVVM como patrón brinda un código más limpio a diferencia de MVP, ya que este patrón reduce drásticamente el uso de interfaces para comunicar clases, por lo que se reduce el boilerplate, además de que los cambios que hay en la lógica se ven reflejados automáticamente en la vista gracias al patrón observer de ViewModel.
