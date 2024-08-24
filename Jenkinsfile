pipeline {
    agent any

    tools {
        maven 'maven'  // Ensure Maven is configured in Jenkins Global Tools
    }

    stages {
        stage('Checkout PageObjectModel') {
            steps {
                // Clone the PageObjectModel repository
                git 'https://github.com/naveenanimation20/PageObjectModel'
            }
        }
        
        stage('Build') {
            steps {
                // Run Maven build
                sh "mvn clean install"
            }
        }
        
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    // Clone the Selenium2024Project repository and run tests
                    git 'https://github.com/vijaytechpro65/Selenium2024Project'
                    sh "mvn clean install"
                }
            }
        }
                
        stage('Publish Allure Reports') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                    ])
                }
            }
        }
        
        stage('Publish Extent Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: false,
                    reportDir: 'build',
                    reportFiles: 'TestExecutionReport.html',
                    reportName: 'HTML Extent Report',
                    reportTitles: ''
                ])
            }
        }
    }
}
