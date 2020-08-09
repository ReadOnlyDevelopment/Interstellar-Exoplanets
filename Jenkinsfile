pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git(url: 'https://github.com/ReadOnly-Mods/Interstellar-Exoplanets', changelog: true)
      }
    }

    stage('Build') {
      steps {
        sh 'chmod +x gradlew'
        sh './gradlew clean build --no-daemon'
      }
    }

    stage('Archive') {
      steps {
        archiveArtifacts(onlyIfSuccessful: true, artifacts: 'build/libs/*jar')
        discordSend(webhookURL: 'env.webhookURL', successful: true, title: 'Interstellar-Exoplanets', thumbnail: 'https://i.imgur.com/cHW8JBO.png')
      }
    }

  }
}