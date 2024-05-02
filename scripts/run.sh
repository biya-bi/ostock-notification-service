#!/bin/bash

if [[ ! -f ${JWT_ISSUER_URI_FILE} ]]; then
    echo "The file ${JWT_ISSUER_URI_FILE} does not exist"
    exit 1
fi

if [[ ! -f ${VAPID_PRIVATE_KEY_FILE} ]]; then
    echo "The file ${VAPID_PRIVATE_KEY_FILE} does not exist"
    exit 1
fi

if [[ ! -f ${VAPID_PUBLIC_KEY_FILE} ]]; then
    echo "The file ${VAPID_PUBLIC_KEY_FILE} does not exist"
    exit 1
fi

if [[ ! -f ${VAPID_SUBJECT_FILE} ]]; then
    echo "The file ${VAPID_SUBJECT_FILE} does not exist"
    exit 1
fi

export JWT_ISSUER_URI=`cat ${JWT_ISSUER_URI_FILE}`
export VAPID_PRIVATE_KEY=`cat ${VAPID_PRIVATE_KEY_FILE}`
export VAPID_PUBLIC_KEY=`cat ${VAPID_PUBLIC_KEY_FILE}`
export VAPID_SUBJECT=`cat ${VAPID_SUBJECT_FILE}`

java org.springframework.boot.loader.launch.JarLauncher