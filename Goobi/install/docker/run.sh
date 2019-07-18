#!/bin/bash

set -e

[ -z "$CONFIGSOURCE" ] && CONFIGSOURCE="default"
[ -z "$WORKING_STORAGE" ] && WORKING_STORAGE=""

set -u

echo "Setting database configuration from environment..."
envsubst '\$DB_SERVER \$DB_PORT \$DB_NAME \$DB_USER \$DB_PASSWORD' </usr/local/tomcat/conf/goobi.xml.template > /usr/local/tomcat/conf/Catalina/localhost/goobi.xml

if [ "${WORKING_STORAGE}_" != "_" ]
then
  CATALINA_TMPDIR="${WORKING_STORAGE}/goobi/jvmtemp"
  mkdir -p "${CATALINA_TMPDIR}"
  echo >> /usr/local/tomcat/bin/setenv.sh
  echo "CATALINA_TMPDIR=${CATALINA_TMPDIR}" >> /usr/local/tomcat/bin/setenv.sh
fi

case $CONFIGSOURCE in
  s3)
    if [ -z "$AWS_S3_BUCKET" ]
    then
      echo "AWS_S3_BUCKET is required"
      exit 1
    fi
    echo "Pulling configuration from s3 bucket"
    aws s3 cp s3://$AWS_S3_BUCKET/goobi/config/ /opt/digiverso/goobi/config/ --recursive
    aws s3 cp s3://$AWS_S3_BUCKET/goobi/rulesets/ /opt/digiverso/goobi/rulesets/ --recursive
    aws s3 cp s3://$AWS_S3_BUCKET/goobi/xslt/ /opt/digiverso/goobi/xslt/ --recursive
    ;;
  *)
    echo "Keeping configuration"
    ;;
esac

#cat /usr/local/tomcat/conf/Catalina/localhost/goobi.xml

echo "Starting application server..."
catalina.sh run
