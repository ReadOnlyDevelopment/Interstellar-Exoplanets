pipeline {
  agent any
  environment {
    SECRET_FILE = credentials('SECRET_FILE')
  }
  stages {
    stage('Clean') {
      steps {
        echo 'Cleaning Project'
        sh 'chmod +x gradlew'
        sh './gradlew clean'
      }
    }
    stage('Build') {
      steps {
        sh './gradlew build --no-daemon'
      }
    }

    stage('Archive') {
      steps {
        archiveArtifacts(onlyIfSuccessful: true, artifacts: 'build/libs/*jar')
      }
    }

    stage('Notify') {
      when {
		branch 'dev-1.12.2'
      }
      steps {
        discordSend(webhookURL: 'env.webhookURL', successful: true, title: 'Interstellar-Exoplanets', thumbnail: 'https://i.imgur.com/cHW8JBO.png')
      }
    }
  }
}
