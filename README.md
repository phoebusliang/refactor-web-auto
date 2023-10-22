mvn clean test -Dcucumber.tags="@complete"

mvn clean verify -P complete

* Control the parallel threads from `<threadCount>2</threadCount>` in pom.xml