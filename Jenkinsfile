pipeline {
    agent { docker 'maven:latest' }
	parameters {
        choice(choices: '1-INT\n2-TEST\n3-PROD', description: 'CloudFoundry Deployment Space', name: 'cfspace')
    }
    
    stages {
    		
        stage('Build') {
            steps {
            	    sh "echo ${params.cfspace}"
                sh '''
                java -version;
                mvn -version;
                mvn clean compile;
                '''
            }
        }
        
        stage('UnitTest') { 
            steps {
                sh "mvn test"
            }
        }
        
        stage('Release') {
            steps {
                sh "mvn install"
            }
        }
        
        stage('Deploy') {
            steps {
                sh "mvn install"
            }
        }
    }
    
    post {
        always {
            junit '**/*Test.xml'
        }
    }
    
}