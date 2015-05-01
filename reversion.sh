#!/bin/bash

if [ "$#" -ne 1 ]; then
    echo "usage: <version>"
    exit 1
fi

version=$1

replacement="s/0.0-DEVELOPMENT/$version/g"
find . -name "pom.xml" -exec sed -i -e "$replacement" {} \;
find . -name "build.gradle" -exec sed -i -e "$replacement" {} \;