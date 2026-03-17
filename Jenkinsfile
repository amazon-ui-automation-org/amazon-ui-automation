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

        stage('Run Tests') {
            steps {
                script {

                    // Pull Request Build
                    if (env.CHANGE_ID) {

                        echo "PR detected → Running SMOKE suite"
                        bat "mvn clean test -DsuiteXmlFile=suites/testng-smoke.xml"

                    }

                    // Main branch build
                    else if (env.BRANCH_NAME == "main") {

                        echo "Main branch → Running FULL REGRESSION"
                        bat "mvn clean test -DsuiteXmlFile=suites/testng-regression.xml"

                    }

                    // Feature branch build
                    else if (env.BRANCH_NAME.startsWith("feature/")) {

                        def feature = env.BRANCH_NAME.split("/")[1]

                        echo "Feature branch detected: ${feature}"
                        echo "Running FEATURE suite"

                        bat "mvn clean test -DsuiteXmlFile=suites/testng-${feature}.xml"

                    }

                    // Other branches
                    else {

                        echo "Other branch → Running SMOKE suite"
                        bat "mvn clean test -DsuiteXmlFile=suites/testng-smoke.xml"

                    }

                }
            }
        }

        stage('Publish Test Results') {
            steps {
                testNG reportFilenamePattern: 'target/surefire-reports/testng-results.xml'
            }
        }

    }

    post {
        always {
            archiveArtifacts artifacts: 'target/surefire-reports/**', allowEmptyArchive: true
        }
    }

}