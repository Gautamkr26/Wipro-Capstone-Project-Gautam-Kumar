pipeline {
    agent any

    tools {
        jdk 'JDK25'
        maven 'Maven3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Gautamkr26/Wipro-Capstone-Project-Gautam-Kumar.git'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Run TestNG Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Generate Reports') {
            steps {
                echo 'Generating Extent Report...'
            }
        }
    }

    post {
        always {

            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/*.xml'

            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',
                reportFiles: 'AutomationReport.html',
                reportName: 'Extent Report'
            ])
        }
    }
}