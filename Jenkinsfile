pipeline {
    agent any
    
	parameters {
        choice(choices: '1-INT\n2-TEST\n3-PROD', description: 'CloudFoundry Deployment Space', name: 'cfspace')
    }
     
    stages {
        stage('Build') {
            agent { docker 'maven:3-alpine' } 
            steps {
            	    sh "echo ${params.cfspace}"
                sh '''
                java -version
                mvn -version;
                mvn clean compile
                '''
            }
        }
        
        stage('UnitTest') {
            agent { docker 'maven:3-alpine' } 
            steps {
                sh "mvn test"
            }
        }
        
        stage('Release') {
            agent { docker 'maven:3-alpine' } 
            steps {
                sh '''mvn install'''
            }
        }
        
        stage('Java') {
            agent { docker 'openjdk:8-jre' } 
            steps {
                echo 'Hello, JDK'
                sh 'java -version'
            }
        }
    }
}
