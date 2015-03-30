#!/bin/bash

if [ "$#" -ne 2 ]; then
    echo "usage: <version> <repositoryURL>"
    exit 1
fi

version=$1
repository=$2

# replace all development versions with release version
replacement="s/0.0-DEVELOPMENT/$version/g"
find . -name "pom.xml" -exec sed -i -e "$replacement" {} \;
find . -name "build.gradle" -exec sed -i -e "$replacement" {} \;

# commit tag of version name
#git add pom.xml
#git add flapi-build-plugin/pom.xml
#git add flapi-build-project/pom.xml
#git add flapi-build-test-consumer/pom.xml
#git add flapi-build-test-producer/pom.xml
#git add flapi-descriptor/pom.xml
#git add flapi-gradle-plugin/pom.xml
#git add flapi-parent/pom.xml
#git add flapi-plugin/pom.xml
#git add flapi-runtime/pom.xml

#git commit -m"releasing version $version"
#git tag $version


# build and release the project
cd flapi-parent
mvn -DaltDeploymentRepository=micro::default::"$2" clean install deploy
cd ..

# replace all release versions with development version
#replacement="s/$version/0.0-DEVELOPMENT/g"
#find . -name "pom.xml" -exec sed -i -e "$replacement" {} \;

# reset to the last working commit
#git reset --hard HEAD~1
