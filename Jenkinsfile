pipeline {
  agent any
  stages {
    stage('Source') {
      steps {
        git(url: 'https://github.com/manson112/Springboot_AWS_Study', branch: 'master')
      }
    }

    stage('Build') {
      steps {
        tool 'gradle'
      }
    }

    stage('Deploy') {
      steps {
        sh 'echo "DEPLOY"'
      }
    }

  }
}