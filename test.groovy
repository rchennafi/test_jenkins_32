pipeline {
    agent { label 'master' }

    options {
        skipDefaultCheckout()
        timestamps()
    }

    stages {
        stage('print changes') {
            steps {
                echo "changes:"
                echo currentBuild.changeSets
            }
        }

        stage('checkquit') {
            when {
                not {
                    changeset "*"
                }
            }

            steps {
                echo "I QUIT!"
                script {
                    currentBuild.getRawBuild().getExecutor().interrupt(Result.SUCCESS)
                    sleep(1)
                }
            }
        }
    }
}
