#!/bin/bash
sbt_cmd="sbt ++$TRAVIS_SCALA_VERSION"

export publish_cmd="publishLocal"

## if [[ $TRAVIS_PULL_REQUEST == "false" && $TRAVIS_BRANCH == "master" && $(cat version.sbt) =~ "-SNAPSHOT" ]]; then
##   export publish_cmd="publish gitSnapshots publish"
##   # temporarily disable to stabilize travis
##   #if [[ $TRAVIS_SCALA_VERSION = "2.11.8" ]]; then
##   #  export publish_cmd="$publish_cmd ghpagesPushSite"
##   #fi
## fi

coverage="$sbt_cmd coverage test coverageReport && bash <(curl -s https://codecov.io/bash)"
#scala_js="$sbt_cmd coreJS/compile" # && $sbt_cmd testsJS/test"
#scala_jvm="$sbt_cmd validateJVM"

#run_cmd="$coverage && $scala_js && $scala_jvm $publish_cmd"
eval $coverage
