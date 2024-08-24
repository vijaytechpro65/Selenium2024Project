pipeline {
    agent any  // Run on any available agent

    // Parameters for environment selection and other configurations
    parameters {
        choice(name: 'Environment', choices: ['dev', 'qa', 'staging', 'prod'], description: 'Select the deployment environment')
        string(name: 'Branch', defaultValue: 'main', description: 'Git branch to build from')
        booleanParam(name: 'SkipTests', defaultValue: false, description: 'Skip unit tests')
    }

    // Environment variables
    environment {
        PROJECT_NAME = 'MyProject'  // Example of setting a global environment variable
        DEPLOY_DIR = "/var/www/${params.Environment}/"  // Dynamic directory based on environment parameter
    }

    // Post actions like sending notifications or cleaning up
    options {
        timestamps()  // Adds timestamps to the console output
        skipStagesAfterUnstable()  // Skip subsequent stages if the build becomes unstable
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the selected branch
                git branch: "${params.Branch}", url: 'https://github.com/username/repo.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Example build command (could be Maven, Gradle, npm, etc.)
                    echo 'Building the project...'
                    sh './build.sh'
                }
            }
        }

        stage('Test') {
            when {
                expression { return !params.SkipTests }  // Conditionally run tests based on parameter
            }
            steps {
                script {
                    echo 'Running unit tests...'
                    sh './run-tests.sh'
                }
            }
            post {
                always {
                    junit 'test-results/*.xml'  // Publish JUnit test results
                }
            }
        }

        stage('Deploy') {
            when {
                anyOf {
                    branch 'main'
                    branch 'release/*'
                }
                environment name: 'Environment', value: 'prod'
            }
            steps {
                script {
                    echo "Deploying to ${params.Environment} environment..."
                    sh "./deploy.sh --env ${params.Environment} --dir ${env.DEPLOY_DIR}"
                }
            }
        }

        stage('Post-Deployment Verification') {
            when {
                environment name: 'Environment', value: 'prod'
            }
            steps {
                script {
                    echo "Verifying deployment in ${params.Environment} environment..."
                    sh './verify-deployment.sh'
                }
            }
        }
    }

    // Post-build actions
    post {
        success {
            echo 'Build and deployment successful!'
            // Add steps like sending notifications here
        }
        failure {
            echo 'Build or deployment failed!'
            // Add steps like sending failure notifications here
        }
        always {
            cleanWs()  // Clean up the workspace after the build
        }
    }
}
