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
                gitCheckout(branch: "main", repo: "git@github.com:rchennafi/test_jenkins_32.git")
		echo "TEST1"
            }
        }
    }

    post {
        cleanup {
            cleanWs deleteDirs: true
        }
    }
}
