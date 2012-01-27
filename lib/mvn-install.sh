mvn install:install-file -DgroupId=com.gsfn \
 -DartifactId=fastpass -Dversion=1.0.0 -Dpackaging=jar \
 -Dfile=./fastpass.jar

mvn install:install-file -DgroupId=com.gsfn \
 -DartifactId=oauth-core -Dversion=1.0.0 -Dpackaging=jar \
 -Dfile=./oauth-core.jar