# Guideresto - hibernate - Bastien

> 62-51.2 Intégration de couches logicielles

**Uses java version 21**, other dependencies are in the pom.xml file.

## Setup

You need to change the [`persistence.xml`](src/main/resources/META-INF/persistence.xml) file to match your database
configuration. Adjust the following properties:

- `jakarta.persistence.jdbc.driver` -> your jdbc driver
- `jakarta.persistence.jdbc.url` -> your database url
- `jakarta.persistence.jdbc.user` -> your database user
- `jakarta.persistence.jdbc.password` -> your database password

Currently, the project is configured with my personal access, this should work without changes if you are on HE-ARC
network.

## Versions

The tag `v2.x.x` of the repository contains the code for the "Project 2" of the course "62-51.2 - Intégration de couches
logicielles".

If you want the code for the "Project 1",
please [checkout the tag `v1.x.x`](https://github.com/fuzoh/62-51.2-software-layer-integration/tree/v1.0.0) of the
repository. (Project 1 is with custom-made data mappers and cache)
