pipeline {
    environment{
    registry = "skillassure/galaxemovie-theater"
    registryCredential = "Docker-Hub-Auth"
    dockerImage = ''
  }
  agent any



    stages {
        
        stage('build the project') {
      steps {
        echo 'building the project'
        sh 'mvn clean install'
       
      }
        }
    
     stage('Sonarqube'){
       steps{
         echo "Sonarqube codequality"
         sh '''
        mvn clean verify sonar:sonar \
          -Dsonar.projectKey=Galaxe_Movie_Theaters \
          -Dsonar.host.url=http://20.205.141.142:9000 \
          -Dsonar.login=sqp_c850f5ae88a80601988626c3ffd6a77dfbb10e53
         '''
       }
    }
    
     stage('Build docker image'){
      steps{
        echo "Building docker image"
        script{
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }



   stage('Push docker image'){
      steps{
        echo "Pushing docker image"
        script{
           docker.withRegistry('',registryCredential) {
            dockerImage.push()
            dockerImage.push('latest')
          }
        }
      }      
    }
    stage('Deploy to Dev'){   
      steps{
        echo "Deploying to dev environment"
        sh 'docker rm -f galaxemovie-theater || true'
        sh 'docker run -d --name=galaxemovie-theater -p 80:8094 -e VIRTUAL_HOST=theater.learn.skillassure.com -e  LETSENCRYPT_HOST=theater.learn.skillassure.com skillassure/galaxemovie-theater'
        //sh 'npm start'
      }
    }  
  }
}
