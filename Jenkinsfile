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
        	CF_CLI = credentials('ORGCFCLI')
    	    }
            steps {
		sh "echo $PWD; ls -ltr $PWD"
	        sh "echo 'cf login -a someAPI -u $CF_CLI_USR -p $CF_CLI_PSW -o ORG -s ${params.cfspace}; cf push manifest_${params.cfspace}.xml'"
            }
        }
	    
      stage('Smoke-Test') {
      parallel {
        stage('Smoke-A') {
          steps {
            sh 'curl -k http://localhost:80 | grep body'
          }
        }
        stage('Smoke-B') {
          steps {
            sh 'curl -k http://localhost:80 | grep body'
          }
        }
        stage('Smoke-C') {
          steps {
            sh 'curl -k http://localhost:80 | grep body'
          }
        }
       }
      }  
	    
    }
    
    post {
        always {
            junit '**/*Test.xml'
        }
    }
    
}
