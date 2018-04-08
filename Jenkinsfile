pipeline {
    agent { docker 'maven:3-alpine' }
    
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
    }
    
    pushToCloudFoundry(
    target: 'api.local.pcfdev.io',
    organization: 'pcfdev-org',
    cloudSpace: 'pcfdev-space',
    credentialsId: 'pcfdev_user'
	)
    
    post {
        always {
            junit '**/*Test.xml'
        }
    }
    
}