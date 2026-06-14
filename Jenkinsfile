pipeline {
    agent any

    tools {
        // Ensure you have configured 'Maven3' in Jenkins Global Tool Configuration
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                // Replace with your actual repository URL
                git branch: 'main', url: 'https://github.com/your-username/GUIAutomationFramework.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Executes all tests defined in your pom.xml
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            // Publishes the Extent Report to the Jenkins Dashboard
            publishHTML([
                allowMissing: false, 
                alwaysLinkToLastBuild: true, 
                keepAll: true, 
                reportDir: 'reports', 
                reportFiles: 'AutomationReport.html', 
                reportName: 'Extent Report'
            ])
        }
    }
}