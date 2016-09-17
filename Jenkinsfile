#!/usr/bin/env groovy

node ('master'){
    stage('Build and Test') {
        checkout scm
        env.PATH = "${tool 'Maven 3'}/bin:${env.PATH}"
        sh 'mvn clean package'
    }
}
