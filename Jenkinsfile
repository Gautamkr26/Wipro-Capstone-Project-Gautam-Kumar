pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Gautamkr26/Wipro-Capstone-Project-Gautam-Kumar.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Execute Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Publishing Reports...'
            }
        }
    }

    post {
        always {

            junit 'target/surefire-reports/*.xml'

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