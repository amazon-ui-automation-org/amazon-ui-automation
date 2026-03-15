pipeline {

    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Smoke Tests') {
            steps {
                bat 'mvn clean test -DsuiteXmlFile=testng-smoke.xml'
            }
        }

        stage('Publish Test Results') {
            steps {
                testNG testResultsPattern: 'target/surefire-reports/testng-results.xml'
            }
        }

    }

    post {
        always {
            archiveArtifacts artifacts: 'target/surefire-reports/**', allowEmptyArchive: true
        }
    }
}