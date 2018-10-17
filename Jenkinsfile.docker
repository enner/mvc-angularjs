pipeline {
    agent 
	{ 
		docker { 
			image 'maven:3-jdk-8'
			args '-v maven-data:/root/.m2'
		}
	}
    parameters 
	{
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
    }
    
    post {
        always {
            junit '**/*Test.xml'
        }
    }
    
}
