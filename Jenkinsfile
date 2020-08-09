pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        sh 'echo "Running build #${env.BUILD_ID} on ${env.JENKINS_URL}"'
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
      }
    }

  }
}