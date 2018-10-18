pipeline {
    agent any
    tools { 
        maven 'Maven-3.5.4' 
        jdk 'OpenJDK-8' 
    }
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
                mvn clean compile test install;
                '''
            }
        }
        
        stage('UnitTest') {
            steps {
		sh '''
		java -version;
		mvn -version;
		mvn clean compile test install;
                ''' 
            }
        }
        
        stage('CF push') {
	    environment {
        	CF_CLI = credentials('CFCLITEST')
    	    }
            steps {
                sh "echo $CF_CLI"
            }
        }        
    }
    
    post {
        always {
            junit '**/*Test.xml'
        }
    }
    
}
