pipeline {
    agent none
    parameters 
	{
		choice(choices: '1-INT\n2-TEST\n3-PROD', description: 'CloudFoundry Deployment Space', name: 'cfspace')
	}
    
    stages {
    		
        stage('Build') {
		agent { docker { 
			image 'maven:3-jdk-8' 
			args '-v maven-data:/root/.m2'
		} }
            steps {
            	sh "echo ${params.cfspace}"
                sh '''
                java -version;
                mvn -version;
                mvn clean compile test instsall;
                '''
            }
        }
        
        stage('UnitTest') {
            steps {
                sh "mvn -version"
            }
        }
        
        stage('Release') {
            steps {
                sh "cf version"
            }
        }        
    }
    
    post {
        always {
            junit '**/*Test.xml'
        }
    }
    
}
