#!/bin/env groovy
library 'pipeline-library'
pipeline {
    agent any
    environment {
        APP_NAME = "aalsabag"
        PROJECT_KEY = "AALS"
        DEMO_APP_PATH = "accelerate-aqe"
        IMAGE = "${APP_NAME}-demo"
        DEV_IP = "dev.${APP_NAME}.liatr.io"
        SONAR_URL = 'http://sonarqube.liatr.io'
        TAG = ''
        JIRA_ISSUE = ''
        JIRA_URL = 'http://jira.liatr.io'
        ARTIFACTORY_URL = 'https://artifactory.liatr.io'
        BITBUCKET_URL = "http://bitbucket.liatr.io/projects/${PROJECT_KEY}/repos/accelerate-aqe"
        DOCKER_REPO = "docker.artifactory.liatr.io"
        AWS_ACCESS_KEY_ID = credentials('AWSaccess')
        AWS_SECRET_ACCESS_KEY = credentials('AWSsecret')
        SNYK_TOKEN = credentials('snyk')
        AWS_DEFAULT_REGION = 'us-west-2'
        GROUP_ID = ''
        ARTIFACT_ID = ''
        VERSION = ''
        STAGE = ''
        SLACK_ROOM = "${APP_NAME}"
    }
    stages {
        stage('Maven: Build and push artifact to Artifactory') {
            agent {
                docker {
                    image 'maven:3.5.0'
                    reuseNode true
                }
            }
            steps {
                script {
                    STAGE = env.STAGE_NAME
                    def gitUrl = env.GIT_URL ? env.GIT_URL : env.GIT_URL_1
                    withCredentials([usernamePassword(credentialsId: 'Artifactory', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh "mvn clean install -Dtest=TestCaseFailedTest"
                    }
                    
                    jacoco()
                    
                }
            }
        }
        stage('Maven: Analyze code with Sonar') {
            agent {
                docker {
                    image 'maven:3.5.0'
                    reuseNode true
                }
            }
            steps {
                script {
                    STAGE = env.STAGE_NAME
                    withCredentials([string(credentialsId: 'sonarqube', variable: 'sonarqubeToken')]) {
                        sh "mvn sonar:sonar -Dsonar.host.url=http://sonarqube.liatr.io -Dsonar.login=${sonarqubeToken}"
                    }
                    pom = readMavenPom file: 'pom.xml'
                    
                }
            }
        }
        
    }
    post {
        failure {
            slackSend channel: env.SLACK_ROOM, color: 'danger', message: "Pipeline failed at stage: <${RUN_DISPLAY_URL}|${STAGE}>"
        }
    }
}