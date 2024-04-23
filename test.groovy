@Library('shared_tools@ramzi/scm_skip')_

pipeline {
    agent { label 'master' }

    options {
        skipDefaultCheckout()
        timestamps()
    }

    stages {
        stage('print changes') {
            steps {
                gitCheckoutTest(branch: "main", repo: "git@github.com:rchennafi/test_jenkins_32.git")
            }
        }
    }

    post {
        cleanup {
            cleanWs deleteDirs: true
        }
    }
}
