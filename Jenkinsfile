pipeline {
    agent any
    tools { maven 'Maven 3.8'; jdk 'Java 17' }
    stages {
        stage('Checkout') { steps { checkout scm } }
        stage('Execute Fast CI/CD Mode') { steps { sh 'mvn clean test -Dbrowser=chrome -Dheadless=true -P fast' } }
    }
    post {
        always {
            archiveArtifacts artifacts: 'logs/*.log, reports/*.html', allowEmptyArchive: true
            publishHTML(target: [allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'reports', reportFiles: 'AutomationReport.html', reportName: 'Extent Report'])
        }
    }
}