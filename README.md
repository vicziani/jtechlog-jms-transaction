Elosztott tranzakciókezelés Spring Boottal
==========================================

Ez a program a JTechLog (<http://jtechlog.hu>) blog _Elosztott tranzakciókezelés Spring Boottal_ posztjához készült példaprogram.
A letöltést követően Mavennel, az `mvn spring-boot:run` paranccsal buildelhető és
futtatható. Fejlesztőeszközben a `EmployeesApplication` osztály futtatásával indítható.

A következő http kérésre reagál:

```shell
http http://localhost:8080/api/employees name="John Doe"
```

Ez a következő JSON-t postolja el a megadott URL-re:

```json
{
    "name": "John Doe"
}
```

viczian.istvan a gmail-en
