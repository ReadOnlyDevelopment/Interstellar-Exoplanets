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
        discordSend(webhookURL: 'https://discordapp.com/api/webhooks/740671930545471600/oDusdaLobUppsrdzN5m6joSjtrV8n86bw4kFdvf-4tA5X_3q70Cusz_Bz23sFiPqDzCo', successful: true, title: 'Interstellar-Exoplanets', thumbnail: 'https://i.imgur.com/cHW8JBO.png')
      }
    }

  }
}