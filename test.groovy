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
            when { changeset "*"} {
                not {
                    shouldTrigger(currentBuild)
                }
            }

            steps {
                echo "I QUIT!"
            }
        }
    }
}
